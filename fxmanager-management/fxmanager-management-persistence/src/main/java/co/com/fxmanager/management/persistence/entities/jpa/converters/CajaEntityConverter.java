package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.Caja;
import co.com.fxmanager.management.domain.entities.Saldo;
import co.com.fxmanager.management.persistence.entities.jpa.CajaEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SaldoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SucursalEntity;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.SucursalSpringRepository;
import lombok.Getter;

@Getter
@Component
public class CajaEntityConverter implements EntityConverter<Caja, CajaEntity> {

	@Autowired
	private SaldoEntityConverter saldoEntityConverter;

	@Autowired
	private SaldoConverter saldoConverter;

	@Autowired
	private SucursalSpringRepository sucursalSpringRepository;
	
		
	@Override
	public CajaEntity convert(Caja caja) {
		return convert(caja, CajaEntity::new);
	}

	@Override
	public CajaEntity convert(Caja caja, Supplier<CajaEntity> supplier) {
		CajaEntity cajaEntity = supplier.get();

		if(caja.getId()!=null) {
			cajaEntity.setId(caja.getId());
		}
		cajaEntity.setNombre(caja.getNombre());
		
		Optional<SucursalEntity> saldoEntityOptional = getSucursalSpringRepository().findByCodigo(caja.getCodigoSucursal());
		cajaEntity.setSucursal(saldoEntityOptional.orElseThrow(NoFoundDataException::new));
		
		
		List<Saldo> listaSaldos = caja.getListaSaldos();

		List<SaldoEntity> listaSaldosEntities = cajaEntity.getListaSaldos();
		if (listaSaldosEntities == null) {
			listaSaldosEntities = new ArrayList<>();
			cajaEntity.setListaSaldos(listaSaldosEntities);
		}

		
		cajaEntity.getListaSaldos().removeAll(listaSaldosEntities);
		
//		List<SaldoEntity> listaSaldosParaEditar = listaSaldosEntities.stream()
//				.filter(saldoEntity -> listaSaldos.contains(saldoConverter.convert(saldoEntity)))
//				.collect(Collectors.toList());
//
//		listaSaldosParaEditar.forEach((saldoEntity) -> {
//			listaSaldos.stream().map(saldoEntityConverter::convert).filter(s -> {
//				return s.getFx().getId().equals(saldoEntity.getFx().getId());
//			}).forEach(saldo -> {
//				saldoEntity.setNominal(saldo.getNominal());
//				saldoEntity.setPrecioPromedio(saldo.getPrecioPromedio());
//			});
//		});

//		List<SaldoEntity> listaSaldosParaBorrar = listaSaldosEntities.stream()
//				.filter(Predicate
//						.not(saldoEntity -> listaSaldos.contains(saldoConverter.convert(saldoEntity))))
//				.collect(Collectors.toList());
//
//		cajaEntity.getListaSaldos().removeAll(listaSaldosParaBorrar);

		List<Saldo> listaSaldosActuales = cajaEntity.getListaSaldos().stream()
				.map(saldoConverter::convert).collect(Collectors.toList());

		List<SaldoEntity> listaDeSaldosParaAgregar = listaSaldos.stream()
				.filter(Predicate.not(listaSaldosActuales::contains)).map(saldo -> {
					SaldoEntity saldoEntity = saldoEntityConverter.convert(saldo);
					saldoEntity.setCaja(cajaEntity);
					return saldoEntity;
				}).collect(Collectors.toList());
		listaDeSaldosParaAgregar.forEach(cajaEntity.getListaSaldos()::add);
		
		
		
		
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
		
		return cajaEntity;
	}

}
