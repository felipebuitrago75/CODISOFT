package co.com.fxmanager.management.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.auth.domain.services.AuthService;
import co.com.fxmanager.management.persistence.repositories.FlujoExtraordinarioRepository;

@Service
public class FlujoExtraordinarioSpringService extends FlujoExtraordinarioService {

	@Autowired
	public FlujoExtraordinarioSpringService(FlujoExtraordinarioRepository flujoExtraordinarioRepository,
			AuthService authService) {
		super(flujoExtraordinarioRepository, authService);
	}

}
