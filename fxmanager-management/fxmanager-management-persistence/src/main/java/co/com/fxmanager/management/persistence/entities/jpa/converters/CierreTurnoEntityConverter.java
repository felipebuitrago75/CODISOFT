package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.com.fxmanager.management.domain.entities.CierreTurno;
import co.com.fxmanager.management.persistence.entities.jpa.CierreTurnoEntity;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.FxSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Component
public class CierreTurnoEntityConverter implements EntityConverter<CierreTurno, CierreTurnoEntity> {

	@Autowired
	private TurnoSpringRepository turnoSpringRepository;
	
	@Autowired
	private FxSpringRepository fxSpringRepository;
	
	@Override
	public CierreTurnoEntity convert(CierreTurno cierreTurno) {
		return convert(cierreTurno, CierreTurnoEntity::new);
	}

	@Override
	public CierreTurnoEntity convert(CierreTurno cierreTurno, Supplier<CierreTurnoEntity> supplier) {
		CierreTurnoEntity cierreTurnoEntity = supplier.get();

		if(cierreTurno.getId()!=null) {
			cierreTurnoEntity.setId(cierreTurno.getId());
		}
		cierreTurnoEntity.setPrecioPromedio(cierreTurno.getPrecioPromedio().orElse(null));
		cierreTurnoEntity.setNominal(cierreTurno.getNominal());
		
		cierreTurnoEntity.setTurno(turnoSpringRepository.findById(cierreTurno.getIdTurno())
				.orElseThrow(NoFoundDataException::new));
		
		cierreTurnoEntity.setMoneda(fxSpringRepository.findByCodigo(cierreTurno.getMoneda().getCodigo())
				.orElseThrow(NoFoundDataException::new));
		return cierreTurnoEntity;
	}

}
