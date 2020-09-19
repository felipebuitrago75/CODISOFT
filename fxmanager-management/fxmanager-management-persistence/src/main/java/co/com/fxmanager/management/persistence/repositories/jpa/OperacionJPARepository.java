package co.com.fxmanager.management.persistence.repositories.jpa;

import java.util.List;

import co.com.fxmanager.management.domain.entities.OperacionFiltro;
import co.com.fxmanager.management.persistence.entities.jpa.OperacionEntity;

public interface OperacionJPARepository {

	public List<OperacionEntity> obtenerConFiltro(OperacionFiltro operacion);
	
	public List<OperacionEntity> operacionesUltimas();
}
