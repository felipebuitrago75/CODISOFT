package co.com.fxmanager.management.persistence.repositories.springdata;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.DenominacionCantidadEntity;

@Repository
public interface DenominacionCantidadSpringRepository extends JpaRepository<DenominacionCantidadEntity, Long> {

	Optional<DenominacionCantidadEntity> findById(Long id);
	
	@Query("SELECT denominacion FROM DenominacionCantidadEntity denominacion JOIN FETCH denominacion.denominacion WHERE "
			+ "denominacion.saldo.id = ?1 ")
	List<DenominacionCantidadEntity> obtenerDenominacionCantidadPorSaldo(Long idSaldo);
}
