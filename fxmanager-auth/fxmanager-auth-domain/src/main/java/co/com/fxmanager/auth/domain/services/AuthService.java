package co.com.fxmanager.auth.domain.services;

import java.util.Optional;

import co.com.fxmanager.auth.domain.entities.User;

public interface AuthService {

	public Optional<User> getCurrentUser();
	
}
