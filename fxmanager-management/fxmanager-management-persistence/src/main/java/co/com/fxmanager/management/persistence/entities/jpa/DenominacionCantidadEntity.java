package co.com.fxmanager.management.persistence.entities.jpa;

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
@Table(name = DenominacionCantidadEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
public class DenominacionCantidadEntity {
	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "denominacion_cantidad";
		public static final String ID = "id";
		public static final String DENOMINACION = "denominacion";
		public static final String CANTIDAD = "cantidad";
		public static final String OPERACION = "operacion";
		public static final String TRASLADO = "traslado";
		public static final String SALDO = "saldo";
		public static final String FLUJO_EXTRAORDINARIO = "flujo_extraordinario";
		public static final String TIPO = "tipo";
		public static final String FONDEO = "fondeo";
	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String DENOMINACION = "denominacion";
		public static final String CANTIDAD = "cantidad";
		public static final String OPERACION = "operacion";
		public static final String TRASLADO = "traslado";
		public static final String FLUJO_EXTRAORDINARIO = "flujo";
		public static final String TIPO = "tipo";
		public static final String SALDO = "saldo";
		public static final String FONDEO = "fondeo";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;

	@Column(name = TableInfo.TIPO)
	@NonNull
	private String tipo;

	@Column(name = TableInfo.CANTIDAD)
	@NonNull
	private Integer cantidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.DENOMINACION, referencedColumnName = DenominacionEntity.TableInfo.ID)
	@NonNull
	private DenominacionEntity denominacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.OPERACION, referencedColumnName = OperacionEntity.TableInfo.ID)
	@NonNull
	private OperacionEntity operacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.TRASLADO, referencedColumnName = TrasladoEntity.TableInfo.ID)
	@NonNull
	private TrasladoEntity traslado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.FLUJO_EXTRAORDINARIO, referencedColumnName = FlujoExtraordinarioEntity.TableInfo.ID)
	@NonNull
	private FlujoExtraordinarioEntity flujo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.SALDO, referencedColumnName = SaldoEntity.TableInfo.ID)
	@NonNull
	private SaldoEntity saldo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.FONDEO, referencedColumnName = FondeoEntity.TableInfo.ID)
	@NonNull
	private FondeoEntity fondeo;
}
