package co.com.fxmanager.auth.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.auth.domain.entities.Mail;
import co.com.fxmanager.auth.domain.services.webflux.SyncronicWebFluxAdapter;
import co.com.fxmanager.auth.persistence.repositories.MailRepository;
import reactor.core.publisher.Flux;

@Service
public class SpringMailScheduler extends MailScheduler {

	private SyncronicWebFluxAdapter concurrentWebFluxAdapter;

	@Autowired
	public SpringMailScheduler(MailRepository mailRepository, SyncronicWebFluxAdapter concurrentWebFluxAdapter) {
		super(mailRepository);
		this.concurrentWebFluxAdapter = concurrentWebFluxAdapter;
	}

	public Flux<Mail> getMailsRX() {
		return concurrentWebFluxAdapter.ofMany(this::getMails);
	}
}
