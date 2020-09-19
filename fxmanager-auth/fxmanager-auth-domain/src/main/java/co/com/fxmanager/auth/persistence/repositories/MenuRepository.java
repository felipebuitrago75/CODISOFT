package co.com.fxmanager.auth.persistence.repositories;

import java.util.List;
import java.util.Optional;

import co.com.fxmanager.auth.domain.entities.Menu;

public interface MenuRepository extends AbstractRepository<Menu> {

	public Menu update(String name, Menu menu);

	public void delete(String name);

	public Optional<Menu> getMenu(String name);
	
	public List<Menu> getMenusForFunctionalities(Integer first, Integer max);
	
	public List<Menu> getMenusForParent(Integer first, Integer max);
}
