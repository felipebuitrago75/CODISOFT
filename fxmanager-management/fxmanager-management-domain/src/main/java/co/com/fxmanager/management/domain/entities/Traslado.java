package co.com.fxmanager.management.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Getter
@Setter
public class Traslado {

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String FECHA = "fecha";
		public static final String SUCURSAL_ORIGEN = "sucursalOrigen";
		public static final String SUCURSAL_DESTINO = "sucursalDestino";
		public static final String TIPO_TRASLADO = "tipoTraslado";
		public static final String USUARIO_ORIGEN = "usuarioOrigen";
		public static final String USUARIO_DESTINO = "usuarioDestino";
		public static final String ESTADO = "estado";
		public static final String PRECIO_TRASLADO = "precioTraslado";
		public static final String VALOR_GIRO = "valorGiro";
		public static final String FX = "fx";
		
	}

	@NonNull
	protected Long id;

	@NonNull
	protected LocalDateTime fecha;
	
	@NonNull
	protected Sucursal sucursalOrigen;
	
	@NonNull
	protected Sucursal sucursalDestino;
	
	@NonNull
	protected String tipoTraslado;
	
//	@NonNull
	protected Turno turnoOrigen;
//	
//	@NonNull
	protected Turno turnoDestino;

	@NonNull
	protected String estado;
	
	@NonNull
	protected BigDecimal precioTraslado;
	
	@NonNull
	protected BigDecimal valorGiro;
	
	@NonNull
	protected Fx fx;
	
	protected List<DenominacionCantidad> denominacionesYCantidades;
	
	@SuppressWarnings("unused")
	private Traslado() {
	}

	public Traslado( @NonNull Long id, @NonNull  LocalDateTime fecha,  @NonNull  Sucursal sucursalOrigen, @NonNull  Sucursal sucursalDestino,
			 @NonNull String tipoTraslado,  @NonNull String estado,
			 @NonNull BigDecimal precioTraslado, @NonNull  BigDecimal valorGiro, @NonNull  Fx fx) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.sucursalOrigen = sucursalOrigen;
		this.sucursalDestino = sucursalDestino;
		this.tipoTraslado = tipoTraslado;
//		this.usuarioOrigen = usuarioOrigen;
//		this.usuarioDestino = usuarioDestino;
		this.estado = estado;
		this.precioTraslado = precioTraslado;
		this.valorGiro = valorGiro;
		this.fx = fx;
		this.denominacionesYCantidades= new ArrayList<>();
	}
	
	
	public Optional<Turno> getTurnoDestino() {
		return Optional.ofNullable(turnoDestino);
	}
	
	
}
