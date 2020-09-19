package co.com.fxmanager.auth.persistence.entities.jpa;

import java.util.List;
import java.util.Optional;

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
@Table(name = MenuEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
@NamedEntityGraphs({ @NamedEntityGraph(name = MenuEntity.EntityGraph.WITH_PARENT, attributeNodes = {
		@NamedAttributeNode(MenuEntity.ClassInfo.PARENT) }) })
public class MenuEntity {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "menu";
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String ORDINAL = "ordinal";
		public static final String PARENT = "parent";
		public static final String DESCRIPTION = "description";
		public static final String TARGET = "target";
	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String INDEX = "index";
		public static final String PARENT = "parent";
		public static final String DESCRIPTION = "description";
		public static final String TARGET = "target";
		public static final String FUNCTIONALITIES = "functionalities";
		public static final String CHILDREN = "children";
	}
	
	@UtilityClass
	public static final class EntityGraph {
		public static final String WITH_PARENT = "MENU_WITH_PARENT";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;
	@Column(name = TableInfo.NAME)
	@NonNull
	private String name;
	@Column(name = TableInfo.ORDINAL)
	@NonNull
	private Integer index;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.PARENT, referencedColumnName = MenuEntity.TableInfo.ID)
	private MenuEntity parent;
	@Column(name = TableInfo.DESCRIPTION)
	private String description;
	@Column(name = TableInfo.TARGET)
	private String target;

	@OneToMany(mappedBy = MenuEntity.ClassInfo.PARENT, fetch = FetchType.LAZY)
	@NonNull
	private List<MenuEntity> children;

	public Optional<MenuEntity> getParent() {
		return Optional.ofNullable(parent);
	}

	public Optional<String> getDescription() {
		return Optional.ofNullable(description);
	}

	public Optional<String> getTarget() {
		return Optional.ofNullable(target);
	}

}
