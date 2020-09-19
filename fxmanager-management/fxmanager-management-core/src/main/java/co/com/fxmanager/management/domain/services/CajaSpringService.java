package co.com.fxmanager.management.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.management.persistence.repositories.CajaRepository;

@Service
public class CajaSpringService extends CajaService {

	@Autowired
	public CajaSpringService(CajaRepository cajaRepository) {
		super(cajaRepository);
	}

}
