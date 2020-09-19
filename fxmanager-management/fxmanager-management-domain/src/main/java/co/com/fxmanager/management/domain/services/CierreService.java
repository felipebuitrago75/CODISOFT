package co.com.fxmanager.management.domain.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Cierre;
import co.com.fxmanager.management.domain.entities.CierreConsolidado;
import co.com.fxmanager.management.domain.entities.ReporteEstadoActual;
import co.com.fxmanager.management.domain.entities.ReportePortafolio;
import co.com.fxmanager.management.persistence.repositories.CierreRepository;
import lombok.Getter;
import lombok.NonNull;

public class CierreService {
	
	@Getter
	private CierreRepository  cierreRepository;
	
	public CierreService (@NonNull CierreRepository  cierreRepository) {
		super();
		this.cierreRepository = cierreRepository;
	}
	
	public List<Cierre> realizarCierre(LocalDate fecha) {
		return getCierreRepository().realizarCierre(fecha);
	}

	public List<Cierre> getCierres(Integer first, Integer max){
		return this.getCierreRepository().getList(first, max);
	}
	
	public Optional<Cierre> getCierre(Long id){
		return this.getCierreRepository().getCierre(id);
	}
	
	public List<Cierre> obtenerCierresDia(LocalDate fechaCierre, Long idSucursal){
		return this.getCierreRepository().obtenerCierresDia(fechaCierre, idSucursal);
	}

	public List<CierreConsolidado> obtenerCierreConsolidadoDia(LocalDate fecha) {
		return this.getCierreRepository().obtenerCierreConsolidadoDia(fecha);
	}

	public List<ReportePortafolio> reportePortafolioPorFecha(LocalDate fecha) {
		return this.getCierreRepository().reportePortafolioPorFecha(fecha);
		
	}

	public List<ReporteEstadoActual> reporteEstadoActual() {
		return this.getCierreRepository().reporteEstadoActual();
	}

	public List<ReporteEstadoActual> reporteEstadoActualDetallado() {
		return this.getCierreRepository().reporteEstadoActualDetallado();
	}

}
