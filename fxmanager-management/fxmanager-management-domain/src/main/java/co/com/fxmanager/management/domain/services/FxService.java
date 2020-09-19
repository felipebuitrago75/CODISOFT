package co.com.fxmanager.management.domain.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import co.com.fxmanager.management.domain.entities.Fx;
import co.com.fxmanager.management.domain.services.constants.MessageConstants;
import co.com.fxmanager.management.domain.services.exceptions.ValidationException;
import co.com.fxmanager.management.persistence.repositories.FxRepository;
import lombok.Getter;
import lombok.NonNull;

public class FxService {

	@Getter
	private FxRepository fxRepository;

	public FxService(@NonNull FxRepository fxRepository) {
		super();
		this.fxRepository = fxRepository;
	}

	public Fx save(@NonNull Fx fx) {
		checkDataRequired(fx);
		return getFxRepository().create(fx);
	}

	public Fx update(@NonNull String codigo,@NonNull Fx fx) {
//		checkFxExist(codigo);
		checkDataRequired(fx);
		return getFxRepository().update(codigo, fx);
	}

	public void delete(@NonNull String codigo) {		
		checkFxExist(codigo);
		getFxRepository().delete(codigo);
	}

	protected Fx checkFxExist(String codigo) {
		Optional<Fx> fx = getFxRepository().getFx(codigo);
		return fx.orElseThrow(()->new ValidationException(MessageConstants.FX_DOESNT_EXIST));
	}

	protected void checkDataRequired(Fx fx) {
		if (StringUtils.isBlank(fx.getCodigo())) {
			throw new ValidationException(MessageConstants.FX_DATA_REQUIRED);
		}
	}
	
	public List<Fx> getFxs(Integer first, Integer max){
		return this.getFxRepository().getList(first, max);
	}
	
	public Optional<Fx> getFx(String cod){
		return this.getFxRepository().getFx(cod);
	}
}
