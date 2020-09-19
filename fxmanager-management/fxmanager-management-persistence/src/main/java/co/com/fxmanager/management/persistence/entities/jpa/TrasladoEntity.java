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
@Table(name = TrasladoEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
@NamedEntityGraphs({ @NamedEntityGraph(name = TrasladoEntity.EntityGraph.WITH_SUCURSALES_Y_FX, attributeNodes = {
		@NamedAttributeNode(TrasladoEntity.ClassInfo.SUCURSAL_ORIGEN ),
		@NamedAttributeNode(TrasladoEntity.ClassInfo.SUCURSAL_DESTINO ),
		@NamedAttributeNode(TrasladoEntity.ClassInfo.DENOMINACIONES_CANTIDAD ),
		@NamedAttributeNode(TrasladoEntity.ClassInfo.FX )}) })
public class TrasladoEntity  {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "traslado";
		public static final String ID = "id";
		public static final String FECHA = "fecha";
		public static final String SUCURSAL_ORIGEN = "sucursal_origen";
		public static final String SUCURSAL_DESTINO = "sucursal_destino";
		public static final String TIPO_TRASLADO = "tipo_traslado";
		public static final String TURNO_DESTINO = "turno_destino";
		public static final String TURNO_ORIGEN = "turno_origen";
		public static final String ESTADO = "estado";
		public static final String PRECIO_TRASLADO = "precio_traslado";
		public static final String VALOR_GIRO = "valor_giro";
		public static final String FX = "fx";

	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String FECHA = "fecha";
		public static final String SUCURSAL_ORIGEN = "sucursalOrigen";
		public static final String SUCURSAL_DESTINO = "sucursalDestino";
		public static final String TIPO_TRASLADO = "tipoTraslado";
		public static final String TURNO_ORIGEN = "turnoOrigen";
		public static final String TURNO_DESTINO = "turnoDestino";
		public static final String ESTADO = "estado";
		public static final String PRECIO_TRASLADO = "precioTraslado";
		public static final String VALOR_GIRO = "valorGiro";
		public static final String FX = "fx";
		public static final String DENOMINACIONES_CANTIDAD = "denominacionesCantidad";
		
	}
	
	@UtilityClass
	public static final class EntityGraph {
		public static final String WITH_SUCURSALES_Y_FX = "TRASLADO_WITH_SUCURSALES_Y_FX";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;
	
	@Column(name = TableInfo.FECHA)
	@NonNull
	private LocalDateTime fecha;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.SUCURSAL_ORIGEN, referencedColumnName = SucursalEntity.TableInfo.ID)
	@NonNull
	private SucursalEntity sucursalOrigen;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.SUCURSAL_DESTINO, referencedColumnName = SucursalEntity.TableInfo.ID)
	@NonNull
	private SucursalEntity sucursalDestino;
	
	@Column(name = TableInfo.TIPO_TRASLADO)
	@NonNull
	private String tipoTraslado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.TURNO_DESTINO, referencedColumnName = TurnoEntity.TableInfo.ID)
	private TurnoEntity turnoDestino;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.TURNO_ORIGEN, referencedColumnName = TurnoEntity.TableInfo.ID)
	private TurnoEntity turnoOrigen;
	
	@Column(name = TableInfo.ESTADO)
	@NonNull
	private String estado;
	
	@Column(name = TableInfo.PRECIO_TRASLADO)
	@NonNull
	private BigDecimal precioTraslado;
	
	@Column(name = TableInfo.VALOR_GIRO)
	@NonNull
	private BigDecimal valorGiro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.FX, referencedColumnName = FxEntity.TableInfo.ID)
	private FxEntity fx;
	
	@OneToMany(mappedBy = DenominacionCantidadEntity.ClassInfo.TRASLADO, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@NonNull
	private Set<DenominacionCantidadEntity> denominacionesCantidad;

}
