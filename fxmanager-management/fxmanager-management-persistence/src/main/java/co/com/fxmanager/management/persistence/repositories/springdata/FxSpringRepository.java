package co.com.fxmanager.management.persistence.repositories.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.FxEntity;

@Repository
public interface FxSpringRepository extends JpaRepository<FxEntity, Long> {

	@EntityGraph(value = FxEntity.EntityGraph.WITH_DENOMINACIONES, type = EntityGraphType.LOAD)
	Optional<FxEntity> findByCodigo(String codigo);
}
