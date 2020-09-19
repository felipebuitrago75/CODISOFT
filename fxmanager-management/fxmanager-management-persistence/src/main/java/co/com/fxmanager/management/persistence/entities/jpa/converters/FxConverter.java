package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.Fx;
import co.com.fxmanager.management.persistence.entities.jpa.DenominacionEntity;
import co.com.fxmanager.management.persistence.entities.jpa.FxEntity;
import lombok.Getter;

@Component
@Getter
public class FxConverter implements Converter<FxEntity, Fx> {

	public FxConverter() {
		super();
	}

	@Override
	public Fx convert(FxEntity fxEntity) {
		Fx fx = new Fx(fxEntity.getCodigo(), fxEntity.getConcepto());
		
		if(fxEntity.getId()!=null) {
			fx.setId(fxEntity.getId());
		}
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		if (persistenceUtil.isLoaded(fxEntity, FxEntity.ClassInfo.DENOMINACIONES)) {
			List<Integer> denominaciones = fxEntity.getDenominaciones().stream().map(DenominacionEntity::getValor)
					.sorted(Comparator.reverseOrder()).collect(Collectors.toList());
			fx.setDenominaciones(denominaciones);
		}
		return fx;
	}

}
