package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.com.fxmanager.management.domain.entities.AperturaTurno;
import co.com.fxmanager.management.persistence.entities.jpa.AperturaTurnoEntity;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.FxSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Component
public class AperturaTurnoEntityConverter implements EntityConverter<AperturaTurno, AperturaTurnoEntity>{

	@Autowired
	private TurnoSpringRepository turnoSpringRepository;
	
	@Autowired
	private FxSpringRepository fxSpringRepository;
	
	@Override
	public AperturaTurnoEntity convert(AperturaTurno aperturaTurno) {
		return convert(aperturaTurno, AperturaTurnoEntity::new);
	}

	@Override
	public AperturaTurnoEntity convert(AperturaTurno aperturaTurno, Supplier<AperturaTurnoEntity> supplier) {
		AperturaTurnoEntity aperturaTurnoEntity = supplier.get();

		if(aperturaTurno.getId()!=null) {
			aperturaTurnoEntity.setId(aperturaTurno.getId());
		}
		aperturaTurnoEntity.setPrecioPromedio(aperturaTurno.getPrecioPromedio().orElse(null));
		aperturaTurnoEntity.setNominal(aperturaTurno.getNominal());
		
		aperturaTurnoEntity.setTurno(turnoSpringRepository.findById(aperturaTurno.getIdTurno())
				.orElseThrow(NoFoundDataException::new));
		
		aperturaTurnoEntity.setFx(fxSpringRepository.findByCodigo(aperturaTurno.getFx().getCodigo())
				.orElseThrow(NoFoundDataException::new));
		return aperturaTurnoEntity;
	}

}
