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
public class Operacion {

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String PAR_FX = "parFx";
		public static final String FECHA_OPERACION = "fechaOperacion";
		public static final String NOMINAL = "nominal";
		public static final String TIPO = "tipo";
		public static final String ESTADO = "estado";
		public static final String ID_SOIPC = "idSoipc";

	}

	@NonNull
	protected Long id;

	@NonNull
	protected ParFx parFx;

	@NonNull
	protected LocalDateTime fechaOperacion;

	@NonNull
	protected String tipo;

	@NonNull
	protected BigDecimal nominal;

	@NonNull
	protected String estado;

	protected String idSoipc;
	
	protected String descripcion;
	
	protected Turno turno;
	
	protected Turno turnoEdicion;
	
	protected List<DenominacionCantidad> denominacionesYCantidadesEntregadas;
	
	protected List<DenominacionCantidad> denominacionesYCantidadesRecibidas;

	@SuppressWarnings("unused")
	private Operacion() {
		// Usado para la deseralización de Jackson
	}

	public Operacion(@NonNull Long id, @NonNull ParFx parFx, @NonNull LocalDateTime fechaOperacion, @NonNull String tipo,
			@NonNull BigDecimal nominal, @NonNull String estado) {
		super();
		this.id = id;
		this.parFx = parFx;
		this.fechaOperacion = fechaOperacion;
		this.tipo = tipo;
		this.nominal = nominal;
		this.estado = estado;
		this.denominacionesYCantidadesEntregadas= new ArrayList<>();
		this.denominacionesYCantidadesRecibidas= new ArrayList<>();
	
	}

	public Operacion(@NonNull Long id, @NonNull ParFx parFx, @NonNull LocalDateTime fechaOperacion, @NonNull String tipo,
			@NonNull BigDecimal nominal, @NonNull String estado,@NonNull String idSoipc) {
		this(id, parFx, fechaOperacion, tipo, nominal, estado);
		this.idSoipc = idSoipc;
	}
	

	public Optional<String> getIdSoipc() {
		return Optional.ofNullable(idSoipc);
	}
	
	public Optional<String> getDescripcion() {
		return Optional.ofNullable(descripcion);
	}
	
	public Optional<Turno> getTurnoEdicion() {
		return Optional.ofNullable(turnoEdicion);
	}
}
