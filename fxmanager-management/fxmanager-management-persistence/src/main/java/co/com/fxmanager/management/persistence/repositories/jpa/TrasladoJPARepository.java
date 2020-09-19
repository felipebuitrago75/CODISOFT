package co.com.fxmanager.management.persistence.repositories.jpa;

import java.util.List;

import co.com.fxmanager.management.domain.entities.TrasladoFiltro;
import co.com.fxmanager.management.persistence.entities.jpa.TrasladoEntity;

public interface TrasladoJPARepository {

	public List<TrasladoEntity> obtenerConFiltro(TrasladoFiltro trasladoFiltro);
	
}
