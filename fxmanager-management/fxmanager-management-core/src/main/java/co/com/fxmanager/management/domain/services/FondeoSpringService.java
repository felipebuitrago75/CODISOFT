package co.com.fxmanager.management.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.management.persistence.repositories.FondeoRepository;

@Service
public class FondeoSpringService extends FondeoService {

	@Autowired
	public FondeoSpringService(FondeoRepository fondeoRepository) {
		super(fondeoRepository);
	}

}
