package co.com.fxmanager.management.domain.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import co.com.fxmanager.management.domain.entities.Sucursal;
import co.com.fxmanager.management.domain.services.constants.MessageConstants;
import co.com.fxmanager.management.domain.services.exceptions.ValidationException;
import co.com.fxmanager.management.persistence.repositories.SucursalRepository;
import lombok.Getter;
import lombok.NonNull;

public class SucursalService {

	@Getter
	private SucursalRepository sucursalRepository;

	public SucursalService(@NonNull SucursalRepository sucursalRepository) {
		super();
		this.sucursalRepository = sucursalRepository;
	}

	public Sucursal save(@NonNull Sucursal sucursal) {
		checkDataRequired(sucursal);
		return getSucursalRepository().create(sucursal);
	}

	public Sucursal update(@NonNull String cod,@NonNull Sucursal sucursal) {
		checkSucursalExist(cod);
		checkDataRequired(sucursal);
		return getSucursalRepository().update(cod, sucursal);
	}

	public void delete(@NonNull String cod) {		
		checkSucursalExist(cod);
		getSucursalRepository().delete(cod);
	}

	protected Sucursal checkSucursalExist(String cod) {
		Optional<Sucursal> sucursal = getSucursalRepository().getSucursal(cod);
		return sucursal.orElseThrow( () -> new ValidationException(MessageConstants.FX_SUCURSAL_DOESNT_EXIST));
				
	}

	//TODO: validar los datos obligatorios
	protected void checkDataRequired(Sucursal sucursal) {
		if (StringUtils.isBlank(sucursal.getCod())) {
			throw new ValidationException(MessageConstants.SUCURSAL_DATA_REQUIRED);
		}
	}
	
	public List<Sucursal> getSucursales(Integer first, Integer max){
		return this.getSucursalRepository().getList(first, max);
	}
	
	public Optional<Sucursal> getSucursal(String cod){
		return this.getSucursalRepository().getSucursal(cod);
	}
}
