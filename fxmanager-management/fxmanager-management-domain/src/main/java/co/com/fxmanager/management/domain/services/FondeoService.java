package co.com.fxmanager.management.domain.services;

import java.util.List;
import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Fondeo;
import co.com.fxmanager.management.domain.services.constants.MessageConstants;
import co.com.fxmanager.management.domain.services.exceptions.ValidationException;
import co.com.fxmanager.management.persistence.repositories.FondeoRepository;
import lombok.Getter;
import lombok.NonNull;

public class FondeoService {

	@Getter
	private FondeoRepository fondeoRepository;

	public FondeoService(@NonNull  FondeoRepository fondeoRepository) {
		super();
		this.fondeoRepository = fondeoRepository;
	}

	public Fondeo save(@NonNull Fondeo fondeo) {
		//checkDataRequired(traslado);
		return getFondeoRepository().create(fondeo);
	}

	public Fondeo update(@NonNull Long id,@NonNull Fondeo fondeo) {
		checkOperacionExist(id);
		checkDataRequired(fondeo);
		return getFondeoRepository().update(id, fondeo);
	}

	public void delete(@NonNull Long id) {		
		checkOperacionExist(id);
		getFondeoRepository().delete(id);
	}

	protected Fondeo checkOperacionExist(Long id) {
		Optional<Fondeo> traslado = getFondeoRepository().getFondeo(id);
		return traslado.orElseThrow(()->new ValidationException(MessageConstants.FONDEO_DOESNT_EXIST));
		
	}

	protected void checkDataRequired(Fondeo fondeo) {
	
	}
	
	public List<Fondeo> getFondeos(Integer first, Integer max){
		return this.getFondeoRepository().getList(first, max);
	}
	
	public Optional<Fondeo> getFondeo(Long id){
		return this.getFondeoRepository().getFondeo(id);
	}
}
