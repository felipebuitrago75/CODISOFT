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
import co.com.fxmanager.management.domain.entities.FlujoExtraordinario;
import co.com.fxmanager.management.domain.entities.FlujoFiltro;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.domain.services.exceptions.BusinessException;
import co.com.fxmanager.management.persistence.entities.jpa.DenominacionCantidadEntity;
import co.com.fxmanager.management.persistence.entities.jpa.FlujoExtraordinarioEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SaldoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TurnoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.converters.FlujoExtraordinarioConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.FlujoExtraordinarioEntityConverter;
import co.com.fxmanager.management.persistence.repositories.FlujoExtraordinarioRepository;
import co.com.fxmanager.management.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.DenominacionCantidadSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.FlujoExtraordinarioSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.SaldoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Service
public class FlujoExtraordinarioFacadeRepository implements FlujoExtraordinarioRepository {

	private FlujoExtraordinarioSpringRepository flujoExtraordinarioSpringRepository;

	private FlujoExtraordinarioEntityConverter flujoExtraordinarioEntityConverter;

	private FlujoExtraordinarioConverter flujoExtraordinarioConverter;

	private TurnoSpringRepository turnoSpringRepository;

	private SaldoSpringRepository saldoSpringRepository;

	private DenominacionCantidadSpringRepository denominacionCantidadSpringRepository;

	@Autowired
	public FlujoExtraordinarioFacadeRepository(FlujoExtraordinarioSpringRepository flujoExtraordinarioSpringRepository,
			FlujoExtraordinarioEntityConverter flujoExtraordinarioEntityConverter,
			FlujoExtraordinarioConverter flujoExtraordinarioConverter, TurnoSpringRepository turnoSpringRepository,
			SaldoSpringRepository saldoSpringRepository,
			DenominacionCantidadSpringRepository denominacionCantidadSpringRepository) {
		super();
		this.flujoExtraordinarioSpringRepository = flujoExtraordinarioSpringRepository;
		this.flujoExtraordinarioEntityConverter = flujoExtraordinarioEntityConverter;
		this.flujoExtraordinarioConverter = flujoExtraordinarioConverter;
		this.turnoSpringRepository = turnoSpringRepository;
		this.saldoSpringRepository = saldoSpringRepository;
		this.denominacionCantidadSpringRepository = denominacionCantidadSpringRepository;
	}

