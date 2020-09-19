package co.com.fxmanager.management.persistence.repositories.springdata;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.FlujoExtraordinarioEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TrasladoEntity;
import co.com.fxmanager.management.persistence.repositories.jpa.FlujoJPARepository;


@Repository
public interface FlujoExtraordinarioSpringRepository extends  FlujoJPARepository, JpaRepository<FlujoExtraordinarioEntity, Long>  {

	@EntityGraph(value = FlujoExtraordinarioEntity.EntityGraph.WITH_DENOMINACIONES_CANTIDADES, type = EntityGraphType.LOAD)
	Optional<FlujoExtraordinarioEntity> findById(Long id);
	
	
	@Query("SELECT e FROM FlujoExtraordinarioEntity e JOIN FETCH e.turno t JOIN FETCH t.caja c  WHERE SUBSTRING(e.fecha, 1,10) = SUBSTRING(?1, 1,10) AND e.tipo = 'EGRESO' AND e.estado = 'EJECUTADO' "
			+ " AND c.sucursal.id = ?2 AND e.fx.id = ?3")
	public List<FlujoExtraordinarioEntity> obtenerEgresos(LocalDate fecha, Long idSucursal, Long idFx);
	
	@Query("SELECT i FROM FlujoExtraordinarioEntity i JOIN FETCH i.turno t JOIN FETCH t.caja c WHERE SUBSTRING(i.fecha, 1,10) = SUBSTRING(?1, 1,10) AND i.tipo = 'INGRESO'  AND i.estado = 'EJECUTADO'"
			+ " AND c.sucursal.id = ?2 AND i.fx.id = ?3")
	public List<FlujoExtraordinarioEntity> obtenerIngresos(LocalDate fecha, Long idSucursal, Long idFx);
	
	@Query("SELECT i FROM FlujoExtraordinarioEntity i JOIN FETCH i.turno t WHERE t.id = ?1  "
			+ "AND i.estado = 'EJECUTADO'")
	public List<FlujoExtraordinarioEntity> listaFlujosPorTurno(Long idTurno);
	
	@Query("SELECT i FROM FlujoExtraordinarioEntity i JOIN FETCH i.turno t WHERE t.id = ?1  "
			+ "AND i.estado = 'EJECUTADO' AND i.tipo = 'EGRESO' AND i.fx.id = ?2 ")
	public List<FlujoExtraordinarioEntity> egresosPorTurno(Long idTurno, Long idFx);
	
	@Query("SELECT i FROM FlujoExtraordinarioEntity i JOIN FETCH i.turno t WHERE t.id = ?1  "
			+ "AND i.estado = 'EJECUTADO'  AND i.tipo = 'INGRESO'  AND i.fx.id = ?2  ")
	public List<FlujoExtraordinarioEntity> ingresosPorTurno(Long idTurno, Long idFx);
}
