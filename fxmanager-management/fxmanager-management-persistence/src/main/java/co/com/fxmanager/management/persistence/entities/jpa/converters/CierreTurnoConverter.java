package co.com.fxmanager.management.persistence.entities.jpa.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import co.com.fxmanager.management.domain.entities.CierreTurno;
import co.com.fxmanager.management.persistence.entities.jpa.CierreTurnoEntity;
import lombok.Getter;

@Component
@Getter
public class CierreTurnoConverter implements Converter<CierreTurnoEntity, CierreTurno> {

	@Autowired
	private TurnoConverter turnoConverter;
	
	@Autowired
	private FxConverter fxConverter;
	
	@Override
	public CierreTurno convert(CierreTurnoEntity cierreTurnoEntity) {
		return new CierreTurno(cierreTurnoEntity.getId(), cierreTurnoEntity.getTurno().getId(), 
				fxConverter.convert(cierreTurnoEntity.getMoneda()), 
				cierreTurnoEntity.getNominal(), cierreTurnoEntity.getPrecioPromedio());
	}

}
