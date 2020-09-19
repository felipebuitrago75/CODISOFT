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
import co.com.fxmanager.management.domain.entities.Fondeo;
import co.com.fxmanager.management.domain.services.exceptions.BusinessException;
import co.com.fxmanager.management.persistence.entities.jpa.DenominacionCantidadEntity;
import co.com.fxmanager.management.persistence.entities.jpa.FondeoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SaldoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TurnoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.converters.FondeoConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.FondeoEntityConverter;
import co.com.fxmanager.management.persistence.repositories.FondeoRepository;
import co.com.fxmanager.management.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.DenominacionCantidadSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.FondeoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.SaldoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Service
public class FondeoFacadeRepository implements FondeoRepository {

	private FondeoSpringRepository fondeoSpringRepository;

	private FondeoEntityConverter fondeoEntityConverter;

	private FondeoConverter fondeoConverter;

	private TurnoSpringRepository turnoSpringRepository;

	private DenominacionCantidadSpringRepository denominacionCantidadSpringRepository;

	private SaldoSpringRepository saldoSpringRepository;

	@Autowired
	FondeoFacadeRepository(FondeoConverter fondeoConverter, FondeoEntityConverter fondeoEntityConverter,
			FondeoSpringRepository fondeoSpringRepository, TurnoSpringRepository turnoSpringRepository,
			DenominacionCantidadSpringRepository denominacionCantidadSpringRepository,
			SaldoSpringRepository saldoSpringRepository) {
		super();
		this.fondeoSpringRepository = fondeoSpringRepository;
		this.fondeoEntityConverter = fondeoEntityConverter;
		this.fondeoConverter = fondeoConverter;
		this.turnoSpringRepository = turnoSpringRepository;
		this.denominacionCantidadSpringRepository = denominacionCantidadSpringRepository;
		this.saldoSpringRepository = saldoSpringRepository;
	}

	@Transactional
	@Override
	public Fondeo create(Fondeo fondeo) {
		Fondeo newFondeo;
		try {
			fondeo.setFecha(LocalDateTime.now(ZoneId.of("America/Bogota")));
			FondeoEntity fodeoEntity = getFondeoEntityConverter().convert(fondeo);

			TurnoEntity turno = getTurnoSpringRepository().findById(fondeo.getTurnoOrigen().getId())
					.orElseThrow(NoFoundDataException::new);
			

			List<SaldoEntity> listaSaldosCaja = getSaldoSpringRepository().saldosPorCaja(turno.getCaja().getId());

			listaSaldosCaja.forEach(saldo -> {
				if (saldo.getFx().getId().equals(fodeoEntity.getFx().getId())) {
					if (saldo.getNominal().compareTo(fodeoEntity.getValorGiro()) > 0) {
						saldo.setNominal(saldo.getNominal().subtract(fodeoEntity.getValorGiro()));

						List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
								.obtenerDenominacionCantidadPorSaldo(saldo.getId());

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {

							for (DenominacionCantidad denominacionEntregadas : fondeo
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
								"No es posible realizar el fondeo ya que no se cuenta con esa cantidad en caja");
					}
				}
			});
			
			TurnoEntity turnoDestino = getTurnoSpringRepository().findById(fondeo.getTurnoDestino().getId())
					.orElseThrow(NoFoundDataException::new);
			
			TurnoEntity turnoDestinoEntity = getTurnoSpringRepository().findById(turnoDestino.getId())
					.orElseThrow(NoFoundDataException::new);
			
			List<SaldoEntity> listaSaldosCajaDestino = getSaldoSpringRepository().saldosPorCaja(turnoDestinoEntity.getCaja().getId());

			listaSaldosCajaDestino.forEach(saldo -> {
				if (saldo.getFx().getId().equals(fodeoEntity.getFx().getId())) {
					saldo.setNominal(saldo.getNominal().add(fodeoEntity.getValorGiro()));
					
					List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
							.obtenerDenominacionCantidadPorSaldo(saldo.getId());

					
					listaDenominacionesSaldo.forEach(denominacionCantidad -> {

						for (DenominacionCantidad denominacionRecibida : fondeo
								.getDenominacionesYCantidades()) {
							if (denominacionRecibida.getValor()
									.equals(denominacionCantidad.getDenominacion().getValor())) {
								denominacionCantidad.setCantidad(
										denominacionCantidad.getCantidad() + denominacionRecibida.getCantidad());
							}
						}

					});
				}
			});

			getSaldoSpringRepository().saveAll(listaSaldosCaja);
			getSaldoSpringRepository().saveAll(listaSaldosCajaDestino);

			FondeoEntity newTrasladoEntity = getFondeoSpringRepository().save(fodeoEntity);
			newFondeo = getFondeoConverter().convert(newTrasladoEntity);

		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newFondeo;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Fondeo> getList(int first, int max) {
		List<Fondeo> fondeos;
		try {
			Page<FondeoEntity> page = getFondeoSpringRepository().findAll(PageRequest.of(first / max, max));
			fondeos = page.stream().map(trasladoEntity -> getFondeoConverter().convert(trasladoEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return fondeos;
	}

	@Transactional
	@Override
	public Fondeo update(Long id, Fondeo fondeo) {
		Fondeo newFondeo;
		try {
			FondeoEntity fondeoEntityEntity = getFondeoEntityConverter().convert(fondeo, () -> {
				Optional<FondeoEntity> fondeoEntityOptioanal = getFondeoSpringRepository().findById(id);
				return fondeoEntityOptioanal.orElseThrow(NoFoundDataException::new);
			});
			FondeoEntity newFondeoEntity = getFondeoSpringRepository().save(fondeoEntityEntity);
			newFondeo = getFondeoConverter().convert(newFondeoEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newFondeo;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		try {
			Optional<FondeoEntity> fondeoEntityOptioanal = getFondeoSpringRepository().findById(id);
			fondeoEntityOptioanal.ifPresent(getFondeoSpringRepository()::delete);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Fondeo> getFondeo(Long id) {
		Optional<Fondeo> fondeo;
		try {
			Optional<FondeoEntity> fondeoEntityOptioanal = getFondeoSpringRepository().findById(id);
			fondeo = fondeoEntityOptioanal.map(trasladoEntity -> getFondeoConverter().convert(trasladoEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return fondeo;
	}

}
