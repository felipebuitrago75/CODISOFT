package co.com.fxmanager.management.domain.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Getter
@Setter
public class Fx {

	@UtilityClass
	public static final class ClassInfo {
		public static final String CODIGO = "codigo";
		public static final String CONCEPTO = "concepto";
		public static final String DENOMINACIONES = "denominaciones";
	}


	protected Long id;
	
	@NonNull
	protected String codigo;

	@NonNull
	protected String concepto;

	@NonNull
	private List<Integer> denominaciones;

	@SuppressWarnings("unused")
	private Fx() {
		// Usado para la deseralización de Jackson
	}

	public Fx(@NonNull String codigo, @NonNull String concepto) {
		super();
		this.codigo = codigo;
		this.concepto = concepto;
		this.denominaciones = new ArrayList<>();

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fx other = (Fx) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
}
