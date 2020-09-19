package co.com.fxmanager.auth.domain.entities;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Getter
@Setter
public class Resource {

	@UtilityClass
	public static final class ClassInfo {
		public static final String NAME = "name";
		public static final String URI = "uri";
		public static final String ACCESS_TYPE = "accessType";
		public static final String DESCRIPTION = "description";
		public static final String FUNCTIONALITIES = "functionalities";
	}

	@NonNull
	protected String name;

	@NonNull
	protected String uri;

	@NonNull
	protected AccessType accessType;

	protected String description;

	protected List<Functionality> functionalities;

	@SuppressWarnings("unused")
	private Resource() {
		//Usado para la deseralización de Jackson
	}
	
	
	public Resource(@NonNull String name, @NonNull String uri, @NonNull AccessType accessType) {
		this.name = name;
		this.uri = uri;
		this.accessType = accessType;
	}

	public Resource(@NonNull String name, @NonNull String uri, @NonNull AccessType accessType,
			@NonNull String description) {
		this(name, uri, accessType);
		this.description = description;
	}

	public Optional<String> getDescription() {
		return Optional.ofNullable(description);
	}

	public Optional<List<Functionality>> getFunctionalities() {
		return Optional.ofNullable(functionalities);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Resource resource = (Resource) obj;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(this.getName(), resource.getName());
		return builder.build();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.getName());
		return builder.build();
	}

}
