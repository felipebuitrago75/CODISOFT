package co.com.fxmanager.management.persistence.repositories.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.DenominacionEntity;

@Repository
public interface DenominacionSpringRepository extends JpaRepository<DenominacionEntity, Long> {

	Optional<DenominacionEntity> findById(Long id);
	
	@Query("SELECT denominacion FROM DenominacionEntity denominacion WHERE "
			+ "denominacion.fx.id = ?1 AND denominacion.valor = ?2 ")
	Optional<DenominacionEntity> obtenerDenominacionPorFxyValor(Long idFx, Integer valor );
}
