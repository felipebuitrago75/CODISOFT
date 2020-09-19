package co.com.fxmanager.auth.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.auth.domain.services.UserService;
import co.com.fxmanager.auth.persistence.repositories.UserRepository;

@Service
public class UserSpringService extends UserService {

	@Autowired
	public UserSpringService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super(userRepository, passwordEncoder);
	}

	

}
