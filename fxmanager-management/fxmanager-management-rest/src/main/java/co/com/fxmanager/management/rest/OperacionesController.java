package co.com.fxmanager.management.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.fxmanager.management.domain.entities.Operacion;
import co.com.fxmanager.management.domain.entities.OperacionFiltro;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.domain.services.OperacionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OperacionesController {

	private OperacionService operacionService;

	@Autowired
	public OperacionesController(OperacionService operacionService) {
		super();
		this.operacionService = operacionService;
	}

	@GetMapping(value = "/operaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Operacion> getOperaciones(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(operacionService.getOperaciones(first, max));
	}
	
	@GetMapping(value = "/operaciones/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Operacion> getOperacion(@PathVariable("id") Long id) {
		return Mono.justOrEmpty(operacionService.getOperacion(id));
	}
	
	@GetMapping(value = "/operacionesPorTurno/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Operacion> getOperacionesPorTurno(@PathVariable("id") Long id) {
		return Flux.fromIterable(operacionService.getOperacionesPorTurno(id));
	}
	
	@GetMapping(value = "/operacionesUltimas", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Operacion> operacionesUltimas() {
		return Flux.fromIterable(operacionService.getOperacionesUltima());
	}
	
	@PutMapping(value = "/cancelar/operacion/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Operacion> cancelarOperacion(@PathVariable("id") Long id, @RequestBody Turno turno) {
		operacionService.cancelarOperacion(id, turno);
		return Mono.empty();
	}
	
	@PostMapping(value = "/operaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Operacion> saveOperaciones(@RequestBody Operacion operacion) {
		return Mono.justOrEmpty(operacionService.save(operacion));
	}
	
	@PutMapping(value = "/operaciones/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Operacion> updateOperacion(@PathVariable("id") Long id, @RequestBody Operacion operacion) {
		return Mono.justOrEmpty(operacionService.update(id, operacion));
	}
	
	@DeleteMapping(value = "/operaciones/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Operacion> deleteOperacion(@PathVariable("id") Long id) {
		operacionService.delete(id);
		return Mono.empty();
	}
	@PostMapping(value = "/buscarOperaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Operacion> obtenerConFiltro(@RequestBody OperacionFiltro operacion) {
		 return Flux.fromIterable(operacionService.buscarConFiltro(operacion));
	}
}
