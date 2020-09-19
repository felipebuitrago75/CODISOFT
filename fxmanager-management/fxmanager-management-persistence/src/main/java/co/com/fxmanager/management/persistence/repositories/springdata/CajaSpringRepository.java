package co.com.fxmanager.management.persistence.repositories.springdata;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.CajaEntity;

@Repository
public interface CajaSpringRepository extends JpaRepository<CajaEntity, Long> {

	@EntityGraph(value = CajaEntity.EntityGraph.WITH_SALDOS, type = EntityGraphType.LOAD)
	Optional<CajaEntity> findById(Long id);

	@Query("SELECT c FROM CajaEntity c WHERE c.sucursal.codigo = ?1")
	List<CajaEntity> cajasPorSucursal(String cod);

}
