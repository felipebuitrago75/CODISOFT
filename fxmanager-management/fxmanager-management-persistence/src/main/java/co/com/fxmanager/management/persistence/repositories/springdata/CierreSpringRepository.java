package co.com.fxmanager.management.persistence.repositories.springdata;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.CierreConsolidadoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.CierreEntity;

@Repository
public interface CierreSpringRepository extends JpaRepository<CierreEntity, Long> {

	Optional<CierreEntity> findById(Long id);
	
	/**
	 * TODO: LIMITAR LA CONULTA A 1
	 * @param idCaja
	 * @param idFx
	 * @return
	 */
	@Query("SELECT c FROM CierreEntity c  WHERE c.sucursal.id= ?1  AND c.moneda.id= ?2 ORDER BY c.fecha DESC")
	Page<CierreEntity> obtenerElUltimoCierrePorSucursal(Long idSucursal, Long idFx, Pageable pageable);
	
	@Query("SELECT c FROM CierreConsolidadoEntity c  WHERE  c.moneda.id= ?1 AND  ?2 = c.fecha")
	Optional<CierreConsolidadoEntity> obtenerCierreConsolidado( Long idFx, LocalDate fecha);
	
	@Query("SELECT c FROM CierreEntity c  ORDER BY c.fecha DESC")
	Page<CierreEntity> obtenerFechaDelUltimoCierre(Pageable pageable);
	
	@Query("SELECT c FROM CierreEntity c  WHERE c.sucursal.id= ?1 AND c.fecha = "
			+ " (SELECT max(c2.fecha) FROM CierreEntity c2 WHERE c2.sucursal.id  = c.sucursal.id)")
	public List<CierreEntity> obtenerlistaUltimosCierres(Long idSucursal);
	

	@Query("SELECT c FROM CierreEntity c  WHERE c.sucursal.id= ?1 AND c.fecha = "
			+ " (SELECT max(c2.fecha) FROM CierreEntity c2 WHERE c2.sucursal.id  = c.sucursal.id AND SUBSTRING(?1, 6,2) = SUBSTRING(c.fecha, 6,2))")
	public List<CierreEntity> obtenerlistaUltimosCierresMesAnterior(Long idSucursal, LocalDate fecha);

	
	@Query("SELECT c FROM CierreEntity c  WHERE ?1 = c.fecha AND  c.sucursal.id= ?2")
	public List<CierreEntity> obtenerlistaCierres(LocalDate fecha,Long idSucursal);
	
	@Query("SELECT c FROM CierreEntity c  WHERE ?1 = c.fecha")
	public List<CierreEntity> obtenerlistaCierresPorFecha(LocalDate fecha);

	@Query("SELECT c FROM CierreEntity c  WHERE ?2 = c.fecha AND  c.moneda.id= ?1")
	public  List<CierreEntity> obtenerlistaCierresPorMonedayFecha(Long idFx, LocalDate fecha);
	
	@Query("SELECT c FROM CierreEntity c  WHERE  SUBSTRING(?2, 1,7) = SUBSTRING(c.fecha, 1,7)  AND  c.moneda.id= ?1 AND ?3 = c.sucursal.id " )
	public  List<CierreEntity> obtenerlistaCierresDelMesConDivisa(Long idFx, LocalDate fecha, Long idSucursal);
	
}
