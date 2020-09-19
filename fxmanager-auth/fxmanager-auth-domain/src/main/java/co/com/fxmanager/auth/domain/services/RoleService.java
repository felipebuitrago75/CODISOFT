package co.com.fxmanager.auth.domain.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import co.com.fxmanager.auth.domain.entities.Role;
import co.com.fxmanager.auth.domain.services.constants.MessageConstants;
import co.com.fxmanager.auth.domain.services.exceptions.ValidationException;
import co.com.fxmanager.auth.persistence.repositories.RoleRepository;
import lombok.Getter;
import lombok.NonNull;

public class RoleService {

	@Getter
	private RoleRepository roleRepository;

	public RoleService(@NonNull RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	public Role save(@NonNull Role role) {
		checkDataRequired(role);
		return getRoleRepository().create(role);
	}

	public Role update(@NonNull String name,@NonNull Role role) {
		checkRoleExist(name);
		checkDataRequired(role);
		return getRoleRepository().update(name, role);
	}

	public void delete(@NonNull String name) {		
		checkRoleExist(name);
		getRoleRepository().delete(name);
	}

	protected Role checkRoleExist(String name) {
		Optional<Role> role = getRoleRepository().getRole(name);		
		return role.orElseThrow(()->new ValidationException(MessageConstants.ROLE_DOESNT_EXIST));
	}

	protected void checkDataRequired(Role role) {
		if (StringUtils.isBlank(role.getName())) {
			throw new ValidationException(MessageConstants.ROLE_NAME_REQUIRED);
		}
	}
	
	public List<Role> getRoles(Integer first, Integer max){
		return this.getRoleRepository().getList(first, max);
	}
	
	public Optional<Role> getRole(String name){
		return this.getRoleRepository().getRole(name);
	}
}
