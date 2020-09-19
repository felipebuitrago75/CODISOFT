package co.com.fxmanager.management.persistence.repositories.springdata;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.OperacionEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TurnoEntity;
import co.com.fxmanager.management.persistence.repositories.jpa.OperacionJPARepository;

@Repository
public interface OperacionSpringRepository extends OperacionJPARepository, JpaRepository<OperacionEntity, Long> {
	
	@EntityGraph(value = OperacionEntity.EntityGraph.WITH_TURNOS, type = EntityGraphType.LOAD)
	Optional<OperacionEntity> findById(Long id);
	
	@Query("SELECT p FROM OperacionEntity p JOIN FETCH p.fxOper fx JOIN FETCH p.turno t WHERE fx.id = ?2 AND t.id = ?1 AND p.estado= 'EJECUTADA'")
	List<OperacionEntity> operacionesPorTurnoYDivisa(Long idTurno, Long idFx);
	
	@Query("SELECT p FROM OperacionEntity p JOIN FETCH p.fxOper fx JOIN FETCH p.turno t WHERE t.id = ?1 ")
	List<OperacionEntity> operacionesPorTurno(Long idTurno);
	
	@Query("SELECT p FROM OperacionEntity p JOIN FETCH p.fxOper fx JOIN FETCH p.turno t WHERE fx.id = ?2 AND t.id = ?1 AND p.estado= 'EJECUTADA' AND p.tipo = 'COMPRA'")
	List<OperacionEntity> operacionesPorTurnoYDivisaCompras(Long idTurno, Long idFx);
	
	@Query("SELECT p FROM OperacionEntity p JOIN FETCH p.fxOper fx JOIN FETCH p.turno t WHERE fx.id = ?2 AND t.id = ?1 AND p.estado= 'EJECUTADA' AND p.tipo = 'VENTA' ")
	List<OperacionEntity> operacionesPorTurnoYDivisaVentas(Long idTurno, Long idFx);
	
	@Query("SELECT p FROM OperacionEntity p JOIN FETCH p.turno t WHERE SUBSTRING(p.fechaOperacion,1,10) = ?1 AND  t.caja.id = ?2 AND p.estado= 'EJECUTADA' AND p.tipo = 'COMPRA' ")
	List<OperacionEntity> operacionesCompraEnUnDiaPorCaja(LocalDate dia, Long idCaja);
	
	@Query("SELECT p FROM OperacionEntity p JOIN FETCH p.turno t WHERE SUBSTRING(p.fechaOperacion,1,10) = ?1 AND t.caja.id = ?2 AND p.estado= 'EJECUTADA' AND p.tipo = 'VENTA' ")
	List<OperacionEntity> operacionesVentaEnUnDiaPorCaja(LocalDate dia, Long idCaja );
	
	@Query("SELECT p FROM OperacionEntity p JOIN FETCH p.turno t WHERE SUBSTRING(p.fechaOperacion,1,10) = SUBSTRING(?1, 1,10) AND  t.caja.id = ?2 AND p.estado= 'EJECUTADA' AND p.tipo = 'COMPRA' AND p.fxOper.id= ?3 ")
	List<OperacionEntity> operacionesCompraEnUnDiaPorCajaYDivisa(LocalDate dia, Long idCaja, Long idFx);
	
	@Query("SELECT p FROM OperacionEntity p JOIN FETCH p.turno t WHERE SUBSTRING(p.fechaOperacion,1,10) = SUBSTRING(?1, 1,10) AND t.caja.id = ?2 AND p.estado= 'EJECUTADA' AND p.tipo = 'VENTA' AND p.fxOper.id= ?3 ")
	List<OperacionEntity> operacionesVentaEnUnDiaPorCajayDivisa(LocalDate dia, Long idCaja, Long idFx);
	
	@Query("SELECT p FROM OperacionEntity p  WHERE SUBSTRING(p.fechaOperacion,1,10) = SUBSTRING(?1, 1,10)  AND p.estado= 'EJECUTADA' AND p.tipo = 'COMPRA' AND p.fxOper.id= ?2 ")
	List<OperacionEntity> operacionesCompraEnUnDiaPorDivisa(LocalDate dia, Long idFx);
	
	@Query("SELECT p FROM OperacionEntity p  WHERE SUBSTRING(p.fechaOperacion,1,10) = SUBSTRING(?1, 1,10)  AND p.estado= 'EJECUTADA' AND p.tipo = 'VENTA' AND p.fxOper.id= ?2 ")
	List<OperacionEntity> operacionesVentaEnUnDiaPorDivisa(LocalDate dia, Long idFx);
	
	
	@Query("SELECT p FROM OperacionEntity p JOIN FETCH p.turno t JOIN FETCH t.caja c WHERE SUBSTRING(p.fechaOperacion,1,10) = SUBSTRING(?1, 1,10) AND  c.sucursal.id = ?2 AND p.estado= 'EJECUTADA' AND p.tipo = 'COMPRA' AND p.fxOper.id= ?3 ")
	List<OperacionEntity> operacionesCompraEnUnDiaPorSucursalYDivisa(LocalDate dia, Long idSucursal, Long idFx);
	
	@Query("SELECT p FROM OperacionEntity p JOIN FETCH p.turno t JOIN FETCH t.caja c WHERE SUBSTRING(p.fechaOperacion,1,10) = SUBSTRING(?1, 1,10) AND c.sucursal.id = ?2 AND p.estado= 'EJECUTADA' AND p.tipo = 'VENTA' AND p.fxOper.id= ?3 ")
	List<OperacionEntity> operacionesVentaEnUnDiaPorSucursalyDivisa(LocalDate dia, Long idSucursal, Long idFx);

}
