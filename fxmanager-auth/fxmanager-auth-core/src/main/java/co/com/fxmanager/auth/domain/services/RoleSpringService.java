package co.com.fxmanager.auth.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.auth.persistence.repositories.RoleRepository;

@Service
public class RoleSpringService extends RoleService {

	@Autowired
	public RoleSpringService(RoleRepository roleRepository) {
		super(roleRepository);
	}

}
