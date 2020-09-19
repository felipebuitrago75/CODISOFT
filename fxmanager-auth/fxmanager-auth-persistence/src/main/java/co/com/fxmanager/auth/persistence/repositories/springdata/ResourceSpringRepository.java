package co.com.fxmanager.auth.persistence.repositories.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.auth.persistence.entities.jpa.ResourceEntity;
import co.com.fxmanager.auth.persistence.repositories.jpa.ResourceJPARepository;

@Repository
public interface ResourceSpringRepository extends ResourceJPARepository, JpaRepository<ResourceEntity, Long> {

	Optional<ResourceEntity> findByName(String name);

}
