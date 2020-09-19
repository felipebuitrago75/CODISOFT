package co.com.fxmanager.auth.persistence.repositories.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.auth.persistence.entities.jpa.MailEntity;

@Repository
public interface MailSpringRepository extends JpaRepository<MailEntity, Long> {

}
