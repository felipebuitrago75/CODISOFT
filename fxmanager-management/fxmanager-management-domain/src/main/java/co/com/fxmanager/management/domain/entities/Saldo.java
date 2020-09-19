package co.com.fxmanager.management.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Getter
@Setter
public class Saldo {

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String MONEDA = "moneda";
		public static final String NOMINAL = "nominal";
		public static final String PRECIO_PROMEDIO = "precioPromedio";
		
	}

	@NonNull
	protected Long id;
	
	@NonNull
	protected Fx moneda;
	
	@NonNull
	protected BigDecimal nominal;
	
	@NonNull
	protected BigDecimal precioPromedio;
	
	protected List<DenominacionCantidad> denominacionesYCantidades;
	
	@SuppressWarnings("unused")
	private Saldo() {
	}

	public Saldo(@NonNull Long id, @NonNull Long idCaja, @NonNull Fx moneda, @NonNull BigDecimal nominal,
			@NonNull BigDecimal precioPromedio) {
		super();
		this.id = id;
		this.moneda = moneda;
		this.nominal = nominal;
		this.precioPromedio=precioPromedio;
		this.denominacionesYCantidades= new ArrayList<DenominacionCantidad>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((moneda == null) ? 0 : moneda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Saldo other = (Saldo) obj;
		if (moneda == null) {
			if (other.moneda != null)
				return false;
		} else if (!moneda.equals(other.moneda))
			return false;
		return true;
	}

	
	
	
	
	
}
