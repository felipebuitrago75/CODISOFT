package co.com.fxmanager.auth.persistence.repositories.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.auth.persistence.entities.jpa.FunctionalityEntity;

@Repository
public interface FunctionalitySpringRepository extends JpaRepository<FunctionalityEntity, Long> {

	@EntityGraph(value = FunctionalityEntity.EntityGraph.WITH_MENU_RESOURCES, type = EntityGraphType.LOAD)
	Optional<FunctionalityEntity> findByName(String name);

}
