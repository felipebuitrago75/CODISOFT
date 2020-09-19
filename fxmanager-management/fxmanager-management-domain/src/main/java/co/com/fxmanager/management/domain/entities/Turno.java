package co.com.fxmanager.management.domain.entities;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Getter
@Setter
public class Turno {

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String ID_CAJA = "idCaja";
		public static final String USUARIO = "idUsuario";
		public static final String FECHA_INICIO = "fechaInicio";
		public static final String FECHA_FIN = "fechaFin";
		public static final String SALDO_INICIAL = "saldoInicial";
		public static final String SALDO_FINAL = "saldoFinal";
		
		
	}

	@NonNull
	protected Long id;
	
	@NonNull
	protected Long idCaja;

	@NonNull
	protected Long idUsuario;
	
	protected String nombreUsuario;
	
	@NonNull
	protected LocalDateTime fechaInicio;
	
	protected LocalDateTime fechaFin;
	
	@SuppressWarnings("unused")
	private Turno() {
	}
	
	public Turno(@NonNull Long id, @NonNull Long idCaja,@NonNull  Long idUsuario, @NonNull  LocalDateTime fechaInicio, String nombreUsuario) {
		super();
		this.id = id;
		this.idCaja = idCaja;
		this.idUsuario = idUsuario;
		this.fechaInicio = fechaInicio;
		this.nombreUsuario = nombreUsuario;
	}


	public Optional<LocalDateTime> getFechaFin(){
		return Optional.ofNullable(fechaFin);
	}
	

	
	
	
	
}
