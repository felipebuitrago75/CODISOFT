package co.com.fxmanager.management.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportePortafolio {
	

	protected LocalDate fecha;
	
	protected String codigoSucursal;
	
	protected String codigoMoneda;
	
	protected BigDecimal nominal;
	
	protected BigDecimal costoPromedio;
	
	protected BigDecimal valorCompra;
	
	protected BigDecimal precioValoracion;
	
	protected BigDecimal valoracion;
	
	
	public ReportePortafolio() {
		
	}

}
