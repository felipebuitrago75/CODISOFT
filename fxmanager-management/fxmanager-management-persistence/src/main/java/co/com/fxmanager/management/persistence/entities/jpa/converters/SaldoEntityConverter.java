package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.Saldo;
import co.com.fxmanager.management.persistence.entities.jpa.DenominacionCantidadEntity;
import co.com.fxmanager.management.persistence.entities.jpa.FxEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SaldoEntity;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.DenominacionSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.FxSpringRepository;
import lombok.Getter;

@Getter
@Component
public class SaldoEntityConverter implements EntityConverter<Saldo, SaldoEntity> {

	@Autowired
	private FxSpringRepository fxSpringRepository;
	
	
	@Autowired
	private FxEntityConverter fxEntityConverter;
	
	@Override
	public SaldoEntity convert(Saldo saldo) {
		return convert(saldo, SaldoEntity::new);
	}
	
	@Autowired
	DenominacionSpringRepository denominacionSpringRepository;

	@Override
	public SaldoEntity convert(Saldo saldo, Supplier<SaldoEntity> supplier) {
		SaldoEntity saldoEntity = supplier.get();
		
		//saldoEntity.setId(saldo.getId());
		
		saldoEntity.setNominal(saldo.getNominal());
		saldoEntity.setPrecioPromedio(saldo.getPrecioPromedio());
		
		Optional<FxEntity> fxEntityOptioanal = getFxSpringRepository().findByCodigo(saldo.getMoneda().getCodigo());
		saldoEntity.setFx(fxEntityOptioanal.orElseThrow(NoFoundDataException::new));
		
		List<DenominacionCantidadEntity> denominacionesCantidad= saldo
				.getDenominacionesYCantidades().stream().map(denominacionCantidad -> {
					DenominacionCantidadEntity denominacionCantidadEntity = new DenominacionCantidadEntity();
					denominacionCantidadEntity.setCantidad(denominacionCantidad.getCantidad());
					denominacionCantidadEntity.setDenominacion(denominacionSpringRepository
							.obtenerDenominacionPorFxyValor(denominacionCantidad.getIdFx(), denominacionCantidad.getValor()).orElseThrow(NoFoundDataException::new));
					denominacionCantidadEntity.setSaldo(saldoEntity);
					denominacionCantidadEntity.setTipo("ENTRADA");
					return denominacionCantidadEntity;
				}).collect(Collectors.toList());
		saldoEntity.setDenominacionesCantidad(denominacionesCantidad);
		
		
		return saldoEntity;
	}

}
