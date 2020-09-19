package co.com.fxmanager.auth.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.auth.persistence.repositories.FunctionalityRepository;
import co.com.fxmanager.auth.persistence.repositories.MenuRepository;

@Service
public class FunctionalitySpringService extends FunctionalityService {

	@Autowired
	public FunctionalitySpringService(FunctionalityRepository functionalityRepository) {
		super(functionalityRepository);
	}

}
