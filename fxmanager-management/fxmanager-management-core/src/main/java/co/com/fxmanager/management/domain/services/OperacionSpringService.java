package co.com.fxmanager.management.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.auth.domain.services.AuthService;
import co.com.fxmanager.management.persistence.repositories.OperacionRepository;

@Service
public class OperacionSpringService extends OperacionService {

	@Autowired
	public OperacionSpringService(OperacionRepository operacionRepository , AuthService authService) {
		super(operacionRepository, authService);
	}

}
