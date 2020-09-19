package co.com.fxmanager.management.persistence.repositories;

import java.util.List;
import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Traslado;
import co.com.fxmanager.management.domain.entities.TrasladoFiltro;
import co.com.fxmanager.management.domain.entities.Turno;

public interface TrasladoRepository extends AbstractRepository<Traslado> {

	public Traslado update(Long id, Turno turno);

	public void delete(Long id);

	public Optional<Traslado> getTraslado(Long id);
	
	public void cancelarTraslado(Long id, Turno turno);
	
	public List<Traslado> obtenerConFiltro(TrasladoFiltro trasladoFiltro);
}
