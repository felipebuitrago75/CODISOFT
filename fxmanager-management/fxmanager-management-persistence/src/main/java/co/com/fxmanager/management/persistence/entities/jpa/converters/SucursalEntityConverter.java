package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.FxSucursal;
import co.com.fxmanager.management.domain.entities.Sucursal;
import co.com.fxmanager.management.persistence.entities.jpa.FxSucursalEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SucursalEntity;
import lombok.Getter;

@Getter
@Component
public class SucursalEntityConverter implements EntityConverter<Sucursal, SucursalEntity> {

	@Autowired
	private FxSucursalEntityConverter fxSucursalEntityConverter;

	@Autowired
	private FxSucursalConverter fxSucursalConverter;



	@Autowired
	private CajaConverter cajaConverter;
	
	
	@Override
	public SucursalEntity convert(Sucursal sucursal) {
		return convert(sucursal, SucursalEntity::new);
	}

	@Override
	public SucursalEntity convert(Sucursal sucursal, Supplier<SucursalEntity> supplier) {
		SucursalEntity sucursalEntity = supplier.get();

		sucursalEntity.setNombre(sucursal.getNombre());
		sucursalEntity.setCodigo(sucursal.getCod());
		sucursalEntity.setEstado(sucursal.getEstado());
		sucursalEntity.setDireccion(sucursal.getDireccion());
		sucursalEntity.setChkPrincipal(sucursal.getChkPrincipal());
		if (sucursal.getTelefonos() != null) {
			sucursalEntity.setTelefonos(sucursal.getTelefonos());
		}

		List<FxSucursal> listaPrecios = sucursal.getListaPrecios();

		List<FxSucursalEntity> listaPreciosEntities = sucursalEntity.getListaPrecios();
		if (listaPreciosEntities == null) {
			listaPreciosEntities = new ArrayList<>();
			sucursalEntity.setListaPrecios(listaPreciosEntities);
		}

		List<FxSucursalEntity> listaPreciosParaEditar = listaPreciosEntities.stream()
				.filter(fxSucursalEntity -> listaPrecios.contains(fxSucursalConverter.convert(fxSucursalEntity)))
				.collect(Collectors.toList());

		listaPreciosParaEditar.forEach((fxSucursalEntity) -> {
			listaPrecios.stream().map(fxSucursalEntityConverter::convert).filter(fse -> {
				return fse.getFx().getId().equals(fxSucursalEntity.getFx().getId());
			}).forEach(fxSucursal -> {
				fxSucursalEntity.setPrecioCompra(fxSucursal.getPrecioCompra());
				fxSucursalEntity.setPrecioValoracion(fxSucursal.getPrecioValoracion());
				fxSucursalEntity.setPrecioVenta(fxSucursal.getPrecioVenta());
			});
		});

		List<FxSucursalEntity> listaPreciosParaBorrar = listaPreciosEntities.stream()
				.filter(Predicate
						.not(fxSucursalEntity -> listaPrecios.contains(fxSucursalConverter.convert(fxSucursalEntity))))
				.collect(Collectors.toList());

		sucursalEntity.getListaPrecios().removeAll(listaPreciosParaBorrar);

		List<FxSucursal> listaPreciosActuales = sucursalEntity.getListaPrecios().stream()
				.map(fxSucursalConverter::convert).collect(Collectors.toList());

		List<FxSucursalEntity> listaDePreciosParaAgregar = listaPrecios.stream()
				.filter(Predicate.not(listaPreciosActuales::contains)).map(fxSucursal -> {
					FxSucursalEntity fxSucursalEntity = fxSucursalEntityConverter.convert(fxSucursal);
					fxSucursalEntity.setSucursal(sucursalEntity);
					return fxSucursalEntity;
				}).collect(Collectors.toList());
		listaDePreciosParaAgregar.forEach(sucursalEntity.getListaPrecios()::add);
		
		
		
		
		/**
		 * inicio de operaciones de conjuntos para cajas
		 */

		
//		Set<Caja> listaCajas = sucursal.getListaCajas();
//
//		Set<CajaEntity> listaCajasEntities = sucursalEntity.getListaCajas();
//		if (listaCajasEntities == null) {
//			listaCajasEntities = new HashSet<>();
//			sucursalEntity.setListaCajas(listaCajasEntities);
//		}
//
//		Set<CajaEntity> listaCajasParaEditar = listaCajasEntities.stream()
//				.filter(cajaEntity -> listaCajas.contains(cajaConverter.convert(cajaEntity)))
//				.collect(Collectors.toSet());
//
//		listaCajasParaEditar.forEach((cajaEntity) -> {
//			listaCajas.stream().map(cajaEntityConverter::convert).filter(ce -> {
//				return ce.getId().equals(cajaEntity.getId());
//			}).forEach(caja -> {
//				cajaEntity.setId(caja.getId());
//				cajaEntity.setNombre(caja.getNombre());
//			});
//		});
//
//		Set<CajaEntity> listaCajasParaBorrar = listaCajasEntities.stream()
//				.filter(Predicate
//						.not(cajaEntity -> listaCajas.contains(cajaConverter.convert(cajaEntity))))
//				.collect(Collectors.toSet());
//
//		sucursalEntity.getListaCajas().removeAll(listaCajasParaBorrar);
//
//		Set<Caja> listaCajasActuales = sucursalEntity.getListaCajas().stream()
//				.map(cajaConverter::convert).collect(Collectors.toSet());
//
//		Set<CajaEntity> listaCajasParaAgregar = listaCajas.stream()
//				.filter(Predicate.not(listaCajasActuales::contains)).map(caja -> {
//					CajaEntity cajaEntity = cajaEntityConverter.convert(caja);
//					cajaEntity.setSucursal(sucursalEntity);
//					return cajaEntity;
//				}).collect(Collectors.toSet());
//		listaCajasParaAgregar.forEach(sucursalEntity.getListaCajas()::add);
		
		return sucursalEntity;
	}

}
