package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.Mail;
import co.com.fxmanager.auth.persistence.entities.jpa.MailEntity;
import co.com.fxmanager.auth.persistence.repositories.springdata.MailSpringRepository;
import lombok.Getter;

@Component
public class MailEntityConverter implements Converter<Mail, MailEntity> {

	@Autowired
	@Getter
	private MailSpringRepository mailRepository;

	
	public MailEntityConverter() {
		super();
	}

	@Override
	public MailEntity convert(Mail mail) {
		final MailEntity mailEntity;
		final Optional<String> uid = mail.getUid();
		if (uid.isPresent()) {
			mailEntity = getMailRepository().getOne(Long.valueOf(uid.get()));
		} else {
			mailEntity = new MailEntity();
		}
		mailEntity.setSubject(mail.getSubject());
		mailEntity.setContent(mail.getContent());
		mailEntity.setContenType(mail.getContenType());
		mailEntity.setCreationDate(mail.getCreationDate());
		String recipientsTo = mail.getRecipientsTo().stream().collect(Collectors.joining(Mail.ADDRESS_SEPARATOR));
		String recipientsCC = mail.getRecipientsCC().stream().collect(Collectors.joining(Mail.ADDRESS_SEPARATOR));
		String recipientsBCC = mail.getRecipientsBCC().stream().collect(Collectors.joining(Mail.ADDRESS_SEPARATOR));
		mailEntity.setRecipientsTo(recipientsTo);
		mailEntity.setRecipientsCC(recipientsCC);
		mailEntity.setRecipientsBCC(recipientsBCC);
		return mailEntity;
	}

}
