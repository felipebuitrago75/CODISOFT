package co.com.fxmanager.management.domain.entities;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Getter
@Setter
public class FxSucursal {

	@UtilityClass
	public static final class ClassInfo {
		public static final String FX = "fx";
		public static final String PRECIO_VENTA = "precioVenta";
		public static final String PRECIO_COMPRA = "precioCompra";
		public static final String PRECIO_VALORACION = "precioValoracion";

	}
	
	@NonNull
	protected BigDecimal precioVenta;
	
	@NonNull
	protected BigDecimal precioCompra;
	
	@NonNull
	protected BigDecimal precioValoracion;

	@NonNull
	protected Fx fx;
	

	public FxSucursal(@NonNull  BigDecimal precioVenta, @NonNull BigDecimal precioCompra, 
			@NonNull BigDecimal precioValoracion, @NonNull Fx fx) {
		super();
		this.precioVenta = precioVenta;
		this.precioCompra = precioCompra;
		this.precioValoracion = precioValoracion;
		this.fx= fx;

	}
	
	@SuppressWarnings("unused")
	private FxSucursal() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fx == null) ? 0 : fx.hashCode());
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
		FxSucursal other = (FxSucursal) obj;
		if (fx == null) {
			if (other.fx != null)
				return false;
		} else if (!fx.equals(other.fx))
			return false;
		return true;
	}

	
	
}
