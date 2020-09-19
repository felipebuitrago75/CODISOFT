package co.com.fxmanager.auth.domain.entities;

import org.apache.commons.lang3.math.NumberUtils;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class User {

	@NonNull
	protected String username;

	@NonNull
	protected String password;

	@NonNull
	protected String salt;

	@NonNull
	protected Boolean enabled;

	@NonNull
	protected Boolean locked;

	@NonNull
	protected Integer failedLogins;

	protected Role role;
	
	@SuppressWarnings("unused")
	private User() {
		this.enabled=Boolean.TRUE;
		this.failedLogins=0;
		this.locked=Boolean.FALSE;
		// Usado para la deseralización de Jackson
	}

	public User(@NonNull String username, @NonNull String password, @NonNull String salt, @NonNull Role role) {
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.role = role;
		this.enabled = Boolean.TRUE;
		this.locked = Boolean.FALSE;
		this.failedLogins = NumberUtils.INTEGER_ZERO;
	}

	public User(@NonNull String username, @NonNull String password, @NonNull String salt, @NonNull Boolean enabled,
			@NonNull Boolean locked, @NonNull Integer failedLogins, @NonNull Role role) {
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.enabled = enabled;
		this.locked = locked;
		this.failedLogins = failedLogins;
		this.role = role;
	}

}
