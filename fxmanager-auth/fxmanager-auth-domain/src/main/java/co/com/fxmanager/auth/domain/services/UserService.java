package co.com.fxmanager.auth.domain.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import co.com.fxmanager.auth.domain.entities.User;
import co.com.fxmanager.auth.domain.services.constants.MessageConstants;
import co.com.fxmanager.auth.persistence.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class UserService {

	@Getter(value = AccessLevel.PROTECTED)
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;

	public UserService(@NonNull UserRepository userRepository,@NonNull PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder=passwordEncoder;
	}

	public User save(User user) {
		checkDataCreateRequired(user);
		user.setSalt(String.valueOf(System.nanoTime()));
		user.setPassword(getPasswordEncoder().encode(user.getPassword(), user.getSalt()));
		return getUserRepository().create(user);
	}

	public User update(String username, User user) {
		checkUserExist(username);
		checkDataRequired(user);
		if(StringUtils.isNoneBlank(user.getPassword())) {
			user.setPassword(getPasswordEncoder().encode(user.getPassword(), user.getSalt()));
		}
		user.setSalt(String.valueOf(System.nanoTime()));
		return getUserRepository().update(username, user);
	}

	public void delete(String username) {
		checkUserExist(username);
		getUserRepository().delete(username);
	}

	protected User checkUserExist(String username) {
		Optional<User> user = getUserRepository().getUser(username);
		if (user.isEmpty()) {
			throw new IllegalArgumentException(MessageConstants.USER_DOESNT_EXIST);
		}
		return user.get();
	}

	protected void checkDataRequired(User user) {
		if (StringUtils.isBlank(user.getUsername())) {
			throw new IllegalArgumentException(MessageConstants.USER_USUERNAME_REQUIRED);
		}
	}
	
	protected void checkDataCreateRequired(User user) {
		checkDataRequired(user);
		if (StringUtils.isBlank(user.getPassword())) {
			throw new IllegalArgumentException(MessageConstants.USER_PASSWORD_REQUIRED);
		}
	}

	public Optional<User> getUser(String username) {
		if (StringUtils.isBlank(username)) {
			throw new IllegalArgumentException(MessageConstants.USER_USUERNAME_REQUIRED);
		}
		return getUserRepository().getUser(username);
	}

	public List<User> getUsers() {
		return getUserRepository().getList();
	}
}
