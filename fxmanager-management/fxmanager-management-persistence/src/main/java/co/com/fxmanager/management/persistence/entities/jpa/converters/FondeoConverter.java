package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.DenominacionCantidad;
import co.com.fxmanager.management.domain.entities.Fondeo;
import co.com.fxmanager.management.domain.entities.Fx;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.persistence.entities.jpa.FondeoEntity;
import lombok.Getter;

@Component
@Getter
public class FondeoConverter implements Converter<FondeoEntity, Fondeo> {

	@Autowired
	private TurnoConverter turnoConverter;

	@Autowired
	private FxConverter fxConverter;

	public FondeoConverter() {
		super();
	}

	@Override
	public Fondeo convert(FondeoEntity fondeoEntity) {
		Fondeo fondeo;
		Turno turnoOrigen = turnoConverter.convert(fondeoEntity.getTurnoOrigen());
		Turno turnoDestino = turnoConverter.convert(fondeoEntity.getTurnoDestino());

		Fx fx = fxConverter.convert(fondeoEntity.getFx());

		fondeo = new Fondeo(fondeoEntity.getId(), fondeoEntity.getFecha(), fondeoEntity.getEstado(),
				fondeoEntity.getValorGiro(), fx, turnoOrigen, turnoDestino);
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();

		if (persistenceUtil.isLoaded(fondeoEntity, FondeoEntity.ClassInfo.DENOMINACIONES_CANTIDAD)) {
			List<DenominacionCantidad> denominacionesCantidad = fondeoEntity.getDenominacionesCantidad().stream()
					.map(denominacionCantidadEntity -> {
						return new DenominacionCantidad(denominacionCantidadEntity.getCantidad(),
								denominacionCantidadEntity.getDenominacion().getValor(),
								denominacionCantidadEntity.getDenominacion().getFx().getId());
					}).collect(Collectors.toList());

			fondeo.setDenominacionesYCantidades(denominacionesCantidad);
		}

		return fondeo;
	}

}
