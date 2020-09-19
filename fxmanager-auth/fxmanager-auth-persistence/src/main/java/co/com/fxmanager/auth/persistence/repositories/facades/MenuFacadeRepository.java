package co.com.fxmanager.auth.persistence.repositories.facades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.fxmanager.auth.domain.entities.Menu;
import co.com.fxmanager.auth.persistence.entities.jpa.MenuEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.converters.MenuConverter;
import co.com.fxmanager.auth.persistence.entities.jpa.converters.MenuEntityConverter;
import co.com.fxmanager.auth.persistence.repositories.MenuRepository;
import co.com.fxmanager.auth.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.auth.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.auth.persistence.repositories.springdata.MenuSpringRepository;
import lombok.Getter;

@Getter
@Service
public class MenuFacadeRepository implements MenuRepository {

	private MenuSpringRepository menuSpringRepository;

	private MenuEntityConverter menuEntityConverter;

	private MenuConverter menuConverter;

	@Autowired
	public MenuFacadeRepository(MenuSpringRepository menuSpringRepository, MenuConverter menuConverter,
			MenuEntityConverter menuEntityConverter) {
		super();
		this.menuSpringRepository = menuSpringRepository;
		this.menuConverter = menuConverter;
		this.menuEntityConverter = menuEntityConverter;
	}

	@Transactional
	@Override
	public Menu create(Menu menu) {
		Menu newMenu;
		try {
			MenuEntity menuEntity = getMenuEntityConverter().convert(menu);
			MenuEntity newMenuEntity = getMenuSpringRepository().save(menuEntity);
			newMenu = getMenuConverter().convert(newMenuEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newMenu;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Menu> getList(int first, int max) {
		List<Menu> menus;
		try {
			Page<MenuEntity> page = getMenuSpringRepository()
					.findAll(PageRequest.of(first / max, max, Sort.by(MenuEntity.ClassInfo.NAME)));
			menus = page.stream().map(menuEntity -> getMenuConverter().convert(menuEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return menus;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Menu> getMenusForFunctionalities(Integer first, Integer max) {
		List<Menu> menus;
		try {
			Page<MenuEntity> page = getMenuSpringRepository().findByTargetNotNull(PageRequest.of(first / max, max));
			menus = page.stream().map(menuEntity -> getMenuConverter().convert(menuEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return menus;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Menu> getMenusForParent(Integer first, Integer max) {
		List<Menu> menus;
		try {
			Page<MenuEntity> page = getMenuSpringRepository().findByTargetIsNull(PageRequest.of(first / max, max));
			menus = page.stream().map(menuEntity -> getMenuConverter().convert(menuEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return menus;
	}

	@Transactional
	@Override
	public Menu update(String name, Menu menu) {
		Menu newMenu;
		try {
			MenuEntity menuEntity = getMenuEntityConverter().convert(menu, () -> {
				Optional<MenuEntity> menuEntityOptioanal = getMenuSpringRepository().findByName(name);
				return menuEntityOptioanal.orElseThrow(NoFoundDataException::new);
			});
			MenuEntity newMenuEntity = getMenuSpringRepository().save(menuEntity);
			newMenu = getMenuConverter().convert(newMenuEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newMenu;
	}

	@Transactional
	@Override
	public void delete(String name) {
		try {
			Optional<MenuEntity> menuEntityOptioanal = getMenuSpringRepository().findByName(name);
			menuEntityOptioanal.ifPresent(getMenuSpringRepository()::delete);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Menu> getMenu(String name) {
		Optional<Menu> menu;
		try {
			Optional<MenuEntity> menuEntityOptioanal = getMenuSpringRepository().findByName(name);
			menu = menuEntityOptioanal.map(roleEntity -> getMenuConverter().convert(roleEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return menu;
	}

}
