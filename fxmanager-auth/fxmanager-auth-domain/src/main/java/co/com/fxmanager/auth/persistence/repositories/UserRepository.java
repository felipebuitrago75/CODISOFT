package co.com.fxmanager.auth.persistence.repositories;

import java.util.Optional;

import co.com.fxmanager.auth.domain.entities.User;

public interface UserRepository extends AbstractRepository<User> {

	public User update(String username,User user);

	public void delete(String username);

	public Optional<User> getUser(String username);

}
