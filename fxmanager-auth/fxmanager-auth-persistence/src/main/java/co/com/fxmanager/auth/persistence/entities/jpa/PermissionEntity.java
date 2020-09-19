package co.com.fxmanager.auth.persistence.entities.jpa;

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
@Table(name = PermissionEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
public class PermissionEntity {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "permission";
		public static final String ID = "id";
		public static final String ROLE = "role";
		public static final String FUNCTIONALITY = "functionality";
		public static final String ACCESS_TYPES = "access_types";
	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String ROLE = "role";
		public static final String FUNCTIONALITY = "functionality";
		public static final String ACCESS_TYPES = "accessTypes";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.ROLE, referencedColumnName = RoleEntity.TableInfo.ID)
	@NonNull
	private RoleEntity role;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.FUNCTIONALITY, referencedColumnName = FunctionalityEntity.TableInfo.ID)
	@NonNull
	private FunctionalityEntity functionality;
	@Column(name = TableInfo.ACCESS_TYPES)
	@NonNull
	private String accessTypes;

}
