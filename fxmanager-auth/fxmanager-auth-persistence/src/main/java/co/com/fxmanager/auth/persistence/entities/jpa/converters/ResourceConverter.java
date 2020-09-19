package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.AccessType;
import co.com.fxmanager.auth.domain.entities.Resource;
import co.com.fxmanager.auth.persistence.entities.jpa.ResourceEntity;

@Component
public class ResourceConverter implements Converter<ResourceEntity, Resource> {

	@Override
	public Resource convert(ResourceEntity resourceEntity) {
		Resource resource = new Resource(resourceEntity.getName(), resourceEntity.getUri(),
				AccessType.valueOf(resourceEntity.getAccessType()));
		resourceEntity.getDescription().ifPresent(resource::setDescription);
		return resource;
	}

}
