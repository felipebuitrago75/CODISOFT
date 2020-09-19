package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.PreCierre;
import co.com.fxmanager.management.persistence.entities.jpa.ArqueoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.ItemArqueoEntity;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.FxSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Component
public class ArqueoEntityConverter implements EntityConverter<PreCierre, ArqueoEntity> {

	@Autowired
	private FxEntityConverter fxEntityConverter;

	@Autowired
	private FxSpringRepository fxSpringRepository;

	@Autowired
	private TurnoSpringRepository turnoSpringRepository;
	

	@Override
	public ArqueoEntity convert(PreCierre preCierre) {
		return convert(preCierre, ArqueoEntity::new);
	}

	@Override
	public ArqueoEntity convert(PreCierre preCierre, Supplier<ArqueoEntity> supplier) {
		ArqueoEntity arqueoEntity = supplier.get();

		if (preCierre.getId() != null)
			arqueoEntity.setId(preCierre.getId());

		arqueoEntity.setFecha(preCierre.getFecha());

		arqueoEntity.setTurno(getTurnoSpringRepository().findById(preCierre.getTurno().getId())
				.orElseThrow(NoFoundDataException::new));

		Set<ItemArqueoEntity> listaItemArqueo = preCierre
				.getListaItems().stream().map(itemArqueo -> {
					ItemArqueoEntity itemArqueoEntity = new ItemArqueoEntity();
					itemArqueoEntity.setArqueo(arqueoEntity);
					itemArqueoEntity.setMoneda(getFxSpringRepository().findByCodigo(itemArqueo.getMoneda().getCodigo())
							.orElseThrow(NoFoundDataException::new));
					
					itemArqueoEntity.setSaldoInicial(itemArqueo.getSaldosInicial());
					itemArqueoEntity.setSaldoFinal(itemArqueo.getSaldosFinal());
					itemArqueoEntity.setNominalCompras(itemArqueo.getNominalCompras());
					itemArqueoEntity.setNominalVentas(itemArqueo.getNominalVentas());
					itemArqueoEntity.setNominalEgresos(itemArqueo.getNominalEgresos());
					itemArqueoEntity.setNominalIngresos(itemArqueo.getNominalIngresos());
					itemArqueoEntity.setNominalTrasladosEntrantes(itemArqueo.getNominalTrasladosEntrantes());
					itemArqueoEntity.setNominalTrasladosSalientes(itemArqueo.getNominalTrasladosSalientes());
					itemArqueoEntity.setSaldosRealEnCaja(itemArqueo.getSaldosRealEnCaja());
					
					return itemArqueoEntity;
				}).collect(Collectors.toSet());
		arqueoEntity.setItemsArqueo(listaItemArqueo);

		return arqueoEntity;
	}

}
