package co.com.fxmanager.management.domain.entities;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlujoFiltro {

	
	protected Long id;
	protected String codigoFx;
	protected LocalDateTime fecha;
	protected String tipo;
	protected String estado;
	protected String criterio;

}
