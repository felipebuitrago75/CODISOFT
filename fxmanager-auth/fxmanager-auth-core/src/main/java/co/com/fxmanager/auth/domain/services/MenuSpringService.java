package co.com.fxmanager.auth.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.fxmanager.auth.persistence.repositories.MenuRepository;

@Service
public class MenuSpringService extends MenuService {

	@Autowired
	public MenuSpringService(MenuRepository menuRepository) {
		super(menuRepository);
	}

}
