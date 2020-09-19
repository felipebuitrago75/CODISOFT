package co.com.fxmanager.management.persistence.repositories.facades;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.com.fxmanager.management.domain.entities.AperturaTurno;
import co.com.fxmanager.management.persistence.entities.jpa.AperturaTurnoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.converters.AperturaTurnoConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.AperturaTurnoEntityConverter;
import co.com.fxmanager.management.persistence.repositories.AperturaTurnoRepository;
import co.com.fxmanager.management.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.AperturaTurnoSpringRepository;
import lombok.Getter;

@Getter
@Service
public class AperturaTurnoFacadeRepository implements AperturaTurnoRepository {

	private AperturaTurnoSpringRepository aperturaTurnoSpringRepository;

	private AperturaTurnoConverter aperturaTurnoConverter;

	private AperturaTurnoEntityConverter aperturaTurnoEntityConverter;

	@Autowired
	public AperturaTurnoFacadeRepository(AperturaTurnoSpringRepository aperturaTurnoSpringRepository,
			AperturaTurnoConverter aperturaTurnoConverter, AperturaTurnoEntityConverter aperturaTurnoEntityConverter) {
		super();
		this.aperturaTurnoSpringRepository = aperturaTurnoSpringRepository;
		this.aperturaTurnoConverter = aperturaTurnoConverter;
		this.aperturaTurnoEntityConverter = aperturaTurnoEntityConverter;
	}

	@Transactional
	@Override
	public AperturaTurno create(AperturaTurno aperturaTurno) {
		AperturaTurno newAperturaTurno;
		try {
			AperturaTurnoEntity aperturaTurnoEntity =  getAperturaTurnoEntityConverter().convert(aperturaTurno);
			AperturaTurnoEntity newAperturaTurnoEntity = getAperturaTurnoSpringRepository().save(aperturaTurnoEntity);
			newAperturaTurno = getAperturaTurnoConverter().convert(newAperturaTurnoEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newAperturaTurno;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<AperturaTurno> getList(int first, int max) {
		List<AperturaTurno> listaAperturaTurnos;
		try {
			Page<AperturaTurnoEntity> page = getAperturaTurnoSpringRepository().findAll(PageRequest.of(first / max, max));
			listaAperturaTurnos = page.stream().map(aperturaTurnoEntity -> getAperturaTurnoConverter().convert(aperturaTurnoEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return listaAperturaTurnos;
	}

	@Transactional
	@Override
	public AperturaTurno update(Long id, AperturaTurno aperturaTurno) {
		AperturaTurno newAperturaTurno;
		try {
			AperturaTurnoEntity aperturaTurnoEntity = getAperturaTurnoEntityConverter().convert(aperturaTurno, () -> {
				Optional<AperturaTurnoEntity> aperturaTurnoEntityOptional = getAperturaTurnoSpringRepository().findById(id);
				return aperturaTurnoEntityOptional.orElseThrow(NoFoundDataException::new);
			});
			
			AperturaTurnoEntity newAperturaTurnoEntity = getAperturaTurnoSpringRepository().save(aperturaTurnoEntity);
			newAperturaTurno = getAperturaTurnoConverter().convert(newAperturaTurnoEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newAperturaTurno;
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<AperturaTurno> getAperturaTurno(Long id) {
		Optional<AperturaTurno> aperturaTurno;
		try {
			Optional<AperturaTurnoEntity> aperturaTurnoEntityOptional = getAperturaTurnoSpringRepository().findById(id);
			aperturaTurno = aperturaTurnoEntityOptional.map(aperturaTurnoEntity -> getAperturaTurnoConverter().convert(aperturaTurnoEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return aperturaTurno;
	}

}
