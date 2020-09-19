package co.com.fxmanager.auth.persistence.repositories.facades;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.auth.domain.entities.Permission;
import co.com.fxmanager.auth.persistence.entities.jpa.PermissionEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.converters.PermissionConverter;
import co.com.fxmanager.auth.persistence.repositories.PermissionRepository;
import co.com.fxmanager.auth.persistence.repositories.springdata.PermissionSpringRepository;
import lombok.Getter;

@Getter
@Service
public class PermissionFacadeRepository implements PermissionRepository {

	@Autowired
	private PermissionSpringRepository permissionSpringRepository;

	@Autowired
	private PermissionConverter permissionConverter;

	@Override
	public Permission create(Permission domainEntity) {
		return null;
	}

	@Override
	public List<Permission> getList(int first, int max) {
		return null;
	}

	@Override
	public List<Permission> getPermissionsWithRolesByResource() {
		List<PermissionEntity> permissions = permissionSpringRepository.getPermissionsWithRolesByResource();
		return permissions.stream().map(permissionConverter::convert).collect(Collectors.toList());

	}

}
