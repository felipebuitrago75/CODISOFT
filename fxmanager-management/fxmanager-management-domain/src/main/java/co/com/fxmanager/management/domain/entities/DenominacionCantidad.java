package co.com.fxmanager.management.domain.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class DenominacionCantidad {

	@NonNull
	protected Integer cantidad;

	@NonNull
	protected Integer valor;

	@NonNull
	protected Long idFx;

	@SuppressWarnings("unused")
	private DenominacionCantidad() {
	}

	public DenominacionCantidad(Integer cantidad, Integer valor, Long idFx) {
		super();
		this.cantidad = cantidad;
		this.valor = valor;
		this.idFx = idFx;
	}

}
