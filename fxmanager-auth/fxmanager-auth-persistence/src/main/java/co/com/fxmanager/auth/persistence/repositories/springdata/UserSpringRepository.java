package co.com.fxmanager.auth.persistence.repositories.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.auth.persistence.entities.jpa.UserEntity;

@Repository
public interface UserSpringRepository extends JpaRepository<UserEntity, Long> {

	@EntityGraph(value = UserEntity.EntityGraph.WITH_ROLE, type = EntityGraphType.LOAD)
	Optional<UserEntity> findByUsername(String username);

}
