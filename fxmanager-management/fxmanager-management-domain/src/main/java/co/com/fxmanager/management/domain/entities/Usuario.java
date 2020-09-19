package co.com.fxmanager.management.domain.entities;

import co.com.fxmanager.auth.domain.entities.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Getter
@Setter
public class Usuario {

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String NOMBRE = "nombre";
		public static final String APELLIDO = "apellido";

	}

	@NonNull
	protected Long id;

	@NonNull
	protected String nombre;
	
	@NonNull
	protected String apellido;

	@NonNull
	protected User user;
	
	@SuppressWarnings("unused")
	private Usuario() {
		// Usado para la deseralización de Jackson
	}

	public Usuario(@NonNull Long id, @NonNull String nombre, @NonNull String apellido, @NonNull User user) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.user=user;

	}
}
