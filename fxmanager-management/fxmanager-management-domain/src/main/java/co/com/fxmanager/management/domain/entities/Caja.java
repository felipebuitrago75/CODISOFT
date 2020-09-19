package co.com.fxmanager.management.domain.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Getter
@Setter
public class Caja {

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String NOMBRE = "nombre";
		public static final String CODIGO_SUCURSAL = "codigoSucursal";
		public static final String LISTA_SALDOS = "listaSaldos";
	}

	@NonNull
	protected Long id;

	@NonNull
	protected String nombre;
	
	@NonNull
	protected String codigoSucursal;
	
	@NonNull
	protected List<Saldo> listaSaldos;
	
	@SuppressWarnings("unused")
	private Caja() {
	}

	public Caja(@NonNull Long id, @NonNull String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		listaSaldos= new ArrayList<>();
	}

	
	
	
	
	
}
