package co.com.fxmanager.management.persistence.entities.jpa.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.persistence.entities.jpa.TurnoEntity;
import lombok.Getter;

@Component
@Getter
public class TurnoConverter implements Converter<TurnoEntity, Turno> {

	@Autowired
	private UsuarioConverter usuarioConverter;
	
	@Autowired
	private CajaConverter cajaConverter;
	
	
	public TurnoConverter() {
		super();
	}

	@Override
	public Turno convert(TurnoEntity turnoEntity) {
		
		Turno turno = new Turno(turnoEntity.getId(), turnoEntity.getCaja().getId(), 
				turnoEntity.getUsuario().getId(), 
				turnoEntity.getFechaInicio(), turnoEntity.getUsuario().getNombre());
		
		turno.setFechaFin(turnoEntity.getFechaFin()!=null?turnoEntity.getFechaFin():null);
		turno.setNombreUsuario(turnoEntity.getUsuario()!=null?turnoEntity.getUsuario().getNombre():null);
		return turno;
	}

}
