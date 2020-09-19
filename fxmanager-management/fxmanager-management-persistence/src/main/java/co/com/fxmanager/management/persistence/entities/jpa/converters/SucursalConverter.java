package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import co.com.fxmanager.management.domain.entities.FxSucursal;
import co.com.fxmanager.management.domain.entities.Sucursal;
import co.com.fxmanager.management.persistence.entities.jpa.FxSucursalEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SucursalEntity;
import lombok.Getter;

@Component
@Getter
public class SucursalConverter implements Converter<SucursalEntity, Sucursal> {

	@Autowired
	private FxSucursalConverter fxSucursalConverter;
	
	@Autowired
	private CajaConverter cajaConverter;
	
	public SucursalConverter() {
		super();
	}

	@Override
	public Sucursal convert(SucursalEntity sucursalEntity) {
		Sucursal sucursal = new Sucursal(sucursalEntity.getCodigo(), sucursalEntity.getNombre(), sucursalEntity.getDireccion(),
				sucursalEntity.getEstado() , sucursalEntity.getTelefonos(),sucursalEntity.getChkPrincipal());
		
		if(sucursalEntity.getId()!=null) {
			sucursal.setId(sucursalEntity.getId());
		}
		
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		if (persistenceUtil.isLoaded(sucursalEntity, SucursalEntity.ClassInfo.LISTA_PRECIOS)) {
			List<FxSucursalEntity> listaPrecios = sucursalEntity.getListaPrecios();
			List<FxSucursal> listaPreciosConvertido = new ArrayList<>();
			for (FxSucursalEntity fxSucursalEntity : listaPrecios) {
				listaPreciosConvertido.add(fxSucursalConverter.convert(fxSucursalEntity));
			}
			sucursal.setListaPrecios(listaPreciosConvertido);
		}
//		if (persistenceUtil.isLoaded(sucursalEntity, SucursalEntity.ClassInfo.LISTA_CAJAS)) {
//			Set<CajaEntity> listaCajas = sucursalEntity.getListaCajas();
//			Set<Caja> listaCajasConvertido = new HashSet<>();
//			for (CajaEntity cajaEntity : listaCajas) {
//				listaCajasConvertido.add(cajaConverter.convert(cajaEntity));
//			}
//			sucursal.setListaCajas(listaCajasConvertido);
//		}
		return sucursal;
	}

}
