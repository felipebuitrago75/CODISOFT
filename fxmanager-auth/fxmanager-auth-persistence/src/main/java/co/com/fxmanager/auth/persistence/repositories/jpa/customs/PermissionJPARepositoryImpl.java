package co.com.fxmanager.auth.persistence.repositories.jpa.customs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.auth.persistence.entities.jpa.FunctionalityEntity_;
import co.com.fxmanager.auth.persistence.entities.jpa.PermissionEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.PermissionEntity_;
import co.com.fxmanager.auth.persistence.repositories.jpa.PermissionJPARepository;
import lombok.Getter;

@Getter
@Repository
public class PermissionJPARepositoryImpl implements PermissionJPARepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<PermissionEntity> getPermissionsWithRolesByResource() {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PermissionEntity> criteriaQuery = criteriaBuilder.createQuery(PermissionEntity.class);
		Root<PermissionEntity> root = criteriaQuery.from(PermissionEntity.class);
		root.fetch(PermissionEntity_.functionality).fetch(FunctionalityEntity_.resources);
		root.fetch(PermissionEntity_.role);
		criteriaQuery.distinct(Boolean.TRUE);
		TypedQuery<PermissionEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

}
