package co.com.fxmanager.auth.domain.services.exceptions;

public class MailException extends RuntimeException {

	private static final long serialVersionUID = 14535L;

	public MailException() {
	}

	public MailException(String message) {
		super(message);
	}

	public MailException(String message, Throwable cause) {
		super(message, cause);
	}

	public MailException(Throwable cause) {
		super(cause);
	}

}
