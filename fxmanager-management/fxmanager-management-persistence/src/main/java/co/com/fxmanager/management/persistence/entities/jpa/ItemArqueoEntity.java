package co.com.fxmanager.management.persistence.entities.jpa;

import java.math.BigDecimal;

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
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Entity
@Table(name = ItemArqueoEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
public class ItemArqueoEntity {
	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "item_arqueo";
		public static final String ID = "id";
		public static final String MONEDA = "moneda";
		public static final String ARQUEO = "arqueo";
		public static final String SALDO_INICIAL = "saldo_inicial";
		public static final String SALDO_FINAL = "saldo_final";
		public static final String SALDO_REAL = "saldo_real";
		public static final String NOMINAL_COMPRAS = "nominal_compras";
		public static final String NOMINAL_VENTAS = "nominal_ventas";
		public static final String NOMINAL_EGRESOS = "nominal_egresos";
		public static final String NOMINAL_INGRESOS = "nominal_ingresos";
		public static final String NOMINAL_TRASLADOS_ENTRANTES = "nominal_traslados_entrantes";
		public static final String NOMINAL_TRASLADOS_SALIENTES = "nominal_traslados_salientes";
	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String ARQUEO = "arqueo";
		public static final String MONEDA = "moneda";
		public static final String SALDO_INICIAL = "saldoInicial";
		public static final String SALDO_FINAL = "saldoFinal";
		public static final String SALDO_REAL = "saldo_real";
		public static final String NOMINAL_COMPRAS = "nominalCompras";
		public static final String NOMINAL_VENTAS = "nominalVentas";
		public static final String NOMINAL_EGRESOS = "nominalEgresos";
		public static final String NOMINAL_INGRESOS = "nominalIngresos";
		public static final String NOMINAL_TRASLADOS_ENTRANTES = "nominalTrasladosEntrantes";
		public static final String NOMINAL_TRASLADOS_SALIENTES = "nominalTrasladosSalientes";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;

	@Column(name = TableInfo.SALDO_INICIAL)
	private BigDecimal saldoInicial;

	@Column(name = TableInfo.SALDO_FINAL)
	private BigDecimal saldoFinal;
	
	@Column(name = TableInfo.SALDO_REAL)
	private BigDecimal saldosRealEnCaja;
	
	@Column(name = TableInfo.NOMINAL_COMPRAS)
	private BigDecimal nominalCompras;
	
	@Column(name = TableInfo.NOMINAL_VENTAS)
	private BigDecimal nominalVentas;
	
	@Column(name = TableInfo.NOMINAL_EGRESOS)
	private BigDecimal nominalEgresos;
	
	@Column(name = TableInfo.NOMINAL_INGRESOS)
	private BigDecimal nominalIngresos;
	
	@Column(name = TableInfo.NOMINAL_TRASLADOS_ENTRANTES)
	private BigDecimal nominalTrasladosEntrantes;
	
	@Column(name = TableInfo.NOMINAL_TRASLADOS_SALIENTES)
	private BigDecimal nominalTrasladosSalientes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.ARQUEO, referencedColumnName = ArqueoEntity.TableInfo.ID)
	@NonNull
	private ArqueoEntity arqueo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = TableInfo.MONEDA, referencedColumnName = FxEntity.TableInfo.ID)
	@NonNull
	private FxEntity moneda;
}
