package co.com.fxmanager.management.persistence.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Cierre;
import co.com.fxmanager.management.domain.entities.CierreConsolidado;
import co.com.fxmanager.management.domain.entities.ReporteEstadoActual;
import co.com.fxmanager.management.domain.entities.ReportePortafolio;

public interface CierreRepository extends AbstractRepository<Cierre> {
	
	public Optional<Cierre> getCierre(Long id);
	
	public List<Cierre> realizarCierre(LocalDate fechaCierre);
	
	public List<Cierre> obtenerCierresDia(LocalDate fechaCierre, Long idSucursal);

	public List<CierreConsolidado> obtenerCierreConsolidadoDia(LocalDate fecha);

	public List<ReportePortafolio> reportePortafolioPorFecha(LocalDate fecha);

	public List<ReporteEstadoActual> reporteEstadoActual();

	public List<ReporteEstadoActual> reporteEstadoActualDetallado();

}
