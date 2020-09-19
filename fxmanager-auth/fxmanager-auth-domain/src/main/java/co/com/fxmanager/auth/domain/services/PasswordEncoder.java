package co.com.fxmanager.auth.domain.services;

public interface PasswordEncoder {

	public String encode(String password, String salt);
	
}
