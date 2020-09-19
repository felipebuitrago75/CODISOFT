package co.com.fxmanager.auth.persistence.repositories.facades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.com.fxmanager.auth.domain.entities.Resource;
import co.com.fxmanager.auth.persistence.entities.jpa.ResourceEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.converters.ResourceConverter;
import co.com.fxmanager.auth.persistence.entities.jpa.converters.ResourceEntityConverter;
import co.com.fxmanager.auth.persistence.repositories.ResourceRepository;
import co.com.fxmanager.auth.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.auth.persistence.repositories.springdata.ResourceSpringRepository;
import lombok.Getter;

@Getter
@Service
public class ResourceFacadeRepository implements ResourceRepository {

	private ResourceSpringRepository resourceSpringRepository;

	private ResourceEntityConverter resourceEntityConverter;

	private ResourceConverter resourceConverter;

	@Autowired
	public ResourceFacadeRepository(ResourceSpringRepository resourceSpringRepository,
			ResourceConverter resourceConverter, ResourceEntityConverter resourceEntityConverter) {
		super();
		this.resourceSpringRepository = resourceSpringRepository;
		this.resourceConverter = resourceConverter;
		this.resourceEntityConverter = resourceEntityConverter;
	}

	@Override
	public Resource create(Resource resource) {
		ResourceEntity resourceEntity = getResourceEntityConverter().convert(resource);
		ResourceEntity newResourceEntity = getResourceSpringRepository().save(resourceEntity);
		Resource newResource = getResourceConverter().convert(newResourceEntity);
		return newResource;
	}

	@Override
	public List<Resource> getList(int first, int max) {
		Page<ResourceEntity> page = getResourceSpringRepository()
				.findAll(PageRequest.of(first, max, Sort.by(ResourceEntity.ClassInfo.NAME)));
		return page.stream().map(resourceEntity -> getResourceConverter().convert(resourceEntity))
				.collect(Collectors.toList());
	}

	@Override
	public Resource update(String name, Resource resource) {
		ResourceEntity resourceEntity = getResourceEntityConverter().convert(resource, () -> {
			Optional<ResourceEntity> resourceEntityOptioanal = getResourceSpringRepository().findByName(name);
			return resourceEntityOptioanal.orElseThrow(NoFoundDataException::new);
		});
		ResourceEntity newResourceEntity = getResourceSpringRepository().save(resourceEntity);
		Resource newResource = getResourceConverter().convert(newResourceEntity);
		return newResource;
	}

	@Override
	public void delete(String name) {
		Optional<ResourceEntity> resourceEntityOptioanal = getResourceSpringRepository().findByName(name);
		resourceEntityOptioanal.ifPresent(getResourceSpringRepository()::delete);
	}

	@Override
	public Optional<Resource> getResource(String name) {
		Optional<ResourceEntity> resourceEntityOptioanal = getResourceSpringRepository().findByName(name);
		return resourceEntityOptioanal.map(resourceEntity -> getResourceConverter().convert(resourceEntity));
	}

}
