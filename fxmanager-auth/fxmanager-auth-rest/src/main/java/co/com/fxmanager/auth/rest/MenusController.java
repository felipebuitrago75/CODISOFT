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

import co.com.fxmanager.auth.domain.entities.Menu;
import co.com.fxmanager.auth.domain.entities.Role;
import co.com.fxmanager.auth.domain.services.MenuService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MenusController {

	private MenuService menuService;

	@Autowired
	public MenusController(MenuService menuService) {
		super();
		this.menuService = menuService;
	}

	@GetMapping(value = "/menus", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Menu> getMenus(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(menuService.getMenus(first, max));
	}
	
	@GetMapping(value = "/menus/for/functionalities", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Menu> getMenusForFunctionalities(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(menuService.getMenusForFunctionalities(first, max));
	}
	
	@GetMapping(value = "/menus/for/parents", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Menu> getMenusForParent(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(menuService.getMenusForParent(first, max));
	}
	
	@GetMapping(value = "/menus/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Menu> getMenu(@PathVariable("name") String name) {
		return Mono.justOrEmpty(menuService.getMenu(name));
	}
	
	@PostMapping(value = "/menus", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Menu> saveRole(@RequestBody Menu menu) {
		return Mono.justOrEmpty(menuService.save(menu));
	}
	
	@PutMapping(value = "/menus/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Menu> updateRole(@PathVariable("name") String name, @RequestBody Menu menu) {
		return Mono.justOrEmpty(menuService.update(name, menu));
	}
	
	@DeleteMapping(value = "/menus/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Role> updateRole(@PathVariable("name") String name) {
		menuService.delete(name);
		return Mono.empty();
	}

}
