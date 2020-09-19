package co.com.fxmanager.auth.domain.services.oauth2;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.fxmanager.auth.domain.entities.User;
import co.com.fxmanager.auth.domain.services.UserService;
import co.com.fxmanager.auth.domain.services.constants.MessageConstants;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;


	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> userOptional = userService.getUser(username);
		User user = userOptional.orElseThrow(() -> new UsernameNotFoundException(MessageConstants.USER_DOESNT_EXIST));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority(user.getRole().getName())));
	}

}
