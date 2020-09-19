package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.DenominacionCantidad;
import co.com.fxmanager.management.domain.entities.Saldo;
import co.com.fxmanager.management.persistence.entities.jpa.SaldoEntity;
import lombok.Getter;

@Component
@Getter
public class SaldoConverter implements Converter<SaldoEntity, Saldo> {

	@Autowired
	private FxConverter fxConverter;

	public SaldoConverter() {
		super();
	}

	@Override
	public Saldo convert(SaldoEntity saldoEntity) {
		Saldo saldo = new Saldo(saldoEntity.getId(), saldoEntity.getCaja().getId(), 
				fxConverter.convert(saldoEntity.getFx()), saldoEntity.getNominal(), 
				saldoEntity.getPrecioPromedio());
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		if (persistenceUtil.isLoaded(saldoEntity, SaldoEntity.ClassInfo.DENOMINACIONES_CANTIDAD)) {
			List<DenominacionCantidad> denominacionesCantidad = saldoEntity.getDenominacionesCantidad().stream().map( denominacionCantidadEntity ->{
				return new DenominacionCantidad(denominacionCantidadEntity.getCantidad(), denominacionCantidadEntity.getDenominacion().getValor(), denominacionCantidadEntity.getDenominacion().getFx().getId());
			}).collect(Collectors.toList());
				
			saldo.setDenominacionesYCantidades(denominacionesCantidad);
		}
		
		return saldo;
	}

}
