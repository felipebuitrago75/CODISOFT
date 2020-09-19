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

import co.com.fxmanager.management.domain.entities.Sucursal;
import co.com.fxmanager.management.domain.services.SucursalService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SucursalesController {

	private SucursalService sucursalService;

	@Autowired
	public SucursalesController(SucursalService sucursalService) {
		super();
		this.sucursalService = sucursalService;
	}

	@GetMapping(value = "/sucursales", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Sucursal> getAll(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(sucursalService.getSucursales(first, max));
	}

	@GetMapping(value = "/sucursales/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Sucursal> get(@PathVariable("codigo") String codigo) {
		return Mono.justOrEmpty(sucursalService.getSucursal(codigo));
	}

	@PostMapping(value = "/sucursales", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Sucursal> save(@RequestBody Sucursal sucursal) {
		return Mono.justOrEmpty(sucursalService.save(sucursal));
	}

	@PutMapping(value = "/sucursales/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Sucursal> update(@PathVariable("codigo") String codigo, @RequestBody Sucursal sucursal) {
		return Mono.justOrEmpty(sucursalService.update(codigo, sucursal));
	}

	@DeleteMapping(value = "/sucursales/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Sucursal> delete(@PathVariable("codigo") String codigo) {
		sucursalService.delete(codigo);
		return Mono.empty();
	}

}
