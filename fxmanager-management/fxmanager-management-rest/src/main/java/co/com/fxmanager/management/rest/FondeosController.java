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

import co.com.fxmanager.management.domain.entities.Fondeo;
import co.com.fxmanager.management.domain.services.FondeoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FondeosController {

	private FondeoService fondeoService;

	@Autowired
	public FondeosController(FondeoService fondeoService) {
		super();
		this.fondeoService = fondeoService;
	}

	
	@GetMapping(value = "/fondeos", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Fondeo> getFondeos(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(fondeoService.getFondeos(first, max));
	}
	
	@GetMapping(value = "/fondeos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Fondeo> getFondeo(@PathVariable("id") Long id) {
		return Mono.justOrEmpty(fondeoService.getFondeo(id));
	}
	
	@PostMapping(value = "/fondeos", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Fondeo> save(@RequestBody Fondeo fondeo) {
		return Mono.justOrEmpty(fondeoService.save(fondeo));
	}
	
	@PutMapping(value = "/fondeos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Fondeo> update(@PathVariable("id") Long id, @RequestBody Fondeo fondeo) {
		return Mono.justOrEmpty(fondeoService.update(id, fondeo));
	}
	
	@DeleteMapping(value = "/fondeos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Fondeo> delete(@PathVariable("id") Long id) {
		fondeoService.delete(id);
		return Mono.empty();
	}

}
