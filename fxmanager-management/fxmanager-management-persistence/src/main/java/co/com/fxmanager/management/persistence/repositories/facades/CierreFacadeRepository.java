package co.com.fxmanager.management.persistence.repositories.facades;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.fxmanager.management.domain.entities.Cierre;
import co.com.fxmanager.management.domain.entities.CierreConsolidado;
import co.com.fxmanager.management.domain.entities.ReporteEstadoActual;
import co.com.fxmanager.management.domain.entities.ReportePortafolio;
import co.com.fxmanager.management.domain.services.exceptions.BusinessException;
import co.com.fxmanager.management.persistence.entities.jpa.AperturaTurnoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.CajaEntity;
import co.com.fxmanager.management.persistence.entities.jpa.CierreConsolidadoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.CierreEntity;
import co.com.fxmanager.management.persistence.entities.jpa.CierreTurnoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.FlujoExtraordinarioEntity;
import co.com.fxmanager.management.persistence.entities.jpa.FxEntity;
import co.com.fxmanager.management.persistence.entities.jpa.OperacionEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SaldoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.SucursalEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TrasladoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.TurnoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.converters.CierreConsolidadoConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.CierreConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.CierreConsolidadoEntityConverter;
import co.com.fxmanager.management.persistence.repositories.CierreRepository;
import co.com.fxmanager.management.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.AperturaTurnoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.CajaSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.CierreConsolidadoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.CierreSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.CierreTurnoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.FlujoExtraordinarioSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.FxSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.OperacionSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.SaldoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.SucursalSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TrasladoSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.TurnoSpringRepository;
import lombok.Getter;

@Getter
@Service
public class CierreFacadeRepository implements CierreRepository {

	private CierreSpringRepository cierreSpringRepository;

	private CierreConsolidadoSpringRepository cierreConsolidadoSpringRepository;

	private CierreConverter cierreConverter;

	private CierreConsolidadoEntityConverter cierreEntityConverter;

	private CierreConsolidadoConverter cierreConsolidadoConverter;

	private TurnoSpringRepository turnoSpringRepository;

	private OperacionSpringRepository operacionSpringRepository;

	private CajaSpringRepository cajaSpringRepository;

	private AperturaTurnoSpringRepository aperturaTurnoSpringRepository;

	private CierreTurnoSpringRepository cierreTurnoSpringRepository;

	private SucursalSpringRepository sucursalSpringRepository;

	private SaldoSpringRepository saldoSpringRepository;

	private FxSpringRepository fxSpringRepository;

	private FlujoExtraordinarioSpringRepository flujoExtraordinarioSpringRepository;

	private TrasladoSpringRepository trasladoSpringRepository;

	public CierreFacadeRepository(CierreSpringRepository cierreSpringRepository, CierreConverter cierreConverter,
			CierreConsolidadoEntityConverter cierreEntityConverter, OperacionSpringRepository operacionSpringRepository,
			TurnoSpringRepository turnoSpringRepository, CajaSpringRepository cajaSpringRepository,
			AperturaTurnoSpringRepository aperturaTurnoSpringRepository,
			CierreTurnoSpringRepository cierreTurnoSpringRepository, SucursalSpringRepository sucursalSpringRepository,
			SaldoSpringRepository saldoSpringRepository, FxSpringRepository fxSpringRepository,
			FlujoExtraordinarioSpringRepository flujoExtraordinarioSpringRepository,
			TrasladoSpringRepository trasladoSpringRepository,
			CierreConsolidadoSpringRepository cierreConsolidadoSpringRepository,
			CierreConsolidadoConverter cierreConsolidadoConverter) {
		super();
		this.cierreSpringRepository = cierreSpringRepository;
		this.cierreConverter = cierreConverter;
		this.cierreConsolidadoConverter = cierreConsolidadoConverter;
		this.cierreEntityConverter = cierreEntityConverter;
		this.operacionSpringRepository = operacionSpringRepository;
		this.turnoSpringRepository = turnoSpringRepository;
		this.cajaSpringRepository = cajaSpringRepository;
		this.aperturaTurnoSpringRepository = aperturaTurnoSpringRepository;
		this.cierreTurnoSpringRepository = cierreTurnoSpringRepository;
		this.sucursalSpringRepository = sucursalSpringRepository;
		this.saldoSpringRepository = saldoSpringRepository;
		this.fxSpringRepository = fxSpringRepository;
		this.flujoExtraordinarioSpringRepository = flujoExtraordinarioSpringRepository;
		this.trasladoSpringRepository = trasladoSpringRepository;
		this.cierreConsolidadoSpringRepository = cierreConsolidadoSpringRepository;

	}

