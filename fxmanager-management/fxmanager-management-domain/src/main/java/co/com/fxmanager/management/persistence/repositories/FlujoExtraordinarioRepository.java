package co.com.fxmanager.management.persistence.repositories;
import java.util.List;
import java.util.Optional;

import co.com.fxmanager.management.domain.entities.FlujoExtraordinario;
import co.com.fxmanager.management.domain.entities.FlujoFiltro;
import co.com.fxmanager.management.domain.entities.Turno;

public interface FlujoExtraordinarioRepository extends AbstractRepository<FlujoExtraordinario> {
	
	public FlujoExtraordinario update(Long id, FlujoExtraordinario flujoExtraordinario);

	public void delete(Long id);

	public Optional<FlujoExtraordinario> getFlujoExtraordinario(Long id);

	public List<FlujoExtraordinario> listaFlujosPorTurno(Turno turno);
	
	public void cancelarFlujo(Long id, Turno turno);

	public List<FlujoExtraordinario> obtenerConFiltro(FlujoFiltro flujoFiltro);
}
