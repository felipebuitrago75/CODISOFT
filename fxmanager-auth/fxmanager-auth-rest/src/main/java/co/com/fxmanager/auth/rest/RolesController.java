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

import co.com.fxmanager.auth.domain.entities.Role;
import co.com.fxmanager.auth.domain.services.RoleService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class RolesController {

	private RoleService roleService;

	@Autowired
	public RolesController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}

	@GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Role> getRoles(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(roleService.getRoles(first, max));
	}
	
	@GetMapping(value = "/roles/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Role> getRole(@PathVariable("name") String name) {
		return Mono.justOrEmpty(roleService.getRole(name));
	}
	
	@PostMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Role> saveRole(@RequestBody Role role) {
		return Mono.justOrEmpty(roleService.save(role));
	}
	
	@PutMapping(value = "/roles/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Role> updateRole(@PathVariable("name") String name, @RequestBody Role role) {
		return Mono.justOrEmpty(roleService.update(name, role));
	}
	
	@DeleteMapping(value = "/roles/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Role> updateRole(@PathVariable("name") String name) {
		roleService.delete(name);
		return Mono.empty();
	}

}
