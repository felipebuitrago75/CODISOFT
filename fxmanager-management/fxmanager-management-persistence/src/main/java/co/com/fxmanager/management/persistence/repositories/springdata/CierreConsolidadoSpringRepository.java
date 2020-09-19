package co.com.fxmanager.management.persistence.repositories.springdata;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.CierreConsolidadoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.CierreEntity;

@Repository
public interface CierreConsolidadoSpringRepository extends JpaRepository<CierreConsolidadoEntity, Long> {

	Optional<CierreConsolidadoEntity> findById(Long id);
	
	@Query("SELECT c FROM CierreConsolidadoEntity c  WHERE  c.moneda.id= ?1 AND  ?2 = c.fecha")
	Optional<CierreConsolidadoEntity> obtenerCierreConsolidado( Long idFx, LocalDate fecha);
	
	@Query("SELECT c FROM CierreConsolidadoEntity c  WHERE ?1 = c.fecha")
	public List<CierreConsolidadoEntity> obtenerlistaCierresConsolidados(LocalDate fecha);

	@Query("SELECT c FROM CierreConsolidadoEntity c  WHERE ?2 = c.fecha AND  c.moneda.id= ?1")
	public  List<CierreConsolidadoEntity> obtenerlistaCierresConsolidadosPorMonedayFecha(Long idFx, LocalDate fecha);

	@Query("SELECT c FROM CierreConsolidadoEntity c  WHERE  SUBSTRING(?2, 1,7) = SUBSTRING(c.fecha, 1,7)  AND  c.moneda.id= ?1 " )
	public  List<CierreConsolidadoEntity> obtenerlistaCierresDelMesConDivisa(Long idFx, LocalDate fecha );
}
