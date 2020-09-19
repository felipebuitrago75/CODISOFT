package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.DenominacionCantidad;
import co.com.fxmanager.management.domain.entities.Operacion;
import co.com.fxmanager.management.domain.entities.ParFx;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.persistence.entities.jpa.OperacionEntity;
import lombok.Getter;

@Component
@Getter
public class OperacionConverter implements Converter<OperacionEntity, Operacion> {

	@Autowired
	private FxConverter fxConverter;
	
	@Autowired
	private TurnoConverter turnoConverter;
	
	public OperacionConverter() {
		super();
	}

	@Override
	public Operacion convert(OperacionEntity operacionEntity) {
		Operacion operacion;
	 
		
		ParFx parFx = new ParFx(fxConverter.convert(operacionEntity.getFxBase()), fxConverter.convert(operacionEntity.getFxOper()), 
				operacionEntity.getValorFxOperacion(), operacionEntity.getValorValoracion());
		
		if(operacionEntity.getIdSoipc() == null) {
			operacion = new Operacion(operacionEntity.getId(), parFx, operacionEntity.getFechaOperacion(),
					operacionEntity.getTipo(), operacionEntity.getNominal(), operacionEntity.getEstado());
		}else {
			operacion = new Operacion(operacionEntity.getId(), parFx, operacionEntity.getFechaOperacion(),
					operacionEntity.getTipo(), operacionEntity.getNominal(), operacionEntity.getEstado(), 
					operacionEntity.getIdSoipc());
		}
		
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		if (persistenceUtil.isLoaded(operacionEntity, OperacionEntity.ClassInfo.TURNO) && operacionEntity.getTurno()!=null ) {
			Turno turno = new Turno(operacionEntity.getTurno().getId(), operacionEntity.getTurno().getCaja().getId(),
					operacionEntity.getTurno().getUsuario().getId(), operacionEntity.getTurno().getFechaInicio() ,  operacionEntity.getTurno().getUsuario().getNombre()+" "+ operacionEntity.getTurno().getUsuario().getApellido());
			if(operacionEntity.getTurno().getFechaFin() !=null) {
				turno.setFechaFin(operacionEntity.getTurno().getFechaFin());
			}
			operacion.setTurno(turno);
		}
		
		if (persistenceUtil.isLoaded(operacionEntity, OperacionEntity.ClassInfo.TURNO_EDICION) && operacionEntity.getTurnoEdicion()!=null) {
			Turno turno = new Turno(operacionEntity.getTurnoEdicion().getId(), operacionEntity.getTurnoEdicion().getCaja().getId(),
					operacionEntity.getTurnoEdicion().getUsuario().getId(), operacionEntity.getTurnoEdicion().getFechaInicio() , operacionEntity.getTurno().getUsuario().getNombre()+" "+ operacionEntity.getTurno().getUsuario().getApellido());
			if(operacionEntity.getTurnoEdicion().getFechaFin() !=null) {
				turno.setFechaFin(operacionEntity.getTurnoEdicion().getFechaFin());
			}
			operacion.setTurnoEdicion(turno);
		}
		
		if (persistenceUtil.isLoaded(operacionEntity, OperacionEntity.ClassInfo.DENOMINACIONES_CANTIDAD_ENTREGADAS)) {
			List<DenominacionCantidad> denominacionesCantidadEntregadas = operacionEntity.getDenominacionesCantidadEntregadas().stream().map( denominacionCantidadEntity ->{
				return new DenominacionCantidad(denominacionCantidadEntity.getCantidad(), denominacionCantidadEntity.getDenominacion().getValor(), denominacionCantidadEntity.getDenominacion().getFx().getId());
			}).collect(Collectors.toList());
				
			operacion.setDenominacionesYCantidadesEntregadas(denominacionesCantidadEntregadas);
		}
		
		
		if (persistenceUtil.isLoaded(operacionEntity, OperacionEntity.ClassInfo.DENOMINACIONES_CANTIDAD_RECIBIDAS)) {
			List<DenominacionCantidad> denominacionesCantidadRecibidas = operacionEntity.getDenominacionesCantidadRecibidas().stream().map( denominacionCantidadEntity ->{
				return new DenominacionCantidad(denominacionCantidadEntity.getCantidad(), denominacionCantidadEntity.getDenominacion().getValor(), denominacionCantidadEntity.getDenominacion().getFx().getId());
			}).collect(Collectors.toList());
				
			operacion.setDenominacionesYCantidadesRecibidas(denominacionesCantidadRecibidas);
		}
		
		if(operacionEntity.getDescripcion() != null) {
			operacion.setDescripcion(operacionEntity.getDescripcion());
		}
		
		return operacion;		

	}

}
