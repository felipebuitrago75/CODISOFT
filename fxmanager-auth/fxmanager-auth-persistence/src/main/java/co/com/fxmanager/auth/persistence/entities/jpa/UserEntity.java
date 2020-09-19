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
@Table(name = UserEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
@NamedEntityGraphs({ @NamedEntityGraph(name = UserEntity.EntityGraph.WITH_ROLE, attributeNodes = {
		@NamedAttributeNode(UserEntity.ClassInfo.ROLE) }) })
public class UserEntity {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "user";
		public static final String ID = "id";
		public static final String USERNAME = "username";
		public static final String PASSWORD = "password";
		public static final String SALT = "salt";
		public static final String ENABLED = "enabled";
		public static final String LOCKED = "locked";
		public static final String FAILED_LOGINS = "failed_logins";
		public static final String ROLE = "role";
	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String USERNAME = "username";
		public static final String PASSWORD = "password";
		public static final String SALT = "salt";
		public static final String ENABLED = "enabled";
		public static final String LOCKED = "locked";
		public static final String FAILED_LOGINS = "failedLogins";
		public static final String ROLE = "role";
		public static final String SESSIONS = "sessions";
	}

	@UtilityClass
	public static final class EntityGraph {
		public static final String WITH_ROLE = "USER_WITH_ROLE";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;
	@Column(name = TableInfo.USERNAME)
	@NonNull
	private String username;
	@Column(name = TableInfo.PASSWORD)
	@NonNull
	private String password;
	@Column(name = TableInfo.SALT)
	@NonNull
	private String salt;
	@Column(name = TableInfo.ENABLED)
	@NonNull
	private Boolean enabled;
	@Column(name = TableInfo.LOCKED)
	@NonNull
	private Boolean locked;
	@Column(name = TableInfo.FAILED_LOGINS)
	@NonNull
	private Integer failedLogins;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.ROLE, referencedColumnName = RoleEntity.TableInfo.ID)
	private RoleEntity role;

	@OneToMany(mappedBy = SessionEntity.ClassInfo.USER, fetch = FetchType.LAZY)
	@NonNull
	private List<SessionEntity> sessions;

}
