package co.com.fxmanager.management.rest;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.fxmanager.management.domain.entities.Cierre;
import co.com.fxmanager.management.domain.entities.CierreConsolidado;
import co.com.fxmanager.management.domain.entities.ReporteEstadoActual;
import co.com.fxmanager.management.domain.entities.ReportePortafolio;
import co.com.fxmanager.management.domain.services.CierreService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CierresContoller {
	
	private CierreService cierreService;
	
	@Autowired
	public CierresContoller(CierreService cierreService) {
		super();
		this.cierreService = cierreService;
	}
	
	@GetMapping(value = "/cierres", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Cierre> getAll(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(cierreService.getCierres(first, max));
	}

	@GetMapping(value = "/cierres/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Cierre> get(@PathVariable("id") Long id) {
		return Mono.justOrEmpty(cierreService.getCierre(id));
	}

	@PostMapping(value = "/cierres", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Cierre> save() {
		//return Flux.fromIterable(cierreService.realizarCierre(LocalDate.of(2019, 07, 19)));
		return Flux.fromIterable(cierreService.realizarCierre(LocalDate.now(ZoneId.of("America/Bogota"))));
	}
	
	@PostMapping(value = "/realizarCierresDeUnaFecha", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Cierre> save(@RequestBody LocalDate fecha) {
		return Flux.fromIterable(cierreService.realizarCierre(fecha));
	}

	@PutMapping(value = "/obtenerCierresDia/{idSucursal}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Cierre> obtenerCierresDia( @PathVariable("idSucursal") Long idSucursal) {
		return  Flux.fromIterable(cierreService.obtenerCierresDia(LocalDate.now(ZoneId.of("America/Bogota")), idSucursal));
	}
	
	@PutMapping(value = "/obtenerCierresSucursalPorFecha/{idSucursal}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Cierre> obtenerCierresSucursalPorFecha( @PathVariable("idSucursal") Long idSucursal,  @RequestBody LocalDate fecha) {
		return  Flux.fromIterable(cierreService.obtenerCierresDia(fecha, idSucursal));
	}
	
	@PutMapping(value = "/obtenerCierresConsolidadoPorFecha", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<CierreConsolidado> obtenerCierresSucursalPorFecha(@RequestBody LocalDate fecha) {
		return  Flux.fromIterable(cierreService.obtenerCierreConsolidadoDia(fecha));
	}
	
	@PutMapping(value = "/reportePortafolioPorFecha", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<ReportePortafolio> reportePortafolioPorFecha(@RequestBody LocalDate fecha) {
		return  Flux.fromIterable(cierreService.reportePortafolioPorFecha(fecha));
	}
	
	@PutMapping(value = "/reporteEstadoActual", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<ReporteEstadoActual> reporteEstadoActual() {
		return  Flux.fromIterable(cierreService.reporteEstadoActual());
	}
	
	@PutMapping(value = "/reporteEstadoActualDetallado", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<ReporteEstadoActual> reporteEstadoActualDetallado() {
		return  Flux.fromIterable(cierreService.reporteEstadoActualDetallado());
	}
}
