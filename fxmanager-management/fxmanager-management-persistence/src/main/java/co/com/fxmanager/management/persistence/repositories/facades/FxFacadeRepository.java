package co.com.fxmanager.management.persistence.repositories.facades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.fxmanager.management.domain.entities.Fx;
import co.com.fxmanager.management.persistence.entities.jpa.FxEntity;
import co.com.fxmanager.management.persistence.entities.jpa.converters.FxConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.FxEntityConverter;
import co.com.fxmanager.management.persistence.repositories.FxRepository;
import co.com.fxmanager.management.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.FxSpringRepository;
import lombok.Getter;

@Getter
@Service
public class FxFacadeRepository implements FxRepository {

	private FxSpringRepository fxSpringRepository;

	private FxEntityConverter fxEntityConverter;

	private FxConverter fxConverter;

	@Autowired
	public FxFacadeRepository(FxSpringRepository fxSpringRepository, FxConverter fxConverter,
			FxEntityConverter roleEntityConverter) {
		super();
		this.fxSpringRepository = fxSpringRepository;
		this.fxConverter = fxConverter;
		this.fxEntityConverter = roleEntityConverter;
	}

	@Transactional
	@Override
	public Fx create(Fx fx) {
		Fx newfx;
		try {
			FxEntity FxEntity = getFxEntityConverter().convert(fx);
			FxEntity newFxEntity = getFxSpringRepository().save(FxEntity);
			newfx = getFxConverter().convert(newFxEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newfx;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Fx> getList(int first, int max) {
		List<Fx> fxs;
		try {
			Page<FxEntity> page = getFxSpringRepository().findAll(PageRequest.of(first / max, max));
			fxs = page.stream().map(fxEntity -> getFxConverter().convert(fxEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return fxs;
	}

	@SuppressWarnings("unused")
	@Transactional
	@Override
	public Fx update(String cod, Fx fx) {
		Fx newFx;
		try {
			System.out.println();
			FxEntity fxEntity = getFxEntityConverter().convert(fx, () -> {
				Optional<FxEntity> fxEntityOptioanal = getFxSpringRepository().findByCodigo(cod);
				return fxEntityOptioanal.orElseThrow(NoFoundDataException::new);
			});
			FxEntity newFxEntity = getFxSpringRepository().saveAndFlush(fxEntity);
			//newFx = getFxConverter().convert(newFxEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return fx;
	}

	@Transactional
	@Override
	public void delete(String cod) {
		try {
			Optional<FxEntity> fxEntityOptioanal = getFxSpringRepository().findByCodigo(cod);
			fxEntityOptioanal.ifPresent(getFxSpringRepository()::delete);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Fx> getFx(String cod) {
		Optional<Fx> fx;
		try {
			Optional<FxEntity> fxEntityOptioanal = getFxSpringRepository().findByCodigo(cod);
			fx = fxEntityOptioanal.map(fxEntity -> getFxConverter().convert(fxEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return fx;
	}
}
