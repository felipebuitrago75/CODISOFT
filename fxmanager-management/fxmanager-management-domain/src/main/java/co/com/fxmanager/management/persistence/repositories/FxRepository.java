package co.com.fxmanager.management.persistence.repositories;

import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Fx;

public interface FxRepository extends AbstractRepository<Fx> {

	public Fx update(String name, Fx fx);

	public void delete(String name);

	public Optional<Fx> getFx(String name);
}
