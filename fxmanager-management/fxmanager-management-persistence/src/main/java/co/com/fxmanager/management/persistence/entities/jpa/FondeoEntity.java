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
@Table(name = FondeoEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
@NamedEntityGraphs({ @NamedEntityGraph(name = FondeoEntity.EntityGraph.WITH_SUCURSALES_Y_FX, attributeNodes = {
		@NamedAttributeNode(FondeoEntity.ClassInfo.TURNO_ORIGEN ),
		@NamedAttributeNode(FondeoEntity.ClassInfo.TURNO_DESTINO ),
		@NamedAttributeNode(FondeoEntity.ClassInfo.DENOMINACIONES_CANTIDAD ),
		@NamedAttributeNode(FondeoEntity.ClassInfo.FX )}) })
public class FondeoEntity  {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "fondeo";
		public static final String ID = "id";
		public static final String FECHA = "fecha";
		public static final String SUCURSAL = "sucursal";
		public static final String TURNO_ORIGEN = "turno_origen";
		public static final String TURNO_DESTINO = "turno_destino";
		public static final String ESTADO = "estado";
		public static final String VALOR_GIRO = "valor_giro";
		public static final String FX = "fx";

	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String FECHA = "fecha";
		public static final String SUCURSAL = "sucursal";
		public static final String TURNO_ORIGEN = "turnoOrigen";
		public static final String TURNO_DESTINO = "turnoDestino";
		public static final String ESTADO = "estado";
		public static final String VALOR_GIRO = "valorGiro";
		public static final String FX = "fx";
		public static final String DENOMINACIONES_CANTIDAD = "denominacionesCantidad";
		
	}
	
	@UtilityClass
	public static final class EntityGraph {
		public static final String WITH_SUCURSALES_Y_FX = "FONDEO_WITH_SUCURSALES_Y_FX";
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
	@JoinColumn(name = TableInfo.TURNO_DESTINO, referencedColumnName = TurnoEntity.TableInfo.ID)
	private TurnoEntity turnoDestino;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.TURNO_ORIGEN, referencedColumnName = TurnoEntity.TableInfo.ID)
	private TurnoEntity turnoOrigen;
	
	@Column(name = TableInfo.ESTADO)
	@NonNull
	private String estado;
	
	@Column(name = TableInfo.VALOR_GIRO)
	@NonNull
	private BigDecimal valorGiro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.FX, referencedColumnName = FxEntity.TableInfo.ID)
	private FxEntity fx;
	
	@OneToMany(mappedBy = DenominacionCantidadEntity.ClassInfo.FONDEO, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@NonNull
	private Set<DenominacionCantidadEntity> denominacionesCantidad;

}
