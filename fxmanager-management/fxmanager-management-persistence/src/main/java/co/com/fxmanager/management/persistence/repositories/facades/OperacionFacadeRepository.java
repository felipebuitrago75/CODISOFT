package co.com.fxmanager.management.persistence.repositories.facades;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.fxmanager.management.domain.entities.DenominacionCantidad;
import co.com.fxmanager.management.domain.entities.Operacion;
import co.com.fxmanager.management.domain.entities.OperacionFiltro;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.domain.services.exceptions.BusinessException;
import co.com.fxmanager.management.persistence.entities.jpa.DenominacionCantidadEntity;
import co.com.fxmanager.management.persistence.entities.jpa.OperacionEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SaldoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TurnoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.converters.OperacionConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.OperacionEntityConverter;
import co.com.fxmanager.management.persistence.repositories.OperacionRepository;
import co.com.fxmanager.management.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.DenominacionCantidadSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.OperacionSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.SaldoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Service
public class OperacionFacadeRepository implements OperacionRepository {

	public static final String TIPO_COMPRA = "COMPRA";
	public static final String TIPO_CANCELADA = "CANCELADA";

	private OperacionSpringRepository operacionSpringRepository;

	private OperacionEntityConverter operacionEntityConverter;

	private OperacionConverter operacionConverter;

	private TurnoSpringRepository turnoSpringRepository;

	private SaldoSpringRepository saldoSpringRepository;

	private DenominacionCantidadSpringRepository denominacionCantidadSpringRepository;

	@Autowired
	public OperacionFacadeRepository(OperacionSpringRepository operacionSpringRepository,
			OperacionConverter operacionConverter, OperacionEntityConverter operacionEntityConverter,
			TurnoSpringRepository turnoSpringRepository, SaldoSpringRepository saldoSpringRepository,
			DenominacionCantidadSpringRepository denominacionCantidadSpringRepository) {
		super();
		this.operacionSpringRepository = operacionSpringRepository;
		this.operacionConverter = operacionConverter;
		this.operacionEntityConverter = operacionEntityConverter;
		this.turnoSpringRepository = turnoSpringRepository;
		this.saldoSpringRepository = saldoSpringRepository;
		this.denominacionCantidadSpringRepository = denominacionCantidadSpringRepository;
	}

