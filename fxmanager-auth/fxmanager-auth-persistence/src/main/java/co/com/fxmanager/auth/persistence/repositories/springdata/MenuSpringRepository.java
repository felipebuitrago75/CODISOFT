package co.com.fxmanager.auth.persistence.repositories.springdata;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.auth.persistence.entities.jpa.MenuEntity;

@Repository
public interface MenuSpringRepository extends JpaRepository<MenuEntity, Long> {
	
	@EntityGraph(value = MenuEntity.EntityGraph.WITH_PARENT, type = EntityGraphType.LOAD)
	Optional<MenuEntity> findByName(String name);
	
	@EntityGraph(value = MenuEntity.EntityGraph.WITH_PARENT, type = EntityGraphType.LOAD)
	@Override
	Page<MenuEntity> findAll(Pageable pageable);
	
	Page<MenuEntity> findByTargetNotNull(Pageable pageable);
	
	Page<MenuEntity> findByTargetIsNull(Pageable pageable);
}
