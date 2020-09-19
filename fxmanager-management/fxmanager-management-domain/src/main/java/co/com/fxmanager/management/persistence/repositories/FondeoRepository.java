package co.com.fxmanager.management.persistence.repositories;

import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Fondeo;

public interface FondeoRepository extends AbstractRepository<Fondeo> {

	public Fondeo update(Long id, Fondeo fondeo);

	public void delete(Long id);

	public Optional<Fondeo> getFondeo(Long id);
	
}
