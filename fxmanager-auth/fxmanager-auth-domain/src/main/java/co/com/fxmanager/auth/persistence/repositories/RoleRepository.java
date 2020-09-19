package co.com.fxmanager.auth.persistence.repositories;

import java.util.Optional;

import co.com.fxmanager.auth.domain.entities.Role;

public interface RoleRepository extends AbstractRepository<Role> {

	public Role update(String name, Role role);

	public void delete(String name);

	public Optional<Role> getRole(String name);
}
