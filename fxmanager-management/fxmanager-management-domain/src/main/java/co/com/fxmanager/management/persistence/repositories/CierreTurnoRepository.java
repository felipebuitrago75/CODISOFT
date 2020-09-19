package co.com.fxmanager.management.persistence.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import co.com.fxmanager.management.domain.entities.CierreTurno;
import co.com.fxmanager.management.domain.entities.PreCierre;

public interface CierreTurnoRepository extends AbstractRepository<CierreTurno> {
	
	public CierreTurno update(Long id, CierreTurno cierreTurno);

	public Optional<CierreTurno> getCierreTurno(Long id);
	
	public PreCierre createYDevolverPrecierre(CierreTurno cierreTurno);
	
	public void cancelarPrecierre(Long idTurno);

	public PreCierre createRegistroArqueo(PreCierre preCierre);

	public List<PreCierre> arqueosHistoricos(LocalDate fecha);

	public Optional<PreCierre> getArqueosHistoricos(Long id);

}
