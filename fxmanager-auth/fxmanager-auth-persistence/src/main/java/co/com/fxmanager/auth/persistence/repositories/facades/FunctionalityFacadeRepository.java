package co.com.fxmanager.auth.persistence.repositories.facades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.com.fxmanager.auth.domain.entities.Functionality;
import co.com.fxmanager.auth.persistence.entities.jpa.FunctionalityEntity;
import co.com.fxmanager.auth.persistence.entities.jpa.converters.FunctionalityConverter;
import co.com.fxmanager.auth.persistence.entities.jpa.converters.FunctionalityEntityConverter;
import co.com.fxmanager.auth.persistence.repositories.FunctionalityRepository;
import co.com.fxmanager.auth.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.auth.persistence.repositories.springdata.FunctionalitySpringRepository;
import lombok.Getter;

@Getter
@Service
public class FunctionalityFacadeRepository implements FunctionalityRepository {

	private FunctionalitySpringRepository functionalitySpringRepository;

	private FunctionalityEntityConverter functionalityEntityConverter;

	private FunctionalityConverter functionalityConverter;

	@Autowired
	public FunctionalityFacadeRepository(FunctionalitySpringRepository functionalitySpringRepository,
			FunctionalityConverter functionalityConverter, FunctionalityEntityConverter functionalityEntityConverter) {
		super();
		this.functionalitySpringRepository = functionalitySpringRepository;
		this.functionalityConverter = functionalityConverter;
		this.functionalityEntityConverter = functionalityEntityConverter;
	}

	@Override
	public Functionality create(Functionality functionality) {
		FunctionalityEntity functionalityEntity = getFunctionalityEntityConverter().convert(functionality);
		FunctionalityEntity newFunctionalityEntity = getFunctionalitySpringRepository().save(functionalityEntity);
		return getFunctionalityConverter().convert(newFunctionalityEntity);
	}

	@Override
	public List<Functionality> getList(int first, int max) {
		Page<FunctionalityEntity> page = getFunctionalitySpringRepository()
				.findAll(PageRequest.of(first, max, Sort.by(FunctionalityEntity.ClassInfo.NAME)));
		return page.stream().map(functionalityEntity -> getFunctionalityConverter().convert(functionalityEntity))
				.collect(Collectors.toList());
	}

	@Override
	public Functionality update(String name, Functionality functionality) {
		FunctionalityEntity functionalityEntity = getFunctionalityEntityConverter().convert(functionality, () -> {
			Optional<FunctionalityEntity> functionalityEntityOptioanal = getFunctionalitySpringRepository()
					.findByName(name);
			return functionalityEntityOptioanal.orElseThrow(NoFoundDataException::new);
		});
		FunctionalityEntity newFunctionalityEntity = getFunctionalitySpringRepository().save(functionalityEntity);
		return getFunctionalityConverter().convert(newFunctionalityEntity);
	}

	@Override
	public void delete(String name) {
		Optional<FunctionalityEntity> functionalityEntityOptioanal = getFunctionalitySpringRepository()
				.findByName(name);
		functionalityEntityOptioanal.ifPresent(getFunctionalitySpringRepository()::delete);
	}

	@Override
	public Optional<Functionality> getFunctionality(String name) {
		Optional<FunctionalityEntity> functionalityEntityOptioanal = getFunctionalitySpringRepository()
				.findByName(name);
		return functionalityEntityOptioanal
				.map(functionalityEntity -> getFunctionalityConverter().convert(functionalityEntity));
	}

}
