package co.com.fxmanager.auth.persistence.entities.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = RoleEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
@NamedEntityGraphs({ @NamedEntityGraph(name = RoleEntity.EntityGraph.WITH_PERMISSIONS, attributeNodes = {
		@NamedAttributeNode(RoleEntity.ClassInfo.PERMISSIONS) })
})
public class RoleEntity  {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "role";
		public static final String ID = "id";
		public static final String NAME = "name";
	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String USERS = "users";
		public static final String PERMISSIONS = "permissions";
	}
	
	@UtilityClass
	public static final class EntityGraph {
		public static final String WITH_PERMISSIONS = "ROLE_WITH_PERMISSIONS";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;
	
	@Column(name = TableInfo.NAME)
	@NonNull
	private String name;

	@OneToMany(mappedBy = UserEntity.ClassInfo.ROLE, fetch = FetchType.LAZY)
	@NonNull
	private List<UserEntity> users;
	
	@OneToMany(mappedBy = PermissionEntity.ClassInfo.ROLE, fetch = FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
	@NonNull
	private List<PermissionEntity> permissions;

}
