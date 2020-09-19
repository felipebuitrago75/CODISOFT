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

import co.com.fxmanager.management.domain.entities.Caja;
import co.com.fxmanager.management.domain.entities.PreCierre;
import co.com.fxmanager.management.domain.services.CajaService;
import co.com.fxmanager.management.domain.services.CierreTurnoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CajasController {

	private CajaService cajaService;
	
	private CierreTurnoService cierreTurnoService;

	@Autowired
	public CajasController(CajaService cajaService, CierreTurnoService cierreTurnoService) {
		super();
		this.cajaService = cajaService;
		this.cierreTurnoService=cierreTurnoService;
	}

	@GetMapping(value = "/cajas", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Caja> getAll(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(cajaService.getCajas(first, max));
	}

	@GetMapping(value = "/cajas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Caja> get(@PathVariable("id") Long id) {
		return Mono.justOrEmpty(cajaService.getCaja(id));
	}

	@PostMapping(value = "/cajas", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Caja> save(@RequestBody Caja caja) {
		return Mono.justOrEmpty(cajaService.save(caja));
	}

	@PutMapping(value = "/cajas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Caja> update(@PathVariable("id") Long id, @RequestBody Caja caja) {
		return Mono.justOrEmpty(cajaService.update(id, caja));
	}

	@DeleteMapping(value = "/cajas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Caja> delete(@PathVariable("id") Long id) {
		cajaService.delete(id);
		return Mono.empty();
	}
	
	@GetMapping(value = "/cajasPorSucursal/{cod}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Caja> getCajaPorSucursal(@PathVariable("cod") String cod) {
		return Flux.fromIterable(cajaService.getCajasPorSucursal(cod));
	}
	

}
