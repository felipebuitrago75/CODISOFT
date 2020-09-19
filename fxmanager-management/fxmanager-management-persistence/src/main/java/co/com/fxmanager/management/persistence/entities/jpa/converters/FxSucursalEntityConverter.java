package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.FxSucursal;
import co.com.fxmanager.management.persistence.entities.jpa.FxEntity;
import co.com.fxmanager.management.persistence.entities.jpa.FxSucursalEntity;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.FxSpringRepository;
import lombok.Getter;

@Getter
@Component
public class FxSucursalEntityConverter implements EntityConverter<FxSucursal, FxSucursalEntity> {

	@Autowired
	private FxSpringRepository fxSpringRepository;
	
	
	@Autowired
	private FxEntityConverter fxEntityConverter;
	
	@Override
	public FxSucursalEntity convert(FxSucursal fxSucursal) {
		return convert(fxSucursal, FxSucursalEntity::new);
	}

	@Override
	public FxSucursalEntity convert(FxSucursal fxSucursal, Supplier<FxSucursalEntity> supplier) {
		FxSucursalEntity fxSucursalEntity = supplier.get();
		
		fxSucursalEntity.setPrecioCompra(fxSucursal.getPrecioCompra());
		fxSucursalEntity.setPrecioValoracion(fxSucursal.getPrecioValoracion());
		fxSucursalEntity.setPrecioVenta(fxSucursal.getPrecioVenta());

		Optional<FxEntity> fxEntityOptioanal = getFxSpringRepository().findByCodigo(fxSucursal.getFx().getCodigo());
		fxSucursalEntity.setFx(fxEntityOptioanal.orElseThrow(NoFoundDataException::new));
		
		
		
		return fxSucursalEntity;
	}

}
