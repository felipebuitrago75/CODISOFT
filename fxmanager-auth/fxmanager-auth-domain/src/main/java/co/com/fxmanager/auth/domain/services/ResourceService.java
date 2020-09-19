package co.com.fxmanager.auth.domain.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import co.com.fxmanager.auth.domain.entities.Resource;
import co.com.fxmanager.auth.domain.services.constants.MessageConstants;
import co.com.fxmanager.auth.domain.services.exceptions.ValidationException;
import co.com.fxmanager.auth.persistence.repositories.ResourceRepository;
import lombok.Getter;
import lombok.NonNull;

public class ResourceService {

	@Getter
	private ResourceRepository resourceRepository;

	public ResourceService(@NonNull ResourceRepository resourceRepository) {
		super();
		this.resourceRepository = resourceRepository;
	}

	public Resource save(Resource resource) {
		checkDataRequired(resource);
		return getResourceRepository().create(resource);
	}

	public Resource update(String name,Resource resource) {
		checkResourceExist(name);
		checkDataRequired(resource);
		return getResourceRepository().update(name,resource);
	}

	public void delete(String name) {
		checkResourceExist(name);
		getResourceRepository().delete(name);
	}

	protected Resource checkResourceExist(String name) {
		Optional<Resource> resource = getResourceRepository().getResource(name);
		return resource.orElseThrow(()->new ValidationException(MessageConstants.RESOURCE_DOESNT_EXIST));
	}

	protected void checkDataRequired(Resource resource) {
		if (StringUtils.isBlank(resource.getName())) {
			throw new ValidationException(MessageConstants.RESOURCE_NAME_REQUIRED);
		}
		if (StringUtils.isBlank(resource.getUri())) {
			throw new ValidationException(MessageConstants.RESOURCE_URI_REQUIRED);
		}
	}
	
	public List<Resource> getResources(Integer first, Integer max){
		return this.getResourceRepository().getList(first, max);
	}
	
	public Optional<Resource> getResource(String name){
		return this.getResourceRepository().getResource(name);
	}
}
