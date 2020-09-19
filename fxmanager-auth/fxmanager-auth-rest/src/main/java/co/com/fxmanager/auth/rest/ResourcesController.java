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

import co.com.fxmanager.auth.domain.entities.Resource;
import co.com.fxmanager.auth.domain.services.ResourceService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ResourcesController {

	private ResourceService resourceService;

	@Autowired
	public ResourcesController(ResourceService resourceService) {
		super();
		this.resourceService = resourceService;
	}

	@GetMapping(value = "/resources", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Resource> getResources(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(resourceService.getResources(first, max));
	}
	
	@GetMapping(value = "/resources/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Resource> getResource(@PathVariable("name") String name) {
		return Mono.justOrEmpty(resourceService.getResource(name));
	}
	
	@PostMapping(value = "/resources", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Resource> saveResource(@RequestBody Resource resource) {
		return Mono.justOrEmpty(resourceService.save(resource));
	}
	
	@PutMapping(value = "/resources/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Resource> updateResource(@PathVariable("name") String name, @RequestBody Resource resource) {
		return Mono.justOrEmpty(resourceService.update(name, resource));
	}
	
	@DeleteMapping(value = "/resources/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Resource> updateResource(@PathVariable("name") String name) {
		resourceService.delete(name);
		return Mono.empty();
	}

}
