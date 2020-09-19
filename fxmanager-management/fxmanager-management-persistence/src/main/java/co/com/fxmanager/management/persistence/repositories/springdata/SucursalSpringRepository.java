package co.com.fxmanager.management.persistence.repositories.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.SucursalEntity;

@Repository
public interface SucursalSpringRepository extends JpaRepository<SucursalEntity, Long> {

	@EntityGraph(value = SucursalEntity.EntityGraph.WITH_LISTA_PRECIOS_CAJAS, type = EntityGraphType.LOAD)
	Optional<SucursalEntity> findByCodigo(String codigo);
	
	
}
