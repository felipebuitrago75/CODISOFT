package co.com.fxmanager.auth.domain.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import co.com.fxmanager.auth.domain.entities.Menu;
import co.com.fxmanager.auth.domain.services.constants.MessageConstants;
import co.com.fxmanager.auth.domain.services.exceptions.ValidationException;
import co.com.fxmanager.auth.persistence.repositories.MenuRepository;
import lombok.Getter;
import lombok.NonNull;

public class MenuService {

	@Getter
	private MenuRepository menuRepository;

	public MenuService(@NonNull MenuRepository menuRepository) {
		super();
		this.menuRepository = menuRepository;
	}

	public Menu save(@NonNull Menu menu) {
		checkDataRequired(menu);
		return getMenuRepository().create(menu);
	}

	public Menu update(@NonNull String name,@NonNull Menu menu) {
		checkMenuExist(name);
		checkDataRequired(menu);
		return getMenuRepository().update(name, menu);
	}

	public void delete(@NonNull String name) {		
		checkMenuExist(name);
		getMenuRepository().delete(name);
	}

	protected Menu checkMenuExist(String name) {
		Optional<Menu> menu = getMenuRepository().getMenu(name);
		if (menu.isEmpty()) {
			throw new ValidationException(MessageConstants.ROLE_DOESNT_EXIST);
		}
		return menu.get();
	}

	protected void checkDataRequired(Menu menu) {
		if (StringUtils.isBlank(menu.getName())) {
			throw new ValidationException(MessageConstants.MENU_NAME_REQUIRED);
		}
		if (menu.getIndex()==null) {
			throw new ValidationException(MessageConstants.MENU_INDEX_REQUIRED);
		}
	}
	
	public List<Menu> getMenus(Integer first, Integer max){
		return this.getMenuRepository().getList(first, max);
	}
	
	public List<Menu> getMenusForFunctionalities(Integer first, Integer max){
		return this.getMenuRepository().getMenusForFunctionalities(first, max);
	}
	
	public List<Menu> getMenusForParent(Integer first, Integer max){
		return this.getMenuRepository().getMenusForParent(first, max);
	}
	
	public Optional<Menu> getMenu(String name){
		return this.getMenuRepository().getMenu(name);
	}
}
