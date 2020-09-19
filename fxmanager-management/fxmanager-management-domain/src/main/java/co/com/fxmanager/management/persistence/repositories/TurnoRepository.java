package co.com.fxmanager.management.persistence.repositories;

import java.util.List;
import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Turno;

public interface TurnoRepository extends AbstractRepository<Turno> {

	public Turno update(Long id, Turno turno);

	public void delete(Long id);

	public Optional<Turno> getTurno(Long id);
	
	public Optional<Turno> obtenerTurnoActivo(String nombreUsuario);

	public List<Turno> getTurnosSucursal(Long idSucursal);
	
}

