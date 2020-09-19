package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.Fondeo;
import co.com.fxmanager.management.persistence.entities.jpa.DenominacionCantidadEntity;
import co.com.fxmanager.management.persistence.entities.jpa.FondeoEntity;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.DenominacionSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.FxSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.SucursalSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Component
public class FondeoEntityConverter implements EntityConverter<Fondeo, FondeoEntity> {

	@Autowired
	private FxEntityConverter fxEntityConverter;

	@Autowired
	private FxSpringRepository fxSpringRepository;

	@Autowired
	private SucursalSpringRepository sucursalSpringRepository;

	@Autowired
	private TurnoSpringRepository turnoSpringRepository;
	
	@Autowired
	DenominacionSpringRepository denominacionSpringRepository;

	@Override
	public FondeoEntity convert(Fondeo fondeo) {
		return convert(fondeo, FondeoEntity::new);
	}

	@Override
	public FondeoEntity convert(Fondeo fondeo, Supplier<FondeoEntity> supplier) {
		FondeoEntity fondeoEntity = supplier.get();

		if (fondeo.getId() != null)
			fondeoEntity.setId(fondeo.getId());

		fondeoEntity.setFecha(fondeo.getFecha());
		fondeoEntity.setEstado(fondeo.getEstado());
		fondeoEntity.setValorGiro(fondeo.getValorGiro());

		fondeoEntity.setFx(getFxSpringRepository().findByCodigo(fondeo.getFx().getCodigo())
				.orElseThrow(NoFoundDataException::new));

		fondeoEntity.setTurnoOrigen(getTurnoSpringRepository().findById(fondeo.getTurnoOrigen().getId())
				.orElseThrow(NoFoundDataException::new));

		fondeoEntity.setTurnoDestino(getTurnoSpringRepository().findById(fondeo.getTurnoDestino().getId())
				.orElseThrow(NoFoundDataException::new));
		
		Set<DenominacionCantidadEntity> denominacionesCantidadEntregadas = fondeo
				.getDenominacionesYCantidades().stream().map(denominacionCantidad -> {
					DenominacionCantidadEntity denominacionCantidadEntity = new DenominacionCantidadEntity();
					denominacionCantidadEntity.setCantidad(denominacionCantidad.getCantidad());
					denominacionCantidadEntity.setDenominacion(denominacionSpringRepository
							.obtenerDenominacionPorFxyValor(denominacionCantidad.getIdFx(), denominacionCantidad.getValor()).orElseThrow(NoFoundDataException::new));
					denominacionCantidadEntity.setFondeo(fondeoEntity);
					denominacionCantidadEntity.setTipo("SALIDA");
					return denominacionCantidadEntity;
				}).collect(Collectors.toSet());
		fondeoEntity.setDenominacionesCantidad(denominacionesCantidadEntregadas);

		return fondeoEntity;
	}

}
