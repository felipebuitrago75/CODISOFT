package co.com.fxmanager.management.domain.entities;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Getter
@Setter
public class AperturaTurno {
	
	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String ID_TURNO = "idTurno";
		public static final String MONEDA = "fx";
		public static final String NOMINAL = "nominal";
		public static final String PRECIO_PROMEDIO = "precioPromedio";
	}
	
	@NonNull
	protected Long id;
	
	@NonNull
	protected Long idTurno;

	@NonNull
	protected Fx fx;
	
	@NonNull
	protected BigDecimal nominal;
	
	protected BigDecimal precioPromedio;
	
	public AperturaTurno(Long id, @NonNull Long idTurno,@NonNull  Fx fx, 
			@NonNull  BigDecimal nominal, @NonNull BigDecimal precioPromedio) {
		super();
		this.id = id;
		this.idTurno = idTurno;
		this.fx = fx;
		this.nominal = nominal;
		this.precioPromedio = precioPromedio;
	}
	
	@SuppressWarnings("unused")
	private AperturaTurno() {
	}
	
	public Optional<BigDecimal> getPrecioPromedio(){
		return Optional.ofNullable(precioPromedio);
	}

}
