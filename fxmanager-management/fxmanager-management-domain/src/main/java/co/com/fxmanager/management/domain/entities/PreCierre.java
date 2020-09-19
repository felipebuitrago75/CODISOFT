package co.com.fxmanager.management.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreCierre {



	protected Long id;
	
	protected Caja caja;
	
	protected Usuario usuario;
	
	protected Turno turno;
	
	protected LocalDateTime fecha;

	protected List<ItemPreCierre> listaItems;

	
}
