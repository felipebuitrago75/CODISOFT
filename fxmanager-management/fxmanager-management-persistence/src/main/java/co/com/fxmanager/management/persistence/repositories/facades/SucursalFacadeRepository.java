package co.com.fxmanager.management.persistence.repositories.facades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.com.fxmanager.management.domain.entities.Sucursal;
import co.com.fxmanager.management.persistence.entities.jpa.SucursalEntity;
import co.com.fxmanager.management.persistence.entities.jpa.converters.SucursalConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.SucursalEntityConverter;
import co.com.fxmanager.management.persistence.repositories.SucursalRepository;
import co.com.fxmanager.management.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.SucursalSpringRepository;
import lombok.Getter;

@Getter
@Service
public class SucursalFacadeRepository implements SucursalRepository {

	private SucursalSpringRepository sucursalSpringRepository;

	private SucursalEntityConverter sucursalEntityConverter;

	private SucursalConverter sucursalConverter;

	@Autowired
	public SucursalFacadeRepository( SucursalSpringRepository sucursalSpringRepository, SucursalEntityConverter sucursalEntityConverter,
			SucursalConverter sucursalConverter) {
		super();
		this.sucursalSpringRepository = sucursalSpringRepository;
		this.sucursalConverter = sucursalConverter;
		this.sucursalEntityConverter = sucursalEntityConverter;
	}

	@Transactional
	@Override
	public Sucursal create(Sucursal sucursal) {
		Sucursal newSucursal;
		try {
			SucursalEntity sucursalEntity = getSucursalEntityConverter().convert(sucursal);
			SucursalEntity newSucursalEntity = getSucursalSpringRepository().save(sucursalEntity);
			newSucursal = getSucursalConverter().convert(newSucursalEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newSucursal;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Sucursal> getList(int first, int max) {
		List<Sucursal> listaSucursales;
		try {
			Page<SucursalEntity> page = getSucursalSpringRepository().findAll(PageRequest.of(first / max, max));
			listaSucursales = page.stream().map(sucursalEntity -> getSucursalConverter().convert(sucursalEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return listaSucursales;
	}

	@Transactional
	@Override
	public Sucursal update(String cod, Sucursal sucursal) {
		Sucursal newSucursal;
		try {
			System.out.println("update sucursal");
			SucursalEntity sucursalEntity = getSucursalEntityConverter().convert(sucursal, () -> {
				Optional<SucursalEntity> sucursalEntityOptioanal = getSucursalSpringRepository().findByCodigo(cod);
				return sucursalEntityOptioanal.orElseThrow(NoFoundDataException::new);
			});
			
			SucursalEntity newSucursalEntity = getSucursalSpringRepository().save(sucursalEntity);
			newSucursal = getSucursalConverter().convert(newSucursalEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newSucursal;
	}

	@Transactional
	@Override
	public void delete(String cod) {
		try {
			Optional<SucursalEntity> sucursalEntityOptioanal = getSucursalSpringRepository().findByCodigo(cod);
			sucursalEntityOptioanal.ifPresent(getSucursalSpringRepository()::delete);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Sucursal> getSucursal(String cod) {
		Optional<Sucursal> sucursal;
		try {
			Optional<SucursalEntity> sucursalEntityOptioanal = getSucursalSpringRepository().findByCodigo(cod);
			sucursal = sucursalEntityOptioanal.map(sucursalEntity -> getSucursalConverter().convert(sucursalEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return sucursal;
	}
}
