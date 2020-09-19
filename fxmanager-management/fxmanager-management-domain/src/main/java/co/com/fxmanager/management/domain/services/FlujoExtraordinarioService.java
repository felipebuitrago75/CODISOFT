package co.com.fxmanager.management.domain.services;

import java.util.List;
import java.util.Optional;

import co.com.fxmanager.auth.domain.services.AuthService;
import co.com.fxmanager.management.domain.entities.FlujoExtraordinario;
import co.com.fxmanager.management.domain.entities.FlujoFiltro;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.domain.services.constants.MessageConstants;
import co.com.fxmanager.management.domain.services.exceptions.ValidationException;
import co.com.fxmanager.management.persistence.repositories.FlujoExtraordinarioRepository;
import lombok.Getter;
import lombok.NonNull;

public class FlujoExtraordinarioService {

	@Getter
	private FlujoExtraordinarioRepository flujoExtraordinarioRepository;

	//private AuthService authService;

	public FlujoExtraordinarioService(@NonNull FlujoExtraordinarioRepository flujoExtraordinarioRepository,
			AuthService authService) {
		super();
		this.flujoExtraordinarioRepository = flujoExtraordinarioRepository;
		//this.authService = authService;
	}

	public FlujoExtraordinario save(@NonNull FlujoExtraordinario flujoExtraordinario) {
		//Optional<User> optionalUser= authService.getCurrentUser();
		checkDataRequired(flujoExtraordinario);
		return getFlujoExtraordinarioRepository().create(flujoExtraordinario);
	}
	
	public FlujoExtraordinario update(@NonNull Long id,@NonNull FlujoExtraordinario flujoExtraordinario) {
		checkOperacionExist(id);
		checkDataRequired(flujoExtraordinario);
		return getFlujoExtraordinarioRepository().update(id, flujoExtraordinario);
	}

	public void delete(@NonNull Long id) {		
		checkOperacionExist(id);
		getFlujoExtraordinarioRepository().delete(id);
	}

	protected FlujoExtraordinario checkOperacionExist(Long id) {
		Optional<FlujoExtraordinario> flujoExtraordinario = getFlujoExtraordinarioRepository().getFlujoExtraordinario(id);
		return flujoExtraordinario.orElseThrow(()->new ValidationException(MessageConstants.FLUJO_EXTRAORDINARIO_DOESNT_EXIST));
		
	}
	
	protected void checkDataRequired(FlujoExtraordinario flujoExtraordinario) {
		
		if(flujoExtraordinario.getTipo() == null) {
			throw new ValidationException(MessageConstants.FLUJO_EXTRAORDINARIO_TIPO_REQUIRED);
		}
		if(flujoExtraordinario.getValor() == null) {
			throw new ValidationException(MessageConstants.FLUJO_EXTRAORDINARIO_VALOR_REQUIRED);
		}
	}
	
	public List<FlujoExtraordinario> getFlujoExtraordinarios(Integer first, Integer max){
		return this.getFlujoExtraordinarioRepository().getList(first, max);
	}
	
	public Optional<FlujoExtraordinario> getFlujoExtraordinario(Long id){
		return this.getFlujoExtraordinarioRepository().getFlujoExtraordinario(id);
	}
	
	public List<FlujoExtraordinario> listaFlujosPorTurno(Turno turno){
		return this.getFlujoExtraordinarioRepository().listaFlujosPorTurno(turno);
	}
	
	public void cancelarFlujo(@NonNull Long id, Turno turno) {
		this.getFlujoExtraordinarioRepository().cancelarFlujo(id, turno);
	}
	
	public List<FlujoExtraordinario> obtenerConFiltro(FlujoFiltro flujoFiltro){
		return this.getFlujoExtraordinarioRepository().obtenerConFiltro(flujoFiltro);
	}
}
