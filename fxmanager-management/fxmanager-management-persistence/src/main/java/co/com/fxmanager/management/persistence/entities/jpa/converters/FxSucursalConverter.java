package co.com.fxmanager.management.persistence.entities.jpa.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.FxSucursal;
import co.com.fxmanager.management.persistence.entities.jpa.FxSucursalEntity;
import lombok.Getter;

@Component
@Getter
public class FxSucursalConverter implements Converter<FxSucursalEntity, FxSucursal> {

	@Autowired
	private FxConverter fxConverter;
	
	public FxSucursalConverter() {
		super();
	}

	@Override
	public FxSucursal convert(FxSucursalEntity fxSucursalEntity) {
		return new FxSucursal(fxSucursalEntity.getPrecioVenta(), 
				fxSucursalEntity.getPrecioCompra(), fxSucursalEntity.getPrecioValoracion(), 
				fxConverter.convert(fxSucursalEntity.getFx()));
	}

}
