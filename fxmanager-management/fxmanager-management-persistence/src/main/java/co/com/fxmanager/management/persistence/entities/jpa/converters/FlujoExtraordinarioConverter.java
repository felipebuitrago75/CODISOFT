package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.DenominacionCantidad;
import co.com.fxmanager.management.domain.entities.FlujoExtraordinario;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.persistence.entities.jpa.FlujoExtraordinarioEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TrasladoEntity;
import lombok.Getter;

@Component
@Getter
public class FlujoExtraordinarioConverter implements Converter<FlujoExtraordinarioEntity, FlujoExtraordinario> {

	@Autowired
	private FxConverter fxConverter;

	public FlujoExtraordinarioConverter() {
		super();
	}

	@Override
	public FlujoExtraordinario convert(FlujoExtraordinarioEntity flujoExtraordinarioEntity) {

		FlujoExtraordinario flujoExtraordinario;

		if (flujoExtraordinarioEntity.getIdSoipc() == null) {
			flujoExtraordinario = new FlujoExtraordinario(flujoExtraordinarioEntity.getId(),
					flujoExtraordinarioEntity.getTipo(), flujoExtraordinarioEntity.getValor(),
					flujoExtraordinarioEntity.getFecha(), flujoExtraordinarioEntity.getEstado(),
					fxConverter.convert(flujoExtraordinarioEntity.getFx()), flujoExtraordinarioEntity.getNaturaleza(),
					flujoExtraordinarioEntity.getReceptor());
		} else {
			flujoExtraordinario = new FlujoExtraordinario(flujoExtraordinarioEntity.getId(),
					flujoExtraordinarioEntity.getTipo(), flujoExtraordinarioEntity.getValor(),
					flujoExtraordinarioEntity.getFecha(), flujoExtraordinarioEntity.getEstado(),
					fxConverter.convert(flujoExtraordinarioEntity.getFx()), flujoExtraordinarioEntity.getNaturaleza(),
					flujoExtraordinarioEntity.getReceptor(), flujoExtraordinarioEntity.getIdSoipc());
		}

		flujoExtraordinario.setCriterio(flujoExtraordinarioEntity.getCriterio());

		if (flujoExtraordinarioEntity.getDescripcion() != null) {
			flujoExtraordinario.setDescripcion(flujoExtraordinarioEntity.getDescripcion());
		}

		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		if (persistenceUtil.isLoaded(flujoExtraordinarioEntity, FlujoExtraordinarioEntity.ClassInfo.TURNO)
				&& flujoExtraordinarioEntity.getTurno() != null) {
			Turno turno = new Turno(flujoExtraordinarioEntity.getTurno().getId(),
					flujoExtraordinarioEntity.getTurno().getCaja().getId(),
					flujoExtraordinarioEntity.getTurno().getUsuario().getId(),
					flujoExtraordinarioEntity.getTurno().getFechaInicio(), flujoExtraordinarioEntity.getTurno().getUsuario().getNombre()+" "+ flujoExtraordinarioEntity.getTurno().getUsuario().getApellido()  );
			if (flujoExtraordinarioEntity.getTurno().getFechaFin() != null) {
				turno.setFechaFin(flujoExtraordinarioEntity.getTurno().getFechaFin());
			}

			flujoExtraordinario.setTurno(turno);
		}

		if (persistenceUtil.isLoaded(flujoExtraordinarioEntity, TrasladoEntity.ClassInfo.DENOMINACIONES_CANTIDAD)) {
			List<DenominacionCantidad> denominacionesCantidad = flujoExtraordinarioEntity.getDenominacionesCantidad()
					.stream().map(denominacionCantidadEntity -> {
						return new DenominacionCantidad(denominacionCantidadEntity.getCantidad(),
								denominacionCantidadEntity.getDenominacion().getValor(),
								denominacionCantidadEntity.getDenominacion().getFx().getId());
					}).collect(Collectors.toList());

			flujoExtraordinario.setDenominacionesYCantidades(denominacionesCantidad);
		}
		return flujoExtraordinario;
	}

}
