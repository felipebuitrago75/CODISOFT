package co.com.fxmanager.auth.persistence.repositories.facades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.fxmanager.auth.domain.entities.Role;
import co.com.fxmanager.auth.persistence.entities.jpa.RoleEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.converters.RoleConverter;
import co.com.fxmanager.auth.persistence.entities.jpa.converters.RoleEntityConverter;
import co.com.fxmanager.auth.persistence.repositories.RoleRepository;
import co.com.fxmanager.auth.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.auth.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.auth.persistence.repositories.springdata.RoleSpringRepository;
import lombok.Getter;

@Getter
@Service
public class RoleFacadeRepository implements RoleRepository {

	private RoleSpringRepository roleSpringRepository;

	private RoleEntityConverter roleEntityConverter;

	private RoleConverter roleConverter;

	@Autowired
	public RoleFacadeRepository(RoleSpringRepository roleSpringRepository, RoleConverter roleConverter,
			RoleEntityConverter roleEntityConverter) {
		super();
		this.roleSpringRepository = roleSpringRepository;
		this.roleConverter = roleConverter;
		this.roleEntityConverter = roleEntityConverter;
	}

	@Transactional
	@Override
	public Role create(Role role) {
		Role newRole;
		try {
			RoleEntity roleEntity = getRoleEntityConverter().convert(role);
			RoleEntity newRoleEntity = getRoleSpringRepository().save(roleEntity);
			newRole = getRoleConverter().convert(newRoleEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newRole;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Role> getList(int first, int max) {
		List<Role> roles;
		try {
			Page<RoleEntity> page = getRoleSpringRepository()
					.findAll(PageRequest.of(first / max, max, Sort.by(RoleEntity.ClassInfo.NAME)));
			roles = page.stream().map(roleEntity -> getRoleConverter().convert(roleEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return roles;
	}

	@Transactional
	@Override
	public Role update(String name, Role role) {
		Role newRole;
		try {
			RoleEntity roleEntity = getRoleEntityConverter().convert(role, () -> {
				Optional<RoleEntity> roleEntityOptioanal = getRoleSpringRepository().findByName(name);
				return roleEntityOptioanal.orElseThrow(NoFoundDataException::new);
			});
			RoleEntity newRoleEntity = getRoleSpringRepository().save(roleEntity);
			newRole = getRoleConverter().convert(newRoleEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newRole;
	}

	@Transactional
	@Override
	public void delete(String name) {
		try {
			Optional<RoleEntity> roleEntityOptioanal = getRoleSpringRepository().findByName(name);
			roleEntityOptioanal.ifPresent(getRoleSpringRepository()::delete);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Role> getRole(String name) {
		Optional<Role> role;
		try {
			Optional<RoleEntity> roleEntityOptioanal = getRoleSpringRepository().findByName(name);
			role = roleEntityOptioanal.map(roleEntity -> getRoleConverter().convert(roleEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return role;
	}
}
