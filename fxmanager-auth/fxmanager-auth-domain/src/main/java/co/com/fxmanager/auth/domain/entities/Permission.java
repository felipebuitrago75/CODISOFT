package co.com.fxmanager.auth.domain.entities;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Permission {

	@NonNull
	protected Role role;

	@NonNull
	protected Functionality functionality;

	@NonNull
	protected List<AccessType> accessTypes;
	
	private Permission() {
		// Usado para la deseralización de Jackson
	}

	public Permission(@NonNull Role role, @NonNull Functionality functionality, @NonNull AccessType... accessType) {
		this.role = role;
		this.functionality = functionality;
		this.accessTypes = Arrays.asList(accessType);
	}

	public Permission(@NonNull Role role, @NonNull Functionality functionality, @NonNull List<AccessType> accessTypes) {
		this.role = role;
		this.functionality = functionality;
		this.accessTypes = accessTypes;
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
		Permission permission = (Permission) obj;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(this.getFunctionality(), permission.getFunctionality());
		return builder.build();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.getFunctionality());
		return builder.build();
	}
	

}
