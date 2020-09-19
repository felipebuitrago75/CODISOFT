package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.Fx;
import co.com.fxmanager.management.domain.entities.ItemPreCierre;
import co.com.fxmanager.management.domain.entities.PreCierre;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.persistence.entities.jpa.ArqueoEntity;
import lombok.Getter;

@Component
@Getter
public class ArqueoConverter implements Converter<ArqueoEntity, PreCierre> {

	@Autowired
	private TurnoConverter turnoConverter;

	
	public ArqueoConverter() {
		super();
	}

	@Override
	public PreCierre convert(ArqueoEntity arqueoEntity) {
		PreCierre preCierre;
		
		
		preCierre = new PreCierre();
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();

		if (persistenceUtil.isLoaded(arqueoEntity, ArqueoEntity.ClassInfo.ITEMS_ARQUEO)) {
	
			
			List<ItemPreCierre> itemsArqueo = arqueoEntity.getItemsArqueo().stream()
					.map(itemArqueEntity -> {
						ItemPreCierre itemP = new ItemPreCierre();
						itemP.setId(itemArqueEntity.getId());
						itemP.setMoneda(new Fx(itemArqueEntity.getMoneda().getCodigo(), itemArqueEntity.getMoneda().getConcepto()));
						itemP.setNominalCompras(itemArqueEntity.getNominalCompras());
						itemP.setNominalVentas(itemArqueEntity.getNominalVentas());
						itemP.setNominalEgresos(itemArqueEntity.getNominalEgresos());
						itemP.setNominalIngresos(itemArqueEntity.getNominalIngresos());
						itemP.setNominalTrasladosEntrantes(itemArqueEntity.getNominalTrasladosEntrantes());
						itemP.setNominalTrasladosSalientes(itemArqueEntity.getNominalTrasladosSalientes());
						itemP.setSaldosInicial(itemArqueEntity.getSaldoFinal());
						itemP.setSaldosFinal(itemArqueEntity.getSaldoFinal());
						itemP.setSaldosRealEnCaja(itemArqueEntity.getSaldosRealEnCaja());
						return itemP;
					}).collect(Collectors.toList());

			preCierre.setListaItems(itemsArqueo);
		}
		
		if (persistenceUtil.isLoaded(arqueoEntity, ArqueoEntity.ClassInfo.TURNO) && arqueoEntity.getTurno()!=null ) {
			Turno turno = turnoConverter.convert(arqueoEntity.getTurno());
			preCierre.setTurno(turno);
		}
		
		preCierre.setFecha(arqueoEntity.getFecha());
		preCierre.setId(arqueoEntity.getId());
	
		return preCierre;
	}

}
