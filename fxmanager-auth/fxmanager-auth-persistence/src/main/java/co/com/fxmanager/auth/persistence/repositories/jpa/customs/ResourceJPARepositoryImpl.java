package co.com.fxmanager.auth.persistence.repositories.jpa.customs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.fxmanager.auth.persistence.entities.jpa.FunctionalityEntity_;
import co.com.fxmanager.auth.persistence.entities.jpa.PermissionEntity_;
import co.com.fxmanager.auth.persistence.entities.jpa.ResourceEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.ResourceEntity_;
import co.com.fxmanager.auth.persistence.repositories.jpa.ResourceJPARepository;
import lombok.Getter;

@Getter
@Repository
public class ResourceJPARepositoryImpl implements ResourceJPARepository {

	@Autowired
	private EntityManager entityManager;

	
	public List<ResourceEntity> getResourcesWithRoles() {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ResourceEntity> criteriaQuery = criteriaBuilder.createQuery(ResourceEntity.class);
		criteriaQuery.from(ResourceEntity.class).fetch(ResourceEntity_.FUNCTIONALITIES)
				.fetch(FunctionalityEntity_.PERMISSIONS).fetch(PermissionEntity_.ROLE);
		criteriaQuery.distinct(Boolean.TRUE);
		TypedQuery<ResourceEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

}
