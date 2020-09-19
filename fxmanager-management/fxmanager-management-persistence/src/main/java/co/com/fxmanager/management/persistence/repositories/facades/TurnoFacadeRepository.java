package co.com.fxmanager.management.persistence.repositories.facades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.fxmanager.management.domain.entities.AperturaTurno;
import co.com.fxmanager.management.domain.entities.Turno;
import co.com.fxmanager.management.domain.services.exceptions.BusinessException;
import co.com.fxmanager.management.persistence.entities.jpa.AperturaTurnoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.CierreEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SaldoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TurnoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.converters.AperturaTurnoEntityConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.FxConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.FxEntityConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.SaldoEntityConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.TurnoConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.TurnoEntityConverter;
import co.com.fxmanager.management.persistence.repositories.TurnoRepository;
import co.com.fxmanager.management.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.AperturaTurnoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.CajaSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.CierreSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.SaldoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Service
public class TurnoFacadeRepository implements TurnoRepository {

	private TurnoSpringRepository turnoSpringRepository;

	private CajaSpringRepository cajaSpringRepository;

	private TurnoEntityConverter turnoEntityConverter;

	private TurnoConverter turnoConverter;

	private SaldoSpringRepository saldoSpringRepository;

	private SaldoEntityConverter saldoEntityConverter;

	private AperturaTurnoSpringRepository aperturaTurnoSpringRepository;

	private AperturaTurnoEntityConverter aperturaTurnoEntityConverter;

	private FxEntityConverter fxEntityConverter;

	private FxConverter fxConverter;

	private CierreSpringRepository cierreSpringRepository;

	@Autowired
	public TurnoFacadeRepository(TurnoSpringRepository turnoSpringRepository, TurnoEntityConverter turnoEntityConverter,
			TurnoConverter turnoConverter, CajaSpringRepository cajaSpringRepository,
			SaldoSpringRepository saldoSpringRepository, AperturaTurnoSpringRepository aperturaTurnoSpringRepository,
			AperturaTurnoEntityConverter aperturaTurnoEntityConverter, SaldoEntityConverter saldoEntityConverter,
			FxEntityConverter fxEntityConverter, FxConverter fxConverter,
			CierreSpringRepository cierreSpringRepository) {
		super();
		this.turnoSpringRepository = turnoSpringRepository;
		this.turnoEntityConverter = turnoEntityConverter;
		this.turnoConverter = turnoConverter;
		this.cajaSpringRepository = cajaSpringRepository;
		this.saldoSpringRepository = saldoSpringRepository;
		this.aperturaTurnoSpringRepository = aperturaTurnoSpringRepository;
		this.aperturaTurnoEntityConverter = aperturaTurnoEntityConverter;
		this.saldoEntityConverter = saldoEntityConverter;
		this.fxEntityConverter = fxEntityConverter;
		this.fxConverter = fxConverter;
		this.cierreSpringRepository = cierreSpringRepository;
	}

	@Transactional
	@Override
	public Turno create(Turno turno) {
		Turno newTurno;
		try {

			List<TurnoEntity> listaTurnosSinCierre = getTurnoSpringRepository().findTurnosSinCierre(turno.getIdCaja(),
					turno.getNombreUsuario());

			List<TurnoEntity> listaTurnosSinCierreCaja = getTurnoSpringRepository()
					.findTurnosSinCierreEnEstaCaja(turno.getIdCaja());

			Page<CierreEntity> page = cierreSpringRepository.obtenerFechaDelUltimoCierre(PageRequest.of(0, 1));

			if (!page.getContent().isEmpty()
					&&  page.getContent().get(0).getFecha().isEqual(LocalDate.now(ZoneId.of("America/Bogota")))) {
				throw new BusinessException("No es posible abrir turno ya que ya se realizo el cierre de hoy");
			}

			if (listaTurnosSinCierre.size() > 0) {
				if (listaTurnosSinCierre.size() == 1) {
					return turnoConverter.convert(listaTurnosSinCierre.get(0));
				}
				throw new BusinessException("Hay mas de un turno para este usuario y esta caja");
			}

			if (listaTurnosSinCierreCaja.size() > 0) {
				throw new BusinessException("Hay mas de un turno para esta caja");
			}

			List<SaldoEntity> listaSaldosCaja = saldoSpringRepository.saldosPorCaja(turno.getIdCaja());

			turno.setFechaInicio(LocalDateTime.now(ZoneId.of("America/Bogota")));
			TurnoEntity turnoEntity = getTurnoEntityConverter().convert(turno);
			TurnoEntity newTurnoEntity = getTurnoSpringRepository().saveAndFlush(turnoEntity);
			newTurno = getTurnoConverter().convert(newTurnoEntity);

			List<AperturaTurno> listaAperturaTurnos = listaSaldosCaja.stream().map(saldoCajaEntity -> {
				return new AperturaTurno(null, newTurnoEntity.getId(), fxConverter.convert(saldoCajaEntity.getFx()),
						saldoCajaEntity.getNominal(), saldoCajaEntity.getPrecioPromedio());
			}).collect(Collectors.toList());

			List<AperturaTurnoEntity> listaAperturaTurnoEntity = listaAperturaTurnos.stream()
					.map(aperturaTurno -> getAperturaTurnoEntityConverter().convert(aperturaTurno))
					.collect(Collectors.toList());

			listaAperturaTurnoEntity.forEach(getAperturaTurnoSpringRepository()::saveAndFlush);

		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newTurno;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Turno> getList(int first, int max) {
		List<Turno> listaTurnos;
		try {
			Page<TurnoEntity> page = getTurnoSpringRepository().findAll(PageRequest.of(first / max, max));
			listaTurnos = page.stream().map(turnoEntity -> getTurnoConverter().convert(turnoEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return listaTurnos;
	}

	/**
	 * le pone fecha fin al turno
	 */
	@Transactional
	@Override
	public Turno update(Long id, Turno turno) {
		Turno newTurno;
		try {
			System.out.println("update turno");
			TurnoEntity turnoEntity = getTurnoSpringRepository().findById(id).orElseThrow(NoFoundDataException::new);

			turnoEntity.setFechaFin(LocalDateTime.now(ZoneId.of("America/Bogota")));
			TurnoEntity newTurnoEntity = getTurnoSpringRepository().save(turnoEntity);
			newTurno = getTurnoConverter().convert(newTurnoEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newTurno;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		try {
			Optional<TurnoEntity> turnoEntityOptioanal = getTurnoSpringRepository().findById(id);
			turnoEntityOptioanal.ifPresent(getTurnoSpringRepository()::delete);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Turno> getTurno(Long id) {
		Optional<Turno> turno;
		try {
			Optional<TurnoEntity> turnoEntityOptioanal = getTurnoSpringRepository().findById(id);
			turno = turnoEntityOptioanal.map(cajaEntity -> getTurnoConverter().convert(cajaEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return turno;
	}

	@Override
	public Optional<Turno> obtenerTurnoActivo(String nombreUsuario) {
		
		
		return null;
	}

	@Override
	public List<Turno> getTurnosSucursal(Long idSucursal) {
		List<Turno> listaTurnos;
		listaTurnos = turnoSpringRepository.listaTurnosEnUnDia(LocalDate.now(ZoneId.of("America/Bogota")), idSucursal)
				.stream().map(turnoEntity -> getTurnoConverter().convert(turnoEntity)).collect(Collectors.toList());
		return listaTurnos;
	}
}
