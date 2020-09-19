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
@Table(name = OperacionEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
@NamedEntityGraphs({ @NamedEntityGraph(name = OperacionEntity.EntityGraph.WITH_TURNOS, attributeNodes = {
		@NamedAttributeNode(OperacionEntity.ClassInfo.TURNO),
		@NamedAttributeNode(OperacionEntity.ClassInfo.DENOMINACIONES_CANTIDAD_ENTREGADAS),
		@NamedAttributeNode(OperacionEntity.ClassInfo.DENOMINACIONES_CANTIDAD_RECIBIDAS),
		@NamedAttributeNode(OperacionEntity.ClassInfo.TURNO_EDICION )}) })
public class OperacionEntity  {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "operacion";
		public static final String ID = "id";
		public static final String FECHA_OPERACION = "fecha_operacion";
		public static final String FX_BASE = "fx_base";
		public static final String FX_OPER = "fx_oper";
		public static final String TIPO = "tipo";
		public static final String NOMINAL = "nominal";
		public static final String VALOR_FX_OPERACION = "valor_fx_operacion";
		public static final String ESTADO = "estado";
		public static final String ID_SOIPC = "id_soipc";
		public static final String VALOR_VALORACION = "valor_valoracion";
		public static final String DESCRIPCION = "descripcion";
		public static final String TURNO = "turno";
		public static final String TURNO_EDICION = "turno_edicion";
	
	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String FECHA_OPERACION = "fechaOperacion";
		public static final String FX_BASE = "fxBase";
		public static final String FX_OPER = "fxOper";
		public static final String TIPO = "tipo";
		public static final String NOMINAL = "nominal";
		public static final String VALOR_FX_OPERACION = "valorFxOperacion";
		public static final String ESTADO = "estado";
		public static final String ID_SOIPC = "idSoipc";
		public static final String VALOR_VALORACION = "valorValoracion";
		public static final String DESCRIPCION = "descripcion";
		public static final String TURNO = "turno";
		public static final String TURNO_EDICION = "turnoEdicion";
		public static final String DENOMINACIONES_CANTIDAD_ENTREGADAS = "denominacionesCantidadEntregadas";
		public static final String DENOMINACIONES_CANTIDAD_RECIBIDAS = "denominacionesCantidadRecibidas";
	}
	
	@UtilityClass
	public static final class EntityGraph {
		public static final String WITH_TURNOS = "WITH_TURNOS";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;
	
	@Column(name = TableInfo.FECHA_OPERACION)
	@NonNull
	private LocalDateTime fechaOperacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.FX_BASE, referencedColumnName = FxEntity.TableInfo.ID)
	private FxEntity fxBase;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.FX_OPER, referencedColumnName = FxEntity.TableInfo.ID)
	private FxEntity fxOper;
	
	@Column(name = TableInfo.TIPO)
	@NonNull
	private String tipo;
	
	@Column(name = TableInfo.NOMINAL)
	@NonNull
	private BigDecimal nominal;

	@Column(name = TableInfo.VALOR_FX_OPERACION)
	@NonNull
	private BigDecimal valorFxOperacion;
	
	@Column(name = TableInfo.ESTADO)
	@NonNull
	private String estado;
	
	@Column(name = TableInfo.ID_SOIPC)
	@NonNull
	private String idSoipc;
	
	@Column(name = TableInfo.VALOR_VALORACION)
	@NonNull
	private BigDecimal valorValoracion;
	
	@Column(name = TableInfo.DESCRIPCION)
	@NonNull
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.TURNO, referencedColumnName = TurnoEntity.TableInfo.ID)
	private TurnoEntity turno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.TURNO_EDICION, referencedColumnName = TurnoEntity.TableInfo.ID)
	private TurnoEntity turnoEdicion;
	
	@OneToMany(mappedBy = DenominacionCantidadEntity.ClassInfo.OPERACION, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@NonNull
	private Set<DenominacionCantidadEntity> denominacionesCantidadEntregadas;
	
	@OneToMany(mappedBy = DenominacionCantidadEntity.ClassInfo.OPERACION, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@NonNull
	private Set<DenominacionCantidadEntity> denominacionesCantidadRecibidas;

}
