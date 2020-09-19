package co.com.fxmanager.auth.persistence.repositories.exceptions;

public class NoFoundDataException extends DatabaseException {

	private static final long serialVersionUID = -4440022085068110769L;

	public NoFoundDataException() {
		super();
	}

	public NoFoundDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoFoundDataException(String message) {
		super(message);
	}

	public NoFoundDataException(Throwable cause) {
		super(cause);
	}

}
