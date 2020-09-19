package co.com.fxmanager.management.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.management.persistence.repositories.CierreRepository;

@Service
public class CierreSpringService extends CierreService {

	@Autowired
	public CierreSpringService(CierreRepository cierreRepository) {
		super(cierreRepository);
	}

}
