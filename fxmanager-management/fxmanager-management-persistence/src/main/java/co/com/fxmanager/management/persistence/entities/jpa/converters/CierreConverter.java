package co.com.fxmanager.management.persistence.entities.jpa.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.management.domain.entities.Cierre;
import co.com.fxmanager.management.persistence.entities.jpa.CierreEntity;
import lombok.Getter;

@Component
@Getter
public class CierreConverter implements Converter<CierreEntity, Cierre> {

	@Autowired
	private SucursalConverter sucursalConverter;
	
	@Autowired
	private FxConverter fxConverter;
	
	@Override
	public Cierre convert(CierreEntity cierreEntity) {
		
		Cierre cierre = new Cierre();
		cierre.setDiferenciaEgresosIngresos(cierreEntity.getDiferenciaIngresosEgresos());
		cierre.setEgresosAcumulados(cierreEntity.getEgresosAcumulados());
		cierre.setFcAcumulado(cierreEntity.getFcAcumulado());
		cierre.setFcDiario(cierreEntity.getFcDiario());
		cierre.setFecha(cierreEntity.getFecha());
		cierre.setId(cierreEntity.getId());
		cierre.setMargen(cierreEntity.getMargen());
		cierre.setMargePrecioValoracionYPrecioPromedio(cierreEntity.getMargePrecioValoracionYPrecioPromedio());
		cierre.setMoneda(fxConverter.convert(cierreEntity.getMoneda()));
		cierre.setNominalCompra(cierreEntity.getNominalCompra());

		cierre.setNominalVenta(cierreEntity.getNominalVenta());
		cierre.setPrecioCompra(cierreEntity.getPrecioCompra());
		cierre.setPrecioPromedioFinal(cierreEntity.getPrecioPromedioFinal());
		cierre.setPrecioPromedioInicial(cierreEntity.getPrecioPromedioInicial());
	
		cierre.setPrecioValoracion(cierreEntity.getPrecioValoracion());
		cierre.setPrecioVenta(cierreEntity.getPrecioVenta());
		cierre.setPygBruto(cierreEntity.getPygBruto());
		cierre.setPygBrutoConTraslado(cierreEntity.getPygBrutoConTraslado());
		cierre.setPygCaja(cierreEntity.getPygCaja());
		cierre.setPygCajaD(cierreEntity.getPygCajaD());
		cierre.setPygNeto(cierreEntity.getPygNeto());
		cierre.setPygNetoConTraslado(cierreEntity.getPygNetoConTraslado());
		cierre.setPygTrasladoAcumulado(cierreEntity.getPygTrasladoAcumulado());
		cierre.setPygTrasladoDiario(cierreEntity.getPygTrasladoDiario());
		cierre.setPygValoracion(cierreEntity.getPygValoracion());
		cierre.setPygValoracionD(cierreEntity.getPygValoracionD());
		cierre.setRentaBruta(cierreEntity.getRentaBruta());
		cierre.setRentaCaja(cierreEntity.getRentaCaja());
		cierre.setRentaNeta(cierreEntity.getRentaNeta());
		cierre.setRentaVal(cierreEntity.getRentaVal());
		cierre.setSaldoCop(cierreEntity.getSaldoCop());
		cierre.setSaldoFinal(cierreEntity.getSaldoFinal());
		cierre.setSaldoInicial(cierreEntity.getSaldoInicial());
		cierre.setSucursal(sucursalConverter.convert(cierreEntity.getSucursal()));
		cierre.setUtilidadDiaria(cierreEntity.getUtilidadDiaria());
		cierre.setValoracion(cierreEntity.getValoracion());
		cierre.setValorCompra(cierreEntity.getValorCompra());
		cierre.setValorCompraFinal(cierreEntity.getValorCompraFinal());
		cierre.setValorCompraInicial(cierreEntity.getValorCompraInicial());

		cierre.setValorGiroTrasladoSalientes(cierreEntity.getValorGiroTrasladoSalientes());
		cierre.setValorGiroTrasladoEntrantes(cierreEntity.getValorGiroTrasladoEntrantes());
		cierre.setValorTraslados(cierreEntity.getValorTraslados());
		
		cierre.setNominalTrasladosEntrantes(cierreEntity.getNominalTrasladosEntrantes());
		cierre.setPrecioTrasladoEntrantes(cierreEntity.getPrecioTrasladoEntrantes());
		
		cierre.setNominalTrasladosSalientes(cierreEntity.getNominalTrasladosSalientes());
		cierre.setPrecioTrasladoSalientes(cierreEntity.getPrecioTrasladoSalientes());
		
		cierre.setTrasladosAcumulados(cierreEntity.getTrasladosAcumulados());
		
		
		cierre.setValorPort(cierreEntity.getValorPort());
		
		cierre.setValorVenta(cierreEntity.getValorVenta());
		
		cierre.setEgresosMoneda(cierreEntity.getEgresosMoneda());
		
		
		cierre.setSaldoPrecierre(cierreEntity.getSaldoPrecierre());
		cierre.setValorGiroPrecierre(cierreEntity.getValorGiroPrecierre());
		
		cierre.setPrecioValoracionAnterior(cierreEntity.getPrecioValoracionAnterior());
		cierre.setValoracionPrecierreAnterior(cierreEntity.getValoracionPrecierreAnterior());
		cierre.setValoracionPrecierreActual(cierreEntity.getValoracionPrecierreActual());
		
		cierre.setPygValPrecierreAcumulada(cierreEntity.getPygValPrecierreAcumulada());
		cierre.setPygValPrecierreDiaria(cierreEntity.getPygValPrecierreDiaria());
		
		cierre.setPygTrasladoEntrante(cierreEntity.getPygTrasladoEntrante());
		cierre.setPygTrasladoSaliente(cierreEntity.getPygTrasladoSaliente());
		
		cierre.setPygValCierreDiaria(cierreEntity.getPygValCierreDiaria());
		cierre.setPygValCierreAcumulada(cierreEntity.getPygValCierreAcumulada());
		
		cierre.setPygValCierreMensual(cierreEntity.getPygValCierreMensual());
		cierre.setPygValPrecierreMensual(cierreEntity.getPygValPrecierreMensual());
		
		cierre.setEgresosDiarios(cierreEntity.getEgresosDiarios());
		cierre.setIngresosDiarios(cierreEntity.getIngresosDiarios());
		
		
		return cierre;
		
	}

}
