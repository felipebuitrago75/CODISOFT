package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.AccessType;
import co.com.fxmanager.auth.domain.entities.Permission;
import co.com.fxmanager.auth.domain.entities.Resource;
import co.com.fxmanager.auth.domain.entities.Role;
import co.com.fxmanager.auth.persistence.entities.jpa.FunctionalityEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.PermissionEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.ResourceEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.RoleEntity;
import co.com.fxmanager.auth.persistence.repositories.FunctionalityRepository;
import co.com.fxmanager.auth.persistence.repositories.PermissionRepository;
import co.com.fxmanager.auth.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.auth.persistence.repositories.springdata.FunctionalitySpringRepository;
import lombok.Getter;

@Getter
@Component
public class RoleEntityConverter implements EntityConverter<Role, RoleEntity> {

	@Autowired
	private PermissionConverter permissionConverter;

	@Autowired
	private PermissionEntityConverter permissionEntityConverter;

	@Autowired
	private FunctionalitySpringRepository functionalityRepository;

	@Override
	public RoleEntity convert(Role user) {
		return convert(user, RoleEntity::new);
	}

	@Override
	public RoleEntity convert(Role role, Supplier<RoleEntity> supplier) {
		RoleEntity roleEntity = supplier.get();
		roleEntity.setName(role.getName());

		List<Permission> permissions = role.getPermissions();
		List<PermissionEntity> permissionEntities = roleEntity.getPermissions();
		if (permissionEntities == null) {
			permissionEntities = new ArrayList<>();
			roleEntity.setPermissions(permissionEntities);
		}

		List<PermissionEntity> permisosParaEditar = permissionEntities.stream()
				.filter(permissionEntity -> permissions.contains(getPermissionConverter().convert(permissionEntity)))
				.collect(Collectors.toList());
		permisosParaEditar.forEach(permission -> {
			Optional<PermissionEntity> permisoEntity = permissions.stream().filter(p->{
				return p.getFunctionality().getName().equals(permission.getFunctionality().getName());
			})
					.map(getPermissionEntityConverter()::convert).findFirst();
			permisoEntity.ifPresent(p -> {
				permission.setAccessTypes(p.getAccessTypes());
			});
		});

		List<PermissionEntity> permisosParaBorrar = permissionEntities.stream()
				.filter(Predicate.not(
						permissionEntity -> permissions.contains(getPermissionConverter().convert(permissionEntity))))
				.collect(Collectors.toList());

		roleEntity.getPermissions().removeAll(permisosParaBorrar);

		List<Permission> resourcesActuales = permissionEntities.stream().map(getPermissionConverter()::convert)
				.collect(Collectors.toList());

		List<PermissionEntity> permisosParaAgregar = permissions.stream()
				.filter(Predicate.not(permission -> resourcesActuales.contains(permission))).map(permission -> {
					PermissionEntity permissionEntity = getPermissionEntityConverter().convert(permission);
					permissionEntity.setRole(roleEntity);
					return permissionEntity;
				}).collect(Collectors.toList());
		roleEntity.getPermissions().addAll(permisosParaAgregar);

		return roleEntity;
	}

}
