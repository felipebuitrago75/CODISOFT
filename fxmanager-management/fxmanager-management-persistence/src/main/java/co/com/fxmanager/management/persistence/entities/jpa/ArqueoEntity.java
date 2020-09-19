package co.com.fxmanager.management.persistence.entities.jpa;

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
@Table(name = ArqueoEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
@NamedEntityGraphs({ @NamedEntityGraph(name = ArqueoEntity.EntityGraph.WITH_ALL, attributeNodes = {
		@NamedAttributeNode(ArqueoEntity.ClassInfo.ITEMS_ARQUEO ),
		@NamedAttributeNode(ArqueoEntity.ClassInfo.TURNO )}) })
public class ArqueoEntity  {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "arqueo";
		public static final String ID = "id";
		public static final String FECHA = "fecha";
		public static final String TURNO = "turno";

	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String FECHA = "fecha";
		public static final String TURNO = "turno";
		public static final String ITEMS_ARQUEO = "itemsArqueo";
		
	}
	
	@UtilityClass
	public static final class EntityGraph {
		public static final String WITH_ALL = "WITH_ALL";
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
	@JoinColumn(name = TableInfo.TURNO, referencedColumnName = TurnoEntity.TableInfo.ID)
	private TurnoEntity turno;

	
	@OneToMany(mappedBy = ItemArqueoEntity.ClassInfo.ARQUEO, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@NonNull
	private Set<ItemArqueoEntity> itemsArqueo;

}
