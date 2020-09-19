package co.com.fxmanager.management.domain.services;

import java.util.List;
import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Caja;
import co.com.fxmanager.management.domain.services.constants.MessageConstants;
import co.com.fxmanager.management.domain.services.exceptions.ValidationException;
import co.com.fxmanager.management.persistence.repositories.CajaRepository;
import lombok.Getter;
import lombok.NonNull;

public class CajaService {

	@Getter
	private CajaRepository cajaRepository;

	public CajaService(@NonNull CajaRepository cajaRepository) {
		super();
		this.cajaRepository = cajaRepository;
	}

	public Caja save(@NonNull Caja caja) {
		checkDataRequired(caja);
		return getCajaRepository().create(caja);
	}

	public Caja update(@NonNull Long id,@NonNull Caja caja) {
		checkSucursalExist(id);
		checkDataRequired(caja);
		return getCajaRepository().update(id, caja);
	}

	public void delete(@NonNull Long id) {		
		checkSucursalExist(id);
		getCajaRepository().delete(id);
	}
	
	protected Caja checkSucursalExist(Long id) {
		Optional<Caja> caja = getCajaRepository().getCaja(id);
		return caja.orElseThrow( () -> new ValidationException(MessageConstants.CAJA_DOESNT_EXIST));
				
	}

	//TODO: validar los datos obligatorios
	protected void checkDataRequired(Caja caja) {
		
	}
	
	public List<Caja> getCajas(Integer first, Integer max){
		return this.getCajaRepository().getList(first, max);
	}
	
	public Optional<Caja> getCaja(Long id){
		return this.getCajaRepository().getCaja(id);
	}
	
	public List<Caja> getCajasPorSucursal(String cod){
		return this.getCajaRepository().getCajasPorSucursal(cod);
	}
	
	
}
