package co.com.fxmanager.auth.persistence.repositories;

import java.util.Optional;

import co.com.fxmanager.auth.domain.entities.Functionality;

public interface FunctionalityRepository extends AbstractRepository<Functionality> {

	public Functionality update(String name, Functionality functionality);

	public void delete(String name);

	public Optional<Functionality> getFunctionality(String name);
}