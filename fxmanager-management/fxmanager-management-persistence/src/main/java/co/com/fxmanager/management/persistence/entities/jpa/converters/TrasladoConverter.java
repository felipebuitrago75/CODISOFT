package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.DenominacionCantidad;
import co.com.fxmanager.management.domain.entities.Fx;
import co.com.fxmanager.management.domain.entities.Sucursal;
import co.com.fxmanager.management.domain.entities.Traslado;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.persistence.entities.jpa.TrasladoEntity;
import lombok.Getter;

@Component
@Getter
public class TrasladoConverter implements Converter<TrasladoEntity, Traslado> {

	@Autowired
	private SucursalConverter sucursalConverter;
	
	@Autowired
	private FxConverter fxConverter;
	
	public TrasladoConverter() {
		super();
	}

	@Override
	public Traslado convert(TrasladoEntity trasladoEntity) {
		Traslado traslado;
		Sucursal sucursalDestino = sucursalConverter.convert(trasladoEntity.getSucursalDestino());
		Sucursal sucursalOrigen = sucursalConverter.convert(trasladoEntity.getSucursalOrigen());
		Fx fx = fxConverter.convert(trasladoEntity.getFx());
		
		traslado= new Traslado(trasladoEntity.getId(), trasladoEntity.getFecha(), sucursalOrigen, 
				sucursalDestino, trasladoEntity.getTipoTraslado(), trasladoEntity.getEstado(),
				trasladoEntity.getPrecioTraslado(), trasladoEntity.getValorGiro(), fx);
		
		
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		if (persistenceUtil.isLoaded(trasladoEntity, TrasladoEntity.ClassInfo.TURNO_ORIGEN) && trasladoEntity.getTurnoOrigen()!=null ) {
			Turno turnoOrigen = new Turno(trasladoEntity.getTurnoOrigen().getId(), trasladoEntity.getTurnoOrigen().getCaja().getId(),
					trasladoEntity.getTurnoOrigen().getUsuario().getId(), trasladoEntity.getTurnoOrigen().getFechaInicio(), trasladoEntity.getTurnoOrigen().getUsuario().getNombre()+" " +trasladoEntity.getTurnoOrigen().getUsuario().getApellido());
			if(trasladoEntity.getTurnoOrigen().getFechaFin() !=null) {
				turnoOrigen.setFechaFin(trasladoEntity.getTurnoOrigen().getFechaFin());
			}
			traslado.setTurnoOrigen(turnoOrigen);
		}
		
		if (persistenceUtil.isLoaded(trasladoEntity, TrasladoEntity.ClassInfo.TURNO_DESTINO) && trasladoEntity.getTurnoDestino()!=null ) {
			Turno turnoDestino = new Turno(trasladoEntity.getTurnoDestino().getId(), trasladoEntity.getTurnoDestino().getCaja().getId(),
					trasladoEntity.getTurnoDestino().getUsuario().getId(), trasladoEntity.getTurnoDestino().getFechaInicio(),  trasladoEntity.getTurnoDestino().getUsuario().getNombre()+" " +trasladoEntity.getTurnoDestino().getUsuario().getApellido());
			if(trasladoEntity.getTurnoDestino().getFechaFin() !=null) {
				turnoDestino.setFechaFin(trasladoEntity.getTurnoDestino().getFechaFin());
			}
			traslado.setTurnoOrigen(turnoDestino);
		}
		
		if (persistenceUtil.isLoaded(trasladoEntity, TrasladoEntity.ClassInfo.DENOMINACIONES_CANTIDAD)) {
			List<DenominacionCantidad> denominacionesCantidad = trasladoEntity.getDenominacionesCantidad().stream().map( denominacionCantidadEntity ->{
				return new DenominacionCantidad(denominacionCantidadEntity.getCantidad(), denominacionCantidadEntity.getDenominacion().getValor(), denominacionCantidadEntity.getDenominacion().getFx().getId());
			}).collect(Collectors.toList());
				
			traslado.setDenominacionesYCantidades(denominacionesCantidad);
		}
		
		return traslado;		

	}

}
