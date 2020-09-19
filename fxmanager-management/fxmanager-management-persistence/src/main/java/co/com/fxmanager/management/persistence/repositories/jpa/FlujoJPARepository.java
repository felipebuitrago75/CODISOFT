package co.com.fxmanager.management.persistence.repositories.jpa;

import java.util.List;

import co.com.fxmanager.management.domain.entities.FlujoFiltro;
import co.com.fxmanager.management.persistence.entities.jpa.FlujoExtraordinarioEntity;

public interface FlujoJPARepository {

	public List<FlujoExtraordinarioEntity> obtenerConFiltro(FlujoFiltro filtro);
	
}
