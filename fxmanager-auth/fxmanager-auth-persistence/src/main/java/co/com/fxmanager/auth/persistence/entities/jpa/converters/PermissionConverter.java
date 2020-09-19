package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.AccessType;
import co.com.fxmanager.auth.domain.entities.Functionality;
import co.com.fxmanager.auth.domain.entities.Permission;
import co.com.fxmanager.auth.domain.entities.Role;
import co.com.fxmanager.auth.persistence.entities.jpa.PermissionEntity;
import lombok.Getter;

@Getter
@Component
public class PermissionConverter implements Converter<PermissionEntity, Permission> {

	@Autowired
	private RoleConverter roleConverter;

	@Autowired
	private FunctionalityConverter functionalityConverter;	

	@Override
	public Permission convert(PermissionEntity permissionEntity) {
		Role role = getRoleConverter().convert(permissionEntity.getRole());
		Functionality functionality = getFunctionalityConverter().convert(permissionEntity.getFunctionality());
		List<AccessType> accessTypes = Pattern.compile(",").splitAsStream(permissionEntity.getAccessTypes())
				.map(String::trim).map(AccessType::valueOf).collect(Collectors.toList());
		return new Permission(role, functionality, accessTypes);
	}

}
