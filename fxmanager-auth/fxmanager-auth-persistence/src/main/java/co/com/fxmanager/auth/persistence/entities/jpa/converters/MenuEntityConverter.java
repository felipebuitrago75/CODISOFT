package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.Menu;
import co.com.fxmanager.auth.persistence.entities.jpa.MenuEntity;
import co.com.fxmanager.auth.persistence.repositories.springdata.MenuSpringRepository;
import lombok.Getter;

@Getter
@Component
public class MenuEntityConverter implements EntityConverter<Menu, MenuEntity> {

	@Autowired
	private MenuSpringRepository menuSpringRepository;
	
	@Override
	public MenuEntity convert(Menu menu) {
		return convert(menu, MenuEntity::new);
	}

	@Override
	public MenuEntity convert(Menu menu, Supplier<MenuEntity> supplier) {
		MenuEntity menuEntity = supplier.get();
		menuEntity.setName(menu.getName());
		menuEntity.setIndex(menu.getIndex());
		menu.getTarget().ifPresent(menuEntity::setTarget);
		menu.getDescription().ifPresent(menuEntity::setDescription);
		menu.getParent().ifPresent(parent->{
			Optional<MenuEntity> parentOptional = getMenuSpringRepository().findByName(parent.getName());
			parentOptional.ifPresent(menuEntity::setParent);
		});
		return menuEntity;
	}

}
