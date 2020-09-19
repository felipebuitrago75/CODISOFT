package co.com.fxmanager.management.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.management.persistence.repositories.AperturaTurnoRepository;

@Service
public class AperturaTurnoSpringService extends AperturaTurnoService {

	@Autowired
	public AperturaTurnoSpringService(AperturaTurnoRepository aperturaTurnoRepository) {
		super(aperturaTurnoRepository);
	}

}
