package co.com.fxmanager.management.persistence.repositories;

import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Sucursal;

public interface SucursalRepository extends AbstractRepository<Sucursal> {

	public Sucursal update(String cod, Sucursal sucursal);

	public void delete(String cod);

	public Optional<Sucursal> getSucursal(String cod);
	
}
