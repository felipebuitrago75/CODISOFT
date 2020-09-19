package co.com.fxmanager.auth.persistence.repositories.jpa;

import java.util.List;

import co.com.fxmanager.auth.domain.entities.Permission;
import co.com.fxmanager.auth.persistence.entities.jpa.PermissionEntity;

public interface PermissionJPARepository {

	public List<PermissionEntity> getPermissionsWithRolesByResource();
	
}
