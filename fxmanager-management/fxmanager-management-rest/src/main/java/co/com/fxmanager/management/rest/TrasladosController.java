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
import co.com.fxmanager.management.domain.entities.Traslado;
import co.com.fxmanager.management.domain.entities.TrasladoFiltro;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.domain.services.TrasladoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TrasladosController {

	private TrasladoService trasladoService;

	@Autowired
	public TrasladosController(TrasladoService trasladoService) {
		super();
		this.trasladoService = trasladoService;
	}

	@GetMapping(value = "/traslados", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Traslado> getOperaciones(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(trasladoService.getTraslados(first, max));
	}
	
	@GetMapping(value = "/traslados/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Traslado> getOperacion(@PathVariable("id") Long id) {
		return Mono.justOrEmpty(trasladoService.getTraslado(id));
	}
	
	@PostMapping(value = "/traslados", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Traslado> saveOperaciones(@RequestBody Traslado traslado) {
		return Mono.justOrEmpty(trasladoService.save(traslado));
	}
	
	@PutMapping(value = "/traslados/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Traslado> updateOperacion(@PathVariable("id") Long id, @RequestBody Turno turno) {
		return Mono.justOrEmpty(trasladoService.update(id, turno));
	}
	
	@DeleteMapping(value = "/traslados/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Traslado> delete(@PathVariable("id") Long id) {
		trasladoService.delete(id);
		return Mono.empty();
	}
	
	@PutMapping(value = "/cancelarTraslados/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Operacion> cancelarOperacion(@PathVariable("id") Long id, @RequestBody Turno turno) {
		trasladoService.cancelarTraslado(id, turno);
		return Mono.empty();
	}
	
	@PostMapping(value = "/buscarTraslados", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Traslado> obtenerConFiltro(@RequestBody TrasladoFiltro trasladoFiltro) {
		 return Flux.fromIterable(trasladoService.obtenerConFiltro(trasladoFiltro));
	}


}
