package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.Role;
import co.com.fxmanager.auth.domain.entities.User;
import co.com.fxmanager.auth.persistence.entities.jpa.UserEntity;
import lombok.Getter;

@Component
public class UserConverter implements Converter<UserEntity, User> {

	@Autowired
	@Getter
	private RoleConverter roleConverter;

	
	public UserConverter() {
	}

	@Override
	public User convert(UserEntity userEntity) {
		Role role = getRoleConverter().convert(userEntity.getRole());
		User user = new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getSalt(), role);
		user.setEnabled(userEntity.getEnabled());
		user.setFailedLogins(userEntity.getFailedLogins());
		user.setLocked(userEntity.getLocked());
		return user;
	}

}
