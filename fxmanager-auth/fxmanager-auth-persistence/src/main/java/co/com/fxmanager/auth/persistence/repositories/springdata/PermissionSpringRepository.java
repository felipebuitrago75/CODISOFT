package co.com.fxmanager.auth.persistence.repositories.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.auth.persistence.entities.jpa.PermissionEntity;
import co.com.fxmanager.auth.persistence.repositories.jpa.PermissionJPARepository;

@Repository
public interface PermissionSpringRepository extends PermissionJPARepository, JpaRepository<PermissionEntity, Long> {

}
