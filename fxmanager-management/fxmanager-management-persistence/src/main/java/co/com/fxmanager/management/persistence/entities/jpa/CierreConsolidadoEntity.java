package co.com.fxmanager.management.persistence.entities.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Entity
@Table(name = CierreConsolidadoEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
public class CierreConsolidadoEntity {
	
	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "cierre_consolidado";
		public static final String ID = "id";
		public static final String MONEDA = "moneda";
		public static final String FECHA = "fecha";
		public static final String SALDO_INICIAL = "saldo_inicial";
		public static final String PRECIO_PROMEDIO_INICIAL = "precio_promedio_inicial";
		public static final String VALOR_COMPRA_INICIAL = "valor_compra_inicial";
		public static final String NOMINAL_COMPRA = "nominal_compra";
		public static final String PRECIO_COMPRA = "precio_compra";
		public static final String VALOR_COMPRA = "valor_compra";
		public static final String NOMINAL_VENTA = "nominal_venta";
		public static final String PRECIO_VENTA = "precio_venta";
		public static final String VALOR_VENTA = "valor_venta";
		public static final String NOMINAL_TRASLADOS_SALIENTES = "nominal_traslados_salientes";
		public static final String PRECIO_TRASLADO_SALIENTES = "precio_traslado_salientes";
		public static final String NOMINAL_TRASLADOS_ENTRANTES = "nominal_traslados_entrantes";
		public static final String PRECIO_TRASLADO_ENTRANTES = "precio_traslado_entrantes";
		public static final String SALDO_FINAL = "saldo_final";
		public static final String PRECIO_PROMEDIO_FINAL = "precio_promedio_final";
		public static final String VALOR_COMPRA_FINAL = "valor_compra_final";
		public static final String MARGEN = "margen";
		public static final String UTILIDAD_DIARIA = "utilidad_diaria";
		public static final String PYG_CAJA = "pyg_caja";
		public static final String PYG_CAJA_D = "pyg_caja_d";
		public static final String PRECIO_VALORACION = "precio_valoracion";
		public static final String MARGEN_PREC_VALORA_Y_PREC_PROM = "margen_prec_valora_y_prec_prom";
		public static final String VALORACION = "valoracion";
		public static final String PYG_VALORACION = "pyg_valoracion";
		public static final String PYG_VALORACION_D = "pyg_valoracion_d";
		public static final String PYG_BRUTO = "pyg_bruto";
		public static final String RENTA_CAJA = "renta_caja";
		public static final String RENTA_VAL = "renta_val";
		public static final String RENTA_BRUTA = "renta_bruta";
		public static final String EGRESOS = "egresos";
		public static final String EGRESOS_ACUMULADOS = "egresos_acumulados";
		public static final String PYG_NETO = "pyg_neto";
		public static final String RENTA_NETA = "renta_neta";
		public static final String SALDO_COP = "saldo_cop";
		public static final String VALOR_GIRO_TRASLADO = "valor_giro_traslado";
		public static final String VALOR_TRASLADOS = "valor_traslados";
		public static final String VALOR_GIRO_TRASLADO_SALIENTES = "valor_giro_traslado_salientes";
		public static final String VALOR_GIRO_TRASLADO_ENTRANTES = "valor_giro_traslado_entrantes";
		public static final String VALOR_TRASLADOS_SALIENTES = "valor_traslados_salientes";
		public static final String PYG_TRASLADO_DIARIO = "pyg_traslado_diario";
		public static final String PYG_TRASLADO_ACUMULADO = "pyg_traslado_acumulado";
		public static final String PYG_BRUTO_CON_TRASLADO = "pyg_bruto_con_traslado";
		public static final String PYG_NETO_CON_TRASLADO = "pyg_neto_con_traslado";
		public static final String FC_DIARIO = "fc_diario";
		public static final String FC_ACUMULADO = "fc_acumulado";
		public static final String VALOR_PORT = "valor_port";
		public static final String TRASLADOS_ACUMULADOS = "traslados_acumulados";
		public static final String EGRESOS_EN_MONEDA = "egresos_moneda";
	
		public static final String SALDO_PRECIERRE = "saldo_precierre";
		public static final String VALOR_GIRO_PRECIRRE = "valor_giro_precierre";
		public static final String PRECIO_VALORACION_ANTERIOR = "precio_valoracion_anterior";
		public static final String VALORACION_PRECIERRE_ANTERIOR = "valoracion_precierre_anterior";
		public static final String VALORACION_PRECIERRE_ACTUAL = "valoracion_precierre_actual";
		public static final String PYG_VAL_PRECIERRE_MENSUAL = "pyg_val_precierre_mensual";
		public static final String PYG_VAL_PRECIERRE_DIARIA = "pyg_val_precierre_diaria";
		public static final String PYG_TRASLADO_ENTRANTE = "pyg_traslado_entrante";
		public static final String PYG_TRASLADO_SALIENTE = "pyg_traslado_saliente";

		public static final String PYG_VAL_CIERRE_MENSUAL = "pyg_val_cierre_mensual";
		public static final String PYG_VAL_CIERRE_DIARIA = "pyg_val_cierre_diaria";

		public static final String DIFERENCIA_EGRESOS_INGRESOS = "diferencia_egresos_ingresos";
		public static final String EGRESOS_DIARIOS = "egresos";
		public static final String INGRESOS_DIARIOS = "ingresos";
		
		public static final String PYG_VAL_CIERRE_ACUMULADA = "pyg_val_cierre_acumulada";
		public static final String PYG_VAL_PRECIERRE_ACUMULADA = "pyg_val_precierre_acumulada";
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	private Long id;
	
	@Column(name = TableInfo.FECHA)
	private LocalDate fecha;
	
	@Column(name = TableInfo.SALDO_INICIAL)
	private BigDecimal saldoInicial;
	
	@Column(name = TableInfo.PRECIO_PROMEDIO_INICIAL)
	private BigDecimal precioPromedioInicial;
	
	@Column(name = TableInfo.VALOR_COMPRA_INICIAL)
	private BigDecimal valorCompraInicial;
	
	@Column(name = TableInfo.NOMINAL_COMPRA)
	private BigDecimal nominalCompra;
	
	@Column(name = TableInfo.PRECIO_COMPRA)
	private BigDecimal precioCompra;
	
	@Column(name = TableInfo.VALOR_COMPRA)
	private BigDecimal valorCompra;
	
	@Column(name = TableInfo.NOMINAL_VENTA)
	private BigDecimal nominalVenta;
	
	@Column(name = TableInfo.PRECIO_VENTA)
	private BigDecimal precioVenta;
	
	@Column(name = TableInfo.VALOR_VENTA)
	private BigDecimal valorVenta;
	
	@Column(name = TableInfo.NOMINAL_TRASLADOS_ENTRANTES)
	protected BigDecimal nominalTrasladosEntrantes;
	
	@Column(name = TableInfo.PRECIO_TRASLADO_ENTRANTES)
	protected BigDecimal precioTrasladoEntrantes;
	
	@Column(name = TableInfo.NOMINAL_TRASLADOS_SALIENTES)
	protected BigDecimal nominalTrasladosSalientes;
	
	@Column(name = TableInfo.PRECIO_TRASLADO_SALIENTES)
	protected BigDecimal precioTrasladoSalientes;
	
	@Column(name = TableInfo.SALDO_FINAL)
	private BigDecimal saldoFinal;
	
	@Column(name = TableInfo.PRECIO_PROMEDIO_FINAL)
	private BigDecimal precioPromedioFinal;
	
	@Column(name = TableInfo.VALOR_COMPRA_FINAL)
	private BigDecimal valorCompraFinal;
	
	@Column(name = TableInfo.MARGEN)
	private BigDecimal margen;
	
	@Column(name = TableInfo.UTILIDAD_DIARIA)
	private BigDecimal utilidadDiaria;
	
	@Column(name = TableInfo.PYG_CAJA)
	private BigDecimal pygCaja;
	
	@Column(name = TableInfo.PYG_CAJA_D)
	private BigDecimal pygCajaD;
	
	@Column(name = TableInfo.PRECIO_VALORACION)
	private BigDecimal precioValoracion;
	
	@Column(name = TableInfo.MARGEN_PREC_VALORA_Y_PREC_PROM)
	private BigDecimal margePrecioValoracionYPrecioPromedio;

	@Column(name = TableInfo.VALORACION)
	private BigDecimal valoracion;
	
	@Column(name = TableInfo.PYG_VALORACION)
	private BigDecimal pygValoracion;
	
	@Column(name = TableInfo.PYG_VALORACION_D)
	private BigDecimal pygValoracionD;
	
	@Column(name = TableInfo.PYG_BRUTO)
	private BigDecimal pygBruto;
	
	@Column(name = TableInfo.RENTA_CAJA)
	private BigDecimal rentaCaja;
	
	@Column(name = TableInfo.RENTA_VAL)
	private BigDecimal rentaVal;
	
	@Column(name = TableInfo.RENTA_BRUTA)
	private BigDecimal rentaBruta;
	
	@Column(name = TableInfo.DIFERENCIA_EGRESOS_INGRESOS)
	private BigDecimal diferenciaIngresosEgresos;
	
	@Column(name = TableInfo.EGRESOS_DIARIOS)
	private BigDecimal egresosDiarios;
	
	@Column(name = TableInfo.INGRESOS_DIARIOS)
	private BigDecimal ingresosDiarios;
	
	@Column(name = TableInfo.EGRESOS_ACUMULADOS)
	private BigDecimal egresosAcumulados;
	
	@Column(name = TableInfo.PYG_NETO)
	private BigDecimal pygNeto;
	
	@Column(name = TableInfo.RENTA_NETA)
	private BigDecimal rentaNeta;
	
	@Column(name = TableInfo.SALDO_COP)
	private BigDecimal saldoCop;
	
	@Column(name = TableInfo.VALOR_GIRO_TRASLADO_SALIENTES)
	private BigDecimal valorGiroTrasladoSalientes;
	
	@Column(name = TableInfo.VALOR_GIRO_TRASLADO_ENTRANTES)
	private BigDecimal valorGiroTrasladoEntrantes;
	
	@Column(name = TableInfo.VALOR_TRASLADOS)
	private BigDecimal valorTraslados;
	
	@Column(name = TableInfo.PYG_TRASLADO_DIARIO)
	private BigDecimal pygTrasladoDiario;
	
	@Column(name = TableInfo.PYG_TRASLADO_ACUMULADO)
	private BigDecimal pygTrasladoAcumulado;
	
	@Column(name = TableInfo.PYG_BRUTO_CON_TRASLADO)
	private BigDecimal pygBrutoConTraslado;
	
	@Column(name = TableInfo.PYG_NETO_CON_TRASLADO)
	private BigDecimal pygNetoConTraslado;
	
	@Column(name = TableInfo.FC_DIARIO)
	private BigDecimal fcDiario;
	
	@Column(name = TableInfo.FC_ACUMULADO)
	private BigDecimal fcAcumulado;
	
	@Column(name = TableInfo.VALOR_PORT)
	private BigDecimal valorPort;
	
	@Column(name = TableInfo.TRASLADOS_ACUMULADOS)
	private BigDecimal trasladosAcumulados;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.MONEDA, referencedColumnName = FxEntity.TableInfo.ID)
	private FxEntity moneda;

	@Column(name = TableInfo.EGRESOS_EN_MONEDA)
	private BigDecimal egresosMoneda;

	@Column(name = TableInfo.SALDO_PRECIERRE)
	private BigDecimal saldoPrecierre;
	
	@Column(name = TableInfo.VALOR_GIRO_PRECIRRE)
	private BigDecimal valorGiroPrecierre;
	
	@Column(name = TableInfo.PRECIO_VALORACION_ANTERIOR)
	private BigDecimal precioValoracionAnterior;
	
	@Column(name = TableInfo.VALORACION_PRECIERRE_ANTERIOR)
	private BigDecimal valoracionPrecierreAnterior;
	
	@Column(name = TableInfo.VALORACION_PRECIERRE_ACTUAL)
	private BigDecimal valoracionPrecierreActual;
	
	@Column(name = TableInfo.PYG_VAL_PRECIERRE_MENSUAL)
	private BigDecimal pygValPrecierreMensual;
	
	@Column(name = TableInfo.PYG_VAL_PRECIERRE_DIARIA)
	private BigDecimal pygValPrecierreDiaria;
	
	@Column(name = TableInfo.PYG_TRASLADO_ENTRANTE)
	private BigDecimal pygTrasladoEntrante;
	
	@Column(name = TableInfo.PYG_TRASLADO_SALIENTE)
	private BigDecimal pygTrasladoSaliente;

	@Column(name = TableInfo.PYG_VAL_CIERRE_DIARIA)
	private BigDecimal pygValCierreDiaria;
	
	@Column(name = TableInfo.PYG_VAL_CIERRE_MENSUAL)
	private BigDecimal pygValCierreMensual;
	
	@Column(name = TableInfo.PYG_VAL_PRECIERRE_ACUMULADA)
	private BigDecimal pygValPrecierreAcumulada;
	
	@Column(name = TableInfo.PYG_VAL_CIERRE_ACUMULADA)
	private BigDecimal pygValCierreAcumulada;
}
