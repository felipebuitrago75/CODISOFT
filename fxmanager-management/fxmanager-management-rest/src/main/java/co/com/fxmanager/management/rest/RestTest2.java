package co.com.fxmanager.management.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.fxmanager.auth.domain.entities.Mail;
import co.com.fxmanager.auth.domain.services.SpringMailScheduler;
import co.com.fxmanager.auth.domain.services.webflux.SyncronicWebFluxAdapter;
import reactor.core.publisher.Flux;

@RestController
public class RestTest2 {

	private SpringMailScheduler mailScheduler;

	private SyncronicWebFluxAdapter concurrentWebFluxAdapter;
	
	@Autowired
	public RestTest2(SpringMailScheduler mailScheduler, SyncronicWebFluxAdapter concurrentWebFluxAdapter) {
		super();
		this.mailScheduler = mailScheduler;
		this.concurrentWebFluxAdapter = concurrentWebFluxAdapter;
	}

//	@GetMapping(value = "/Dhello", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Flux<Mail> algo() {
//		return (Flux<Mail>) concurrentWebFluxAdapter.ofFlux(mailScheduler::getMailsRX);
//	}

	@PostMapping(value = "/Dhello", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<String> algo2() {
		return Flux.fromArray(new String[] { "1", "2" });
	}
	
	@PostMapping(value = "/Dhola", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<String> hola() {
		return Flux.fromArray(new String[] { "ho", "la"," ","Mun","do" });
	}

}
