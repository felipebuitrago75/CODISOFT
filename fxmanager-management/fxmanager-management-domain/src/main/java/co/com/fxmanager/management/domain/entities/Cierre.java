package co.com.fxmanager.management.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cierre {
	

	
	
	protected Sucursal sucursal;
	
	protected Long id;
	
	protected LocalDate fecha;
	
	protected BigDecimal saldoInicial;
	
	protected BigDecimal precioPromedioInicial;
	
	protected BigDecimal valorCompraInicial;
	
	protected BigDecimal nominalCompra;
	
	protected BigDecimal precioCompra;
	
	protected BigDecimal valorCompra;
	
	protected BigDecimal nominalVenta;
	
	protected BigDecimal precioVenta;
	
	protected BigDecimal valorVenta;
	
	protected BigDecimal nominalTrasladosEntrantes;
	
	protected BigDecimal precioTrasladoEntrantes;
	
	protected BigDecimal nominalTrasladosSalientes;
	
	protected BigDecimal precioTrasladoSalientes;
	
	protected BigDecimal saldoFinal;
	
	protected BigDecimal precioPromedioFinal;
	
	protected BigDecimal valorCompraFinal;
	
	protected BigDecimal margen;
	
	protected BigDecimal utilidadDiaria;
	
	protected BigDecimal pygCaja;
	
	protected BigDecimal pygCajaD;
	
	protected BigDecimal precioValoracion;
	
	protected BigDecimal margePrecioValoracionYPrecioPromedio;

	protected BigDecimal valoracion;
	
	protected BigDecimal pygValoracion;
	
	protected BigDecimal pygValoracionD;

	protected BigDecimal pygBruto;

	protected BigDecimal rentaCaja;

	protected BigDecimal rentaVal;

	protected BigDecimal rentaBruta;
	
	protected BigDecimal diferenciaEgresosIngresos;

	protected BigDecimal egresosAcumulados;
	
	protected BigDecimal pygNeto;
	
	protected BigDecimal rentaNeta;
	
	protected BigDecimal saldoCop;
	
	protected BigDecimal valorGiroTrasladoSalientes;
	
	protected BigDecimal valorGiroTrasladoEntrantes;
	
	protected BigDecimal valorTraslados;
	
	protected BigDecimal pygTrasladoDiario;
	
	protected BigDecimal pygTrasladoAcumulado;
	
	protected BigDecimal pygBrutoConTraslado;
	
	protected BigDecimal pygNetoConTraslado;

	protected BigDecimal fcDiario;
	
	protected BigDecimal fcAcumulado;

	protected BigDecimal valorPort;

	protected Fx moneda;
	
	protected BigDecimal trasladosAcumulados;
	
	protected BigDecimal egresosMoneda;
	
	

	private BigDecimal saldoPrecierre;
	
	private BigDecimal valorGiroPrecierre;
	
	private BigDecimal precioValoracionAnterior;
	
	private BigDecimal valoracionPrecierreAnterior;
	
	private BigDecimal valoracionPrecierreActual;
	
	private BigDecimal pygValPrecierreMensual;
	
	private BigDecimal pygValPrecierreAcumulada;
	
	private BigDecimal pygValPrecierreDiaria;
	
	private BigDecimal pygTrasladoEntrante;
	
	private BigDecimal pygTrasladoSaliente;
	
	private BigDecimal pygValCierreDiaria;
	
	private BigDecimal pygValCierreMensual;
	
	private BigDecimal pygValCierreAcumulada;
	
	private BigDecimal egresosDiarios;
	
	private BigDecimal  ingresosDiarios;
	
	public Cierre() {
		
	}

}
