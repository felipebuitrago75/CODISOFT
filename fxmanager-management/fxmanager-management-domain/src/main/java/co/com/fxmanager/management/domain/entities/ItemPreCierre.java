package co.com.fxmanager.management.domain.entities;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPreCierre {



	protected Long id;
	
	protected Fx moneda;

	protected BigDecimal saldosInicial;
	
	protected BigDecimal saldosFinal;
	
	protected BigDecimal saldosRealEnCaja;
	
	protected BigDecimal nominalCompras;
	
	protected BigDecimal nominalVentas;
	
	protected BigDecimal nominalEgresos;
	
	protected BigDecimal nominalIngresos;
	
	protected BigDecimal nominalTrasladosSalientes;
	
	protected BigDecimal nominalTrasladosEntrantes;

	public ItemPreCierre() {
		super();
	}
	
	



	
	
	
	
	
}
