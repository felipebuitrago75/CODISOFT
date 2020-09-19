package co.com.fxmanager.management.persistence.repositories.springdata;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.SaldoEntity;

@Repository
public interface SaldoSpringRepository  extends JpaRepository<SaldoEntity, Long>{

	@Query("SELECT s FROM SaldoEntity s JOIN FETCH s.denominacionesCantidad WHERE s.id = ?1")
	Optional<SaldoEntity> findById(Long id);
	
	@Query("SELECT s FROM SaldoEntity s JOIN FETCH s.fx WHERE s.caja.id = ?1")
	List<SaldoEntity> saldosPorCaja(Long idCaja);
	
	@Query("SELECT s FROM SaldoEntity s JOIN FETCH s.fx fx JOIN FETCH s.caja caja WHERE  caja.sucursal.id = ?1 AND fx.id = ?2")
	List<SaldoEntity> saldosPorSucursalYMoneda(Long idSucursal, Long idFx);
	

}
