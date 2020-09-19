package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.AccessType;
import co.com.fxmanager.auth.domain.entities.Functionality;
import co.com.fxmanager.auth.domain.entities.Permission;
import co.com.fxmanager.auth.domain.entities.Resource;
import co.com.fxmanager.auth.domain.entities.Role;
import co.com.fxmanager.auth.persistence.entities.jpa.FunctionalityEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.PermissionEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.RoleEntity;
import lombok.Getter;

@Component
@Getter
public class RoleConverter implements Converter<RoleEntity, Role> {

	@Autowired
	private PermissionConverter permissionConverter;
	
	@Autowired
	private FunctionalityConverter functionalityConverter;	
	
	public RoleConverter() {
		super();
	}

	@Override
	public Role convert(RoleEntity roleEntity) {
		Role role = new Role(roleEntity.getName());
		
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		if (persistenceUtil.isLoaded(roleEntity, RoleEntity.ClassInfo.PERMISSIONS)) {
			List<Permission> permissions = roleEntity.getPermissions().stream().map(permissionEntity->{
				Functionality functionality = getFunctionalityConverter().convert(permissionEntity.getFunctionality());
				List<AccessType> accessTypes = Pattern.compile(",").splitAsStream(permissionEntity.getAccessTypes())
						.map(String::trim).map(AccessType::valueOf).collect(Collectors.toList());
				return new Permission(new Role(roleEntity.getName()), functionality, accessTypes);
			}).collect(Collectors.toList());
			role.setPermissions(permissions);
		}
		
		return role;
	}


}
