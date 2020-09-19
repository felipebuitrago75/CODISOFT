package co.com.fxmanager.auth.rest;

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

import co.com.fxmanager.auth.domain.entities.Functionality;
import co.com.fxmanager.auth.domain.entities.Role;
import co.com.fxmanager.auth.domain.services.FunctionalityService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FunctionalitiesController {

	private FunctionalityService functionalityService;

	@Autowired
	public FunctionalitiesController(FunctionalityService functionalityService) {
		super();
		this.functionalityService = functionalityService;
	}

	@GetMapping(value = "/functionalities", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Functionality> getFunctionalities(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(functionalityService.getFunctionalities(first, max));
	}
	
	@GetMapping(value = "/functionalities/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Functionality> getFunctionality(@PathVariable("name") String name) {
		return Mono.justOrEmpty(functionalityService.getFunctionality(name));
	}
	
	@PostMapping(value = "/functionalities", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Functionality> saveRole(@RequestBody Functionality functionality) {
		return Mono.justOrEmpty(functionalityService.save(functionality));
	}
	
	@PutMapping(value = "/functionalities/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Functionality> updateRole(@PathVariable("name") String name, @RequestBody Functionality functionality) {
		return Mono.justOrEmpty(functionalityService.update(name, functionality));
	}
	
	@DeleteMapping(value = "/functionalities/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Role> updateRole(@PathVariable("name") String name) {
		functionalityService.delete(name);
		return Mono.empty();
	}

}
