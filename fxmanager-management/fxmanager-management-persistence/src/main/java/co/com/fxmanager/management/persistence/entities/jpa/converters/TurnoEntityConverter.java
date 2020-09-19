package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.persistence.entities.jpa.TurnoEntity;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.CajaSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.UsuarioSpringRepository;
import lombok.Getter;

@Getter
@Component
public class TurnoEntityConverter implements EntityConverter<Turno, TurnoEntity> {

	
	@Autowired
	private CajaSpringRepository cajaSpringRepository;
	
	@Autowired
	private UsuarioSpringRepository usuarioSpringRepository;
		
	@Override
	public TurnoEntity convert(Turno turno) {
		return convert(turno, TurnoEntity::new);
	}

	@Override
	public TurnoEntity convert(Turno turno, Supplier<TurnoEntity> supplier) {
		TurnoEntity turnoEntity = supplier.get();

		if(turno.getId()!=null) {
			turnoEntity.setId(turno.getId());
		}
		turnoEntity.setFechaFin(turno.getFechaFin().orElse(null));
		turnoEntity.setFechaInicio(turno.getFechaInicio());
		
		turnoEntity.setCaja(cajaSpringRepository.findById(turno.getIdCaja())
				.orElseThrow(NoFoundDataException::new));
		
		turnoEntity.setUsuario(usuarioSpringRepository.findByNombre(turno.getNombreUsuario())
				.orElseThrow(NoFoundDataException::new));
		return turnoEntity;
	}

}
