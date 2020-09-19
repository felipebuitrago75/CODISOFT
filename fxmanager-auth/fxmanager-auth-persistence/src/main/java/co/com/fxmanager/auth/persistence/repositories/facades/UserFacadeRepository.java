package co.com.fxmanager.auth.persistence.repositories.facades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.fxmanager.auth.domain.entities.User;
import co.com.fxmanager.auth.persistence.entities.jpa.UserEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.converters.UserConverter;
import co.com.fxmanager.auth.persistence.entities.jpa.converters.UserEntityConverter;
import co.com.fxmanager.auth.persistence.repositories.UserRepository;
import co.com.fxmanager.auth.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.auth.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.auth.persistence.repositories.springdata.UserSpringRepository;
import lombok.Getter;

@Getter
@Service
public class UserFacadeRepository implements UserRepository {

	private UserSpringRepository userSpringRepository;

	private UserEntityConverter userEntityConverter;

	private UserConverter userConverter;

	@Autowired
	public UserFacadeRepository(UserSpringRepository userSpringRepository, UserConverter userConverter,
			UserEntityConverter userEntityConverter) {
		super();
		this.userSpringRepository = userSpringRepository;
		this.userConverter = userConverter;
		this.userEntityConverter = userEntityConverter;
	}

	@Transactional
	@Override
	public User create(User user) {
		UserEntity userEntity = getUserEntityConverter().convert(user);
		UserEntity newUserEntity = getUserSpringRepository().save(userEntity);
		User newUser = getUserConverter().convert(newUserEntity);
		return newUser;
	}

	@Transactional
	@Override
	public User update(String username, User user) {
		User newUser;
		try {
			UserEntity userEntity = getUserEntityConverter().convert(user, () -> {
				Optional<UserEntity> userEntityOptioanal = getUserSpringRepository().findByUsername(username);
				return userEntityOptioanal.orElseThrow(NoFoundDataException::new);
			});
			UserEntity newUserEntity = getUserSpringRepository().save(userEntity);
			newUser = getUserConverter().convert(newUserEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newUser;
	}

	@Transactional
	@Override
	public void delete(String username) {
		try {
			Optional<UserEntity> userEntityOptioanal = getUserSpringRepository().findByUsername(username);
			userEntityOptioanal.ifPresent(getUserSpringRepository()::delete);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> getList(int first, int max) {
		List<User> users;
		try {
			Page<UserEntity> page = getUserSpringRepository().findAll(PageRequest.of(first, max));
			users = page.stream().map(userEntity -> getUserConverter().convert(userEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return users;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<User> getUser(String username) {
		Optional<User> user;
		try {
			Optional<UserEntity> userEntityOptioanal = getUserSpringRepository().findByUsername(username);
			user = userEntityOptioanal.stream().map(userEntity -> getUserConverter().convert(userEntity)).findFirst();
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return user;
	}

}
