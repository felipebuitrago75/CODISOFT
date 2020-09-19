package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.Mail;
import co.com.fxmanager.auth.persistence.entities.jpa.MailEntity;

@Component
public class MailConverter implements Converter<MailEntity, Mail> {

	@Override
	public Mail convert(MailEntity mailEntity) {
//		Mail mail = new Mail(mailEntity.getSubject(), mailEntity.getContent(), mailEntity.getCreationDate());
//		mail.setUid(Optional.ofNullable(mailEntity.getId()).map(String::valueOf));
//		mail.setContenType(mailEntity.getContenType());
//		mailEntity.getRecipientsTo().ifPresent(mail::setRecipientsTo);
//		mailEntity.getRecipientsCC().ifPresent(mail::setRecipientsCC);
//		mailEntity.getRecipientsBCC().ifPresent(mail::setRecipientsBCC);
		return null;
	}

}
