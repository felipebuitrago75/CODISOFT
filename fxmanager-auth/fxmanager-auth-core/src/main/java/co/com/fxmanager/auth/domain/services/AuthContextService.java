package co.com.fxmanager.auth.domain.services;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.Role;
import co.com.fxmanager.auth.domain.entities.User;

@Component
public class AuthContextService implements AuthService {

	@Override
	public Optional<User> getCurrentUser() {
		User user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {			
			Optional<Role> roleOptional = authentication.getAuthorities().stream().map(auth -> {
				return new Role(auth.getAuthority());
			}).findFirst();
			if (roleOptional.isPresent()) {
				user = new User(authentication.getName(), StringUtils.EMPTY, StringUtils.EMPTY, roleOptional.get());
			}
		}
		return Optional.ofNullable(user);
	}
}
