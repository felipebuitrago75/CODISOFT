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
public class FlujoExtraordinario {
	
	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String FX = "fx";
		public static final String VALOR = "valor";
		public static final String TIPO = "tipo";
		public static final String ID_SOIPC = "idSoipc";
	}

	@NonNull
	protected Long id;


	protected String idSoipc;

	@NonNull
	protected String tipo;

	@NonNull
	protected BigDecimal valor;

	@NonNull
	protected LocalDateTime fecha;
	
	protected Turno turno;
	
	protected String estado;
	
	protected Fx fx;
	
	protected String descripcion;
	
	protected String criterio;
	
	protected List<DenominacionCantidad> denominacionesYCantidades;
	
	protected String naturaleza;
	
	protected String receptor;
	
	protected String cuenta;
	
	protected String concepto;
	
	public FlujoExtraordinario() {

	}

	public FlujoExtraordinario(@NonNull Long id,  @NonNull String tipo,
			@NonNull BigDecimal valor, LocalDateTime fecha, @NonNull String estado, Fx fx, String naturaleza, String receptor) {
		super();
		this.id = id;
		this.estado=estado;
		this.tipo = tipo;
		this.valor = valor;
		this.fecha= fecha;
		this.fx=fx;
		this.receptor=receptor;
		this.naturaleza=naturaleza;
		this.denominacionesYCantidades= new ArrayList<>();
	}
	
	public FlujoExtraordinario(@NonNull Long id,  @NonNull String tipo,
			@NonNull BigDecimal valor,LocalDateTime fecha,@NonNull String estado, Fx fx, String naturaleza, String receptor ,@NonNull String idSoipc ) {
		this(id,tipo,valor,fecha, estado, fx, naturaleza, receptor);
		this.idSoipc = idSoipc;
	
	}

	public Optional<String> getIdSoipc() {
		return Optional.ofNullable(idSoipc);
	}
	
	public Optional<String> getCuenta() {
		return Optional.ofNullable(cuenta);
	}
	
	public Optional<String> getConcepto() {
		return Optional.ofNullable(concepto);
	}
	
	public Optional<String> getDescripcion() {
		return Optional.ofNullable(descripcion);
	}
	

}
