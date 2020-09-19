package co.com.fxmanager.management.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.management.persistence.repositories.FxRepository;

@Service
public class FxSpringService extends FxService {

	@Autowired
	public FxSpringService(FxRepository fxRepository) {
		super(fxRepository);
	}

}
