package co.com.fxmanager.management.persistence.repositories.exceptions;

public class DatabaseException extends RuntimeException{

	private static final long serialVersionUID = -3610875348715264131L;

	public DatabaseException() {
		super();
	}

	public DatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(Throwable cause) {
		super(cause);
	}

}