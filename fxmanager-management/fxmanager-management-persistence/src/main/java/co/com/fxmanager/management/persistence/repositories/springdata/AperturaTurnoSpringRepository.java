package co.com.fxmanager.management.persistence.repositories.springdata;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.AperturaTurnoEntity;

@Repository
public interface AperturaTurnoSpringRepository  extends JpaRepository<AperturaTurnoEntity, Long>{

	Optional<AperturaTurnoEntity> findById(Long id);
	
	@Query("SELECT a FROM AperturaTurnoEntity a JOIN FETCH a.fx fx JOIN FETCH a.turno t WHERE fx.id = ?2 AND t.id = ?1")
	public Optional<AperturaTurnoEntity> getSaldoAperturaTurnoPorTurnoYDivisa(Long idTurno, Long idFx);

	@Query("SELECT a FROM AperturaTurnoEntity a JOIN FETCH a.fx fx JOIN FETCH a.turno t WHERE  t.id = ?1")
	public List<AperturaTurnoEntity> getSaldoAperturaTurnoPorTurno(Long idTurno);

	
}
