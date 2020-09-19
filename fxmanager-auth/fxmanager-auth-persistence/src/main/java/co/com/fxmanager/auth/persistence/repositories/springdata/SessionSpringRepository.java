package co.com.fxmanager.auth.persistence.repositories.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.auth.persistence.entities.jpa.SessionEntity;

@Repository
public interface SessionSpringRepository extends JpaRepository<SessionEntity, Long> {

}
