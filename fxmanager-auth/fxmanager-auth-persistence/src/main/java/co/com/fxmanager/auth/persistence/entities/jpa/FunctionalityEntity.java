package co.com.fxmanager.auth.persistence.entities.jpa;

import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Entity
@Table(name = FunctionalityEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
@NamedEntityGraphs({ @NamedEntityGraph(name = FunctionalityEntity.EntityGraph.WITH_MENU_RESOURCES, attributeNodes = {
		@NamedAttributeNode(FunctionalityEntity.ClassInfo.MENU),
		@NamedAttributeNode(FunctionalityEntity.ClassInfo.RESOURCES) }) })
public class FunctionalityEntity {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "functionality";
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String DESCRIPTION = "description";
		public static final String MENU = "menu";
	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String DESCRIPTION = "description";
		public static final String MENU = "menu";
		public static final String RESOURCES = "resources";
		public static final String PERMISSIONS = "permissions";
	}

	@UtilityClass
	public static final class EntityGraph {
		public static final String WITH_MENU_RESOURCES = "FUNCTIONALITY_WITH_MENU_RESOURCES";
	}

	@Id
	@Column(name = TableInfo.ID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private Long id;
	@Column(name = TableInfo.NAME)
	@NonNull
	private String name;
	@Column(name = TableInfo.DESCRIPTION)
	@NonNull
	private String description;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.MENU, referencedColumnName = MenuEntity.TableInfo.ID)
	private MenuEntity menu;

	@JoinTable(name = FunctionalityResourceEntity.TableInfo.TABLE_NAME, joinColumns = {
			@JoinColumn(name = FunctionalityResourceEntity.TableInfo.FUNCTIONALITY, referencedColumnName = FunctionalityEntity.TableInfo.ID) }, inverseJoinColumns = {
					@JoinColumn(name = FunctionalityResourceEntity.TableInfo.RESOURCE, referencedColumnName = ResourceEntity.TableInfo.ID) })
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@NonNull
	private List<ResourceEntity> resources;

	@OneToMany(mappedBy = PermissionEntity.ClassInfo.FUNCTIONALITY, fetch = FetchType.LAZY)
	@NonNull
	private List<PermissionEntity> permissions;

	public Optional<MenuEntity> getMenu() {
		return Optional.ofNullable(menu);
	}

}
