package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.Traslado;
import co.com.fxmanager.management.persistence.entities.jpa.DenominacionCantidadEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TrasladoEntity;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.DenominacionSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.FxSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.SucursalSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Component
public class TrasladoEntityConverter implements EntityConverter<Traslado, TrasladoEntity> {

	@Autowired
	private FxEntityConverter fxEntityConverter;

	@Autowired
	private FxSpringRepository fxSpringRepository;
	
	@Autowired
	private SucursalSpringRepository sucursalSpringRepository;

	@Autowired TurnoSpringRepository turnoSpringRepository;
	

	@Autowired
	DenominacionSpringRepository denominacionSpringRepository;
	
	@Override
	public TrasladoEntity convert(Traslado traslado) {
		return convert(traslado, TrasladoEntity::new);
	}

	@Override
	public TrasladoEntity convert(Traslado traslado, Supplier<TrasladoEntity> supplier) {
		TrasladoEntity trasladoEntity = supplier.get();
		
		if(traslado.getId()!=null)
		trasladoEntity.setId(traslado.getId());
		
		trasladoEntity.setFecha(traslado.getFecha());
		trasladoEntity.setEstado(traslado.getEstado());
		trasladoEntity.setPrecioTraslado(traslado.getPrecioTraslado());
		trasladoEntity.setTipoTraslado(traslado.getTipoTraslado());
		trasladoEntity.setValorGiro(traslado.getValorGiro());

		trasladoEntity.setFx(getFxSpringRepository().findByCodigo(traslado.getFx().getCodigo())
				.orElseThrow(NoFoundDataException::new));
		
		trasladoEntity.setSucursalOrigen(getSucursalSpringRepository().findByCodigo(traslado.getSucursalOrigen().getCod())
				.orElseThrow(NoFoundDataException::new));
		
		trasladoEntity.setSucursalDestino(getSucursalSpringRepository().findByCodigo(traslado.getSucursalDestino().getCod())
				.orElseThrow(NoFoundDataException::new));
		
		
		trasladoEntity.setTurnoOrigen(getTurnoSpringRepository().findById(traslado.getTurnoOrigen().getId())
				.orElseThrow(NoFoundDataException::new)); 
		
		if(traslado.getTurnoDestino().isPresent()) {
			trasladoEntity.setTurnoDestino(getTurnoSpringRepository().findById(traslado.getTurnoDestino().get().getId())
				.orElse(null)); 
		}

		Set<DenominacionCantidadEntity> denominacionesCantidadEntregadas = traslado
				.getDenominacionesYCantidades().stream().map(denominacionCantidad -> {
					DenominacionCantidadEntity denominacionCantidadEntity = new DenominacionCantidadEntity();
					denominacionCantidadEntity.setCantidad(denominacionCantidad.getCantidad());
					denominacionCantidadEntity.setDenominacion(denominacionSpringRepository
							.obtenerDenominacionPorFxyValor(denominacionCantidad.getIdFx(), denominacionCantidad.getValor()).orElseThrow(NoFoundDataException::new));
					denominacionCantidadEntity.setTraslado(trasladoEntity);
					denominacionCantidadEntity.setTipo("SALIDA");
					return denominacionCantidadEntity;
				}).collect(Collectors.toSet());
		trasladoEntity.setDenominacionesCantidad(denominacionesCantidadEntregadas);
		
		
		return trasladoEntity;
	}

}
