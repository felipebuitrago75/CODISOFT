package co.com.fxmanager.management.domain.entities;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrasladoFiltro {

	
	protected Long id;
	protected String codigoFx;
	protected LocalDateTime fecha;
	protected String codigoSucursalOrigen;
	protected String codigoSucursalDestino;
	protected String estado;

}
