package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.domain.entities.Menu;
import co.com.fxmanager.auth.persistence.entities.jpa.MenuEntity;
import lombok.Getter;

@Component
@Getter
public class MenuConverter implements Converter<MenuEntity, Menu> {

	public MenuConverter() {
		super();
	}

	@Override
	public Menu convert(MenuEntity menuEntity) {
		Menu menu = new Menu(menuEntity.getName(), menuEntity.getIndex());
		menuEntity.getDescription().ifPresent(menu::setDescription);
		menuEntity.getTarget().ifPresent(menu::setTarget);
	
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		if (persistenceUtil.isLoaded(menuEntity, MenuEntity.ClassInfo.PARENT)) {
			Optional<MenuEntity> parentotional = menuEntity.getParent();
			parentotional.ifPresent(parent -> {
				menu.setParent(this.convert(parent));
			});
		}
		return menu;
	}

}
