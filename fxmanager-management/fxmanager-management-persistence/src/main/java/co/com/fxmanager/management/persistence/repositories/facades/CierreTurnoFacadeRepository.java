package co.com.fxmanager.management.persistence.repositories.facades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.fxmanager.management.domain.entities.CierreTurno;
import co.com.fxmanager.management.domain.entities.ItemPreCierre;
import co.com.fxmanager.management.domain.entities.PreCierre;
import co.com.fxmanager.management.persistence.entities.jpa.AperturaTurnoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.ArqueoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.CierreTurnoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.FlujoExtraordinarioEntity;
import co.com.fxmanager.management.persistence.entities.jpa.OperacionEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SaldoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TrasladoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TurnoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.converters.ArqueoConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.ArqueoEntityConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.CajaConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.CierreTurnoConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.CierreTurnoEntityConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.FxConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.UsuarioConverter;
import co.com.fxmanager.management.persistence.repositories.CierreTurnoRepository;
import co.com.fxmanager.management.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.AperturaTurnoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.ArqueoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.CierreTurnoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.FlujoExtraordinarioSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.OperacionSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.SaldoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TrasladoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Service
public class CierreTurnoFacadeRepository implements CierreTurnoRepository {

	private CierreTurnoSpringRepository cierreTurnoSpringRepository;

	private CierreTurnoConverter cierreTurnoConverter;

	private CierreTurnoEntityConverter cierreTurnoEntityConverter;

	private SaldoSpringRepository saldoSpringRepository;

	private TurnoSpringRepository turnoSpringRepository;

	private FxConverter fxConverter;

	private OperacionSpringRepository operacionSpringRepository;

	private AperturaTurnoSpringRepository aperturaTurnoSpringRepository;

	private TrasladoSpringRepository trasladoSpringRepository;

	private FlujoExtraordinarioSpringRepository flujoExtraordinarioSpringRepository;

	private CajaConverter cajaConverter;

	private UsuarioConverter usuarioConverter;

	private UsuarioFacadeRepository usuarioFacadeRepository;

	private ArqueoSpringRepository arqueoSpringRepository;

	private ArqueoEntityConverter arqueoEntityConverter;

	private ArqueoConverter arqueoConverter;

	public CierreTurnoFacadeRepository(CierreTurnoSpringRepository cierreTurnoSpringRepository,
			CierreTurnoConverter cierreTurnoConverter, CierreTurnoEntityConverter cierreTurnoEntityConverter,
			SaldoSpringRepository saldoSpringRepository, TurnoSpringRepository turnoSpringRepository,
			FxConverter fxConverter, OperacionSpringRepository operacionSpringRepository,
			AperturaTurnoSpringRepository aperturaTurnoSpringRepository,
			TrasladoSpringRepository trasladoSpringRepository,
			FlujoExtraordinarioSpringRepository flujoExtraordinarioSpringRepository, CajaConverter cajaConverter,
			UsuarioConverter usuarioConverter, UsuarioFacadeRepository usuarioFacadeRepository,
			ArqueoSpringRepository arqueoSpringRepository, ArqueoEntityConverter arqueoEntityConverter,
			ArqueoConverter arqueoConverter) {
		super();
		this.cierreTurnoSpringRepository = cierreTurnoSpringRepository;
		this.cierreTurnoConverter = cierreTurnoConverter;
		this.cierreTurnoEntityConverter = cierreTurnoEntityConverter;
		this.saldoSpringRepository = saldoSpringRepository;
		this.turnoSpringRepository = turnoSpringRepository;
		this.fxConverter = fxConverter;
		this.operacionSpringRepository = operacionSpringRepository;
		this.aperturaTurnoSpringRepository = aperturaTurnoSpringRepository;
		this.flujoExtraordinarioSpringRepository = flujoExtraordinarioSpringRepository;
		this.trasladoSpringRepository = trasladoSpringRepository;
		this.cajaConverter = cajaConverter;
		this.usuarioConverter = usuarioConverter;
		this.usuarioFacadeRepository = usuarioFacadeRepository;
		this.arqueoSpringRepository = arqueoSpringRepository;
		this.arqueoEntityConverter = arqueoEntityConverter;
		this.arqueoConverter = arqueoConverter;

	}

	@Transactional
	@Override
	public CierreTurno create(CierreTurno cierreTurno) {
		return cierreTurno;
	}

