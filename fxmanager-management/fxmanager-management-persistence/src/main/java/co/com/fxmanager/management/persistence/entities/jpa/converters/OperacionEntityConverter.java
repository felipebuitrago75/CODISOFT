package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.Operacion;
import co.com.fxmanager.management.persistence.entities.jpa.DenominacionCantidadEntity;
import co.com.fxmanager.management.persistence.entities.jpa.OperacionEntity;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.DenominacionSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.FxSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Component
public class OperacionEntityConverter implements EntityConverter<Operacion, OperacionEntity> {

	@Autowired
	private FxEntityConverter fxEntityConverter;

	@Autowired
	private FxSpringRepository fxSpringRepository;

	@Autowired
	private TurnoEntityConverter turnoEntityConverter;

	@Autowired
	TurnoSpringRepository turnoSpringRepository;

	@Autowired
	DenominacionSpringRepository denominacionSpringRepository;

	
	@Override
	public OperacionEntity convert(Operacion operacion) {
		return convert(operacion, OperacionEntity::new);
	}

	@Override
	public OperacionEntity convert(Operacion operacion, Supplier<OperacionEntity> supplier) {
		OperacionEntity operacionEntity = supplier.get();

		if (operacion.getId() != null)
			operacionEntity.setId(operacion.getId());
		operacionEntity.setFechaOperacion(operacion.getFechaOperacion());

		operacionEntity.setTipo(operacion.getTipo());
		operacionEntity.setNominal(operacion.getNominal());
		operacionEntity.setValorFxOperacion(operacion.getParFx().getValorFxOperacion());
		operacionEntity.setValorValoracion(operacion.getParFx().getValorValoracion());
		operacionEntity.setEstado(operacion.getEstado());
		operacion.getIdSoipc().ifPresent(operacionEntity::setIdSoipc);

		operacionEntity.setFxBase(getFxSpringRepository().findByCodigo(operacion.getParFx().getFxBase().getCodigo())
				.orElseThrow(NoFoundDataException::new));
		operacionEntity.setFxOper(getFxSpringRepository().findByCodigo(operacion.getParFx().getFxOper().getCodigo())
				.orElseThrow(NoFoundDataException::new));

		operacionEntity.setTurno(getTurnoSpringRepository().findById(operacion.getTurno().getId())
				.orElseThrow(NoFoundDataException::new));

		if (operacion.getTurnoEdicion().isPresent()) {
			operacionEntity.setTurno(
					getTurnoSpringRepository().findById(operacion.getTurnoEdicion().get().getId()).orElse(null));
		}

		Set<DenominacionCantidadEntity> denominacionesCantidadRecibidas = operacion
				.getDenominacionesYCantidadesRecibidas().stream().map(denominacionCantidad -> {
					DenominacionCantidadEntity denominacionCantidadEntity = new DenominacionCantidadEntity();
					denominacionCantidadEntity.setCantidad(denominacionCantidad.getCantidad());
					denominacionCantidadEntity.setDenominacion(denominacionSpringRepository
							.obtenerDenominacionPorFxyValor(denominacionCantidad.getIdFx(), denominacionCantidad.getValor()).orElseThrow(NoFoundDataException::new));
					denominacionCantidadEntity.setOperacion(operacionEntity);
					denominacionCantidadEntity.setTipo("ENTRADA");
					return denominacionCantidadEntity;
				}).collect(Collectors.toSet());
		operacionEntity.setDenominacionesCantidadRecibidas(denominacionesCantidadRecibidas);
		
		Set<DenominacionCantidadEntity> denominacionesCantidadEntregadas = operacion
				.getDenominacionesYCantidadesEntregadas().stream().map(denominacionCantidad -> {
					DenominacionCantidadEntity denominacionCantidadEntity = new DenominacionCantidadEntity();
					denominacionCantidadEntity.setCantidad(denominacionCantidad.getCantidad());
					denominacionCantidadEntity.setDenominacion(denominacionSpringRepository
							.obtenerDenominacionPorFxyValor(denominacionCantidad.getIdFx(), denominacionCantidad.getValor()).orElseThrow(NoFoundDataException::new));
					denominacionCantidadEntity.setOperacion(operacionEntity);
					denominacionCantidadEntity.setTipo("SALIDA");
					return denominacionCantidadEntity;
				}).collect(Collectors.toSet());
		operacionEntity.setDenominacionesCantidadEntregadas(denominacionesCantidadEntregadas);
		
		
		
		return operacionEntity;
	}

}
