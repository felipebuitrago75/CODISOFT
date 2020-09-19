package co.com.fxmanager.management.persistence.repositories.springdata;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.ArqueoEntity;

@Repository
public interface ArqueoSpringRepository extends JpaRepository<ArqueoEntity, Long> {

	@EntityGraph(value = ArqueoEntity.EntityGraph.WITH_ALL, type = EntityGraphType.LOAD)
	Optional<ArqueoEntity> findById(Long id);
	
	@Query("SELECT distinct a FROM ArqueoEntity a JOIN FETCH a.turno LEFT JOIN FETCH a.itemsArqueo i WHERE SUBSTRING(a.fecha, 1, 10) = SUBSTRING(?1, 1, 10)")
	List<ArqueoEntity> arqueosHistoricos(LocalDate fecha);

}
