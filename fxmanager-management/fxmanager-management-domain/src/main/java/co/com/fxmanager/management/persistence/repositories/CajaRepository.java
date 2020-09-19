package co.com.fxmanager.management.persistence.repositories;

import java.util.List;
import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Caja;

public interface CajaRepository extends AbstractRepository<Caja> {

	public Caja update(Long id, Caja caja);

	public void delete(Long id);

	public Optional<Caja> getCaja(Long id);
	
	public List<Caja> getCajasPorSucursal(String cod);
	
}
