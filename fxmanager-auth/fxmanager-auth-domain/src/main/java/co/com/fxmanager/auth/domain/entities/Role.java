package co.com.fxmanager.auth.domain.entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Role {

	@NonNull
	protected String name;

	@NonNull
	protected List<Permission> permissions;

	@SuppressWarnings("unused")
	private Role() {
		// Usado para la deseralización de Jackson
	}

	public Role(@NonNull String name) {
		this.name = name;
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
		Role role = (Role) obj;
		EqualsBuilder builder = new EqualsBuilder();
		return builder.append(this.getName(), role.getName()).build();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		return builder.append(this.getName()).build();
	}

}
