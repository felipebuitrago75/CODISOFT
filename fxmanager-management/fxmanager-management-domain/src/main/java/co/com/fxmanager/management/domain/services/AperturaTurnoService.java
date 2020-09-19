package co.com.fxmanager.management.domain.services;

import java.util.List;
import java.util.Optional;
import co.com.fxmanager.management.domain.entities.AperturaTurno;
import co.com.fxmanager.management.domain.services.constants.MessageConstants;
import co.com.fxmanager.management.domain.services.exceptions.ValidationException;
import co.com.fxmanager.management.persistence.repositories.AperturaTurnoRepository;
import lombok.Getter;
import lombok.NonNull;

public class AperturaTurnoService {
	
	@Getter
	private AperturaTurnoRepository aperturaTurnoRepository;
	
	public AperturaTurnoService(@NonNull AperturaTurnoRepository aperturaTurnoRepository) {
		super();
		this.aperturaTurnoRepository = aperturaTurnoRepository;
	}
	
	public AperturaTurno save(@NonNull AperturaTurno aperturaTurno) {
		checkDataRequired(aperturaTurno);
		return getAperturaTurnoRepository().create(aperturaTurno);
	}

	public AperturaTurno update(@NonNull Long id,@NonNull AperturaTurno aperturaTurno) {
		checkTurnoExist(id);
		checkDataRequired(aperturaTurno);
		return getAperturaTurnoRepository().update(id, aperturaTurno);
	}
	
	protected AperturaTurno checkTurnoExist(Long id) {
		Optional<AperturaTurno> aperturaTurno = getAperturaTurnoRepository().getAperturaTurno(id);
		return aperturaTurno.orElseThrow( () -> new ValidationException(MessageConstants.APERTURA_DOESNT_EXIST));
				
	}

	protected void checkDataRequired(AperturaTurno aperturaTurno) {
		
	}
	
	public List<AperturaTurno> getAperturaTurnos(Integer first, Integer max){
		return this.getAperturaTurnoRepository().getList(first, max);
	}
	
	public Optional<AperturaTurno> getAperturaTurno(Long id){
		return this.getAperturaTurnoRepository().getAperturaTurno(id);
	}

}