	@Transactional
	@Override
	public FlujoExtraordinario create(FlujoExtraordinario flujoExtraordinario) {
		FlujoExtraordinario newFlujoExtraordinario;
		try {

			flujoExtraordinario.setFecha(LocalDateTime.now(ZoneId.of("America/Bogota")));
			flujoExtraordinario.setEstado("EJECUTADO");

			FlujoExtraordinarioEntity flujoExtraordinarioEntity = getFlujoExtraordinarioEntityConverter()
					.convert(flujoExtraordinario);

			TurnoEntity turno = getTurnoSpringRepository().findById(flujoExtraordinario.getTurno().getId())
					.orElseThrow(NoFoundDataException::new);

			List<SaldoEntity> listaSaldosCaja = getSaldoSpringRepository().saldosPorCaja(turno.getCaja().getId());

			listaSaldosCaja.forEach(saldo -> {
				if (saldo.getFx().getCodigo().equals(flujoExtraordinario.getFx().getCodigo())) {
					if (flujoExtraordinario.getTipo().equals("INGRESO")) {
						saldo.setNominal(saldo.getNominal().add(flujoExtraordinario.getValor()));

						List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
								.obtenerDenominacionCantidadPorSaldo(saldo.getId());

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {

							for (DenominacionCantidad denominacionRecibida : flujoExtraordinario
									.getDenominacionesYCantidades()) {
								if (denominacionRecibida.getValor()
										.equals(denominacionCantidad.getDenominacion().getValor())) {
									denominacionCantidad.setCantidad(
											denominacionCantidad.getCantidad() + denominacionRecibida.getCantidad());
								}
							}

						});
					} else if (saldo.getNominal().compareTo(flujoExtraordinario.getValor()) > 0) {
						saldo.setNominal(saldo.getNominal().subtract(flujoExtraordinario.getValor()));

						List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
								.obtenerDenominacionCantidadPorSaldo(saldo.getId());

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {

							for (DenominacionCantidad denominacionEntregadas : flujoExtraordinario
									.getDenominacionesYCantidades()) {
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
						throw new NoFoundDataException(
								"No es posible realizar el egreso ya que no se cuenta con esa cantidad en caja");
					}
				}
			});

			getSaldoSpringRepository().saveAll(listaSaldosCaja);

			FlujoExtraordinarioEntity newFlujoExtraordinarioEntity = getFlujoExtraordinarioSpringRepository()
					.save(flujoExtraordinarioEntity);
			newFlujoExtraordinario = getFlujoExtraordinarioConverter().convert(newFlujoExtraordinarioEntity);

		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newFlujoExtraordinario;
	}

	@Transactional(readOnly = true)
	@Override
	public List<FlujoExtraordinario> getList(int first, int max) {
		List<FlujoExtraordinario> flujosExtraordinarios;
		try {
			Page<FlujoExtraordinarioEntity> page = getFlujoExtraordinarioSpringRepository()
					.findAll(PageRequest.of(first / max, max));
			flujosExtraordinarios = page.stream().map(
					flujoExtraordinarioEntity -> getFlujoExtraordinarioConverter().convert(flujoExtraordinarioEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return flujosExtraordinarios;
	}

	@Transactional
	@Override
	public FlujoExtraordinario update(Long id, FlujoExtraordinario flujoExtraordinario) {
		FlujoExtraordinario newFlujoExtraordinario;
		try {
			FlujoExtraordinarioEntity flujoExtraordinarioEntity = getFlujoExtraordinarioEntityConverter()
					.convert(flujoExtraordinario, () -> {
						Optional<FlujoExtraordinarioEntity> flujoExtraordinarioEntityOptional = getFlujoExtraordinarioSpringRepository()
								.findById(id);
						return flujoExtraordinarioEntityOptional.orElseThrow(NoFoundDataException::new);
					});
			FlujoExtraordinarioEntity newFlujoExtraordinarioEntity = getFlujoExtraordinarioSpringRepository()
					.save(flujoExtraordinarioEntity);
			newFlujoExtraordinario = getFlujoExtraordinarioConverter().convert(newFlujoExtraordinarioEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newFlujoExtraordinario;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		try {
			Optional<FlujoExtraordinarioEntity> flujoExtraordinarioEntityOptional = getFlujoExtraordinarioSpringRepository()
					.findById(id);
			flujoExtraordinarioEntityOptional.ifPresent(getFlujoExtraordinarioSpringRepository()::delete);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<FlujoExtraordinario> getFlujoExtraordinario(Long id) {
		Optional<FlujoExtraordinario> flujoExtraordinario;
		try {
			Optional<FlujoExtraordinarioEntity> flujoExtraordinarioEntityOptional = getFlujoExtraordinarioSpringRepository()
					.findById(id);
			flujoExtraordinario = flujoExtraordinarioEntityOptional.map(getFlujoExtraordinarioConverter()::convert);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return flujoExtraordinario;
	}

	@Override
	public List<FlujoExtraordinario> listaFlujosPorTurno(Turno turno) {
		List<FlujoExtraordinario> flujosExtraordinarios;
		try {
			List<FlujoExtraordinarioEntity> flujosExtraordinariosEntity = flujoExtraordinarioSpringRepository
					.listaFlujosPorTurno(turno.getId());
			flujosExtraordinarios = flujosExtraordinariosEntity.stream().map(
					flujoExtraordinarioEntity -> getFlujoExtraordinarioConverter().convert(flujoExtraordinarioEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return flujosExtraordinarios;
	}

	/**
	 * este metodo recibe el id del flujo y el turno en el que se cancelo el flujo
	 * solo es posible cancelar el flujo si se cancelo en el mismo turno
	 */
	@Override
	public void cancelarFlujo(Long id, Turno turno) {

		FlujoExtraordinarioEntity flujoExtraordinarioEntity = flujoExtraordinarioSpringRepository.findById(id)
				.orElseThrow(NoFoundDataException::new);

		FlujoExtraordinario flujoExtraordinario = flujoExtraordinarioConverter.convert(flujoExtraordinarioEntity);

		TurnoEntity turnoCreacionFlujo = getTurnoSpringRepository()
				.findById(flujoExtraordinarioEntity.getTurno().getId()).orElseThrow(NoFoundDataException::new);

		TurnoEntity turnoCancelacionFlujo = getTurnoSpringRepository().findById(turno.getId())
				.orElseThrow(NoFoundDataException::new);

		if (!turnoCreacionFlujo.getId().equals(turnoCancelacionFlujo.getId())) {
			throw new NoFoundDataException(
					"No es posible cancelar este flujo extraordinario, el turno de creacion es diferente al turno actual");
		}
		if (flujoExtraordinarioEntity.getEstado().equals("CANCELADO")) {
			throw new NoFoundDataException("No es posible cancelar un flujo extraordinario que ya esta cancelado");
		}

		List<SaldoEntity> listaSaldosCaja = getSaldoSpringRepository().saldosPorCaja(turno.getIdCaja());

		listaSaldosCaja.forEach(saldo -> {
			if (saldo.getFx().getCodigo().equals(flujoExtraordinarioEntity.getFx().getCodigo())) {
				if (flujoExtraordinarioEntity.getTipo().equals("EGRESO")) {
					saldo.setNominal(saldo.getNominal().add(flujoExtraordinarioEntity.getValor()));

					List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
							.obtenerDenominacionCantidadPorSaldo(saldo.getId());

					listaDenominacionesSaldo.forEach(denominacionCantidad -> {

						for (DenominacionCantidad denominacionRecibida : flujoExtraordinario
								.getDenominacionesYCantidades()) {
							if (denominacionRecibida.getValor()
									.equals(denominacionCantidad.getDenominacion().getValor())) {
								denominacionCantidad.setCantidad(
										denominacionCantidad.getCantidad() + denominacionRecibida.getCantidad());
							}
						}

					});
				} else if (saldo.getNominal().compareTo(flujoExtraordinarioEntity.getValor()) > 0) {
					saldo.setNominal(saldo.getNominal().subtract(flujoExtraordinarioEntity.getValor()));
					List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
							.obtenerDenominacionCantidadPorSaldo(saldo.getId());

					listaDenominacionesSaldo.forEach(denominacionCantidad -> {

						for (DenominacionCantidad denominacionEntregadas : flujoExtraordinario
								.getDenominacionesYCantidades()) {
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
					throw new NoFoundDataException(
							"No es posible realizar el egreso ya que no se cuenta con esa cantidad en caja");
				}
			}
		});

		flujoExtraordinarioEntity.setEstado("CANCELADO");

		getSaldoSpringRepository().saveAll(listaSaldosCaja);

		getFlujoExtraordinarioSpringRepository().save(flujoExtraordinarioEntity);

	}

	@Override
	public List<FlujoExtraordinario> obtenerConFiltro(FlujoFiltro flujoFiltro) {
		return getFlujoExtraordinarioSpringRepository().obtenerConFiltro(flujoFiltro).stream()
				.map(flujoEntity -> getFlujoExtraordinarioConverter().convert(flujoEntity)).collect(Collectors.toList());
	}

}
