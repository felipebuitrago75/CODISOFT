package co.com.fxmanager.management.domain.services;

import java.util.List;
import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Traslado;
import co.com.fxmanager.management.domain.entities.TrasladoFiltro;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.domain.services.constants.MessageConstants;
import co.com.fxmanager.management.domain.services.exceptions.ValidationException;
import co.com.fxmanager.management.persistence.repositories.TrasladoRepository;
import lombok.Getter;
import lombok.NonNull;

public class TrasladoService {

	@Getter
	private TrasladoRepository trasladoRepository;

	public TrasladoService(@NonNull  TrasladoRepository trasladoRepository) {
		super();
		this.trasladoRepository = trasladoRepository;
	}

	public Traslado save(@NonNull Traslado traslado) {
		//checkDataRequired(traslado);
		return getTrasladoRepository().create(traslado);
	}

	public Traslado update(@NonNull Long id,@NonNull Turno turno) {
		checkOperacionExist(id);
		return getTrasladoRepository().update(id, turno);
	}

	public void delete(@NonNull Long id) {		
		checkOperacionExist(id);
		getTrasladoRepository().delete(id);
	}

	protected Traslado checkOperacionExist(Long id) {
		Optional<Traslado> traslado = getTrasladoRepository().getTraslado(id);
		return traslado.orElseThrow(()->new ValidationException(MessageConstants.OPERACION_DOESNT_EXIST));
		
	}

	protected void checkDataRequired(Traslado traslado) {
	
	}
	
	public List<Traslado> getTraslados(Integer first, Integer max){
		return this.getTrasladoRepository().getList(first, max);
	}
	
	public Optional<Traslado> getTraslado(Long id){
		return this.getTrasladoRepository().getTraslado(id);
	}
	
	public void cancelarTraslado(Long id, Turno turno){
		this.getTrasladoRepository().cancelarTraslado(id,turno);
	}

	public List<Traslado> obtenerConFiltro(TrasladoFiltro trasladoFiltro) {
		return this.getTrasladoRepository().obtenerConFiltro(trasladoFiltro);
	}
}
