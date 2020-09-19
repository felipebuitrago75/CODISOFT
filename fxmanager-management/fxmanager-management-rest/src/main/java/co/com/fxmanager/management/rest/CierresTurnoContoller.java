package co.com.fxmanager.management.rest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.fxmanager.management.domain.entities.CierreTurno;
import co.com.fxmanager.management.domain.entities.PreCierre;
import co.com.fxmanager.management.domain.services.CierreTurnoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CierresTurnoContoller {
	
	private CierreTurnoService cierreTurnoService;
	
	@Autowired
	public CierresTurnoContoller(CierreTurnoService cierreTurnoService) {
		super();
		this.cierreTurnoService = cierreTurnoService;
	}
	
	@GetMapping(value = "/cierreturnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<CierreTurno> getAll(@RequestParam("first") int first, @RequestParam("max") int max) {
		return Flux.fromIterable(cierreTurnoService.getCierreTurnos(first, max));
	}

	@GetMapping(value = "/cierreturnos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<CierreTurno> get(@PathVariable("id") Long id) {
		return Mono.justOrEmpty(cierreTurnoService.getCierreTurno(id));
	}

	@PostMapping(value = "/cierreturnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<PreCierre> save(@RequestBody CierreTurno cierreTurno) {
		return Mono.justOrEmpty(cierreTurnoService.createYDevolverPrecierre(cierreTurno));
	}

	@PutMapping(value = "/cierreturnos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<CierreTurno> update(@PathVariable("id") Long id, @RequestBody  CierreTurno cierreTurno) {
		return Mono.justOrEmpty(cierreTurnoService.update(id, cierreTurno));
	}
	
	@PostMapping(value = "/createRegistroArqueo", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<PreCierre> save(@RequestBody PreCierre preCierre) {
		return Mono.justOrEmpty(cierreTurnoService.createRegistroArqueo(preCierre));
	}
	
	@PutMapping(value = "/arqueosHistoricos", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<PreCierre> arqueosHistoricos(@RequestBody LocalDate fecha) {
		return Flux.fromIterable(cierreTurnoService.arqueosHistoricos(fecha));
	}
	
	@GetMapping(value = "/arqueosHistoricos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<PreCierre> getArqueosHistoricosId(@PathVariable("id") Long id) {
		return Mono.justOrEmpty(cierreTurnoService.getArqueosHistoricos(id));
	}
	
	
}
