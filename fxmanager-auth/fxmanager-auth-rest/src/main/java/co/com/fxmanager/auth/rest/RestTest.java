package co.com.fxmanager.auth.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.fxmanager.auth.domain.entities.Mail;
import co.com.fxmanager.auth.domain.services.AuthContextService;
import co.com.fxmanager.auth.domain.services.AuthService;
import co.com.fxmanager.auth.domain.services.SpringMailScheduler;
import co.com.fxmanager.auth.domain.services.webflux.SyncronicWebFluxAdapter;
import reactor.core.publisher.Flux;

@RestController
public class RestTest {

	private SpringMailScheduler mailScheduler;

	private SyncronicWebFluxAdapter concurrentWebFluxAdapter;

	private AuthService authContextService;
	
	@Autowired
	public RestTest(AuthService authContextService, SpringMailScheduler mailScheduler, SyncronicWebFluxAdapter concurrentWebFluxAdapter) {
		super();
		this.mailScheduler = mailScheduler;
		this.concurrentWebFluxAdapter = concurrentWebFluxAdapter;
		this.authContextService=authContextService;
	}

//	@GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Flux<Mail> algo() {
//		
//		return concurrentWebFluxAdapter.ofFlux(mailScheduler::getMailsRX);
//	}

	@PostMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<String> algo2() {
		return Flux.fromArray(new String[] { "1", "2" });
	}
	
	@PostMapping(value = "/hola", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<String> hola() {
		return Flux.fromArray(new String[] { "ho", "la"," ","Mun","do" });
	}

}