	@Transactional
	@Override
	public Cierre create(Cierre cierre) {
		Cierre newCierre = null;
		try {
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newCierre;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Cierre> getList(int first, int max) {
		List<Cierre> listaCierres;
		try {
			Page<CierreEntity> page = getCierreSpringRepository().findAll(PageRequest.of(first / max, max));
			listaCierres = page.stream().map(cierreEntity -> getCierreConverter().convert(cierreEntity))
					.collect(Collectors.toList());

		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return listaCierres;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Cierre> getCierre(Long id) {
		Optional<Cierre> cierre;
		try {
			Optional<CierreEntity> cierreEntityOptional = getCierreSpringRepository().findById(id);
			cierre = cierreEntityOptional.map(cierreEntity -> getCierreConverter().convert(cierreEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return cierre;
	}

	@Transactional
	@Override
	public List<Cierre> realizarCierre(LocalDate fechaCierre) {
		List<SucursalEntity> listaSucursales = sucursalSpringRepository.findAll();
		List<FxEntity> listaMonedas = fxSpringRepository.findAll();

		validarSiHayTurnoAbiertosHoySinCerrar(fechaCierre);

		BigDecimal precioPromedioCompraFinal = BigDecimal.ZERO;

		ArrayList<CierreEntity> listaCierres = new ArrayList<>();
		for (SucursalEntity sucursalEntity : listaSucursales) {

			CierreEntity cierreCop = new CierreEntity();
			CierreEntity cierreEntity;

			for (FxEntity fxEntity : listaMonedas) {

				cierreEntity = new CierreEntity();
				if (fxEntity.getCodigo().equals("COP")) {
					cierreCop.setFecha(fechaCierre);
					cierreCop.setMoneda(fxEntity);
					cierreCop.setSucursal(sucursalEntity);
					calcularDatosTotalesInicialesPorDivisaYSucursal(sucursalEntity.getCodigo(), fxEntity.getId(),
							cierreCop);
					
					// se calcula solo los datos iniciales de pesos ya que no se requiere demas
					// datos para la moneda COP
					calcularEgresosEIngresos(cierreCop);
					continue;
				} else {

					precioPromedioCompraFinal = calcularPrecioPromedioFinal(fxEntity.getId(), fechaCierre);
					cierreEntity.setSucursal(sucursalEntity);
					cierreEntity.setFecha(fechaCierre);
					cierreEntity.setMoneda(fxEntity);

					calcularDatosTotalesInicialesPorDivisaYSucursal(sucursalEntity.getCodigo(), fxEntity.getId(),
							cierreEntity);
				}

				// se obtienen datos finales totales de la moneda por sucursal
				calcularDatosTotalesFinalesPorDivisaYSucursal(sucursalEntity.getCodigo(), fxEntity.getId(),
						cierreEntity);

				Optional<CierreEntity> cierreAnterior;
				Page<CierreEntity> page = cierreSpringRepository.obtenerElUltimoCierrePorSucursal(
						sucursalEntity.getId(), fxEntity.getId(), PageRequest.of(0, 1));
				if (page.getContent().size() != 0) {
					cierreAnterior = Optional.of(page.getContent().get(0));
				} else {
					cierreAnterior = Optional.empty();
				}

				if (cierreAnterior.isPresent() && cierreAnterior.get().getFecha().isEqual(cierreEntity.getFecha())) {
					throw new BusinessException("el cierre de este dia ya se realizo");
				}

				calcularIndicadoresTraslados(cierreEntity, cierreAnterior);

				// se calcula los datos finales del cierre
				calcularSaldosFinales(fechaCierre, sucursalEntity.getId(), fxEntity.getId(), cierreEntity,
						precioPromedioCompraFinal);

				/**
				 * se calcula los datos faltantes de los traslados
				 */
				cierreEntity.setValorTraslados(
						cierreEntity.getNominalTrasladosSalientes().multiply(cierreEntity.getPrecioPromedioFinal()));

				if (cierreEntity.getNominalTrasladosSalientes().compareTo(BigDecimal.ZERO) > 0) {
					cierreEntity.setPygTrasladoDiario(
							cierreEntity.getValorGiroTrasladoSalientes().subtract(cierreEntity.getValorTraslados()));
				} else {
					cierreEntity.setPygTrasladoDiario(new BigDecimal(0));
				}

				if (cierreAnterior.isPresent()) {
					cierreEntity.setPygTrasladoAcumulado(
							cierreAnterior.get().getPygTrasladoAcumulado().add(cierreEntity.getPygTrasladoDiario()));
				} else {
					cierreEntity.setPygTrasladoAcumulado(cierreEntity.getPygTrasladoDiario());
				}
				/**
				 * fin de calcular los datos faltantes de traslados
				 */

				calcularIndicadores(cierreEntity, cierreAnterior);

				listaCierres.add(cierreEntity);

				actualizarPrecioPromedioSaldos(sucursalEntity.getId(), fxEntity.getId(),
						cierreEntity.getPrecioPromedioFinal());

			}

			calcularValorCopTotal(listaCierres, cierreCop);
			listaCierres.add(cierreCop);

		}

		List<Cierre> listaCierre = new ArrayList<>();

		listaCierres.forEach(cierreEntity -> {
			CierreEntity cierreGuardada = cierreSpringRepository.saveAndFlush(cierreEntity);
			listaCierre.add(cierreConverter.convert(cierreGuardada));
		});

		realizarCierreConsolidado(fechaCierre);
		return listaCierre;
	}

	/**
	 * valida que no hallan turnos abiertos para el dia que llega por parametros
	 * 
	 * @param fechaCierre
	 */
	private void validarSiHayTurnoAbiertosHoySinCerrar(LocalDate fechaCierre) {

		List<TurnoEntity> listaDeTurnoHoy = turnoSpringRepository.listaTurnosEnUnDia(fechaCierre);

		if (!listaDeTurnoHoy.isEmpty()) {
			throw new NoFoundDataException(
					"No es posible realizar el cierre ya que hay turno(s) " + listaDeTurnoHoy.size() + " abierto(s)");
		}

	}

	/**
	 * actualiza los precios promedios de los saldos
	 * 
	 * @param idSucursal
	 * @param idFx
	 * @param precioPromedio
	 */
	private void actualizarPrecioPromedioSaldos(Long idSucursal, Long idFx, BigDecimal precioPromedio) {
		List<SaldoEntity> listaSaldos = saldoSpringRepository.saldosPorSucursalYMoneda(idSucursal, idFx);
		for (SaldoEntity saldoEntity : listaSaldos) {
			saldoEntity.setPrecioPromedio(precioPromedio);
			saldoSpringRepository.save(saldoEntity);
		}
	}

	private void calcularValorCopTotal(ArrayList<CierreEntity> listaCierres, CierreEntity cierreCop) {

		List<CierreEntity> listaCierresSucursal = listaCierres.stream().filter(cierre -> {
			return cierreCop.getSucursal().getId() == cierre.getSucursal().getId();
		}).collect(Collectors.toList());

		BigDecimal valorVentas = BigDecimal.ZERO;
		BigDecimal valorCompras = BigDecimal.ZERO;
		BigDecimal valorEgresos = BigDecimal.ZERO;

		for (CierreEntity cierreEntity : listaCierresSucursal) {
			valorVentas = valorVentas.add(cierreEntity.getValorVenta());
			valorCompras = valorCompras.add(cierreEntity.getValorCompra());
			valorEgresos = cierreEntity.getDiferenciaIngresosEgresos();
		}

		cierreCop.setPrecioCompra(BigDecimal.ONE);
		cierreCop.setPrecioVenta(BigDecimal.ONE);

		cierreCop.setValorCompra(valorCompras);
		cierreCop.setValorVenta(valorVentas);

		cierreCop.setNominalCompra(valorCompras);
		cierreCop.setNominalVenta(valorVentas);

		cierreCop.setDiferenciaIngresosEgresos(valorEgresos);
		cierreCop.setSaldoFinal(cierreCop.getSaldoInicial().add(valorVentas).subtract(valorCompras).add(valorEgresos));
		cierreCop.setPrecioPromedioFinal(BigDecimal.ONE);
		cierreCop.setValorCompraFinal(cierreCop.getSaldoFinal());

		Optional<CierreEntity> cierreAnterior;
		Page<CierreEntity> page = cierreSpringRepository.obtenerElUltimoCierrePorSucursal(
				listaCierresSucursal.get(0).getSucursal().getId(), listaCierresSucursal.get(0).getMoneda().getId(),
				PageRequest.of(0, 1));
		if (page.getContent().size() != 0) {
			cierreAnterior = Optional.of(page.getContent().get(0));
		} else {
			cierreAnterior = Optional.empty();
		}

		for (CierreEntity cierreEntity : listaCierresSucursal) {
			if (cierreAnterior.isPresent()) {
				cierreEntity.setSaldoCop(
						cierreAnterior.get().getSaldoCop().add(valorVentas).subtract(valorCompras).add(valorEgresos));
			} else {
				cierreEntity.setSaldoCop(
						cierreCop.getSaldoInicial().add(valorVentas).subtract(valorCompras).add(valorEgresos));
			}
			cierreEntity.setValorPort(cierreEntity.getValoracion().add(cierreEntity.getSaldoCop()));

		}

	}

	/**
	 * metodo encargado de calcular los saldos finales
	 * 
	 * @param fechaTurno fecha de los turnos
	 * @param idFx
	 * @return
	 */
	private void calcularSaldosFinales(LocalDate fechaTurno, Long idSucursal, Long idFx, CierreEntity cierre,
			BigDecimal precioPromedioFinal) {

		calcularDatosCompras(fechaTurno, idSucursal, idFx, cierre);

		calcularDatosVentas(fechaTurno, idSucursal, idFx, cierre);

		cierre.setPrecioPromedioFinal(precioPromedioFinal);
		cierre.setValorCompraFinal(precioPromedioFinal.multiply(cierre.getSaldoFinal()));

		calcularDatosPrecierre(cierre, precioPromedioFinal);
	}

	private void calcularDatosPrecierre(CierreEntity cierre, BigDecimal precioPromedioFinal) {
		/** saldo precierre = saldo inicial + compras -ventas */
		cierre.setSaldoPrecierre(
				cierre.getSaldoInicial().add(cierre.getNominalCompra()).subtract(cierre.getNominalVenta()));
		cierre.setValorGiroPrecierre(cierre.getSaldoPrecierre().multiply(precioPromedioFinal));
	}

	private void calcularDatosVentas(LocalDate fechaTurno, Long idSucursal, Long idFx, CierreEntity cierre) {
		List<OperacionEntity> listaCompletaOperacionesVentasEnUnDiaDivisa = operacionSpringRepository
				.operacionesVentaEnUnDiaPorSucursalyDivisa(fechaTurno, idSucursal, idFx);

		BigDecimal nominalVentaDia = new BigDecimal(0);
		BigDecimal valorTotalOperacionesVentaDia = new BigDecimal(0);

		/**
		 * recorre la lista de ventas del dia y suma su nominal y el valor total de las
		 * operciones
		 */
		for (OperacionEntity operacionEntity : listaCompletaOperacionesVentasEnUnDiaDivisa) {
			nominalVentaDia = nominalVentaDia.add(operacionEntity.getNominal());
			valorTotalOperacionesVentaDia = valorTotalOperacionesVentaDia
					.add(operacionEntity.getNominal().multiply(operacionEntity.getValorFxOperacion()));
		}

		cierre.setNominalVenta(nominalVentaDia);
		cierre.setValorVenta(valorTotalOperacionesVentaDia);

		if (nominalVentaDia.compareTo(BigDecimal.ZERO) == 0) {
			cierre.setPrecioVenta(BigDecimal.ZERO);
		} else {
			cierre.setPrecioVenta(valorTotalOperacionesVentaDia.divide(nominalVentaDia, 8, RoundingMode.HALF_UP));
		}
	}

	private void calcularDatosCompras(LocalDate fechaTurno, Long idSucursal, Long idFx, CierreEntity cierre) {
		List<OperacionEntity> listaCompletaOperacionesComprasEnUnDiaDivisa = operacionSpringRepository
				.operacionesCompraEnUnDiaPorSucursalYDivisa(fechaTurno, idSucursal, idFx);

		BigDecimal nominalCompraDia = new BigDecimal(0);
		BigDecimal valorTotalOperacionesCompraDia = new BigDecimal(0);

		/**
		 * recorre la lista de compras del dia y suma su nominal y el valor total de las
		 * operciones
		 */
		for (OperacionEntity operacionEntity : listaCompletaOperacionesComprasEnUnDiaDivisa) {
			nominalCompraDia = nominalCompraDia.add(operacionEntity.getNominal());
			valorTotalOperacionesCompraDia = valorTotalOperacionesCompraDia
					.add(operacionEntity.getNominal().multiply(operacionEntity.getValorFxOperacion()));
		}

		cierre.setNominalCompra(nominalCompraDia);
		cierre.setValorCompra(valorTotalOperacionesCompraDia);

		if (nominalCompraDia.compareTo(BigDecimal.ZERO) == 0) {
			cierre.setPrecioCompra(BigDecimal.ZERO);
		} else {
			cierre.setPrecioCompra(valorTotalOperacionesCompraDia.divide(nominalCompraDia, 8, RoundingMode.HALF_UP));
		}
	}

	private void calcularIndicadores(CierreEntity cierre, Optional<CierreEntity> cierreAnterior) {
		BigDecimal margen = new BigDecimal(0);

		// si no se vendio nada el margen es 0
		if (cierre.getPrecioVenta().compareTo(BigDecimal.ZERO) == 0) {
			margen = BigDecimal.ZERO;
		} else {
			margen = cierre.getPrecioVenta().subtract(cierre.getPrecioPromedioFinal());
		}
		cierre.setMargen(margen);

		cierre.setUtilidadDiaria(margen.multiply(cierre.getNominalVenta()));

		/**
		 * se calcula el pyg caja y pyg D aprtir de el cierre anterior
		 */
		if (cierreAnterior.isPresent()) {
			cierre.setPygCaja(cierreAnterior.get().getPygCaja().add(cierre.getUtilidadDiaria()));

			cierre.setPygCajaD(cierre.getPygCaja().subtract(cierreAnterior.get().getPygCaja()));

		} else {
			cierre.setPygCaja(cierre.getUtilidadDiaria());
			cierre.setPygCajaD(cierre.getPygCaja());
		}

		calcularPrecioValoracion(cierre, cierreAnterior.orElse(null));

		/**
		 * margenPrecioValoracionYPrecioPromedio = PrecioValoracion- PrecioPromedioFinal
		 */
		cierre.setMargePrecioValoracionYPrecioPromedio(
				cierre.getPrecioValoracion().subtract(cierre.getPrecioPromedioFinal()));

		/** valoracion = saldoFinal * precioValoacion */
		cierre.setValoracion(cierre.getSaldoFinal().multiply(cierre.getPrecioValoracion()));

		/** pyg Valoracion = valoracion - valorCompraFinal */
		cierre.setPygValoracion(cierre.getValoracion().subtract(cierre.getValorCompraFinal()));

		/**
		 * pyg valoracion D = diferencia entre el pygValoracion del cierre anterior y el
		 * cierre actual
		 */
		if (cierreAnterior.isPresent()) {
			cierre.setPygValoracionD(cierre.getPygValoracion().subtract(cierreAnterior.get().getPygValoracion()));

		} else {
			cierre.setPygValoracionD(cierre.getPygValoracion());
		}

		/** pyg bruto = pygCaja +pygValoracion */
		cierre.setPygBruto(cierre.getPygCaja().add(cierre.getPygValoracion()));

		BigDecimal saldoCopInicial = obtenerSaldoInicialCopDelMes(cierre.getSucursal(), cierre.getFecha());

		/**
		 * renta caja = pygCaja / valorCompraInicial + saldoCop al inicial
		 * mes(saldoCopInicial)
		 */
		cierre.setRentaCaja((cierre.getPygCaja().divide((cierre.getValorCompraInicial().add(saldoCopInicial)), 8,
				RoundingMode.HALF_UP)).multiply(new BigDecimal(100)));

		/**
		 * renta val = pygValoracion / (valor compra inicial + saldoCop al iniciar mes)
		 */
		cierre.setRentaVal((cierre.getPygValoracion().divide((cierre.getValorCompraInicial().add(saldoCopInicial)), 8,
				RoundingMode.HALF_UP)).multiply(new BigDecimal(100)));

		/** renta bruta = pygBruto / (valor compra inicial + saldoCop al iniciar mes) */
		cierre.setRentaBruta((cierre.getPygBruto().divide((cierre.getValorCompraInicial().add(saldoCopInicial)), 8,
				RoundingMode.HALF_UP)).multiply(new BigDecimal(100)));

		// se calcula los egresos e ingresos
		calcularEgresosEIngresos(cierre);

		if (cierreAnterior.isPresent()) {
			cierre.setEgresosAcumulados(
					cierreAnterior.get().getEgresosAcumulados().add(cierre.getDiferenciaIngresosEgresos()));
		} else {
			cierre.setEgresosAcumulados(cierre.getDiferenciaIngresosEgresos());
		}

		/** pyg neto = pygbruto - egresos acumulados */
		cierre.setPygNeto(cierre.getPygBruto().add(cierre.getEgresosAcumulados()));

		/** renta neta = pygneto / (valor compra inicial + saldoCop al iniciar mes) */
		cierre.setRentaNeta((cierre.getPygNeto().divide((cierre.getValorCompraInicial().add(saldoCopInicial)), 8,
				RoundingMode.HALF_UP)).multiply(new BigDecimal(100)));

		if (cierreAnterior.isPresent()) {
			cierre.setSaldoCop(cierreAnterior.get().getSaldoCop().add(cierre.getValorVenta())
					.subtract(cierre.getValorCompra()).add(cierre.getDiferenciaIngresosEgresos()));
		} else {
			cierre.setSaldoCop(saldoCopInicial.add(cierre.getValorVenta()).subtract(cierre.getValorCompra())
					.add(cierre.getDiferenciaIngresosEgresos()));
		}

		cierre.setPygBrutoConTraslado(cierre.getPygBruto().add(cierre.getPygTrasladoAcumulado()));
		cierre.setPygNetoConTraslado(cierre.getPygBrutoConTraslado().add(cierre.getEgresosAcumulados()));

		/** fc diario = valor ventas - valor compras - egresos */
		cierre.setFcDiario(
				cierre.getValorVenta().subtract((cierre.getValorCompra().add(cierre.getDiferenciaIngresosEgresos()))));

		if (cierreAnterior.isPresent()) {
			cierre.setFcAcumulado(cierre.getFcDiario().add(cierreAnterior.get().getFcAcumulado()));
		} else {
			cierre.setFcAcumulado(cierre.getFcDiario());
		}

		/**
		 * ajuste segun plantilla el dia 21 de mayo del 2019
		 */
		if (cierreAnterior.isPresent()) {
			cierre.setPrecioValoracionAnterior(cierreAnterior.get().getPrecioValoracion());
		} else {
			cierre.setPrecioValoracionAnterior(cierre.getPrecioValoracion());
		}

		cierre.setValoracionPrecierreAnterior(cierre.getSaldoInicial().multiply(cierre.getPrecioValoracionAnterior()));

		cierre.setValoracionPrecierreActual(cierre.getPrecioValoracion().multiply(cierre.getSaldoPrecierre()));

		cierre.setPygValPrecierreDiaria((cierre.getValoracionPrecierreActual().subtract(cierre.getValorGiroPrecierre())
				.subtract((cierre.getValoracionPrecierreAnterior().subtract(cierre.getValorCompraInicial())))));

		cierre.setPygValPrecierreAcumulada(
				cierre.getValoracionPrecierreActual().subtract(cierre.getValorCompraFinal()));

		// (valcierreaActual- valorGiroSaldoFinal ) - (valPrecierreActual -
		// saldoPrecierreValorGiro)
		cierre.setPygValCierreDiaria((cierre.getValoracion().subtract(cierre.getValorCompraFinal()))
				.subtract((cierre.getValoracionPrecierreActual().subtract(cierre.getValorGiroPrecierre()))));

		// valoracion - valorgiroSaldoFinal
		cierre.setPygValCierreAcumulada(cierre.getValoracion().subtract(cierre.getValorCompraFinal()));

		cierre.setPygTrasladoEntrante((cierre.getNominalTrasladosEntrantes().multiply(cierre.getPrecioPromedioFinal()))
				.subtract(cierre.getValorGiroTrasladoEntrantes()));

		cierre.setPygTrasladoSaliente(cierre.getValorGiroTrasladoSalientes()
				.subtract((cierre.getNominalTrasladosSalientes().multiply(cierre.getPrecioPromedioFinal()))));

		// se modifica
		cierre.setPygTrasladoDiario(cierre.getPygTrasladoSaliente().add(cierre.getPygTrasladoEntrante()));

		// se suman todos los pyg = pygCaja+ pygPrecierre + pygTraslados+ pygcierre
		cierre.setPygBruto(cierre.getUtilidadDiaria().add(cierre.getPygValPrecierreDiaria()
				.add(cierre.getPygTrasladoDiario().add(cierre.getPygValCierreDiaria()))));

		calcularPygAcumulados(cierre);

	}

	private void calcularPygAcumulados(CierreEntity cierre) {
		
		List<CierreEntity> listaCierresMes = cierreSpringRepository.obtenerlistaCierresDelMesConDivisa(cierre.getMoneda().getId(), cierre.getFecha(), cierre.getSucursal().getId());
		
		BigDecimal pygValCierreMensual = BigDecimal.ZERO;
		BigDecimal pygValPrecierreMensual = BigDecimal.ZERO;
		
		for (CierreEntity cierreEntity : listaCierresMes) {
			
			pygValCierreMensual = pygValCierreMensual.add(cierreEntity.getPygValCierreDiaria());
			pygValPrecierreMensual = pygValPrecierreMensual.add(cierreEntity.getPygValPrecierreDiaria());
		}
		
		cierre.setPygValCierreMensual(cierre.getPygValCierreDiaria().add(pygValCierreMensual));
		cierre.setPygValPrecierreMensual(cierre.getPygValPrecierreDiaria().add(pygValPrecierreMensual));
	}

	private void calcularEgresosEIngresos(CierreEntity cierre) {

		FxEntity fxCop = fxSpringRepository.findByCodigo("COP").orElseThrow(NoFoundDataException::new);

		/** se consulta los egresos del dia */
		List<FlujoExtraordinarioEntity> listaEgresos = flujoExtraordinarioSpringRepository
				.obtenerEgresos(cierre.getFecha(), cierre.getSucursal().getId(), fxCop.getId());
		List<FlujoExtraordinarioEntity> listaIngresos = flujoExtraordinarioSpringRepository
				.obtenerIngresos(cierre.getFecha(), cierre.getSucursal().getId(), fxCop.getId());

		BigDecimal egresos = BigDecimal.ZERO;
		BigDecimal ingresos = BigDecimal.ZERO;

		for (FlujoExtraordinarioEntity ingreso : listaIngresos) {
			ingresos = egresos.add(ingreso.getValor());
		}

		for (FlujoExtraordinarioEntity egreso : listaEgresos) {
			egresos = egresos.add(egreso.getValor());
		}

		/**
		 * egresos en COP
		 */
		cierre.setDiferenciaIngresosEgresos(ingresos.subtract(egresos));
		cierre.setIngresosDiarios(ingresos);
		cierre.setEgresosDiarios(egresos);
		/**
		 * se calcula los egresos e ingresos que se realizaron en la moneda del cierre
		 */
		listaEgresos = flujoExtraordinarioSpringRepository.obtenerEgresos(cierre.getFecha(),
				cierre.getSucursal().getId(), cierre.getMoneda().getId());
		listaIngresos = flujoExtraordinarioSpringRepository.obtenerIngresos(cierre.getFecha(),
				cierre.getSucursal().getId(), cierre.getMoneda().getId());

		BigDecimal egresosMoneda = BigDecimal.ZERO;
		BigDecimal ingresosMoneda = BigDecimal.ZERO;

		for (FlujoExtraordinarioEntity ingreso : listaIngresos) {
			ingresosMoneda = ingresosMoneda.add(ingreso.getValor());
		}

		for (FlujoExtraordinarioEntity egreso : listaEgresos) {
			egresosMoneda = egresosMoneda.add(egreso.getValor());
		}

		cierre.setEgresosMoneda(ingresosMoneda.subtract(egresosMoneda));
	}

	private void calcularPrecioValoracion(CierreEntity cierre, CierreEntity cierreAnterior) {
		List<OperacionEntity> listaOperacionesCompra = operacionSpringRepository
				.operacionesCompraEnUnDiaPorDivisa(cierre.getFecha(), cierre.getMoneda().getId());
		List<OperacionEntity> listaOperacionesVenta = operacionSpringRepository
				.operacionesVentaEnUnDiaPorDivisa(cierre.getFecha(), cierre.getMoneda().getId());

		BigDecimal nominalComprasDia = new BigDecimal(0);
		BigDecimal valorTotalOperacionesCompraDia = new BigDecimal(0);
		BigDecimal valorPromedioCompras = new BigDecimal(0);

		/**
		 * recorre la lista de compras del dia y suma su nominal y el valor total de las
		 * operciones
		 */
		for (OperacionEntity operacionEntity : listaOperacionesCompra) {
			nominalComprasDia = nominalComprasDia.add(operacionEntity.getNominal());
			valorTotalOperacionesCompraDia = valorTotalOperacionesCompraDia
					.add(operacionEntity.getNominal().multiply(operacionEntity.getValorFxOperacion()));
		}

		/**
		 * se calcula el valor promedio de las compras
		 */
		if (nominalComprasDia.compareTo(BigDecimal.ZERO) == 0) {
			valorPromedioCompras = BigDecimal.ZERO;
		} else {
			valorPromedioCompras = valorTotalOperacionesCompraDia.divide(nominalComprasDia, 8, RoundingMode.HALF_UP);
		}

		BigDecimal nominalVentasDia = new BigDecimal(0);
		BigDecimal valorTotalOperacionesVentaDia = new BigDecimal(0);
		BigDecimal valorPromedioVentas = new BigDecimal(0);

		/**
		 * recorre la lista de ventas del dia y suma su nominal y el valor total de las
		 * operciones
		 */
		for (OperacionEntity operacionEntity : listaOperacionesVenta) {
			nominalVentasDia = nominalVentasDia.add(operacionEntity.getNominal());
			valorTotalOperacionesVentaDia = valorTotalOperacionesVentaDia
					.add(operacionEntity.getNominal().multiply(operacionEntity.getValorFxOperacion()));
		}

		if (nominalVentasDia.compareTo(BigDecimal.ZERO) == 0) {
			valorPromedioVentas = BigDecimal.ZERO;
		} else {
			valorPromedioVentas = valorTotalOperacionesVentaDia.divide(nominalVentasDia, 8, RoundingMode.HALF_UP);
		}

		if (valorPromedioVentas.compareTo(BigDecimal.ZERO) == 0
				|| valorPromedioCompras.compareTo(BigDecimal.ZERO) == 0) {

			if (cierreAnterior == null || cierreAnterior.getPrecioValoracion() == null) {

				throw new NoFoundDataException("No existe un precio valoracion en fxSucursal");
			} else {
				cierre.setPrecioValoracion(cierreAnterior.getPrecioValoracion());
			}

		} else {
			cierre.setPrecioValoracion(
					valorPromedioCompras.add(valorPromedioVentas).divide(new BigDecimal(2), 8, RoundingMode.HALF_UP));
		}

	}

	/**
	 * revisa la primer saldo del mes para ver el saldo inicial de COP
	 * 
	 * @param idSucursal
	 * @param fecha
	 * @return
	 */
	private BigDecimal obtenerSaldoInicialCopDelMes(SucursalEntity sucursal, LocalDate fecha) {

		List<CierreEntity> listaUltimosCierresMesAnterior = cierreSpringRepository
				.obtenerlistaUltimosCierresMesAnterior(sucursal.getId(), fecha.minusMonths(1));

		if (listaUltimosCierresMesAnterior.size() > 0) {
			return listaUltimosCierresMesAnterior.get(0).getSaldoCop();
		} else {
			listaUltimosCierresMesAnterior = cierreSpringRepository.obtenerlistaUltimosCierres(sucursal.getId());
			if (listaUltimosCierresMesAnterior.size() > 0) {
				return listaUltimosCierresMesAnterior.get(0).getSaldoCop();
			} else {
				FxEntity cop = fxSpringRepository.findByCodigo("COP").orElseThrow(NoFoundDataException::new);
				CierreEntity cierreCop = new CierreEntity();
				cierreCop.setFecha(fecha);
				calcularDatosTotalesInicialesPorDivisaYSucursal(sucursal.getCodigo(), cop.getId(), cierreCop);
				return cierreCop.getSaldoInicial();
			}
		}

	}

	/**
	 * calcula los datos totales iniciales por divisa y sucursal
	 * 
	 * @param codigoSucursal
	 * @param idDivisa
	 * @param cierre
	 */
	private void calcularDatosTotalesInicialesPorDivisaYSucursal(String codigoSucursal, Long idDivisa,
			CierreEntity cierre) {

		List<CajaEntity> cajasSucursal = cajaSpringRepository.cajasPorSucursal(codigoSucursal);
		List<TurnoEntity> listaPrimerTurnoCaja = turnoSpringRepository.findPrimerTurnosDeCadaCaja(cierre.getFecha());
		Boolean sinTurnos = Boolean.FALSE;
		if (listaPrimerTurnoCaja.isEmpty()) {
			sinTurnos = Boolean.TRUE;
			listaPrimerTurnoCaja = turnoSpringRepository
					.findUltimoTurnosDeCadaCaja(cierre.getFecha().minus(1, ChronoUnit.DAYS));

			if (listaPrimerTurnoCaja.isEmpty()) {
				throw new NoFoundDataException("No se encuentra turnos para este cierre");
			}
		}

		BigDecimal saldoInicial = BigDecimal.ZERO;
		BigDecimal precioPromedioInicial = BigDecimal.ZERO;

		// recorre cada caja recojiendo los datos iniciales de cada caja

		for (CajaEntity cajaEntity : cajasSucursal) {

			TurnoEntity primerTurnoEnity = listaPrimerTurnoCaja.stream()
					.filter(t -> t.getCaja().getId().equals(cajaEntity.getId())).findFirst()
					.orElseThrow(NoFoundDataException::new);

			if (sinTurnos) {
				// si no hay turnos en el dia de hoy se consulta el saldo final del ultimo turno
				CierreTurnoEntity saldoAperturaTurno = cierreTurnoSpringRepository
						.getSaldoCierreTurnoPorTurnoYDivisa(primerTurnoEnity.getId(), idDivisa)
						.orElseThrow(NoFoundDataException::new);
				saldoInicial = saldoInicial.add(saldoAperturaTurno.getNominal());
				precioPromedioInicial = precioPromedioInicial.add(saldoAperturaTurno.getPrecioPromedio());

			} else {
				AperturaTurnoEntity saldoAperturaTurno = aperturaTurnoSpringRepository
						.getSaldoAperturaTurnoPorTurnoYDivisa(primerTurnoEnity.getId(), idDivisa)
						.orElseThrow(NoFoundDataException::new);
				saldoInicial = saldoInicial.add(saldoAperturaTurno.getNominal());
				precioPromedioInicial = precioPromedioInicial.add(saldoAperturaTurno.getPrecioPromedio());
			}

		}

		// si la sucursal tiene mas de una caja entonces se calcula el precio promedio
		// inicial
		if (cajasSucursal.size() != 0)
			precioPromedioInicial = precioPromedioInicial.divide(new BigDecimal(cajasSucursal.size()), 8,
					RoundingMode.HALF_UP);

		cierre.setSaldoInicial(saldoInicial);
		cierre.setPrecioPromedioInicial(precioPromedioInicial);
		cierre.setValorCompraInicial(saldoInicial.multiply(precioPromedioInicial));

	}

	/**
	 * se calcula los datos finales de todas las caja al final del dia
	 * 
	 * @param codigoSucursal
	 * @param idDivisa
	 * @param cierre
	 */
	private void calcularDatosTotalesFinalesPorDivisaYSucursal(String codigoSucursal, Long idDivisa,
			CierreEntity cierre) {

		List<CajaEntity> cajasSucursal = cajaSpringRepository.cajasPorSucursal(codigoSucursal);
		List<TurnoEntity> listaUltimosTurnoCaja = turnoSpringRepository.findUltimoTurnosDeCadaCaja(cierre.getFecha()).stream().filter(turno ->{
			if(turno.getCaja().getSucursal().getId().equals(cierre.getSucursal().getId())) {
				return true;
			}
			return false;
		}).collect(Collectors.toList());

		if (listaUltimosTurnoCaja.isEmpty()) {
			listaUltimosTurnoCaja = turnoSpringRepository
					.findUltimoTurnosDeCadaCaja(cierre.getFecha().minus(1, ChronoUnit.DAYS));

			if (listaUltimosTurnoCaja.isEmpty()) {
				throw new NoFoundDataException("No se encuentra turnos para este cierre");
			}
		}

		BigDecimal saldoFinal = BigDecimal.ZERO;

		// se recorre cada caja para obtener los saldos finales
		for (CajaEntity cajaEntity : cajasSucursal) {

			TurnoEntity ultimoturnoEntity = listaUltimosTurnoCaja.stream()
					.filter(t -> t.getCaja().getId().equals(cajaEntity.getId())).findFirst()
					.orElseThrow(NoFoundDataException::new);

			CierreTurnoEntity cierreTurnoEntity = cierreTurnoSpringRepository
					.getSaldoCierreTurnoPorTurnoYDivisa(ultimoturnoEntity.getId(), idDivisa)
					.orElseThrow(NoFoundDataException::new);

			saldoFinal = saldoFinal.add(cierreTurnoEntity.getNominal());

		}

		BigDecimal trasladosSalientes = calcularTraslados(cierre);

		cierre.setSaldoFinal(saldoFinal.subtract(trasladosSalientes));

	}

	private BigDecimal calcularTraslados(CierreEntity cierre) {
		List<TrasladoEntity> listaTrasladosSalientes = trasladoSpringRepository.obtenerListaDeTrasladosSaliente(
				cierre.getSucursal().getId(), cierre.getMoneda().getId(), cierre.getFecha());

		BigDecimal nominalTrasladosSalientes = BigDecimal.ZERO;

		for (TrasladoEntity trasladoEntity : listaTrasladosSalientes) {
			nominalTrasladosSalientes.add(trasladoEntity.getValorGiro());
		}
		return nominalTrasladosSalientes;
	}

	private void calcularIndicadoresTraslados(CierreEntity cierre, Optional<CierreEntity> cierreAnterior) {

		List<TrasladoEntity> listaTrasladosSalientes = trasladoSpringRepository.obtenerListaDeTrasladosSaliente(
				cierre.getSucursal().getId(), cierre.getMoneda().getId(), cierre.getFecha());
		List<TrasladoEntity> listaTrasladosEntrantes = trasladoSpringRepository.obtenerListaDeTrasladosLlegadas(
				cierre.getSucursal().getId(), cierre.getMoneda().getId(), cierre.getFecha());

		BigDecimal nominalTrasladosSalientes = BigDecimal.ZERO;
		BigDecimal nominalTrasladosEntrantes = BigDecimal.ZERO;

		BigDecimal precioTrasladosSalientes = BigDecimal.ZERO;
		BigDecimal precioTrasladosEntrantes = BigDecimal.ZERO;

		for (TrasladoEntity trasladoEntity : listaTrasladosEntrantes) {
			nominalTrasladosEntrantes = nominalTrasladosEntrantes.add(trasladoEntity.getValorGiro());
			precioTrasladosEntrantes = precioTrasladosEntrantes.add(trasladoEntity.getPrecioTraslado());
		}

		if (listaTrasladosEntrantes.size() != 0) {
			precioTrasladosEntrantes = precioTrasladosEntrantes.divide(new BigDecimal(listaTrasladosEntrantes.size()),
					8, RoundingMode.HALF_UP);
		}

		for (TrasladoEntity trasladoEntity : listaTrasladosSalientes) {
			nominalTrasladosSalientes = nominalTrasladosSalientes.add(trasladoEntity.getValorGiro());
			precioTrasladosSalientes = precioTrasladosSalientes.add(trasladoEntity.getPrecioTraslado());
		}

		if (listaTrasladosSalientes.size() != 0) {
			precioTrasladosSalientes = precioTrasladosSalientes.divide(new BigDecimal(listaTrasladosSalientes.size()),
					8, RoundingMode.HALF_UP);
		}

		cierre.setNominalTrasladosEntrantes(nominalTrasladosEntrantes);
		cierre.setNominalTrasladosSalientes(nominalTrasladosSalientes);

		cierre.setPrecioTrasladoEntrantes(precioTrasladosEntrantes);
		cierre.setPrecioTrasladoSalientes(precioTrasladosSalientes);

		if (cierreAnterior.isPresent()) {
			cierre.setTrasladosAcumulados(
					cierreAnterior.get().getTrasladosAcumulados().add(cierre.getNominalTrasladosEntrantes()));
		} else {
			cierre.setTrasladosAcumulados(cierre.getNominalTrasladosEntrantes());
		}

		cierre.setValorGiroTrasladoSalientes(nominalTrasladosSalientes.multiply(precioTrasladosSalientes));
		cierre.setValorGiroTrasladoEntrantes(nominalTrasladosEntrantes.multiply(precioTrasladosEntrantes));

	}

	@Override
	public List<Cierre> obtenerCierresDia(LocalDate fechaCierre, Long idSucursal) {
		return cierreSpringRepository.obtenerlistaCierres(fechaCierre, idSucursal).stream()
				.map(cierreEntity -> getCierreConverter().convert(cierreEntity)).collect(Collectors.toList());
	}

	@Override
	public List<CierreConsolidado> obtenerCierreConsolidadoDia(LocalDate fechaCierre) {
		return cierreConsolidadoSpringRepository.obtenerlistaCierresConsolidados(fechaCierre).stream()
				.map(cierreEntity -> getCierreConsolidadoConverter().convert(cierreEntity))
				.collect(Collectors.toList());
	}

	public void realizarCierreConsolidado(LocalDate fecha) {

		List<CierreConsolidadoEntity> listaDeCierresConsolidados = new ArrayList<>();
		List<CierreEntity> listaDeCierresfx = new ArrayList<>();
		List<FxEntity> listaMonedas = fxSpringRepository.findAll();
		BigDecimal valorInicialCop = BigDecimal.ZERO;

		for (FxEntity fxEntity : listaMonedas) {
			if (!fxEntity.getCodigo().equals("COP")) {
				listaDeCierresfx = cierreSpringRepository.obtenerlistaCierresPorMonedayFecha(fxEntity.getId(), fecha);
				CierreConsolidadoEntity cierreConsolidadoEntity = new CierreConsolidadoEntity();

				cierreConsolidadoEntity.setFecha(fecha);
				cierreConsolidadoEntity.setMoneda(fxEntity);

				BigDecimal saldoInicial = BigDecimal.ZERO;
				BigDecimal costoPromedioInicial = BigDecimal.ZERO;
				BigDecimal valorCompraInicial = BigDecimal.ZERO;

				BigDecimal saldoCompras = BigDecimal.ZERO;
				BigDecimal costoPromedioCompras = BigDecimal.ZERO;
				BigDecimal valorCompras = BigDecimal.ZERO;

				BigDecimal saldoVentas = BigDecimal.ZERO;
				BigDecimal costoPromedioVentas = BigDecimal.ZERO;
				BigDecimal valorVentas = BigDecimal.ZERO;

				BigDecimal saldoFinal = BigDecimal.ZERO;
				BigDecimal costoPromedioFinal = BigDecimal.ZERO;
				BigDecimal valorCompraFinal = BigDecimal.ZERO;

				BigDecimal precioValoracion = BigDecimal.ZERO;
				BigDecimal precioValoracionAnterior = BigDecimal.ZERO;

				BigDecimal saldoPrecierre = BigDecimal.ZERO;
				BigDecimal valorGiroPrecierre = BigDecimal.ZERO;

				BigDecimal egresosDiarios = BigDecimal.ZERO;
				BigDecimal ingresosDiarios = BigDecimal.ZERO;

				BigDecimal egresosCop = BigDecimal.ZERO;

				for (CierreEntity cierreEntity : listaDeCierresfx) {

					saldoInicial = saldoInicial.add(cierreEntity.getSaldoInicial());
					valorCompraInicial = valorCompraInicial.add(cierreEntity.getValorCompraInicial());

					saldoCompras = saldoCompras.add(cierreEntity.getNominalCompra());
					valorCompras = valorCompras.add(cierreEntity.getValorCompra());

					saldoVentas = saldoVentas.add(cierreEntity.getNominalVenta());
					valorVentas = valorVentas.add(cierreEntity.getValorVenta());

					saldoFinal = saldoFinal.add(cierreEntity.getSaldoFinal());
					valorCompraFinal = valorCompraFinal.add(cierreEntity.getValorCompraFinal());

					precioValoracion = cierreEntity.getPrecioValoracion();
					precioValoracionAnterior = cierreEntity.getPrecioValoracionAnterior();

					saldoPrecierre = saldoPrecierre.add(cierreEntity.getSaldoPrecierre());
					valorGiroPrecierre = valorGiroPrecierre.add(cierreEntity.getValorGiroPrecierre());

					cierreEntity.setValoracionPrecierreAnterior(cierreEntity.getValoracionPrecierreAnterior());
					cierreEntity.setValoracionPrecierreActual(cierreEntity.getValoracionPrecierreActual());
 
					egresosDiarios = egresosDiarios.add(cierreEntity.getEgresosDiarios());
					ingresosDiarios = ingresosDiarios.add(cierreEntity.getIngresosDiarios());
					
					egresosCop = egresosCop.add(cierreEntity.getDiferenciaIngresosEgresos());

					cierreConsolidadoEntity.setPrecioPromedioFinal(cierreEntity.getPrecioPromedioFinal());

				}

				if (!(valorCompraInicial.compareTo(BigDecimal.ZERO) == 0)) {
					costoPromedioInicial = valorCompraInicial.divide(saldoInicial, 8, RoundingMode.HALF_UP);
				}

				if (!(valorCompras.compareTo(BigDecimal.ZERO) == 0)) {
					costoPromedioCompras = valorCompras.divide(saldoCompras, 8, RoundingMode.HALF_UP);
				}

				if (!(valorVentas.compareTo(BigDecimal.ZERO) == 0)) {
					costoPromedioVentas = valorVentas.divide(saldoVentas, 8, RoundingMode.HALF_UP);
				}

				if (!(valorCompraFinal.compareTo(BigDecimal.ZERO) == 0)) {
					costoPromedioFinal = valorCompraFinal.divide(saldoFinal, 8, RoundingMode.HALF_UP);
				}

				cierreConsolidadoEntity.setSaldoInicial(saldoInicial);
				cierreConsolidadoEntity.setPrecioPromedioInicial(costoPromedioInicial);
				cierreConsolidadoEntity.setValorCompraInicial(valorCompraInicial);

				cierreConsolidadoEntity.setNominalCompra(saldoCompras);
				cierreConsolidadoEntity.setPrecioCompra(costoPromedioCompras);
				cierreConsolidadoEntity.setValorCompra(valorCompras);

				cierreConsolidadoEntity.setNominalVenta(saldoVentas);
				cierreConsolidadoEntity.setPrecioVenta(costoPromedioVentas);
				cierreConsolidadoEntity.setValorVenta(valorVentas);

				cierreConsolidadoEntity.setSaldoFinal(saldoFinal);
				cierreConsolidadoEntity.setPrecioPromedioFinal(costoPromedioFinal);
				cierreConsolidadoEntity.setValorCompraFinal(valorCompraFinal);

				cierreConsolidadoEntity.setSaldoPrecierre(saldoPrecierre);
				cierreConsolidadoEntity.setValorGiroPrecierre(valorGiroPrecierre);

				cierreConsolidadoEntity.setPrecioValoracion(precioValoracion);
				cierreConsolidadoEntity.setPrecioValoracionAnterior(precioValoracionAnterior);

				cierreConsolidadoEntity.setDiferenciaIngresosEgresos(egresosCop);
				cierreConsolidadoEntity.setEgresosDiarios(egresosDiarios);
				cierreConsolidadoEntity.setIngresosDiarios(ingresosDiarios);

				BigDecimal margen = BigDecimal.ZERO;

				// si no se vendio nada el margen es 0
				if (cierreConsolidadoEntity.getPrecioVenta().compareTo(BigDecimal.ZERO) == 0) {
					margen = BigDecimal.ZERO;
				} else {
					margen = cierreConsolidadoEntity.getPrecioVenta()
							.subtract(cierreConsolidadoEntity.getPrecioPromedioFinal());
				}

				/** se calcula el marger */
				cierreConsolidadoEntity.setMargen(margen);

				/** se calcula la utilidad */
				cierreConsolidadoEntity.setUtilidadDiaria(margen.multiply(cierreConsolidadoEntity.getNominalVenta()));

				cierreConsolidadoEntity.setMargePrecioValoracionYPrecioPromedio(cierreConsolidadoEntity
						.getPrecioValoracion().subtract(cierreConsolidadoEntity.getPrecioPromedioFinal()));

				/**
				 * MODIFICACION 21 DE MAYO
				 */
				/** valoracion = saldoFinal * precioValoacion */
				cierreConsolidadoEntity.setValoracion(cierreConsolidadoEntity.getSaldoFinal()
						.multiply(cierreConsolidadoEntity.getPrecioValoracion()));
				cierreConsolidadoEntity
						.setPygValoracion(saldoFinal.multiply((precioValoracion.subtract(costoPromedioFinal))));
				cierreConsolidadoEntity.setValoracionPrecierreAnterior(cierreConsolidadoEntity.getSaldoInicial()
						.multiply(cierreConsolidadoEntity.getPrecioValoracionAnterior()));
				cierreConsolidadoEntity.setValoracionPrecierreActual(cierreConsolidadoEntity.getPrecioValoracion()
						.multiply(cierreConsolidadoEntity.getSaldoPrecierre()));
				cierreConsolidadoEntity.setPygValPrecierreDiaria((cierreConsolidadoEntity.getValoracionPrecierreActual()
						.subtract(cierreConsolidadoEntity.getValorGiroPrecierre())
						.subtract((cierreConsolidadoEntity.getValoracionPrecierreAnterior()
								.subtract(cierreConsolidadoEntity.getValorCompraInicial())))));
				cierreConsolidadoEntity.setPygValPrecierreAcumulada(cierreConsolidadoEntity
						.getValoracionPrecierreActual().subtract(cierreConsolidadoEntity.getValorCompraFinal()));

				// (valcierreaActual- valorGiroSaldoFinal ) - (valPrecierreActual -
				// saldoPrecierreValorGiro)
				cierreConsolidadoEntity.setPygValCierreDiaria((cierreConsolidadoEntity.getValoracion()
						.subtract(cierreConsolidadoEntity.getValorCompraFinal()))
								.subtract((cierreConsolidadoEntity.getValoracionPrecierreActual()
										.subtract(cierreConsolidadoEntity.getValorGiroPrecierre()))));

				// valoracion - valorgiroSaldoFinal
				cierreConsolidadoEntity.setPygValCierreAcumulada(cierreConsolidadoEntity.getValoracion()
						.subtract(cierreConsolidadoEntity.getValorCompraFinal()));

				// se suman todos los pyg = pygCaja+ pygPrecierre + pygTraslados+ pygcierre
				cierreConsolidadoEntity.setPygBruto(cierreConsolidadoEntity.getUtilidadDiaria()
						.add(cierreConsolidadoEntity.getPygValPrecierreDiaria()));

				
				//se agrega las variables mensuales
				List<CierreConsolidadoEntity> listaCierresMes = cierreConsolidadoSpringRepository.obtenerlistaCierresDelMesConDivisa(cierreConsolidadoEntity.getMoneda().getId(), cierreConsolidadoEntity.getFecha());
				
				BigDecimal pygValCierreMensual = BigDecimal.ZERO;
				BigDecimal pygValPrecierreMensual = BigDecimal.ZERO;
				
				for (CierreConsolidadoEntity cierreC : listaCierresMes) {
					
					pygValCierreMensual = pygValCierreMensual.add(cierreC.getPygValCierreDiaria());
					pygValPrecierreMensual = pygValPrecierreMensual.add(cierreC.getPygValPrecierreDiaria());
				}
				
				cierreConsolidadoEntity.setPygValCierreMensual(cierreConsolidadoEntity.getPygValCierreDiaria().add(pygValCierreMensual));
				cierreConsolidadoEntity.setPygValPrecierreMensual(cierreConsolidadoEntity.getPygValPrecierreDiaria().add(pygValPrecierreMensual));
				
				listaDeCierresConsolidados.add(cierreConsolidadoEntity);
			
			} else {
				listaDeCierresfx = cierreSpringRepository.obtenerlistaCierresPorMonedayFecha(fxEntity.getId(), fecha);

				for (CierreEntity cierreEntity : listaDeCierresfx) {
					valorInicialCop = valorInicialCop.add(cierreEntity.getSaldoInicial());
				}
			}

		}
		CierreConsolidadoEntity cierreConsolidadoEntityCop = new CierreConsolidadoEntity();
		cierreConsolidadoEntityCop.setFecha(fecha);
		FxEntity fxCop = fxSpringRepository.findByCodigo("COP").orElseThrow(NoFoundDataException::new);
		cierreConsolidadoEntityCop.setMoneda(fxCop);
		BigDecimal valorVentas = BigDecimal.ZERO;
		BigDecimal valorCompras = BigDecimal.ZERO;
		BigDecimal valorDiferenciaIngresosEgresos = BigDecimal.ZERO;

		for (CierreConsolidadoEntity cierreConsolidadoEntity : listaDeCierresConsolidados) {
			valorVentas = valorVentas.add(cierreConsolidadoEntity.getValorVenta());
			valorCompras = valorCompras.add(cierreConsolidadoEntity.getValorCompra());
			valorDiferenciaIngresosEgresos = cierreConsolidadoEntity.getDiferenciaIngresosEgresos();

		}

		
		cierreConsolidadoEntityCop.setPrecioCompra(BigDecimal.ONE);
		cierreConsolidadoEntityCop.setPrecioVenta(BigDecimal.ONE);

		cierreConsolidadoEntityCop.setValorCompra(valorCompras);
		cierreConsolidadoEntityCop.setValorVenta(valorVentas);

		cierreConsolidadoEntityCop.setNominalCompra(valorCompras);
		cierreConsolidadoEntityCop.setNominalVenta(valorVentas);

		cierreConsolidadoEntityCop.setDiferenciaIngresosEgresos(valorDiferenciaIngresosEgresos);
		cierreConsolidadoEntityCop.setSaldoInicial(valorInicialCop);
		cierreConsolidadoEntityCop.setSaldoFinal(cierreConsolidadoEntityCop.getSaldoInicial().add(valorVentas)
				.subtract(valorCompras).add(valorDiferenciaIngresosEgresos));
		cierreConsolidadoEntityCop.setPrecioPromedioFinal(BigDecimal.ONE);
		cierreConsolidadoEntityCop.setValorCompraFinal(cierreConsolidadoEntityCop.getSaldoFinal());

		listaDeCierresConsolidados.add(cierreConsolidadoEntityCop);
		listaDeCierresConsolidados.forEach(cierreConsolidadoEntity -> {
			cierreConsolidadoSpringRepository.saveAndFlush(cierreConsolidadoEntity);
		});

	}

	/**
	 * calcula el precio promedio final de compra consolidado ya que apartir de este
	 * dato se calcula lo demas
	 * 
	 * @param idFx
	 * @param fecha
	 * @return
	 */
	private BigDecimal calcularPrecioPromedioFinal(Long idFx, LocalDate fecha) {

		List<SucursalEntity> listaSucursales = sucursalSpringRepository.findAll();
		// lista donde se almcena los datos para luego sumarlos
		List<CierreEntity> listaDeCierresfx = new ArrayList<>();

		for (SucursalEntity sucursalEntity : listaSucursales) {
			CierreEntity cierreEntity = new CierreEntity();
			cierreEntity.setFecha(fecha);
			calcularDatosTotalesInicialesPorDivisaYSucursal(sucursalEntity.getCodigo(), idFx, cierreEntity);
			calcularDatosCompras(fecha, sucursalEntity.getId(), idFx, cierreEntity);
			listaDeCierresfx.add(cierreEntity);
		}

		/**
		 * valores acumulados
		 */
		BigDecimal valorCompraInicial = BigDecimal.ZERO;
		BigDecimal valorTotalOperacionesCompraDia = BigDecimal.ZERO;
		BigDecimal saldoInicial = BigDecimal.ZERO;
		BigDecimal nominalCompraDia = BigDecimal.ZERO;

		for (CierreEntity cierreEntity : listaDeCierresfx) {
			valorCompraInicial = valorCompraInicial.add(cierreEntity.getValorCompraInicial());
			valorTotalOperacionesCompraDia = valorTotalOperacionesCompraDia.add(cierreEntity.getValorCompra());
			saldoInicial = saldoInicial.add(cierreEntity.getSaldoInicial());
			nominalCompraDia = nominalCompraDia.add(cierreEntity.getNominalCompra());
		}

		/**
		 * calcula los totales finales del dia (precioProFinal =
		 * (valorCompraInicial+ValorOperacionesCompra) / saldoInicial+nominalComprasDia
		 * )
		 */
		return (valorCompraInicial.add(valorTotalOperacionesCompraDia)).divide((saldoInicial.add(nominalCompraDia)), 8,
				RoundingMode.HALF_UP);

	}

	@Override
	public List<ReportePortafolio> reportePortafolioPorFecha(LocalDate fecha) {
		List<CierreEntity> listaCierresDia = cierreSpringRepository.obtenerlistaCierresPorFecha(fecha);
		
		List<ReportePortafolio> listaResultado = new ArrayList<ReportePortafolio>();
		
		for (CierreEntity cierreEntity : listaCierresDia) {
			
			ReportePortafolio reportePortafolio = new ReportePortafolio();
			reportePortafolio.setFecha(fecha);
			reportePortafolio.setCodigoSucursal(cierreEntity.getSucursal().getCodigo());
			reportePortafolio.setCodigoMoneda(cierreEntity.getMoneda().getCodigo());
			reportePortafolio.setCostoPromedio(cierreEntity.getPrecioPromedioFinal());
			reportePortafolio.setNominal(cierreEntity.getSaldoFinal());
			reportePortafolio.setPrecioValoracion(cierreEntity.getPrecioValoracion());
			reportePortafolio.setValoracion(cierreEntity.getValoracion());
			reportePortafolio.setValorCompra(cierreEntity.getValorCompraFinal());
			
			listaResultado.add(reportePortafolio);
		}
		
		List<CierreConsolidadoEntity> listaCierresDiaConsolidado = cierreConsolidadoSpringRepository.obtenerlistaCierresConsolidados(fecha);
		
		for (CierreConsolidadoEntity cierreConsolidadoEntity : listaCierresDiaConsolidado) {
			
			ReportePortafolio reportePortafolio = new ReportePortafolio();
			reportePortafolio.setFecha(fecha);
			reportePortafolio.setCodigoSucursal("TOTAL");
			reportePortafolio.setCodigoMoneda(cierreConsolidadoEntity.getMoneda().getCodigo());
			reportePortafolio.setCostoPromedio(cierreConsolidadoEntity.getPrecioPromedioFinal());
			reportePortafolio.setNominal(cierreConsolidadoEntity.getSaldoFinal());
			reportePortafolio.setPrecioValoracion(cierreConsolidadoEntity.getPrecioValoracion());
			reportePortafolio.setValoracion(cierreConsolidadoEntity.getValoracion());
			reportePortafolio.setValorCompra(cierreConsolidadoEntity.getValorCompraFinal());
			
			listaResultado.add(reportePortafolio);
		}
		
		return listaResultado;
	}

	@Override
	public List<ReporteEstadoActual> reporteEstadoActual() {
		
		List<SucursalEntity> listaSucursales = sucursalSpringRepository.findAll();
		List<FxEntity> listaMonedas = fxSpringRepository.findAll();
		List<ReporteEstadoActual> listaResultado = new ArrayList<ReporteEstadoActual>();
		
	
		for (SucursalEntity sucursalEntity : listaSucursales) {

			
			for (FxEntity fxEntity : listaMonedas) {
			
				ReporteEstadoActual resultado = new ReporteEstadoActual();
				List<SaldoEntity> listaSaldosMoneda = saldoSpringRepository.saldosPorSucursalYMoneda(sucursalEntity.getId(), fxEntity.getId());
				
				resultado.setCodigoMoneda(fxEntity.getCodigo());
				resultado.setCodigoSucursal(sucursalEntity.getCodigo());
				resultado.setFecha(LocalDate.now());
				resultado.setNominal(BigDecimal.ZERO);
				
				
				for (SaldoEntity saldo : listaSaldosMoneda) {
					resultado.setNominal(resultado.getNominal().add(saldo.getNominal()));	
				}
				
				listaResultado.add(resultado);
				
				
			}
		}
				
		
		return listaResultado;
	}

	@Override
	public List<ReporteEstadoActual> reporteEstadoActualDetallado() {
		List<SucursalEntity> listaSucursales = sucursalSpringRepository.findAll();
		List<FxEntity> listaMonedas = fxSpringRepository.findAll();
		List<ReporteEstadoActual> listaResultado = new ArrayList<ReporteEstadoActual>();
		
		for (SucursalEntity sucursalEntity : listaSucursales) {

			for (FxEntity fxEntity : listaMonedas) {
			
				
				List<SaldoEntity> listaSaldosMoneda = saldoSpringRepository.saldosPorSucursalYMoneda(sucursalEntity.getId(), fxEntity.getId());
				
				for (SaldoEntity saldo : listaSaldosMoneda) {
					ReporteEstadoActual resultado = new ReporteEstadoActual();
					resultado.setCodigoMoneda(fxEntity.getCodigo());
					resultado.setCodigoSucursal(sucursalEntity.getCodigo()+"-"+saldo.getCaja().getNombre());
					resultado.setFecha(LocalDate.now());
					resultado.setNominal(saldo.getNominal());
					listaResultado.add(resultado);
				}
			}
		}
		
		return listaResultado;
	}

}
