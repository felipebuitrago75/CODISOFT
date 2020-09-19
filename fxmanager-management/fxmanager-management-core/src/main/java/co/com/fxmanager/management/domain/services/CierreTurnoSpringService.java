package co.com.fxmanager.management.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.management.persistence.repositories.CierreTurnoRepository;

@Service
public class CierreTurnoSpringService extends CierreTurnoService {

	@Autowired
	public CierreTurnoSpringService(CierreTurnoRepository cierreTurnoRepository) {
		super(cierreTurnoRepository);
	}

}
