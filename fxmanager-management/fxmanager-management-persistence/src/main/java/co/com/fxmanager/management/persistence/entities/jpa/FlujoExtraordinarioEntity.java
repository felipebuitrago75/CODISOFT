package co.com.fxmanager.management.persistence.entities.jpa;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Entity
@Table(name = FlujoExtraordinarioEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
@NamedEntityGraphs({ @NamedEntityGraph(name = FlujoExtraordinarioEntity.EntityGraph.WITH_DENOMINACIONES_CANTIDADES, attributeNodes = {
		@NamedAttributeNode(FlujoExtraordinarioEntity.ClassInfo.DENOMINACIONES_CANTIDAD ),
		@NamedAttributeNode(FlujoExtraordinarioEntity.ClassInfo.FX )}) })
public class FlujoExtraordinarioEntity {
	
	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "flujo_extraordinario";
		public static final String ID = "id";
		public static final String TIPO = "tipo";
		public static final String VALOR = "valor";
		public static final String ID_SOIPC = "idsoipc";
		public static final String TURNO = "turno";
		public static final String FECHA = "fecha";
		public static final String ESTADO = "estado";
		public static final String FX = "fx";
		public static final String CRITERIO = "criterio";
		public static final String DESCRIPCION = "descripcion";
		
		public static final String NATURALEZA = "naturaleza";
		public static final String RECEPTOR = "receptor";
		public static final String CUENTA = "cuenta";
		public static final String CONCEPTO = "concepto";
	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String TIPO = "tipo";
		public static final String VALOR = "valor";
		public static final String ID_SOIPC = "idSoipc";
		public static final String TURNO = "turno";
		public static final String FECHA = "fecha";
		public static final String ESTADO = "estado";
		public static final String FX = "fx";
		public static final String CRITERIO = "criterio";
		public static final String DESCRIPCION = "descripcion";
		public static final String DENOMINACIONES_CANTIDAD = "denominacionesCantidad";
		
		public static final String NATURALEZA = "naturaleza";
		public static final String RECEPTOR = "receptor";
		public static final String CUENTA = "cuenta";
		public static final String CONCEPTO = "concepto";
	}
	
	@UtilityClass
	public static final class EntityGraph {
		public static final String WITH_DENOMINACIONES_CANTIDADES = "WITH_DENOMINACIONES_CANTIDADES";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;
	
	
	@Column(name = TableInfo.TIPO)
	@NonNull
	private String tipo;
	
	@Column(name = TableInfo.FECHA)
	@NonNull
	private LocalDateTime fecha;
	
	@Column(name = TableInfo.VALOR)
	@NonNull
	private BigDecimal valor;
	
	@Column(name = TableInfo.ID_SOIPC)
	@NonNull
	private String idSoipc;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.TURNO, referencedColumnName = TurnoEntity.TableInfo.ID)
	private TurnoEntity turno;
	
	@Column(name = TableInfo.ESTADO)
	@NonNull
	private String estado;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = TableInfo.FX, referencedColumnName = FxEntity.TableInfo.ID)
	private FxEntity fx;

	@Column(name = TableInfo.CRITERIO)
	@NonNull
	private String criterio;
	
	@Column(name = TableInfo.DESCRIPCION)
	private String descripcion;
	
	@OneToMany(mappedBy = DenominacionCantidadEntity.ClassInfo.FLUJO_EXTRAORDINARIO, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@NonNull
	private Set<DenominacionCantidadEntity> denominacionesCantidad;
	
	@Column(name = TableInfo.NATURALEZA)
	private String naturaleza;
	
	@Column(name = TableInfo.RECEPTOR)
	private String receptor;
	
	@Column(name = TableInfo.CUENTA)
	private String cuenta;
	
	@Column(name = TableInfo.CONCEPTO)
	private String concepto;
}
