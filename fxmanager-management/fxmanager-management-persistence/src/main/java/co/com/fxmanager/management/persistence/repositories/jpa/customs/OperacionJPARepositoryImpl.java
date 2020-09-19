package co.com.fxmanager.management.persistence.repositories.jpa.customs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.domain.entities.OperacionFiltro;
import co.com.fxmanager.management.persistence.entities.jpa.OperacionEntity;
import co.com.fxmanager.management.persistence.repositories.jpa.OperacionJPARepository;
import lombok.Getter;

@Getter
@Repository
public class OperacionJPARepositoryImpl implements OperacionJPARepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<OperacionEntity> obtenerConFiltro(OperacionFiltro operacion) {
		String consulta = "SELECT p FROM OperacionEntity p JOIN FETCH p.fxOper fx WHERE 1=1 ";

		@SuppressWarnings("rawtypes")
		Map<String, Object> mapaParametros = new HashMap();

		if (operacion.getTipo() != null) {
			consulta += "AND p.tipo = :tipo ";
			mapaParametros.put("tipo", operacion.getTipo());
		}
		if (operacion.getEstado() != null) {
			consulta += "AND p.estado = :estado ";
			mapaParametros.put("estado", operacion.getEstado());
		}
		if (operacion.getFechaOperacion() != null) {
			consulta += "AND SUBSTRING(p.fechaOperacion,1,10) = SUBSTRING(:fechaOperacion, 1,10) ";
			mapaParametros.put("fechaOperacion", operacion.getFechaOperacion());
		}
		if (operacion.getCodigoFx() != null ) {
			consulta += "AND fx.codigo = :codigo ";
			mapaParametros.put("codigo", operacion.getCodigoFx());
		}
		TypedQuery<OperacionEntity> q = entityManager.createQuery(consulta, OperacionEntity.class);

		mapaParametros.forEach((parametro, value) -> q.setParameter(parametro, value));

		return q.getResultList();
	}

	@Override
	public List<OperacionEntity> operacionesUltimas() {
		String consulta = " SELECT p FROM OperacionEntity p JOIN FETCH p.fxOper fx JOIN FETCH p.turno t ORDER BY p.fechaOperacion DESC";
		TypedQuery<OperacionEntity> q = entityManager.createQuery(consulta, OperacionEntity.class);
		return q.setMaxResults(100).getResultList();
	}
	
	

}
