package co.com.fxmanager.management.persistence.repositories;

import java.util.List;
import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Operacion;
import co.com.fxmanager.management.domain.entities.OperacionFiltro;
import co.com.fxmanager.management.domain.entities.Turno;

public interface OperacionRepository extends AbstractRepository<Operacion> {

	public Operacion update(Long id, Operacion operacion);

	public void delete(Long id);

	public Optional<Operacion> getOperacion(Long id);

	public Operacion cancelarOperacion(Long id, Turno turno);
	
	public List<Operacion> ObtenerListaOperacionesPorTurnoYDivisa(Turno turno, Long idfxOper);

	public List<Operacion> buscarConFiltro(OperacionFiltro operacion);

	public List<Operacion> getOperacionesPorTurno(Long id);

	public List<Operacion> getOperacionesUltimas();
}
