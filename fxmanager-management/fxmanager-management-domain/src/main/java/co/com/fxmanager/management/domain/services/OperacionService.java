package co.com.fxmanager.management.domain.services;

import java.util.List;
import java.util.Optional;

import co.com.fxmanager.auth.domain.services.AuthService;
import co.com.fxmanager.management.domain.entities.Operacion;
import co.com.fxmanager.management.domain.entities.OperacionFiltro;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.domain.services.constants.MessageConstants;
import co.com.fxmanager.management.domain.services.exceptions.ValidationException;
import co.com.fxmanager.management.persistence.repositories.OperacionRepository;
import lombok.Getter;
import lombok.NonNull;

public class OperacionService {

	@Getter
	private OperacionRepository operacionRepository;
	
	//private AuthService authService;

	public OperacionService(@NonNull  OperacionRepository operacionRepository, AuthService authService) {
		super();
		this.operacionRepository = operacionRepository;
		//this.authService=authService;
	}

	public Operacion save(@NonNull Operacion operacion) {
		//Optional<User> optionalUser= authService.getCurrentUser();
		checkDataRequired(operacion);
		return getOperacionRepository().create(operacion);
	}

	public Operacion update(@NonNull Long id,@NonNull Operacion operacion) {
		checkOperacionExist(id);
		checkDataRequired(operacion);
		return getOperacionRepository().update(id, operacion);
	}

	public void delete(@NonNull Long id) {		
		checkOperacionExist(id);
		getOperacionRepository().delete(id);
	}

	protected Operacion checkOperacionExist(Long id) {
		Optional<Operacion> operacion = getOperacionRepository().getOperacion(id);
		return operacion.orElseThrow(()->new ValidationException(MessageConstants.OPERACION_DOESNT_EXIST));
		
	}

	protected void checkDataRequired(Operacion operacion) {
		if (operacion.getFechaOperacion() == null) {
			throw new ValidationException(MessageConstants.OPERACION_FECHA_OPERACION_REQUIRED);
		}
		if(operacion.getParFx().getFxBase() == null) {
			throw new ValidationException(MessageConstants.OPERACION_FX_BASE_REQUIRED);
		}
		if(operacion.getParFx().getFxOper() == null) {
			throw new ValidationException(MessageConstants.OPERACION_FX_OPER_REQUIRED);
		}
		if(operacion.getParFx().getValorFxOperacion() == null) {
			throw new ValidationException(MessageConstants.OPERACION_VALOR_FX_OPERACION_REQUIRED);
		}
		if(operacion.getParFx().getValorValoracion() == null) {
			throw new ValidationException(MessageConstants.OPERACION_VALOR_VALORACION_REQUIRED);
		}
		if(operacion.getTipo() == null) {
			throw new ValidationException(MessageConstants.OPERACION_TIPO_REQUIRED);
		}
		if(operacion.getNominal() == null) {
			throw new ValidationException(MessageConstants.OPERACION_NOMINAL_REQUIRED);
		}
		if(operacion.getEstado() == null) {
			throw new ValidationException(MessageConstants.OPERACION_ESTADO_REQUIRED);
		}
		
	}
	
	public List<Operacion> getOperaciones(Integer first, Integer max){
		return this.getOperacionRepository().getList(first, max);
	}
	
	public Optional<Operacion> getOperacion(Long id){
		return this.getOperacionRepository().getOperacion(id);
	}

	public Operacion cancelarOperacion(Long id, Turno turno) {
		checkOperacionExist(id);
		return getOperacionRepository().cancelarOperacion(id, turno);
		
	}
	
	public List<Operacion> buscarConFiltro(OperacionFiltro operacion) {
		return getOperacionRepository().buscarConFiltro( operacion);
		
	}

	public List<Operacion> getOperacionesPorTurno(Long id) {
		return getOperacionRepository().getOperacionesPorTurno(id);
	}

	public List<Operacion> getOperacionesUltima() {
		return getOperacionRepository().getOperacionesUltimas();
	}
	
	
}
