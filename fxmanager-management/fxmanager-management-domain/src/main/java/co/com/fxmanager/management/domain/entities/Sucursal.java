package co.com.fxmanager.management.domain.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Getter
@Setter
public class Sucursal {

	@UtilityClass
	public static final class ClassInfo {
		public static final String COD = "cod";
		public static final String NOMBRE = "nombre";
		public static final String DIRECCION = "direccion";
		public static final String ESTADO = "estado";
		public static final String TELEFONOS = "telefonos";
		public static final String CHK_PRINCIPAL = "chkPrincipal";

	}

	protected Long id;
	
	@NonNull
	protected String cod;

	@NonNull
	protected String nombre;
	
	@NonNull
	protected String direccion;
	
	@NonNull
	protected String estado;
	
	protected String telefonos;
	
	@NonNull
	protected Boolean chkPrincipal;
	
	@NonNull
	private List<FxSucursal> listaPrecios;


	
	public Sucursal(@NonNull String cod, @NonNull String nombre, @NonNull String direccion,@NonNull  String estado,@NonNull  Boolean chkPrincipal) {
		super();
		this.cod = cod;
		this.nombre = nombre;
		this.direccion = direccion;
		this.estado = estado;
		this.chkPrincipal = chkPrincipal;
		listaPrecios  = new ArrayList<>();
	}
	
	public Sucursal( @NonNull String cod, @NonNull String nombre, @NonNull String direccion,@NonNull  String estado,String telefonos, @NonNull  Boolean chkPrincipal) {
		this(cod,nombre,direccion,estado,chkPrincipal);
		this.telefonos = telefonos;
	}
	
	@SuppressWarnings("unused")
	private Sucursal() {
		
	}
}
