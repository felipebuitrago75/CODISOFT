package co.com.fxmanager.auth.persistence.repositories.facades;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import co.com.fxmanager.auth.domain.entities.Mail;
import co.com.fxmanager.auth.persistence.entities.jpa.MailEntity;
import co.com.fxmanager.auth.persistence.repositories.MailRepository;
import co.com.fxmanager.auth.persistence.repositories.springdata.MailSpringRepository;
import lombok.Getter;

@Service
public class MailFacadeRepository implements MailRepository {

	@Getter
	private MailSpringRepository mailRepository;

	@Getter
	private ConversionService conversionService;

	@Autowired
	public MailFacadeRepository(MailSpringRepository mailRepository) {
		super();
		this.mailRepository = mailRepository;
	}

	@Override
	public Mail create(Mail mail) {
		MailEntity mailEntity = getConversionService().convert(mail, MailEntity.class);
		MailEntity newMailEntity = getMailRepository().save(mailEntity);
		return getConversionService().convert(newMailEntity, Mail.class);
	}

	public Mail update(Mail mail) {
		return create(mail);
	}

	public void delete(Mail mail) {
		getMailRepository().deleteById(mail.getUid().map(Long::valueOf).orElseThrow());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mail> getList(int first, int max) {
		List<Mail> lista = List.of(new Mail("1", "2", LocalDateTime.now(), "julian"),
				new Mail("2", "3", LocalDateTime.now(), "julian"));
//		if (true) {
//			throw new IllegalArgumentException("ALgo malo");
//		}
		return lista;
//		List<MailEntity> mailEntities = getMailRepository().findAll();
//		return (List<Mail>) getConversionService().convert(mailEntities,
//				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(MailEntity.class)),
//				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Mail.class)));
	}

}
