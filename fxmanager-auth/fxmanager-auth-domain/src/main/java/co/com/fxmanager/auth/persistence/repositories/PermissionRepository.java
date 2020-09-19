package co.com.fxmanager.auth.persistence.repositories;

import java.util.List;

import co.com.fxmanager.auth.domain.entities.Permission;

public interface PermissionRepository extends AbstractRepository<Permission> {

	public List<Permission> getPermissionsWithRolesByResource();

}
