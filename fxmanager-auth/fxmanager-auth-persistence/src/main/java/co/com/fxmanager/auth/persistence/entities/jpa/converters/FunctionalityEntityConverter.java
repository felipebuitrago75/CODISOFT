package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.Functionality;
import co.com.fxmanager.auth.domain.entities.Resource;
import co.com.fxmanager.auth.persistence.entities.jpa.FunctionalityEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.MenuEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.ResourceEntity;
import co.com.fxmanager.auth.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.auth.persistence.repositories.springdata.MenuSpringRepository;
import co.com.fxmanager.auth.persistence.repositories.springdata.ResourceSpringRepository;
import lombok.Getter;

@Getter
@Component
public class FunctionalityEntityConverter implements EntityConverter<Functionality, FunctionalityEntity> {

	@Autowired
	private MenuSpringRepository menuSpringRepository;

	@Autowired
	private ResourceConverter resourceConverter;

	@Autowired
	private ResourceSpringRepository resourceRepository;

	@Override
	public FunctionalityEntity convert(Functionality user) {
		return convert(user, FunctionalityEntity::new);
	}

	@Override
	public FunctionalityEntity convert(Functionality functionality, Supplier<FunctionalityEntity> supplier) {
		FunctionalityEntity functionalityEntity = supplier.get();
		functionalityEntity.setName(functionality.getName());
		functionality.getDescription().ifPresent(functionalityEntity::setDescription);
		functionality.getMenu().ifPresent(menu -> {
			Optional<MenuEntity> menuOptional = getMenuSpringRepository().findByName(menu.getName());
			menuOptional.ifPresent(functionalityEntity::setMenu);
		});

		List<Resource> resources = functionality.getResources();
		List<ResourceEntity> resourceEntities = functionalityEntity.getResources();
		if (resourceEntities == null) {
			resourceEntities = new ArrayList<>();
			functionalityEntity.setResources(resourceEntities);
		}

		List<ResourceEntity> recursosParaBorrar = resourceEntities.stream()
				.filter(Predicate
						.not(resourceEntity -> resources.contains(getResourceConverter().convert(resourceEntity))))
				.collect(Collectors.toList());

		functionalityEntity.getResources().removeAll(recursosParaBorrar);

		List<Resource> resourcesActuales = resourceEntities.stream().map(getResourceConverter()::convert)
				.collect(Collectors.toList());

		List<ResourceEntity> recursosParaAgregar = resources.stream()
				.filter(Predicate.not(recurso -> resourcesActuales.contains(recurso))).map(resource -> {
					return getResourceRepository().findByName(resource.getName())
							.orElseThrow(NoFoundDataException::new);
				}).collect(Collectors.toList());
		functionalityEntity.getResources().addAll(recursosParaAgregar);

		return functionalityEntity;
	}

}
