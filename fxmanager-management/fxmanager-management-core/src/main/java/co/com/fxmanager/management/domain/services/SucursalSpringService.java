package co.com.fxmanager.management.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.management.persistence.repositories.SucursalRepository;

@Service
public class SucursalSpringService extends SucursalService {

	@Autowired
	public SucursalSpringService(SucursalRepository sucursalRepository) {
		super(sucursalRepository);
	}

}