	@Transactional(readOnly = true)
	@Override
	public PreCierre createYDevolverPrecierre(CierreTurno cierreTurno) {

		PreCierre preCierre = null;
		try {
			TurnoEntity turno = getTurnoSpringRepository().findById(cierreTurno.getIdTurno())
					.orElseThrow(NoFoundDataException::new);
			List<SaldoEntity> listaSaldosCaja = getSaldoSpringRepository().saldosPorCaja(turno.getCaja().getId());

			List<CierreTurno> listaCierreTurnos = listaSaldosCaja.stream().map(saldoCajaEntity -> {
				return new CierreTurno(null, turno.getId(), fxConverter.convert(saldoCajaEntity.getFx()),
						saldoCajaEntity.getNominal(), saldoCajaEntity.getPrecioPromedio());
			}).collect(Collectors.toList());

			BigDecimal nominalGastadoCompras = BigDecimal.ZERO;
			BigDecimal nominalIngresadoVentas = BigDecimal.ZERO;

			preCierre = new PreCierre();

			ArrayList<ItemPreCierre> listaItems = new ArrayList<>();

			// se obtienen saldos iniciales
			List<AperturaTurnoEntity> listaSaldosIniciales = aperturaTurnoSpringRepository
					.getSaldoAperturaTurnoPorTurno(turno.getId());

			// se recorre los saldo iniciales de cada moneda
			for (AperturaTurnoEntity aperturaTurnoEntity : listaSaldosIniciales) {

				ItemPreCierre itemPrecierre = new ItemPreCierre();

				CierreTurno cierreTurnoMoneda = listaCierreTurnos.stream()
						.filter(c -> c.getMoneda().getCodigo().equals(aperturaTurnoEntity.getFx().getCodigo()))
						.findFirst().orElseThrow(NoFoundDataException::new);

				AperturaTurnoEntity aperturaTurnoEntitySaldo = listaSaldosIniciales.stream()
						.filter(c -> c.getFx().getCodigo().equals(aperturaTurnoEntity.getFx().getCodigo())).findFirst()
						.orElseThrow(NoFoundDataException::new);

				itemPrecierre.setMoneda(fxConverter.convert(aperturaTurnoEntity.getFx()));
				itemPrecierre.setSaldosFinal(cierreTurnoMoneda.getNominal());
				itemPrecierre.setSaldosInicial(aperturaTurnoEntitySaldo.getNominal());

				List<OperacionEntity> listaCompras = operacionSpringRepository
						.operacionesPorTurnoYDivisaCompras(turno.getId(), aperturaTurnoEntity.getFx().getId());
				List<OperacionEntity> listaVentas = operacionSpringRepository
						.operacionesPorTurnoYDivisaVentas(turno.getId(), aperturaTurnoEntity.getFx().getId());

				BigDecimal nominalCompras = BigDecimal.ZERO;
				BigDecimal nominalVentas = BigDecimal.ZERO;

				// se calcula el nominal de compras
				for (OperacionEntity operacionEntity : listaCompras) {
					nominalCompras = nominalCompras.add(operacionEntity.getNominal());
					nominalGastadoCompras = nominalGastadoCompras
							.add(operacionEntity.getValorFxOperacion().multiply(operacionEntity.getNominal()));
				}

				// se calcula el nominal de ventas
				for (OperacionEntity operacionEntity : listaVentas) {
					nominalVentas = nominalVentas.add(operacionEntity.getNominal());
					nominalIngresadoVentas = nominalIngresadoVentas
							.add(operacionEntity.getValorFxOperacion().multiply(operacionEntity.getNominal()));
				}

				itemPrecierre.setNominalCompras(nominalCompras);
				itemPrecierre.setNominalVentas(nominalVentas);

				// se calcula los traslados salientes
				List<TrasladoEntity> listaTrasladosSalientes = trasladoSpringRepository
						.obtenerListaDeTrasladosSalienteTurnoyFx(turno.getId(), aperturaTurnoEntity.getFx().getId());

				// se calcula los traslados entrantes
				List<TrasladoEntity> listaTrasladosEntrantes = trasladoSpringRepository
						.obtenerListaDeTrasladosLlegadasTurnoyFx(turno.getId(), aperturaTurnoEntity.getFx().getId());

				BigDecimal nominalTrasladosSalientes = BigDecimal.ZERO;
				BigDecimal nominalTrasladosEntrantes = BigDecimal.ZERO;

				// se calcula el total nominal traslados salientes
				for (TrasladoEntity trasladoEntity : listaTrasladosSalientes) {
					nominalTrasladosSalientes = nominalTrasladosSalientes.add(trasladoEntity.getValorGiro());
				}

				// se calcula el total nominal traslados entrantes
				for (TrasladoEntity trasladoEntity : listaTrasladosEntrantes) {
					nominalTrasladosEntrantes = nominalTrasladosEntrantes.add(trasladoEntity.getValorGiro());
				}

				itemPrecierre.setNominalTrasladosEntrantes(nominalTrasladosEntrantes);
				itemPrecierre.setNominalTrasladosSalientes(nominalTrasladosSalientes);

				List<FlujoExtraordinarioEntity> listaEgresos = flujoExtraordinarioSpringRepository
						.egresosPorTurno(turno.getId(), aperturaTurnoEntity.getFx().getId());
				List<FlujoExtraordinarioEntity> listaIngresos = flujoExtraordinarioSpringRepository
						.ingresosPorTurno(turno.getId(), aperturaTurnoEntity.getFx().getId());

				BigDecimal nominalEgresos = BigDecimal.ZERO;
				BigDecimal nominalIngresos = BigDecimal.ZERO;

				for (FlujoExtraordinarioEntity flujoExtraordinarioEntity : listaEgresos) {
					nominalEgresos = nominalEgresos.add(flujoExtraordinarioEntity.getValor());
				}

				for (FlujoExtraordinarioEntity flujoExtraordinarioEntity : listaIngresos) {
					nominalIngresos = nominalIngresos.add(flujoExtraordinarioEntity.getValor());
				}

				itemPrecierre.setNominalEgresos(nominalEgresos);
				itemPrecierre.setNominalIngresos(nominalIngresos);

				listaItems.add(itemPrecierre);

			}

			for (ItemPreCierre itemPreCierre : listaItems) {
				if (itemPreCierre.getMoneda().getCodigo().equals("COP")) {
					itemPreCierre.setNominalCompras(nominalGastadoCompras);
					itemPreCierre.setNominalVentas(nominalIngresadoVentas);
					break;
				}
			}

			preCierre.setFecha(LocalDateTime.now(ZoneId.of("America/Bogota")));
			preCierre.setListaItems(listaItems);
			preCierre.setCaja(cajaConverter.convert(turno.getCaja()));
			preCierre.setUsuario(usuarioFacadeRepository.getUsuario(turno.getUsuario().getId())
					.orElseThrow(NoFoundDataException::new));

		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return preCierre;
	}

	@Transactional(readOnly = true)
	@Override
	public List<CierreTurno> getList(int first, int max) {
		List<CierreTurno> listaCierreTurnos;
		try {
			Page<CierreTurnoEntity> page = getCierreTurnoSpringRepository().findAll(PageRequest.of(first / max, max));
			listaCierreTurnos = page.stream()
					.map(cierreTurnoEntity -> getCierreTurnoConverter().convert(cierreTurnoEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return listaCierreTurnos;
	}

	@Transactional
	@Override
	public CierreTurno update(Long id, CierreTurno cierreTurno) {

		try {
			TurnoEntity turno = getTurnoSpringRepository().findById(id).orElseThrow(NoFoundDataException::new);
			List<SaldoEntity> listaSaldosCaja = getSaldoSpringRepository().saldosPorCaja(turno.getCaja().getId());

			List<CierreTurno> listaCierreTurnos = listaSaldosCaja.stream().map(saldoCajaEntity -> {
				return new CierreTurno(null, turno.getId(), fxConverter.convert(saldoCajaEntity.getFx()),
						saldoCajaEntity.getNominal(), saldoCajaEntity.getPrecioPromedio());
			}).collect(Collectors.toList());

			List<CierreTurnoEntity> listaCierreTurnoEntity = listaCierreTurnos.stream()
					.map(getCierreTurnoEntityConverter()::convert).collect(Collectors.toList());
			listaCierreTurnoEntity.forEach(getCierreTurnoSpringRepository()::saveAndFlush);

			turno.setFechaFin(LocalDateTime.now(ZoneId.of("America/Bogota")));
			getTurnoSpringRepository().save(turno);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<CierreTurno> getCierreTurno(Long id) {
		Optional<CierreTurno> cierreTurno;
		try {
			Optional<CierreTurnoEntity> cierreTurnoEntityOptional = getCierreTurnoSpringRepository().findById(id);
			cierreTurno = cierreTurnoEntityOptional
					.map(cierreTurnoEntity -> getCierreTurnoConverter().convert(cierreTurnoEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return cierreTurno;
	}

	@Transactional
	@Override
	public void cancelarPrecierre(Long idTurno) {

		List<CierreTurnoEntity> listaSaldosCierresABorrar = cierreTurnoSpringRepository
				.getSaldosCierreTurnoPorTurno(idTurno);

		for (CierreTurnoEntity cierreTurnoEntity : listaSaldosCierresABorrar) {
			cierreTurnoSpringRepository.delete(cierreTurnoEntity);
		}

	}

	@Override
	public PreCierre createRegistroArqueo(PreCierre preCierre) {

		preCierre.setFecha(LocalDateTime.now(ZoneId.of("America/Bogota")));
		arqueoSpringRepository.saveAndFlush(arqueoEntityConverter.convert(preCierre));

		return null;
	}

	@Override
	public List<PreCierre> arqueosHistoricos(LocalDate fecha) {
		return arqueoSpringRepository.arqueosHistoricos(fecha).stream().map(getArqueoConverter()::convert)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<PreCierre> getArqueosHistoricos(Long id) {
		Optional<PreCierre> preCierre;
		try {
			Optional<ArqueoEntity> arqueoEntityOptioanal = getArqueoSpringRepository().findById(id);

			preCierre = arqueoEntityOptioanal.map(entity -> getArqueoConverter().convert(entity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return preCierre;
	}

}
