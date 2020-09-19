package co.com.fxmanager.management.persistence.repositories.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.management.persistence.entities.jpa.UsuarioEntity;

@Repository
public interface UsuarioSpringRepository extends JpaRepository<UsuarioEntity, Long> {

	Optional<UsuarioEntity> findById(Long id);
	
	Optional<UsuarioEntity> findByNombre(String nombre);
}
