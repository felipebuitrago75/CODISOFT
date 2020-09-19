package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.Caja;
import co.com.fxmanager.management.domain.entities.Saldo;
import co.com.fxmanager.management.persistence.entities.jpa.CajaEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SaldoEntity;
import lombok.Getter;

@Component
@Getter
public class CajaConverter implements Converter<CajaEntity, Caja> {

	@Autowired
	private SaldoConverter saldoConverter;
	
	public CajaConverter() {
		super();
	}

	@Override
	public Caja convert(CajaEntity cajaEntity) {
		Caja caja = new Caja(cajaEntity.getId(), cajaEntity.getNombre());
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		if (persistenceUtil.isLoaded(cajaEntity, CajaEntity.ClassInfo.LISTA_SALDOS)) {
			List<SaldoEntity> listaSaldos = cajaEntity.getListaSaldos();
			List<Saldo> listaSaldosConvertido = new ArrayList<>();
			for (SaldoEntity saldoEntity : listaSaldos) {
				listaSaldosConvertido.add(saldoConverter.convert(saldoEntity));
			}
			caja.setListaSaldos(listaSaldosConvertido);
		}
		
		caja.setCodigoSucursal(cajaEntity.getSucursal().getCodigo());
		
		
		
		return caja;
	}

}
