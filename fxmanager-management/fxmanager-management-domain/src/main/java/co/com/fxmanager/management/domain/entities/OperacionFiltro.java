package co.com.fxmanager.management.domain.entities;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperacionFiltro {

	
	protected Long id;
	protected String codigoFx;
	protected LocalDateTime fechaOperacion;
	protected String tipo;
	protected String estado;

}
