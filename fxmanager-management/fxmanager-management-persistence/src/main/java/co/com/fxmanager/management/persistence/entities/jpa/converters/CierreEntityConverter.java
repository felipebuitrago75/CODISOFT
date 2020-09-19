package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.Cierre;
import co.com.fxmanager.management.persistence.entities.jpa.CierreEntity;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.FxSpringRepository;
import co.com.fxmanager.management.persistence.repositories.springdata.SucursalSpringRepository;
import lombok.Getter;

@Getter
@Component
public class CierreEntityConverter implements EntityConverter<Cierre, CierreEntity> {

	@Autowired
	private SucursalSpringRepository sucursalSpringRepository;
	
	@Autowired
	private FxSpringRepository fxSpringRepository;
	
	@Override
	public CierreEntity convert(Cierre cierre) {
		return convert(cierre, CierreEntity::new);
	}

	@Override
	public CierreEntity convert(Cierre cierre, Supplier<CierreEntity> supplier) {
		CierreEntity cierreEntity = supplier.get();

		if(cierre.getId()!=null) {
			cierreEntity.setId(cierre.getId());
		}
		
		cierreEntity.setDiferenciaIngresosEgresos(cierre.getDiferenciaEgresosIngresos());
		cierreEntity.setEgresosAcumulados(cierre.getEgresosAcumulados());
		cierreEntity.setFcAcumulado(cierre.getFcAcumulado());
		cierreEntity.setFcDiario(cierre.getFcDiario());
		cierreEntity.setFecha(cierre.getFecha());
		cierreEntity.setMargen(cierre.getMargen());
		cierreEntity.setMargePrecioValoracionYPrecioPromedio(cierre.getMargePrecioValoracionYPrecioPromedio());
		cierreEntity.setNominalCompra(cierre.getNominalCompra());
		
		cierreEntity.setNominalVenta(cierre.getNominalVenta());
		cierreEntity.setPrecioCompra(cierre.getPrecioCompra());
		cierreEntity.setPrecioPromedioFinal(cierre.getPrecioPromedioFinal());
		cierreEntity.setPrecioPromedioInicial(cierre.getPrecioPromedioInicial());
		
		cierreEntity.setPrecioValoracion(cierre.getPrecioValoracion());
		cierreEntity.setPrecioVenta(cierre.getPrecioVenta());
		cierreEntity.setPygBruto(cierre.getPygBruto());
		cierreEntity.setPygBrutoConTraslado(cierre.getPygBrutoConTraslado());
		cierreEntity.setPygCaja(cierre.getPygCaja());
		cierreEntity.setPygNeto(cierre.getPygNeto());
		cierreEntity.setPygNetoConTraslado(cierre.getPygNetoConTraslado());
		cierreEntity.setPygTrasladoAcumulado(cierre.getPygTrasladoAcumulado());
		cierreEntity.setPygTrasladoDiario(cierre.getPygTrasladoDiario());
		cierreEntity.setPygValoracion(cierre.getPygValoracion());
		cierreEntity.setPygValoracionD(cierre.getPygValoracionD());
		cierreEntity.setRentaBruta(cierre.getRentaBruta());
		cierreEntity.setRentaCaja(cierre.getRentaCaja());
		cierreEntity.setRentaNeta(cierre.getRentaNeta());
		cierreEntity.setRentaVal(cierre.getRentaVal());
		cierreEntity.setSaldoCop(cierre.getSaldoCop());
		cierreEntity.setSaldoFinal(cierre.getSaldoFinal());
		cierreEntity.setSaldoInicial(cierre.getSaldoInicial());
		cierreEntity.setUtilidadDiaria(cierre.getUtilidadDiaria());
		cierreEntity.setValoracion(cierre.getValoracion());
		cierreEntity.setValorCompra(cierre.getValorCompra());
		cierreEntity.setValorCompraFinal(cierre.getValorCompraFinal());
		cierreEntity.setValorCompraInicial(cierre.getValorCompraInicial());
		
		cierreEntity.setValorGiroTrasladoSalientes(cierre.getValorGiroTrasladoSalientes());
		cierreEntity.setValorGiroTrasladoEntrantes(cierre.getValorGiroTrasladoEntrantes());
		cierreEntity.setValorTraslados(cierre.getValorTraslados());
		
		cierreEntity.setNominalTrasladosEntrantes(cierre.getNominalTrasladosEntrantes());
		cierreEntity.setPrecioTrasladoEntrantes(cierre.getPrecioTrasladoEntrantes());
		
		cierreEntity.setNominalTrasladosSalientes(cierre.getNominalTrasladosSalientes());
		cierreEntity.setPrecioTrasladoSalientes(cierre.getPrecioTrasladoSalientes());
		
		cierreEntity.setTrasladosAcumulados(cierre.getTrasladosAcumulados());
		
		cierreEntity.setValorPort(cierre.getValorPort());
		cierreEntity.setValorVenta(cierre.getValorVenta());
		
		cierreEntity.setEgresosMoneda(cierre.getEgresosMoneda());
		
		cierreEntity.setSucursal(sucursalSpringRepository.findByCodigo(cierre.getSucursal().getCod())
				.orElseThrow(NoFoundDataException::new));
		
		cierreEntity.setMoneda(fxSpringRepository.findByCodigo(cierre.getMoneda().getCodigo())
				.orElseThrow(NoFoundDataException::new));
		
		cierreEntity.setSaldoPrecierre(cierre.getSaldoPrecierre());
		cierreEntity.setValorGiroPrecierre(cierre.getValorGiroPrecierre());
		cierreEntity.setPrecioValoracionAnterior(cierre.getPrecioValoracionAnterior());
		
		cierreEntity.setValoracionPrecierreAnterior(cierre.getValoracionPrecierreAnterior());
		cierreEntity.setValoracionPrecierreActual(cierre.getValoracionPrecierreActual());
		
		cierreEntity.setPygValPrecierreAcumulada(cierre.getPygValPrecierreAcumulada());
		cierreEntity.setPygValPrecierreDiaria(cierre.getPygValPrecierreDiaria());
		
		cierreEntity.setPygTrasladoEntrante(cierre.getPygTrasladoEntrante());
		cierreEntity.setPygTrasladoSaliente(cierre.getPygTrasladoSaliente());
		
		cierreEntity.setPygValCierreDiaria(cierre.getPygValCierreDiaria());
		cierreEntity.setPygValCierreAcumulada(cierre.getPygValCierreAcumulada());
		
		cierreEntity.setPygValCierreMensual(cierre.getPygValCierreMensual());
		cierreEntity.setPygValPrecierreMensual(cierre.getPygValPrecierreMensual());
		
		cierreEntity.setEgresosDiarios(cierre.getEgresosDiarios());
		cierreEntity.setIngresosDiarios(cierre.getIngresosDiarios());
		
		return cierreEntity;
	}

}
