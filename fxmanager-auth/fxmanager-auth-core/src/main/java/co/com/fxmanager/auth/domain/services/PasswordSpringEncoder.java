package co.com.fxmanager.auth.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordSpringEncoder implements co.com.fxmanager.auth.domain.services.PasswordEncoder{

	@Autowired
	@Lazy
    private PasswordEncoder passwordEncoder;
	
	@Override
	public String encode(String password, String salt) {
		return passwordEncoder.encode(password);
	}

}
