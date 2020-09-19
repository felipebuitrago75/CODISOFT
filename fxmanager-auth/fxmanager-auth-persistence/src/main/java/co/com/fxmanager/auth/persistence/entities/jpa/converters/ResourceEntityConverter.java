package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.Resource;
import co.com.fxmanager.auth.persistence.entities.jpa.ResourceEntity;
import lombok.Getter;

@Getter
@Component
public class ResourceEntityConverter implements EntityConverter<Resource, ResourceEntity> {

	@Override
	public ResourceEntity convert(Resource user) {
		return convert(user, ResourceEntity::new);
	}

	@Override
	public ResourceEntity convert(Resource resource, Supplier<ResourceEntity> supplier) {
		ResourceEntity resourceEntity = supplier.get();
		resourceEntity.setName(resource.getName());
		resourceEntity.setUri(resource.getUri());
		resourceEntity.setAccessType(resource.getAccessType().name());
		resource.getDescription().ifPresent(resourceEntity::setDescription);
		return resourceEntity;
	}

}
