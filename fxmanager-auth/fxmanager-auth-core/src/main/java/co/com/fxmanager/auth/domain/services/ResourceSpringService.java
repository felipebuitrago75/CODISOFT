package co.com.fxmanager.auth.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.auth.persistence.repositories.ResourceRepository;

@Service
public class ResourceSpringService extends ResourceService {

	@Autowired
	public ResourceSpringService(ResourceRepository resourceRepository) {
		super(resourceRepository);
	}

}
