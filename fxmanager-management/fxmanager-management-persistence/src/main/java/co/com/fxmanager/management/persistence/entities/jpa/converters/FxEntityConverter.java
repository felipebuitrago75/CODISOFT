package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.Fx;
import co.com.fxmanager.management.persistence.entities.jpa.DenominacionEntity;
import co.com.fxmanager.management.persistence.entities.jpa.FxEntity;
import lombok.Getter;

@Getter
@Component
public class FxEntityConverter implements EntityConverter<Fx, FxEntity> {

	@Override
	public FxEntity convert(Fx fx) {
		return convert(fx, FxEntity::new);
	}

	@Override
	public FxEntity convert(Fx fx, Supplier<FxEntity> supplier) {
		FxEntity fxEntity = supplier.get();
		
		fxEntity.setConcepto(fx.getConcepto());
		fxEntity.setCodigo(fx.getCodigo());
		

		List<Integer> denominaciones = fx.getDenominaciones();
		List<DenominacionEntity> denominacionEntities = fxEntity.getDenominaciones();
		if (denominacionEntities == null) {
			denominacionEntities = new ArrayList<>();
			fxEntity.setDenominaciones(denominacionEntities);
		}

		List<DenominacionEntity> denominacionesParaBorrar = denominacionEntities.stream()
				.filter(Predicate.not(denominacionEntity -> denominaciones.contains(denominacionEntity.getValor())))
				.collect(Collectors.toList());

		fxEntity.getDenominaciones().removeAll(denominacionesParaBorrar);

		List<Integer> denominacionesActuales = denominacionEntities.stream().map(DenominacionEntity::getValor)
				.collect(Collectors.toList());
		
		
		List<DenominacionEntity> denominacionesParaAgregar = denominaciones.stream()
				.filter(Predicate.not(denominacion -> denominacionesActuales.contains(denominacion)))
				.map(denominacion -> {
					DenominacionEntity denominacionEntity = new DenominacionEntity();
					denominacionEntity.setValor(denominacion);
					denominacionEntity.setFx(fxEntity);
					return denominacionEntity;
				}).collect(Collectors.toList());
		denominacionesParaAgregar.forEach(fxEntity.getDenominaciones()::add);
		return fxEntity;
	}

}
