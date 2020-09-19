package co.com.fxmanager.management.persistence.entities.jpa.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import co.com.fxmanager.management.domain.entities.AperturaTurno;
import co.com.fxmanager.management.persistence.entities.jpa.AperturaTurnoEntity;
import lombok.Getter;

@Component
@Getter
public class AperturaTurnoConverter implements Converter<AperturaTurnoEntity, AperturaTurno> {

	@Autowired
	private TurnoConverter turnoConverter;
	
	@Autowired
	private FxConverter fxConverter;
	
	
	public AperturaTurnoConverter() {
		super();
	}
	
	@Override
	public AperturaTurno convert(AperturaTurnoEntity aperturaTurnoEntity) {
		return new AperturaTurno(aperturaTurnoEntity.getId(), aperturaTurnoEntity.getTurno().getId(), 
				fxConverter.convert(aperturaTurnoEntity.getFx()), 
				aperturaTurnoEntity.getNominal(), aperturaTurnoEntity.getPrecioPromedio());
	}

}
