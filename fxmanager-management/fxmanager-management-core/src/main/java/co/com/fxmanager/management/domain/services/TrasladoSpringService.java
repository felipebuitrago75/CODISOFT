package co.com.fxmanager.management.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.management.persistence.repositories.TrasladoRepository;

@Service
public class TrasladoSpringService extends TrasladoService {

	@Autowired
	public TrasladoSpringService(TrasladoRepository trasladoRepository) {
		super(trasladoRepository);
	}

}
