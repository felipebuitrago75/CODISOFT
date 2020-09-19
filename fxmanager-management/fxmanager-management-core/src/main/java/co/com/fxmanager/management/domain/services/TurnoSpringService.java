package co.com.fxmanager.management.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.management.persistence.repositories.TurnoRepository;

@Service
public class TurnoSpringService extends TurnoService {

	@Autowired
	public TurnoSpringService(TurnoRepository turnoRepository) {
		super(turnoRepository);
	}

}
