package co.com.fxmanager.management.domain.services;

import java.util.List;
import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.domain.services.constants.MessageConstants;
import co.com.fxmanager.management.domain.services.exceptions.ValidationException;
import co.com.fxmanager.management.persistence.repositories.TurnoRepository;
import lombok.Getter;
import lombok.NonNull;

public class TurnoService {

	@Getter
	private TurnoRepository turnoRepository;

	public TurnoService(@NonNull TurnoRepository turnoRepository) {
		super();
		this.turnoRepository = turnoRepository;
	}

	public Turno save(@NonNull Turno turno) {
		checkDataRequired(turno);
		return getTurnoRepository().create(turno);
	}

	public Turno update(@NonNull Long id,@NonNull Turno turno) {
		checkTurnoExist(id);
		checkDataRequired(turno);
		return getTurnoRepository().update(id, turno);
	}

	public void delete(@NonNull Long id) {		
		checkTurnoExist(id);
		getTurnoRepository().delete(id);
	}

	protected Turno checkTurnoExist(Long id) {
		Optional<Turno> turno = getTurnoRepository().getTurno(id);
		return turno.orElseThrow( () -> new ValidationException(MessageConstants.CAJA_DOESNT_EXIST));
				
	}

	//TODO: validar los datos obligatorios
	protected void checkDataRequired(Turno turno) {
		
	}
	
	public List<Turno> getTurnos(Integer first, Integer max){
		return this.getTurnoRepository().getList(first, max);
	}
	
	public Optional<Turno> getTurno(Long id){
		return this.getTurnoRepository().getTurno(id);
	}

	public Optional<Turno> obtenerTurnoActivo(String nombreUsuario) {
		return this.getTurnoRepository().obtenerTurnoActivo(nombreUsuario);
	}

	public List<Turno> getTurnosSucursal(Long idSucursal) {
		return this.turnoRepository.getTurnosSucursal(idSucursal);
	}
}
