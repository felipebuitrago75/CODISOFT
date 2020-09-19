package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.AccessType;
import co.com.fxmanager.auth.domain.entities.Permission;
import co.com.fxmanager.auth.persistence.entities.jpa.FunctionalityEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.PermissionEntity;
import co.com.fxmanager.auth.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.auth.persistence.repositories.springdata.FunctionalitySpringRepository;
import lombok.Getter;

@Getter
@Component
public class PermissionEntityConverter implements EntityConverter<Permission, PermissionEntity> {

	@Autowired
	private FunctionalitySpringRepository functionalitySpringRepository;

	@Override
	public PermissionEntity convert(Permission permission) {
		return convert(permission, PermissionEntity::new);
	}

	@Override
	public PermissionEntity convert(Permission permission, Supplier<PermissionEntity> target) {
		PermissionEntity permissionEntity = target.get();
		if (!Objects.isNull(permission.getAccessTypes())) {
			String accessTypes = permission.getAccessTypes().stream().map(AccessType::name)
					.collect(Collectors.joining(","));
			permissionEntity.setAccessTypes(accessTypes);
		}
		Optional<FunctionalityEntity> functionalityEntity = getFunctionalitySpringRepository().findByName(permission.getFunctionality().getName());
		permissionEntity.setFunctionality(functionalityEntity.orElseThrow(NoFoundDataException::new));
		return permissionEntity;
	}

}
