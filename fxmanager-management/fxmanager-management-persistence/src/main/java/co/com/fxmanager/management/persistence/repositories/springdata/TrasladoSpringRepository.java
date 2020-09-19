package co.com.fxmanager.management.persistence.repositories.springdata;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.TrasladoEntity;
import co.com.fxmanager.management.persistence.repositories.jpa.TrasladoJPARepository;

@Repository
public interface TrasladoSpringRepository extends TrasladoJPARepository, JpaRepository<TrasladoEntity, Long> {

	@EntityGraph(value = TrasladoEntity.EntityGraph.WITH_SUCURSALES_Y_FX, type = EntityGraphType.LOAD)
	Optional<TrasladoEntity> findById(Long id);
	
	@Query("SELECT t FROM TrasladoEntity t WHERE t.sucursalOrigen.id= ?1 AND t.fx.id = ?2 AND ( t.estado = 'EJECUTADO' OR  t.estado = 'EN PROCESO') AND SUBSTRING(t.fecha,1,10) = SUBSTRING(?3,1,10) ")
	public List<TrasladoEntity> obtenerListaDeTrasladosSaliente(Long idSucursal, Long idFx, LocalDate fecha);
	
	/**
	 * TODO: FALTA AGREGAR A LA ENTIDAD LA FECHA DE LLEGADA Y ARREGLAR EL FILTRO DE FECHA
	 * @param idSucursal
	 * @param idFx
	 * @param fecha
	 * @return
	 */
	@Query("SELECT t FROM TrasladoEntity t WHERE t.sucursalDestino.id= ?1 AND t.fx.id = ?2 AND t.estado = 'EJECUTADO' AND SUBSTRING(t.fecha,1,10) = SUBSTRING(?3,1,10) ")
	public List<TrasladoEntity> obtenerListaDeTrasladosLlegadas(Long idSucursal, Long idFx, LocalDate fecha);
	
	@Query("SELECT t FROM TrasladoEntity t WHERE t.sucursalOrigen.id= ?1 AND t.fx.id = ?2 AND t.estado = 'EN PROCESO'  AND SUBSTRING(t.fecha,1,10) = SUBSTRING(?3,1,10) ")
	public List<TrasladoEntity> obtenerListaDeTrasladosSalienteEnProceso(Long idSucursal, Long idFx, LocalDate fecha);
	
	@Query("SELECT t FROM TrasladoEntity t WHERE t.sucursalDestino.id= ?1 AND t.fx.id = ?2 AND t.estado = 'EN PROCESO' AND SUBSTRING(t.fecha,1,10) = SUBSTRING(?3,1,10) ")
	public List<TrasladoEntity> obtenerListaDeTrasladosLlegadasEnProceso(Long idSucursal, Long idFx, LocalDate fecha);
	
	
	@Query("SELECT t FROM TrasladoEntity t WHERE t.turnoOrigen.id= ?1 AND t.fx.id = ?2 AND ( t.estado = 'EJECUTADO' OR  t.estado = 'EN PROCESO')")
	public List<TrasladoEntity> obtenerListaDeTrasladosSalienteTurnoyFx(Long idTurno, Long idFx);
	
	@Query("SELECT t FROM TrasladoEntity t WHERE t.turnoDestino.id= ?1 AND t.fx.id = ?2 AND t.estado = 'EJECUTADO'")
	public List<TrasladoEntity> obtenerListaDeTrasladosLlegadasTurnoyFx(Long idTurno, Long idFx);


}
