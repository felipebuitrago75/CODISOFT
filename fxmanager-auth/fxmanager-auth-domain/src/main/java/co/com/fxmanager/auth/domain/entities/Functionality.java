package co.com.fxmanager.auth.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Functionality {

	@NonNull
	protected String name;

	protected String description;

	protected Menu menu;

	@NonNull
	protected List<Resource> resources;

	private Functionality() {
		// Usado para la deseralización de Jackson
	}

	public Functionality(@NonNull String name) {
		this.name = name;
	}

	public Functionality(@NonNull String name, @NonNull String description) {
		this(name);
		this.description = description;
	}

	public Functionality(@NonNull String name, @NonNull Menu menu) {
		this(name);
		this.menu = menu;
	}

	public Functionality(@NonNull String name, @NonNull String description, @NonNull Menu menu) {
		this(name, description);
		this.menu = menu;
	}

	public Optional<String> getDescription() {
		return Optional.ofNullable(description);
	}

	public Optional<Menu> getMenu() {
		return Optional.ofNullable(menu);
	}

	public void add(Resource resource) {
		if (getResources().isEmpty()) {
			setResources(new ArrayList<>());
		}
		getResources().add(resource);
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
		Functionality functionality = (Functionality) obj;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(this.getName(), functionality.getName());
		return builder.build();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.getName());
		return builder.build();
	}
}
