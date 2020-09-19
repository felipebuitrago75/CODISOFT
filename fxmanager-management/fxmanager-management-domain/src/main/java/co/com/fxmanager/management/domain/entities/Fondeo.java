package co.com.fxmanager.management.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Getter
@Setter
public class Fondeo {

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String FECHA = "fecha";
		public static final String SUCURSAL = "sucursal";
		public static final String USUARIO_ORIGEN = "usuarioOrigen";
		public static final String USUARIO_DESTINO = "usuarioDestino";
		public static final String ESTADO = "estado";
		public static final String VALOR_GIRO = "valorGiro";
		public static final String FX = "fx";

	}

	@NonNull
	protected Long id;

	@NonNull
	protected LocalDateTime fecha;

	@NonNull
	protected Sucursal sucursal;

//	@NonNull
//	protected Usuario usuarioOrigen;
//	
//	@NonNull
//	protected Usuario usuarioDestino;

	@NonNull
	protected String estado;

	@NonNull
	protected BigDecimal valorGiro;

	@NonNull
	protected Fx fx;

	protected Turno turnoOrigen;

	protected Turno turnoDestino;
	
	protected List<DenominacionCantidad> denominacionesYCantidades;

	@SuppressWarnings("unused")
	private Fondeo() {
	}

	public Fondeo(@NonNull Long id, @NonNull LocalDateTime fecha, @NonNull String estado,
			@NonNull BigDecimal valorGiro, @NonNull Fx fx, Turno turnoOrigen, Turno turnoDestino) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.turnoOrigen = turnoOrigen;
		this.turnoDestino = turnoDestino;
		this.estado = estado;
		this.valorGiro = valorGiro;
		this.fx = fx;
		this.denominacionesYCantidades= new ArrayList<>();
	}

}
