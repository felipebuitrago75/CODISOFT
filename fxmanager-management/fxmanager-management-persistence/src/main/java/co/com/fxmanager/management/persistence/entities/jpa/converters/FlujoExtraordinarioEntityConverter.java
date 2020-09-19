package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.com.fxmanager.management.domain.entities.FlujoExtraordinario;
import co.com.fxmanager.management.persistence.entities.jpa.DenominacionCantidadEntity;
import co.com.fxmanager.management.persistence.entities.jpa.FlujoExtraordinarioEntity;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.DenominacionSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.FxSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Component
public class FlujoExtraordinarioEntityConverter implements EntityConverter<FlujoExtraordinario, FlujoExtraordinarioEntity> {

	@Autowired
	private FxEntityConverter fxEntityConverter;
	
	@Autowired
	private FxSpringRepository fxSpringRepository;
	
	@Autowired
	private TurnoEntityConverter turnoEntityConverter;
	
	@Autowired TurnoSpringRepository turnoSpringRepository;
	
	@Autowired
	DenominacionSpringRepository denominacionSpringRepository;
	
	@Override
	public FlujoExtraordinarioEntity convert(FlujoExtraordinario flujoExtraordinario) {
		return convert(flujoExtraordinario, FlujoExtraordinarioEntity::new);
	}

	@Override
	public FlujoExtraordinarioEntity convert(FlujoExtraordinario flujoExtraordinario, Supplier<FlujoExtraordinarioEntity> supplier) {
		FlujoExtraordinarioEntity flujoExtraordinarioEntity = supplier.get();
		
		if(flujoExtraordinario.getId()!=null)
			flujoExtraordinarioEntity.setId(flujoExtraordinario.getId());
		    flujoExtraordinarioEntity.setTipo(flujoExtraordinario.getTipo());
		    flujoExtraordinarioEntity.setValor(flujoExtraordinario.getValor());
		    flujoExtraordinarioEntity.setFecha(flujoExtraordinario.getFecha());
		    flujoExtraordinario.getIdSoipc().ifPresent(flujoExtraordinarioEntity::setIdSoipc);
		    flujoExtraordinarioEntity.setEstado(flujoExtraordinario.getEstado());
			flujoExtraordinarioEntity.setCriterio(flujoExtraordinario.getCriterio());
		    
			flujoExtraordinarioEntity.setNaturaleza(flujoExtraordinario.getNaturaleza());
			flujoExtraordinarioEntity.setReceptor(flujoExtraordinario.getReceptor());
			
			flujoExtraordinario.getDescripcion().ifPresent(flujoExtraordinarioEntity::setDescripcion);
			flujoExtraordinario.getCuenta().ifPresent(flujoExtraordinarioEntity::setCuenta);
			flujoExtraordinario.getConcepto().ifPresent(flujoExtraordinarioEntity::setConcepto);
		    
		    flujoExtraordinarioEntity.setFx(getFxSpringRepository().findByCodigo(flujoExtraordinario.getFx().getCodigo())
					.orElseThrow(NoFoundDataException::new));
		    
		    flujoExtraordinarioEntity.setTurno(getTurnoSpringRepository().findById(flujoExtraordinario.getTurno().getId())
					.orElseThrow(NoFoundDataException::new)); 
		    
		    Set<DenominacionCantidadEntity> denominacionesCantidadEntregadas = flujoExtraordinario
					.getDenominacionesYCantidades().stream().map(denominacionCantidad -> {
						DenominacionCantidadEntity denominacionCantidadEntity = new DenominacionCantidadEntity();
						denominacionCantidadEntity.setCantidad(denominacionCantidad.getCantidad());
						denominacionCantidadEntity.setDenominacion(denominacionSpringRepository
								.obtenerDenominacionPorFxyValor(denominacionCantidad.getIdFx(), denominacionCantidad.getValor()).orElseThrow(NoFoundDataException::new));
						denominacionCantidadEntity.setFlujo(flujoExtraordinarioEntity);
						denominacionCantidadEntity.setTipo(flujoExtraordinario.getTipo().equals("EGRESO")? "SALIDA" : "ENTRADA");
						return denominacionCantidadEntity;
					}).collect(Collectors.toSet());
		    flujoExtraordinarioEntity.setDenominacionesCantidad(denominacionesCantidadEntregadas);
		    
			return flujoExtraordinarioEntity;
	}

}
