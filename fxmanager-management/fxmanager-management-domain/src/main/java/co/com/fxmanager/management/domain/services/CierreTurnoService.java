package co.com.fxmanager.management.domain.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import co.com.fxmanager.management.domain.entities.CierreTurno;
import co.com.fxmanager.management.domain.entities.PreCierre;
import co.com.fxmanager.management.domain.services.constants.MessageConstants;
import co.com.fxmanager.management.domain.services.exceptions.ValidationException;
import co.com.fxmanager.management.persistence.repositories.CierreTurnoRepository;
import lombok.Getter;
import lombok.NonNull;

public class CierreTurnoService {
	
	@Getter
	private CierreTurnoRepository  cierreTurnoRepository;
	
	
	public CierreTurnoService (@NonNull CierreTurnoRepository  cierreTurnoRepository) {
		super();
		this.cierreTurnoRepository = cierreTurnoRepository;
	}
	
	public CierreTurno save(@NonNull CierreTurno cierreTurno) {
		checkDataRequired(cierreTurno);
		return getCierreTurnoRepository().create(cierreTurno);
	}

	public CierreTurno update(@NonNull Long id, CierreTurno cierreTurno) {
		
		checkDataRequired(cierreTurno);
		return getCierreTurnoRepository().update(id, cierreTurno);
	}
	
	protected CierreTurno checkTurnoExist(Long id) {
		Optional<CierreTurno> cierreTurno = getCierreTurnoRepository().getCierreTurno(id);
		return cierreTurno.orElseThrow( () -> new ValidationException(MessageConstants.CIERRE_TURNO_DOESNT_EXIST));
				
	}

	protected void checkDataRequired(CierreTurno cierreTurno) {
		
	}
	
	public List<CierreTurno> getCierreTurnos(Integer first, Integer max){
		return this.getCierreTurnoRepository().getList(first, max);
	}
	
	public Optional<CierreTurno> getCierreTurno(Long id){
		return this.getCierreTurnoRepository().getCierreTurno(id);
	}

	public PreCierre createYDevolverPrecierre(CierreTurno cierreTurno) {
		return this.getCierreTurnoRepository().createYDevolverPrecierre(cierreTurno);
	}
	
	public void cancelarPrecierre(Long idTurno) {
		this.getCierreTurnoRepository().cancelarPrecierre(idTurno);
	}

	public PreCierre createRegistroArqueo(PreCierre preCierre) {
		return this.getCierreTurnoRepository().createRegistroArqueo(preCierre);
	}

	public List<PreCierre> arqueosHistoricos(LocalDate fecha) {
		return this.getCierreTurnoRepository().arqueosHistoricos(fecha);
	}

	public Optional<PreCierre> getArqueosHistoricos(Long id) {
		return this.getCierreTurnoRepository().getArqueosHistoricos(id);
	}
	
	
}
