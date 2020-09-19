package co.com.fxmanager.management.persistence.repositories.springdata;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.TurnoEntity;

@Repository
public interface TurnoSpringRepository extends JpaRepository<TurnoEntity, Long> {

	static final String CONSULTA_ULTIMOS_TUROS_CADA_CAJA = "SELECT t FROM TurnoEntity t JOIN FETCH t.caja "
			+ "WHERE t.fechaFin = (SELECT max(t2.fechaFin) FROM TurnoEntity t2 WHERE t2.caja.id  = t.caja.id)";
	
	static final String CONSULTA_ULTIMOS_TUROS_CADA_CAJA_V2 = "SELECT t FROM TurnoEntity t JOIN FETCH t.caja "
			+ "WHERE t.fechaFin = (SELECT max(t2.fechaFin) FROM TurnoEntity t2 WHERE t2.caja.id  = t.caja.id AND SUBSTRING(t2.fechaFin, 1,10) = SUBSTRING(?1, 1, 10))";
	
	
	static final String CONSULTA_PRIMER_TUROS_CADA_CAJA = "SELECT t0 FROM TurnoEntity t0 JOIN FETCH t0.caja WHERE t0.fechaInicio = "
			+ "(SELECT min(t.fechaInicio) FROM TurnoEntity t WHERE t.caja.id = t0.caja.id AND SUBSTRING(t.fechaInicio, 1,10) = "
			+ "SUBSTRING((SELECT max(t2.fechaFin) as f FROM TurnoEntity t2 WHERE t2.caja.id  = t.caja.id), 1,10) )";
	
	static final String CONSULTA_PRIMER_TUROS_CADA_CAJA_V2= "SELECT t0 FROM TurnoEntity t0 JOIN FETCH t0.caja WHERE t0.fechaInicio = "
			+ "(SELECT min(t.fechaInicio) FROM TurnoEntity t WHERE t.caja.id = t0.caja.id AND SUBSTRING(t.fechaInicio, 1,10) = SUBSTRING(?1, 1, 10) ) ";
	
	static final String CONSULTA_TURNOS_CAJA_EN_UN_DIA = "SELECT t FROM TurnoEntity t JOIN FETCH t.caja c "
			+ "WHERE SUBSTRING(t.fechaFin, 1, 10) = ?1 AND c.id = ?2 ";
	
	static final String CONSULTA_TURNOS_DE_UN_DIA = "SELECT t FROM TurnoEntity t "
			+ "WHERE SUBSTRING(t.fechaInicio, 1, 10) = SUBSTRING(?1, 1, 10) AND t.fechaFin IS NULL ";
	
	static final String CONSULTA_TURNOS_DE_UN_DIA_POR_SUCURSAL = "SELECT t FROM TurnoEntity t "
			+ "WHERE SUBSTRING(t.fechaInicio, 1, 10) = SUBSTRING(?1, 1, 10) AND t.fechaFin IS NULL AND t.caja.sucursal.id = ?2";
	
	static final String CONSULTA_ULTIMOS_TUROS_CADA_CAJA_DE_UN_DIA = "SELECT t FROM TurnoEntity t JOIN FETCH t.caja"
			+ "WHERE t.fechaFin = (SELECT max(t2.fechaFin) FROM TurnoEntity t2 WHERE t2.caja.id  = t.caja.id) AND "
			+ "SUBSTRING(t.fechaFin, 1,10) =  SUBSTRING(?1, 1,10) ";
	
	static final String CONSULTA_PRIMER_TUROS_CADA_CAJA_DE_UN_DIA = "SELECT t0 FROM TurnoEntity t0 JOIN FETCH t0.caja WHERE t0.fechaInicio = "
			+ "(SELECT min(t.fechaInicio) FROM TurnoEntity t WHERE t.caja.id = t0.caja.id AND SUBSTRING(t.fechaInicio, 1,10) = "
			+ "SUBSTRING((SELECT max(t2.fechaFin) as f FROM TurnoEntity t2 WHERE t2.caja.id  = t.caja.id), 1,10) ) AND "
			+ "SUBSTRING(t.fechaFin, 1,10) =  SUBSTRING(?1, 1,10) ";
	
	Optional<TurnoEntity> findById(Long id);
	
	@Query("SELECT t FROM TurnoEntity t WHERE t.caja.id = ?1 AND t.usuario.nombre = ?2 AND t.fechaFin IS NULL")
	List<TurnoEntity> findTurnosSinCierre(Long idCaja, String nombreUsuario);
	
	@Query("SELECT t FROM TurnoEntity t WHERE t.caja.id = ?1 AND t.fechaFin IS NULL")
	List<TurnoEntity> findTurnosSinCierreEnEstaCaja(Long idCaja);
	
	@Query(CONSULTA_ULTIMOS_TUROS_CADA_CAJA_V2)
	List<TurnoEntity> findUltimoTurnosDeCadaCaja(LocalDate dia);
	
	@Query(CONSULTA_PRIMER_TUROS_CADA_CAJA_V2)
	List<TurnoEntity> findPrimerTurnosDeCadaCaja(LocalDate dia);
	
	@Query(CONSULTA_TURNOS_DE_UN_DIA)
	List<TurnoEntity> listaTurnosEnUnDia(LocalDate dia);
	
	@Query(CONSULTA_TURNOS_DE_UN_DIA_POR_SUCURSAL)
	List<TurnoEntity> listaTurnosEnUnDia(LocalDate dia, Long idSucursal);
	
	/**
	 * 
	 * @param dia
	 * @param idCaja
	 * @return retorna la lista de turnos de una caja en un dia especifico
	 */
	@Query(CONSULTA_TURNOS_CAJA_EN_UN_DIA)
	List<TurnoEntity> listaTurnosCadaEnUnDia(LocalDate dia, Long idCaja);
	
	@Query(CONSULTA_TURNOS_CAJA_EN_UN_DIA)
	Optional<TurnoEntity> findTurnoActivo(String nombreUsuario);
}
