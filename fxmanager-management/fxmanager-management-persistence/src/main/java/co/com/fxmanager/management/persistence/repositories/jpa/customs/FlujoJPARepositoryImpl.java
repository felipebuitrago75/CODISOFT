package co.com.fxmanager.management.persistence.repositories.jpa.customs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.domain.entities.FlujoFiltro;
import co.com.fxmanager.management.persistence.entities.jpa.FlujoExtraordinarioEntity;
import co.com.fxmanager.management.persistence.repositories.jpa.FlujoJPARepository;
import lombok.Getter;

@Getter
@Repository
public class FlujoJPARepositoryImpl implements FlujoJPARepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<FlujoExtraordinarioEntity> obtenerConFiltro(FlujoFiltro filtro) {
		String consulta = "SELECT f FROM FlujoExtraordinarioEntity f JOIN FETCH f.fx fx WHERE 1=1 ";

		@SuppressWarnings("rawtypes")
		Map<String, Object> mapaParametros = new HashMap();

		if (filtro.getTipo() != null) {
			consulta += "AND f.tipo = :tipo ";
			mapaParametros.put("tipo", filtro.getTipo());
		}
		if (filtro.getEstado() != null) {
			consulta += "AND f.estado = :estado ";
			mapaParametros.put("estado", filtro.getEstado());
		}
		if (filtro.getCriterio() != null) {
			consulta += "AND f.criterio = :criterio ";
			mapaParametros.put("criterio", filtro.getCriterio());
		}
		if (filtro.getFecha() != null) {
			consulta += "AND SUBSTRING(f.fecha,1,10) = SUBSTRING(:fecha, 1,10) ";
			mapaParametros.put("fecha", filtro.getFecha());
		}
		if (filtro.getCodigoFx() != null ) {
			consulta += "AND fx.codigo = :codigo ";
			mapaParametros.put("codigo", filtro.getCodigoFx());
		}
		TypedQuery<FlujoExtraordinarioEntity> q = entityManager.createQuery(consulta, FlujoExtraordinarioEntity.class);

		mapaParametros.forEach((parametro, value) -> q.setParameter(parametro, value));

		return q.getResultList();
	}

}
