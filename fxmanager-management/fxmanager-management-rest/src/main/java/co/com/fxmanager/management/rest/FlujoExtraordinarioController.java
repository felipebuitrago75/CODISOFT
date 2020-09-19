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

import co.com.fxmanager.management.domain.entities.FlujoExtraordinario;
import co.com.fxmanager.management.domain.entities.FlujoFiltro;
import co.com.fxmanager.management.domain.entities.Operacion;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.domain.services.FlujoExtraordinarioService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FlujoExtraordinarioController {
	
	private FlujoExtraordinarioService flujoExtraordinarioService;
	
	@Autowired
	public FlujoExtraordinarioController(FlujoExtraordinarioService flujoExtraordinarioService) {
		super();
		this.flujoExtraordinarioService = flujoExtraordinarioService;
	}
	
	@GetMapping(value = "/flujosExtraordinarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<FlujoExtraordinario> getFlujosExtraordinarios(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(flujoExtraordinarioService.getFlujoExtraordinarios(first, max));
	}
	
	@GetMapping(value = "/flujosExtraordinarios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<FlujoExtraordinario> getFlujoExtraordinario(@PathVariable("id") Long id) {
		return Mono.justOrEmpty(flujoExtraordinarioService.getFlujoExtraordinario(id));
	}
	
	@PostMapping(value = "/flujosExtraordinarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<FlujoExtraordinario> saveFlujoExtraordinario(@RequestBody FlujoExtraordinario flujoExtraordinario) {
		return Mono.justOrEmpty(flujoExtraordinarioService.save(flujoExtraordinario));
	}
	
	@PutMapping(value = "/flujosExtraordinarios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<FlujoExtraordinario> updateFlujoExtaordinario(@PathVariable("id") Long id, @RequestBody FlujoExtraordinario flujoExtraordinario) {
		return Mono.justOrEmpty(flujoExtraordinarioService.update(id, flujoExtraordinario));
	}
	
	@DeleteMapping(value = "/flujosExtraordinarios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<FlujoExtraordinario> deleteOperacion(@PathVariable("id") Long id) {
		flujoExtraordinarioService.delete(id);
		return Mono.empty();
	}
	
	@PutMapping(value = "/cancelarflujosExtraordinarios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Operacion> cancelarOperacion(@PathVariable("id") Long id, @RequestBody Turno turno) {
		flujoExtraordinarioService.cancelarFlujo(id, turno);
		return Mono.empty();
	}
	
	@PostMapping(value = "/buscarFlujos", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<FlujoExtraordinario> obtenerConFiltro(@RequestBody FlujoFiltro flujoFiltro) {
		 return Flux.fromIterable(flujoExtraordinarioService.obtenerConFiltro(flujoFiltro));
	}

}
