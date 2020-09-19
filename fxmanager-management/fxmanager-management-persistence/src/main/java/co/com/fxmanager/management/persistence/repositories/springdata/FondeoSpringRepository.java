package co.com.fxmanager.management.persistence.repositories.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.FondeoEntity;

@Repository
public interface FondeoSpringRepository extends JpaRepository<FondeoEntity, Long> {

	@EntityGraph(value = FondeoEntity.EntityGraph.WITH_SUCURSALES_Y_FX, type = EntityGraphType.LOAD)
	Optional<FondeoEntity> findById(Long id);
}
