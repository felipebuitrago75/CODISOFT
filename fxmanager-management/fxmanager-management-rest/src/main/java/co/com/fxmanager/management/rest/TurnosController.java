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

import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.domain.services.CierreTurnoService;
import co.com.fxmanager.management.domain.services.TurnoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TurnosController {

	private TurnoService turnoService;
	private CierreTurnoService cierreTurnoService;
	
	@Autowired
	public TurnosController(TurnoService turnoService, CierreTurnoService cierreTurnoService) {
		super();
		this.turnoService = turnoService;
		this.cierreTurnoService=cierreTurnoService;
	}

	@GetMapping(value = "/turnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Turno> getAll(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(turnoService.getTurnos(first, max));
	}

	@GetMapping(value = "/turnos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Turno> get(@PathVariable("id") Long id) {
		return Mono.justOrEmpty(turnoService.getTurno(id));
	}

	@PostMapping(value = "/turnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Turno> save(@RequestBody Turno turno) {
		return Mono.justOrEmpty(turnoService.save(turno));
	}

	@PutMapping(value = "/turnos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Turno> update(@PathVariable("id") Long id, @RequestBody Turno turno) {
		return Mono.justOrEmpty(turnoService.update(id, turno));
	}

	@DeleteMapping(value = "/turnos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Turno> delete(@PathVariable("id") Long id) {
		turnoService.delete(id);
		return Mono.empty();
	}
	
	@GetMapping(value = "/obtenerTurnoActivo/{obtenerTurnoActivo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Turno> getUser(@PathVariable("nombreUsuario") String nombreUsuario) {
		return Mono.justOrEmpty(turnoService.obtenerTurnoActivo(nombreUsuario));
	}
	
	@PutMapping(value = "/cancelarPrecierre/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void update(@PathVariable("id") Long id) {
		 cierreTurnoService.cancelarPrecierre(id);
	}
	
	@GetMapping(value = "/turnosSucursal/{idSucursal}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Turno> getIdSucursal(@PathVariable("idSucursal") Long idSucursal) {
		return Flux.fromIterable(turnoService.getTurnosSucursal(idSucursal));
	}
	
	
	

}
