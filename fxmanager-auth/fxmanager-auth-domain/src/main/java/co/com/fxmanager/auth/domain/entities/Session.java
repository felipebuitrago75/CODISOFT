package co.com.fxmanager.auth.domain.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Session {

	@NonNull
	protected String token;

	@NonNull
	protected LocalDate date;

	@NonNull
	protected LocalTime time;

	@NonNull
	protected String ip;

	@NonNull
	protected User user;

	public Session(@NonNull String token, @NonNull LocalDate date, @NonNull LocalTime time,
			@NonNull String ip, @NonNull User user) {
		this.token = token;
		this.date = date;
		this.time = time;
		this.ip = ip;
		this.user = user;
	}

}
