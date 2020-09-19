package co.com.fxmanager.management.persistence.repositories;

import java.util.Optional;

import co.com.fxmanager.management.domain.entities.AperturaTurno;

public interface AperturaTurnoRepository extends AbstractRepository<AperturaTurno> {
	
	public AperturaTurno update(Long id, AperturaTurno aperturaTurno);

	public Optional<AperturaTurno> getAperturaTurno(Long id);
	
}
