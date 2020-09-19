package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.Functionality;
import co.com.fxmanager.auth.domain.entities.Permission;
import co.com.fxmanager.auth.domain.entities.Resource;
import co.com.fxmanager.auth.persistence.entities.jpa.FunctionalityEntity;
import lombok.Getter;

@Component
@Getter
public class FunctionalityConverter implements Converter<FunctionalityEntity, Functionality> {

	@Autowired
	private PermissionConverter permissionConverter;

	@Autowired
	private ResourceConverter resourceConverter;

	@Autowired
	private MenuConverter menuConverter;

	@Override
	public Functionality convert(FunctionalityEntity functionalityEntity) {
		Functionality functionality = new Functionality(functionalityEntity.getName(),
				functionalityEntity.getDescription());
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		if (persistenceUtil.isLoaded(functionalityEntity, FunctionalityEntity.ClassInfo.MENU)) {
			functionalityEntity.getMenu().ifPresent(menuEntity -> {
				functionality.setMenu(getMenuConverter().convert(menuEntity));
			});
		}

		if (persistenceUtil.isLoaded(functionalityEntity, FunctionalityEntity.ClassInfo.RESOURCES)) {
			List<Resource> resources = functionalityEntity.getResources().stream().map(resourceConverter::convert)
					.collect(Collectors.toList());
			functionality.setResources(resources);
		}

//		if (persistenceUtil.isLoaded(functionalityEntity, FunctionalityEntity.ClassInfo.PERMISSIONS)) {
//			List<Permission> permissions = functionalityEntity.getPermissions().stream()
//					.map(permissionConverter::convert).collect(Collectors.toList());
//			functionality.setPermissions(permissions);
//		}

		return functionality;
	}

}
