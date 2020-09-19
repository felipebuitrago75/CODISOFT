package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import java.util.Optional;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.User;
import co.com.fxmanager.auth.persistence.entities.jpa.RoleEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.UserEntity;
import co.com.fxmanager.auth.persistence.repositories.springdata.RoleSpringRepository;
import co.com.fxmanager.auth.persistence.repositories.springdata.UserSpringRepository;
import lombok.Getter;

@Getter
@Component
public class UserEntityConverter implements EntityConverter<User, UserEntity> {

	@Autowired
	private UserSpringRepository userSpringRepository;

	@Autowired
	private RoleSpringRepository roleSpringRepository;

	
	public UserEntityConverter() {
	}

	@Override
	public UserEntity convert(User user) {
		return convert(user, UserEntity::new);
	}

	@Override
	public UserEntity convert(User user, Supplier<UserEntity> supplier) {
		UserEntity userEntity = supplier.get();
		userEntity.setUsername(user.getUsername());
		userEntity.setEnabled(user.getEnabled());
		userEntity.setFailedLogins(user.getFailedLogins());
		userEntity.setLocked(user.getLocked());
		if(StringUtils.isNoneBlank(user.getPassword())) {
			userEntity.setPassword(user.getPassword());
		}
		if(StringUtils.isNoneBlank(user.getSalt())) {
			userEntity.setSalt(user.getSalt());
		}
		Optional<RoleEntity> roleOptional = getRoleSpringRepository().findByName(user.getRole().getName());
		roleOptional.ifPresent(userEntity::setRole);
		return userEntity;
	}

}
