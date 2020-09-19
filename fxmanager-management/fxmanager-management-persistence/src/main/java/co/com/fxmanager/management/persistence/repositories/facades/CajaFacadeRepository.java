package co.com.fxmanager.management.persistence.repositories.facades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.fxmanager.management.domain.entities.Caja;
import co.com.fxmanager.management.persistence.entities.jpa.CajaEntity;
import co.com.fxmanager.management.persistence.entities.jpa.converters.CajaConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.CajaEntityConverter;
import co.com.fxmanager.management.persistence.repositories.CajaRepository;
import co.com.fxmanager.management.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.CajaSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.SaldoSpringRepository;
import lombok.Getter;

@Getter
@Service
public class CajaFacadeRepository implements CajaRepository {

	private CajaSpringRepository cajaSpringRepository;

	private CajaEntityConverter cajaEntityConverter;

	private CajaConverter cajaConverter;
	
	private SaldoSpringRepository saldoSpringRepository;

	@Autowired
	public CajaFacadeRepository(CajaSpringRepository cajaSpringRepository, CajaEntityConverter cajaEntityConverter,
			CajaConverter cajaConverter, SaldoSpringRepository saldoSpringRepository) {
		super();
		this.cajaSpringRepository = cajaSpringRepository;
		this.cajaEntityConverter = cajaEntityConverter;
		this.cajaConverter = cajaConverter;
		this.saldoSpringRepository = saldoSpringRepository;
	}

	@Transactional
	@Override
	public Caja create(Caja caja) {
		Caja newCaja;
		try {
			CajaEntity cajaEntity = getCajaEntityConverter().convert(caja);
			CajaEntity newCajaEntity = getCajaSpringRepository().save(cajaEntity);
			newCaja = getCajaConverter().convert(newCajaEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newCaja;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Caja> getList(int first, int max) {
		List<Caja> listaCajas;
		try {
			Page<CajaEntity> page = getCajaSpringRepository().findAll(PageRequest.of(first / max, max));
			listaCajas = page.stream().map(cajaEntity -> getCajaConverter().convert(cajaEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return listaCajas;
	}

	@Transactional
	@Override
	public Caja update(Long id, Caja caja) {
		Caja newCaja;
		try {
			System.out.println("update caja");
			CajaEntity cajaEntity = getCajaEntityConverter().convert(caja, () -> {
				Optional<CajaEntity> cajaEntityOptioanal = getCajaSpringRepository().findById(id);
				return cajaEntityOptioanal.orElseThrow(NoFoundDataException::new);
			});

			CajaEntity newCajaEntity = getCajaSpringRepository().save(cajaEntity);
			newCaja = getCajaConverter().convert(newCajaEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newCaja;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		try {
			Optional<CajaEntity> cajaEntityOptioanal = getCajaSpringRepository().findById(id);
			cajaEntityOptioanal.ifPresent(getCajaSpringRepository()::delete);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Caja> getCaja(Long id) {
		Optional<Caja> caja;
		try {
			Optional<CajaEntity> cajaEntityOptioanal = getCajaSpringRepository().findById(id);
			
			
			cajaEntityOptioanal.ifPresent((cajaEntity ->{
				cajaEntity.getListaSaldos().stream().forEach(saldoEntity -> {
					saldoEntity = saldoSpringRepository.findById(saldoEntity.getId()).orElseThrow(NoFoundDataException::new);
				});
			}));
			
			caja = cajaEntityOptioanal.map(cajaEntity -> getCajaConverter().convert(cajaEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return caja;
	}

	@Override
	public List<Caja> getCajasPorSucursal(String cod) {
		List<Caja> listaCajas;
		try {
			List<CajaEntity> listaCajasEntity = cajaSpringRepository.cajasPorSucursal(cod);
			listaCajas = listaCajasEntity.stream().map(cajaEntity -> getCajaConverter().convert(cajaEntity))
					.collect(Collectors.toList());

		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return listaCajas;
	}

}
