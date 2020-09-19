package co.com.fxmanager.auth.persistence.entities.jpa;

import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Entity
@Table(name = ResourceEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
public class ResourceEntity {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "resource";
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String URI = "uri";
		public static final String ACCESS_TYPE = "access_type";
		public static final String DESCRIPTION = "description";
	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String URI = "uri";
		public static final String ACCESS_TYPE = "accessType";
		public static final String DESCRIPTION = "description";
		public static final String FUNCTIONALITIES = "functionalities";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;
	@Column(name = TableInfo.NAME)
	@NonNull
	private String name;
	@Column(name = TableInfo.URI)
	@NonNull
	private String uri;
	@Column(name = TableInfo.ACCESS_TYPE)
	@NonNull
	private String accessType;
	@Column(name = TableInfo.DESCRIPTION)
	private String description;

	@ManyToMany(mappedBy = FunctionalityEntity.ClassInfo.RESOURCES, fetch = FetchType.LAZY)
	@NonNull
	private List<FunctionalityEntity> functionalities;

	public Optional<String> getDescription() {
		return Optional.ofNullable(description);
	}

}
