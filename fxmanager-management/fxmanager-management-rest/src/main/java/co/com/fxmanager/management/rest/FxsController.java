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

import co.com.fxmanager.management.domain.entities.Fx;
import co.com.fxmanager.management.domain.services.FxService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FxsController {

	private FxService fxService;

	@Autowired
	public FxsController(FxService fxService) {
		super();
		this.fxService = fxService;
	}

	@GetMapping(value = "/fxs", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Fx> getFxs(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(fxService.getFxs(first, max));
	}

	@GetMapping(value = "/fxs/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Fx> getFx(@PathVariable("codigo") String codigo) {
		return Mono.justOrEmpty(fxService.getFx(codigo));
	}

	@PostMapping(value = "/fxs", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Fx> saveFx(@RequestBody Fx fx) {
		return Mono.justOrEmpty(fxService.save(fx));
	}

	@PutMapping(value = "/fxs/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Fx> updateFx(@PathVariable("codigo") String codigo, @RequestBody Fx fx) {
		return Mono.justOrEmpty(fxService.update(codigo, fx));
	}

	@DeleteMapping(value = "/fxs/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Fx> deleteFx(@PathVariable("codigo") String codigo) {
		fxService.delete(codigo);
		return Mono.empty();
	}

}