	@Transactional
	@Override
	public Operacion create(Operacion operacion) {
		Operacion newOperacion;
		try {
			if (operacion.getTurno() == null) {
				throw new BusinessException("No tiene un turno iniciado");
			}
			operacion.setFechaOperacion(LocalDateTime.now(ZoneId.of("America/Bogota")));
			OperacionEntity operacionEntity = getOperacionEntityConverter().convert(operacion);
			TurnoEntity turno = getTurnoSpringRepository().findById(operacion.getTurno().getId())
					.orElseThrow(NoFoundDataException::new);
			List<SaldoEntity> listaSaldosCaja = getSaldoSpringRepository().saldosPorCaja(turno.getCaja().getId());

			/**
			 * se opera sobre la moneda de operacion
			 */
			listaSaldosCaja.forEach(saldo -> {
				if (saldo.getFx().getId().equals(operacionEntity.getFxOper().getId())) {

					if (operacionEntity.getTipo().equals(TIPO_COMPRA)) {
						saldo.setNominal(saldo.getNominal().add(operacionEntity.getNominal()));
						List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
								.obtenerDenominacionCantidadPorSaldo(saldo.getId());

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {

							for (DenominacionCantidad denominacionRecibida : operacion
									.getDenominacionesYCantidadesRecibidas()) {
								if (denominacionRecibida.getValor()
										.equals(denominacionCantidad.getDenominacion().getValor())) {
									denominacionCantidad.setCantidad(
											denominacionCantidad.getCantidad() + denominacionRecibida.getCantidad());
								}
							}

						});
					} else if (saldo.getNominal().compareTo(operacionEntity.getNominal()) >= 0) {
						saldo.setNominal(saldo.getNominal().subtract(operacionEntity.getNominal()));

						List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
								.obtenerDenominacionCantidadPorSaldo(saldo.getId());

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {

							for (DenominacionCantidad denominacionEntregadas : operacion
									.getDenominacionesYCantidadesEntregadas()) {
								if (denominacionEntregadas.getValor()
										.equals(denominacionCantidad.getDenominacion().getValor())) {
									denominacionCantidad.setCantidad(
											denominacionCantidad.getCantidad() - denominacionEntregadas.getCantidad());
								}
							}

						});

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {
							if (denominacionCantidad.getCantidad() < 0) {
								throw new BusinessException("No tiene suficiente denominaciones de "
										+ denominacionCantidad.getDenominacion().getValor());
							}
						});

					} else {
						throw new BusinessException("No tiene suficiente saldo de " + saldo.getFx().getCodigo());
					}
				}
			});

			/**
			 * se opera sobre la moneda base COP
			 */
			listaSaldosCaja.forEach(saldo -> {
				if (saldo.getFx().getId().equals(operacionEntity.getFxBase().getId())) {
					if (operacionEntity.getTipo().equals(TIPO_COMPRA)) {

						// se verifica que se cuente con los COP suficientes
						if (operacionEntity.getNominal().multiply(operacionEntity.getValorFxOperacion())
								.compareTo(saldo.getNominal()) >= 0) {
							throw new BusinessException("No tiene suficiente saldo de " + saldo.getFx().getCodigo());
						}
						// se resta la cantidad en pesos del total de la compra
						saldo.setNominal(saldo.getNominal().subtract(
								operacionEntity.getNominal().multiply(operacionEntity.getValorFxOperacion())));

						List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
								.obtenerDenominacionCantidadPorSaldo(saldo.getId());

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {

							for (DenominacionCantidad denominacionEntregadas : operacion
									.getDenominacionesYCantidadesEntregadas()) {
								if (denominacionEntregadas.getValor()
										.equals(denominacionCantidad.getDenominacion().getValor())) {
									denominacionCantidad.setCantidad(
											denominacionCantidad.getCantidad() - denominacionEntregadas.getCantidad());
								}
							}

						});

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {
							if (denominacionCantidad.getCantidad() < 0) {
								throw new BusinessException("No tiene suficiente denominaciones de "
										+ denominacionCantidad.getDenominacion().getValor());
							}
						});
//TODO: analizar
					} else {
						saldo.setNominal(saldo.getNominal()
								.add(operacionEntity.getNominal().multiply(operacionEntity.getValorFxOperacion())));

						List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
								.obtenerDenominacionCantidadPorSaldo(saldo.getId());

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {

							for (DenominacionCantidad denominacionRecibida : operacion
									.getDenominacionesYCantidadesRecibidas()) {
								if (denominacionRecibida.getValor()
										.equals(denominacionCantidad.getDenominacion().getValor())) {
									denominacionCantidad.setCantidad(
											denominacionCantidad.getCantidad() + denominacionRecibida.getCantidad());
								}
							}

						});

					}
				}
			});

			getSaldoSpringRepository().saveAll(listaSaldosCaja);

			OperacionEntity newOperacionEntity = getOperacionSpringRepository().save(operacionEntity);
			newOperacion = getOperacionConverter().convert(newOperacionEntity);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
		return newOperacion;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Operacion> getList(int first, int max) {
		List<Operacion> operaciones;
		try {
			Page<OperacionEntity> page = getOperacionSpringRepository().findAll(PageRequest.of(first / max, max));
			operaciones = page.stream().map(operacionEntity -> getOperacionConverter().convert(operacionEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return operaciones;
	}

	@Transactional
	@Override
	public Operacion update(Long id, Operacion operacion) {
		Operacion newOperacion;
		try {
			OperacionEntity operacionEntity = getOperacionEntityConverter().convert(operacion, () -> {
				Optional<OperacionEntity> operacionEntityOptioanal = getOperacionSpringRepository().findById(id);
				return operacionEntityOptioanal.orElseThrow(NoFoundDataException::new);
			});
			OperacionEntity newOperacionEntity = getOperacionSpringRepository().save(operacionEntity);
			newOperacion = getOperacionConverter().convert(newOperacionEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newOperacion;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		try {
			Optional<OperacionEntity> operacionEntityOptioanal = getOperacionSpringRepository().findById(id);
			operacionEntityOptioanal.ifPresent(getOperacionSpringRepository()::delete);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Operacion> getOperacion(Long id) {
		Optional<Operacion> operacion;
		try {
			Optional<OperacionEntity> operacionEntityOptioanal = getOperacionSpringRepository().findById(id);
			if(operacionEntityOptioanal.isPresent()) {
				operacionEntityOptioanal.get().getTurnoEdicion();
				operacionEntityOptioanal.get().getTurno();
			}
			operacion = operacionEntityOptioanal.map(fxEntity -> getOperacionConverter().convert(fxEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return operacion;
	}

	@Override
	public Operacion cancelarOperacion(Long id, Turno turno) {
		OperacionEntity operacionCancelada;
		try {

			Optional<OperacionEntity> operacionEntityOptioanal = getOperacionSpringRepository().findById(id);
			operacionEntityOptioanal.orElseThrow(NoFoundDataException::new);

			operacionCancelada = operacionEntityOptioanal.get();
			operacionCancelada.setEstado(TIPO_CANCELADA);

			TurnoEntity turnoEntity = getTurnoSpringRepository().findById(turno.getId())
					.orElseThrow(NoFoundDataException::new);
			List<SaldoEntity> listaSaldosCaja = getSaldoSpringRepository().saldosPorCaja(turnoEntity.getCaja().getId());

			if (!turnoEntity.getId().equals(operacionCancelada.getTurno().getId())) {
				throw new BusinessException("El turno actual es diferente al turno de creacion");
			}

			operacionCancelada.setTurnoEdicion(turnoEntity);

			listaSaldosCaja.forEach(saldo -> {
				if (saldo.getFx().getId().equals(operacionCancelada.getFxOper().getId())) {
					// si es una compra resto el nominal de fxoper
					// si es una venta suma el nominal de fxoper
					if (operacionCancelada.getTipo().equals(TIPO_COMPRA)) {
						saldo.setNominal(saldo.getNominal().subtract(operacionCancelada.getNominal()));

						List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
								.obtenerDenominacionCantidadPorSaldo(saldo.getId());

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {

							for (DenominacionCantidadEntity denominacionRecibida : operacionCancelada
									.getDenominacionesCantidadRecibidas()) {
								if (denominacionRecibida.getDenominacion().getValor()
										.equals(denominacionCantidad.getDenominacion().getValor())) {
									denominacionCantidad.setCantidad(
											denominacionCantidad.getCantidad() - denominacionRecibida.getCantidad());
								}
							}

						});

					} else {
						saldo.setNominal(saldo.getNominal().add(operacionCancelada.getNominal()));

						List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
								.obtenerDenominacionCantidadPorSaldo(saldo.getId());

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {

							for (DenominacionCantidadEntity denominacionRecibida : operacionCancelada
									.getDenominacionesCantidadRecibidas()) {
								if (denominacionRecibida.getDenominacion().getValor()
										.equals(denominacionCantidad.getDenominacion().getValor())) {
									denominacionCantidad.setCantidad(
											denominacionCantidad.getCantidad() + denominacionRecibida.getCantidad());
								}
							}

						});
					}
				}
			});

			listaSaldosCaja.forEach(saldo -> {
				if (saldo.getFx().getId().equals(operacionCancelada.getFxBase().getId())) {
					if (operacionCancelada.getTipo().equals(TIPO_COMPRA)) {
						saldo.setNominal(saldo.getNominal().add(
								operacionCancelada.getNominal().multiply(operacionCancelada.getValorFxOperacion())));
						List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
								.obtenerDenominacionCantidadPorSaldo(saldo.getId());

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {

							for (DenominacionCantidadEntity denominacionRecibida : operacionCancelada
									.getDenominacionesCantidadRecibidas()) {
								if (denominacionRecibida.getDenominacion().getValor()
										.equals(denominacionCantidad.getDenominacion().getValor())) {
									denominacionCantidad.setCantidad(
											denominacionCantidad.getCantidad() + denominacionRecibida.getCantidad());
								}
							}

						});
					} else {
						saldo.setNominal(saldo.getNominal().subtract(
								operacionCancelada.getNominal().multiply(operacionCancelada.getValorFxOperacion())));
						List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
								.obtenerDenominacionCantidadPorSaldo(saldo.getId());

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {

							for (DenominacionCantidadEntity denominacionRecibida : operacionCancelada
									.getDenominacionesCantidadRecibidas()) {
								if (denominacionRecibida.getDenominacion().getValor()
										.equals(denominacionCantidad.getDenominacion().getValor())) {
									denominacionCantidad.setCantidad(
											denominacionCantidad.getCantidad() - denominacionRecibida.getCantidad());
								}
							}

						});
					}
				}
			});

			getSaldoSpringRepository().saveAll(listaSaldosCaja);

			@SuppressWarnings("unused")
			OperacionEntity newOperacionEntity = getOperacionSpringRepository().save(operacionCancelada);

		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return operacionConverter.convert(operacionCancelada);
	}

	@Override
	public List<Operacion> ObtenerListaOperacionesPorTurnoYDivisa(Turno turno, Long idfxOper) {
		return getOperacionSpringRepository().operacionesPorTurnoYDivisa(turno.getId(), idfxOper).stream()
				.map(operacionEntity -> getOperacionConverter().convert(operacionEntity)).collect(Collectors.toList());

	}

	@Override
	public List<Operacion> buscarConFiltro(OperacionFiltro operacion) {
		return getOperacionSpringRepository().obtenerConFiltro(operacion).stream()
				.map(operacionEntity -> getOperacionConverter().convert(operacionEntity)).collect(Collectors.toList());
	}

	@Override
	public List<Operacion> getOperacionesPorTurno(Long id) {
		return getOperacionSpringRepository().operacionesPorTurno(id).stream()
				.map(operacionEntity -> getOperacionConverter().convert(operacionEntity)).collect(Collectors.toList());
	}

	@Override
	public List<Operacion> getOperacionesUltimas() {
		return getOperacionSpringRepository().operacionesUltimas().stream()
				.map(operacionEntity -> getOperacionConverter().convert(operacionEntity)).collect(Collectors.toList());
	
	}

}
