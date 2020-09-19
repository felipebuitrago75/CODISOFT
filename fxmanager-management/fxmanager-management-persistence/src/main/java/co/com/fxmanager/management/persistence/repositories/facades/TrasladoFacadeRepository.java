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
import co.com.fxmanager.management.domain.entities.Traslado;
import co.com.fxmanager.management.domain.entities.TrasladoFiltro;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.domain.services.exceptions.BusinessException;
import co.com.fxmanager.management.persistence.entities.jpa.DenominacionCantidadEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SaldoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TrasladoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TurnoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.converters.TrasladoConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.TrasladoEntityConverter;
import co.com.fxmanager.management.persistence.repositories.TrasladoRepository;
import co.com.fxmanager.management.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.DenominacionCantidadSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.SaldoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TrasladoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Service
public class TrasladoFacadeRepository implements TrasladoRepository {

	private TrasladoSpringRepository trasladoSpringRepository;

	private TrasladoEntityConverter trasladoEntityConverter;

	private TrasladoConverter trasladoConverter;
	
	private TurnoSpringRepository turnoSpringRepository;

	private SaldoSpringRepository saldoSpringRepository;

	private DenominacionCantidadSpringRepository denominacionCantidadSpringRepository;
	
	@Autowired
	public TrasladoFacadeRepository(TrasladoSpringRepository trasladoSpringRepository, TrasladoConverter trasladoConverter,
			TrasladoEntityConverter trasladoEntityConverter,
			TurnoSpringRepository turnoSpringRepository, SaldoSpringRepository saldoSpringRepository,
			DenominacionCantidadSpringRepository denominacionCantidadSpringRepository) {
		super();
		this.trasladoSpringRepository = trasladoSpringRepository;
		this.trasladoEntityConverter = trasladoEntityConverter;
		this.trasladoConverter = trasladoConverter;
		this.turnoSpringRepository = turnoSpringRepository;
		this.saldoSpringRepository = saldoSpringRepository;
		this.denominacionCantidadSpringRepository = denominacionCantidadSpringRepository;
	}

