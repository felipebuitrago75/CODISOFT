package co.com.fxmanager.management.persistence.repositories.jpa.customs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.domain.entities.TrasladoFiltro;
import co.com.fxmanager.management.persistence.entities.jpa.TrasladoEntity;
import co.com.fxmanager.management.persistence.repositories.jpa.TrasladoJPARepository;
import lombok.Getter;

@Getter
@Repository
public class TrasladoJPARepositoryImpl implements TrasladoJPARepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<TrasladoEntity> obtenerConFiltro(TrasladoFiltro trasladoFiltro) {
		String consulta = "SELECT t FROM TrasladoEntity t JOIN FETCH t.sucursalOrigen so JOIN FETCH t.sucursalDestino sd JOIN FETCH t.fx fx WHERE 1=1 ";

		Map<String, Object> mapaParametros = new HashMap<String, Object>();

		if (trasladoFiltro.getEstado() != null) {
			consulta += "AND t.estado = :estado ";
			mapaParametros.put("estado", trasladoFiltro.getEstado());
		}
		if (trasladoFiltro.getFecha() != null) {
			consulta += "AND SUBSTRING(p.fechaOperacion,1,10) = SUBSTRING(:fecha, 1,10) ";
			mapaParametros.put("fecha", trasladoFiltro.getFecha());
		}
		if (trasladoFiltro.getCodigoFx() != null ) {
			consulta += "AND fx.codigo = :codigo ";
			mapaParametros.put("codigo", trasladoFiltro.getCodigoFx());
		}
		if (trasladoFiltro.getCodigoSucursalOrigen() != null ) {
			consulta += "AND so.codigo = :codigoSucursalOrigen ";
			mapaParametros.put("codigoSucursalOrigen", trasladoFiltro.getCodigoSucursalOrigen());
		}
		if (trasladoFiltro.getCodigoSucursalDestino() != null ) {
			consulta += "AND sd.codigo = :codigoSucursalDestino ";
			mapaParametros.put("codigoSucursalDestino", trasladoFiltro.getCodigoSucursalDestino());
		}
		TypedQuery<TrasladoEntity> q = entityManager.createQuery(consulta, TrasladoEntity.class);

		mapaParametros.forEach((parametro, value) -> q.setParameter(parametro, value));

		return q.getResultList();
	}

}
