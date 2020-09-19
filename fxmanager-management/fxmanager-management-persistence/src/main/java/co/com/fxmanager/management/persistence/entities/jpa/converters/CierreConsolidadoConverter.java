package co.com.fxmanager.management.persistence.entities.jpa.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.CierreConsolidado;
import co.com.fxmanager.management.persistence.entities.jpa.CierreConsolidadoEntity;
import lombok.Getter;

@Component
@Getter
public class CierreConsolidadoConverter implements Converter<CierreConsolidadoEntity, CierreConsolidado> {

	
	@Autowired
	private FxConverter fxConverter;
	
	@Override
	public CierreConsolidado convert(CierreConsolidadoEntity cierreConsolidadoEntity) {
		
		CierreConsolidado cierreConsolidado = new CierreConsolidado();
		cierreConsolidado.setDiferenciaEgresosIngresos(cierreConsolidadoEntity.getDiferenciaIngresosEgresos());
		cierreConsolidado.setEgresosAcumulados(cierreConsolidadoEntity.getEgresosAcumulados());
		cierreConsolidado.setFcAcumulado(cierreConsolidadoEntity.getFcAcumulado());
		cierreConsolidado.setFcDiario(cierreConsolidadoEntity.getFcDiario());
		cierreConsolidado.setFecha(cierreConsolidadoEntity.getFecha());
		cierreConsolidado.setId(cierreConsolidadoEntity.getId());
		cierreConsolidado.setMargen(cierreConsolidadoEntity.getMargen());
		cierreConsolidado.setMargePrecioValoracionYPrecioPromedio(cierreConsolidadoEntity.getMargePrecioValoracionYPrecioPromedio());
		cierreConsolidado.setMoneda(fxConverter.convert(cierreConsolidadoEntity.getMoneda()));
		cierreConsolidado.setNominalCompra(cierreConsolidadoEntity.getNominalCompra());

		cierreConsolidado.setNominalVenta(cierreConsolidadoEntity.getNominalVenta());
		cierreConsolidado.setPrecioCompra(cierreConsolidadoEntity.getPrecioCompra());
		cierreConsolidado.setPrecioPromedioFinal(cierreConsolidadoEntity.getPrecioPromedioFinal());
		cierreConsolidado.setPrecioPromedioInicial(cierreConsolidadoEntity.getPrecioPromedioInicial());
	
		cierreConsolidado.setPrecioValoracion(cierreConsolidadoEntity.getPrecioValoracion());
		cierreConsolidado.setPrecioVenta(cierreConsolidadoEntity.getPrecioVenta());
		cierreConsolidado.setPygBruto(cierreConsolidadoEntity.getPygBruto());
		cierreConsolidado.setPygBrutoConTraslado(cierreConsolidadoEntity.getPygBrutoConTraslado());
		cierreConsolidado.setPygCaja(cierreConsolidadoEntity.getPygCaja());
		cierreConsolidado.setPygCajaD(cierreConsolidadoEntity.getPygCajaD());
		cierreConsolidado.setPygNeto(cierreConsolidadoEntity.getPygNeto());
		cierreConsolidado.setPygNetoConTraslado(cierreConsolidadoEntity.getPygNetoConTraslado());
		cierreConsolidado.setPygTrasladoAcumulado(cierreConsolidadoEntity.getPygTrasladoAcumulado());
		cierreConsolidado.setPygTrasladoDiario(cierreConsolidadoEntity.getPygTrasladoDiario());
		cierreConsolidado.setPygValoracion(cierreConsolidadoEntity.getPygValoracion());
		cierreConsolidado.setPygValoracionD(cierreConsolidadoEntity.getPygValoracionD());
		cierreConsolidado.setRentaBruta(cierreConsolidadoEntity.getRentaBruta());
		cierreConsolidado.setRentaCaja(cierreConsolidadoEntity.getRentaCaja());
		cierreConsolidado.setRentaNeta(cierreConsolidadoEntity.getRentaNeta());
		cierreConsolidado.setRentaVal(cierreConsolidadoEntity.getRentaVal());
		cierreConsolidado.setSaldoCop(cierreConsolidadoEntity.getSaldoCop());
		cierreConsolidado.setSaldoFinal(cierreConsolidadoEntity.getSaldoFinal());
		cierreConsolidado.setSaldoInicial(cierreConsolidadoEntity.getSaldoInicial());
		cierreConsolidado.setUtilidadDiaria(cierreConsolidadoEntity.getUtilidadDiaria());
		cierreConsolidado.setValoracion(cierreConsolidadoEntity.getValoracion());
		cierreConsolidado.setValorCompra(cierreConsolidadoEntity.getValorCompra());
		cierreConsolidado.setValorCompraFinal(cierreConsolidadoEntity.getValorCompraFinal());
		cierreConsolidado.setValorCompraInicial(cierreConsolidadoEntity.getValorCompraInicial());

		cierreConsolidado.setValorGiroTrasladoSalientes(cierreConsolidadoEntity.getValorGiroTrasladoSalientes());
		cierreConsolidado.setValorGiroTrasladoEntrantes(cierreConsolidadoEntity.getValorGiroTrasladoEntrantes());
		cierreConsolidado.setValorTraslados(cierreConsolidadoEntity.getValorTraslados());
		
		cierreConsolidado.setNominalTrasladosEntrantes(cierreConsolidadoEntity.getNominalTrasladosEntrantes());
		cierreConsolidado.setPrecioTrasladoEntrantes(cierreConsolidadoEntity.getPrecioTrasladoEntrantes());
		
		cierreConsolidado.setNominalTrasladosSalientes(cierreConsolidadoEntity.getNominalTrasladosSalientes());
		cierreConsolidado.setPrecioTrasladoSalientes(cierreConsolidadoEntity.getPrecioTrasladoSalientes());
		
		cierreConsolidado.setTrasladosAcumulados(cierreConsolidadoEntity.getTrasladosAcumulados());
		
		
		cierreConsolidado.setValorPort(cierreConsolidadoEntity.getValorPort());
		
		cierreConsolidado.setValorVenta(cierreConsolidadoEntity.getValorVenta());
		
		cierreConsolidado.setEgresosMoneda(cierreConsolidadoEntity.getEgresosMoneda());
		
		cierreConsolidado.setSaldoPrecierre(cierreConsolidadoEntity.getSaldoPrecierre());
		cierreConsolidado.setValorGiroPrecierre(cierreConsolidadoEntity.getValorGiroPrecierre());
		cierreConsolidado.setPrecioValoracionAnterior(cierreConsolidadoEntity.getPrecioValoracionAnterior());
		
		cierreConsolidado.setValoracionPrecierreAnterior(cierreConsolidadoEntity.getValoracionPrecierreAnterior());
		cierreConsolidado.setValoracionPrecierreActual(cierreConsolidadoEntity.getValoracionPrecierreActual());
		
		cierreConsolidado.setPygValPrecierreAcumulada(cierreConsolidadoEntity.getPygValPrecierreAcumulada());
		cierreConsolidado.setPygValPrecierreDiaria(cierreConsolidadoEntity.getPygValPrecierreDiaria());
		
		cierreConsolidado.setPygTrasladoEntrante(cierreConsolidadoEntity.getPygTrasladoEntrante());
		cierreConsolidado.setPygTrasladoSaliente(cierreConsolidadoEntity.getPygTrasladoSaliente());
		
		cierreConsolidado.setPygValCierreDiaria(cierreConsolidadoEntity.getPygValCierreDiaria());
		cierreConsolidado.setPygValCierreAcumulada(cierreConsolidadoEntity.getPygValCierreAcumulada());
		
		cierreConsolidado.setPygValCierreMensual(cierreConsolidadoEntity.getPygValCierreMensual());
		cierreConsolidado.setPygValPrecierreMensual(cierreConsolidadoEntity.getPygValPrecierreMensual());
		
		cierreConsolidado.setEgresosDiarios(cierreConsolidadoEntity.getEgresosDiarios());
		cierreConsolidado.setIngresosDiarios(cierreConsolidadoEntity.getIngresosDiarios());
		
		return cierreConsolidado;
		
	}

}
