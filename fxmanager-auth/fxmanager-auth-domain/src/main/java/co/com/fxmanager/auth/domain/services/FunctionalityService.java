package co.com.fxmanager.auth.domain.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import co.com.fxmanager.auth.domain.entities.Functionality;
import co.com.fxmanager.auth.domain.services.constants.MessageConstants;
import co.com.fxmanager.auth.domain.services.exceptions.ValidationException;
import co.com.fxmanager.auth.persistence.repositories.FunctionalityRepository;
import lombok.Getter;
import lombok.NonNull;

public class FunctionalityService {

	@Getter
	private FunctionalityRepository functionalityRepository;

	public FunctionalityService(@NonNull FunctionalityRepository functionalityRepository) {
		super();
		this.functionalityRepository = functionalityRepository;
	}

	public Functionality save(Functionality functionality) {
		checkDataRequired(functionality);
		return getFunctionalityRepository().create(functionality);
	}

	public Functionality update(String name,Functionality functionality) {
		checkFunctionalityExist(name);
		checkDataRequired(functionality);
		return getFunctionalityRepository().update(name,functionality);
	}

	public void delete(String name) {
		checkFunctionalityExist(name);
		getFunctionalityRepository().delete(name);
	}

	protected Functionality checkFunctionalityExist(String name) {
		Optional<Functionality> functionality = getFunctionalityRepository().getFunctionality(name);
		return functionality.orElseThrow(()->new ValidationException(MessageConstants.FUNCTIONALITY_DOESNT_EXIST));
	}

	protected void checkDataRequired(Functionality functionality) {
		if (StringUtils.isBlank(functionality.getName())) {
			throw new ValidationException(MessageConstants.FUNCTIONALITY_NAME_REQUIRED);
		}
	}
	
	public List<Functionality> getFunctionalities(Integer first, Integer max){
		return this.getFunctionalityRepository().getList(first, max);
	}
	
	public Optional<Functionality> getFunctionality(String name){
		return this.getFunctionalityRepository().getFunctionality(name);
	}
}
