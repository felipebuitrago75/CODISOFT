package co.com.fxmanager.auth.persistence.repositories.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.auth.persistence.entities.jpa.FunctionalityEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.RoleEntity;

@Repository
public interface RoleSpringRepository extends JpaRepository<RoleEntity, Long> {

	@EntityGraph(value = RoleEntity.EntityGraph.WITH_PERMISSIONS, type = EntityGraphType.LOAD)
	Optional<RoleEntity> findByName(String name);
}
