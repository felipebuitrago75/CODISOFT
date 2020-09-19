package co.com.fxmanager.auth.persistence.repositories;

import java.util.Optional;

import co.com.fxmanager.auth.domain.entities.Resource;

public interface ResourceRepository extends AbstractRepository<Resource> {

	public Resource update(String name,Resource resource);

	public void delete(String name);

	public Optional<Resource> getResource(String name);	
}
