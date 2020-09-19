package co.com.fxmanager.management.persistence.repositories.springdata;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.CierreTurnoEntity;

@Repository
public interface CierreTurnoSpringRepository extends JpaRepository<CierreTurnoEntity, Long> {

	Optional<CierreTurnoEntity> findById(Long id);
	
	@Query("SELECT a FROM CierreTurnoEntity a JOIN FETCH a.moneda fx JOIN FETCH a.turno t WHERE fx.id = ?2 AND t.id = ?1")
	public Optional<CierreTurnoEntity> getSaldoCierreTurnoPorTurnoYDivisa(Long idTurno, Long idFx);

	@Query("SELECT a FROM CierreTurnoEntity a JOIN FETCH a.moneda fx JOIN FETCH a.turno t WHERE t.id = ?1")
	public List<CierreTurnoEntity> getSaldosCierreTurnoPorTurno(Long idTurno);
}
