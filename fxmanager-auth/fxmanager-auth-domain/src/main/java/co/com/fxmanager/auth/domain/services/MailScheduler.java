package co.com.fxmanager.auth.domain.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.fxmanager.auth.domain.entities.Mail;
import co.com.fxmanager.auth.domain.services.exceptions.MailException;
import co.com.fxmanager.auth.persistence.repositories.MailRepository;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.ExtensionMethod;

@ExtensionMethod({ StringUtils.class })
public class MailScheduler {

	@Getter
	private MailRepository mailRepository;

	public MailScheduler(@NonNull MailRepository mailRepository) {
		this.mailRepository = mailRepository;
	}

	public void send(@NonNull Mail mail) {
		if (mail.getSubject().isBlank()) {
			throw new MailException("Subject is required");
		}

		if (mail.getContent().isBlank()) {
			throw new MailException("Content is required");
		}
		mailRepository.create(mail);
	}

	public List<Mail> getMails() {
		return mailRepository.getList();
	}

}