	@Transactional
	@Override
	public Traslado create(Traslado traslado) {
		Traslado newTraslado;
		try {
			traslado.setFecha(LocalDateTime.now(ZoneId.of("America/Bogota")));
			TrasladoEntity trasladoEntity = getTrasladoEntityConverter().convert(traslado);
			
			TurnoEntity turno = getTurnoSpringRepository().findById(traslado.getTurnoOrigen().getId())
					.orElseThrow(NoFoundDataException::new);
			
			List<SaldoEntity> listaSaldosCaja = getSaldoSpringRepository().saldosPorCaja(turno.getCaja().getId());

			listaSaldosCaja.forEach(saldo -> {
				if (saldo.getFx().getId().equals(trasladoEntity.getFx().getId())) {
					 if (saldo.getNominal().compareTo(trasladoEntity.getValorGiro()) > 0 ) {
						saldo.setNominal(saldo.getNominal().subtract(trasladoEntity.getValorGiro()));
						
						List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
								.obtenerDenominacionCantidadPorSaldo(saldo.getId());

						listaDenominacionesSaldo.forEach(denominacionCantidad -> {

							for (DenominacionCantidad denominacionEntregadas : traslado
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
						throw new NoFoundDataException("No es posible realizar el traslado ya que no se cuenta con esa cantidad en caja");
					}
				}
			});


			getSaldoSpringRepository().saveAll(listaSaldosCaja);

			
			
			TrasladoEntity newTrasladoEntity = getTrasladoSpringRepository().save(trasladoEntity);
			newTraslado = getTrasladoConverter().convert(newTrasladoEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newTraslado;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Traslado> getList(int first, int max) {
		List<Traslado> traslados;
		try {
			Page<TrasladoEntity> page = getTrasladoSpringRepository().findAll(PageRequest.of(first / max, max));
			traslados = page.stream().map(trasladoEntity -> getTrasladoConverter().convert(trasladoEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return traslados;
	}

	@Transactional
	@Override
	public Traslado update(Long id, Turno turno) {
		Traslado newTraslado;
		try {
			TrasladoEntity trasladoEntity = trasladoSpringRepository.findById(id)
					.orElseThrow(NoFoundDataException::new);
			
			Traslado traslado = trasladoConverter.convert(trasladoEntity);
			
			TurnoEntity turnoEntity = getTurnoSpringRepository().findById(turno.getId())
					.orElseThrow(NoFoundDataException::new);
			
			List<SaldoEntity> listaSaldosCaja = getSaldoSpringRepository().saldosPorCaja(turnoEntity.getCaja().getId());

			listaSaldosCaja.forEach(saldo -> {
				if (saldo.getFx().getId().equals(trasladoEntity.getFx().getId())) {
					saldo.setNominal(saldo.getNominal().add(trasladoEntity.getValorGiro()));
					
					List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
							.obtenerDenominacionCantidadPorSaldo(saldo.getId());

					
					listaDenominacionesSaldo.forEach(denominacionCantidad -> {

						for (DenominacionCantidad denominacionRecibida : traslado
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

			trasladoEntity.setTurnoDestino(turnoEntity);
			trasladoEntity.setEstado("EJECUTADO");
			
			getSaldoSpringRepository().saveAll(listaSaldosCaja);
			
			TrasladoEntity newTrasladoEntity = getTrasladoSpringRepository().save(trasladoEntity);
			newTraslado = getTrasladoConverter().convert(newTrasladoEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newTraslado;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		try {
			Optional<TrasladoEntity> trasladoEntityOptioanal = getTrasladoSpringRepository().findById(id);
			trasladoEntityOptioanal.ifPresent(getTrasladoSpringRepository()::delete);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Traslado> getTraslado(Long id) {
		Optional<Traslado> traslado;
		try {
			Optional<TrasladoEntity> trasladoEntityOptioanal = getTrasladoSpringRepository().findById(id);
			traslado = trasladoEntityOptioanal.map(trasladoEntity -> getTrasladoConverter().convert(trasladoEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return traslado;
	}

	@Override
	public void cancelarTraslado(Long id, Turno turno) {
		
		TrasladoEntity trasladoEntity = trasladoSpringRepository.findById(id)
				.orElseThrow(NoFoundDataException::new);
		
		Traslado traslado = trasladoConverter.convert(trasladoEntity);
		
		TurnoEntity turnoOrigen = getTurnoSpringRepository().findById(trasladoEntity.getTurnoOrigen().getId())
				.orElseThrow(NoFoundDataException::new);
		
		TurnoEntity turnoCancelacion = getTurnoSpringRepository().findById(turno.getId())
				.orElseThrow(NoFoundDataException::new);
		
		if(!turnoOrigen.getId().equals(turnoCancelacion.getId())){
			throw new NoFoundDataException("No es posible cancelar este traslado, el turno de creacion es diferente al turno actual");
		}
		if(trasladoEntity.getEstado().equals("EJECUTADO") || trasladoEntity.getEstado().equals("CANCELADO") ){
			throw new NoFoundDataException("No es posible cancelar un flujo extraordinario que ya esta cancelado");
		}
		
		List<SaldoEntity> listaSaldosCaja = getSaldoSpringRepository().saldosPorCaja(turnoOrigen.getCaja().getId());

		listaSaldosCaja.forEach(saldo -> {
			if (saldo.getFx().getCodigo().equals(trasladoEntity.getFx().getCodigo())) {
					saldo.setNominal(saldo.getNominal().add(trasladoEntity.getValorGiro()));
					
					List<DenominacionCantidadEntity> listaDenominacionesSaldo = denominacionCantidadSpringRepository
							.obtenerDenominacionCantidadPorSaldo(saldo.getId());

					
					listaDenominacionesSaldo.forEach(denominacionCantidad -> {

						for (DenominacionCantidad denominacionRecibida : traslado
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


		trasladoEntity.setEstado("CANCELADO");
		
		getSaldoSpringRepository().saveAll(listaSaldosCaja);
		
		trasladoSpringRepository.save(trasladoEntity);
		
	}

	@Override
	public List<Traslado> obtenerConFiltro(TrasladoFiltro trasladoFiltro) {
		return getTrasladoSpringRepository().obtenerConFiltro(trasladoFiltro).stream()
				.map(trasladoEntity -> getTrasladoConverter().convert(trasladoEntity)).collect(Collectors.toList());
	}
	
}
