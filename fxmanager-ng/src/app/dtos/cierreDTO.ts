import { SucursalDTO } from './sucursalDTO';
import { Fx } from './fxDTO';

export class cierreDTO {
	
	public id:number;
	
	public fecha:Date;
	
	public sucursal:SucursalDTO;
	
	public saldoInicial: number;
	
	public precioPromedioInicial: number;
	
	public valorCompraInicial: number;
	
	public nominalCompra: number;
	
	public precioCompra: number;
	
	public valorCompra: number;
	
	public nominalVenta: number;
	
	public precioVenta: number;
	
	public valorVenta: number;
	
	public nominalTrasladosEntrantes: number;
	
	public precioTrasladoEntrantes: number;

	public nominalTrasladosSalientes: number;
	
	public precioTrasladoSalientes: number;
	
	public saldoFinal: number;
	
	public precioPromedioFinal: number;
	
	public valorCompraFinal: number;
	
	public margen: number;
	
	public utilidadDiaria: number;
	
	public pygCaja: number;
	
	public pygCajaD: number;
	
	public precioValoracion: number;
	
	public margePrecioValoracionYPrecioPromedio: number;

	public valoracion: number;
	
	public pygValoracion: number;
	
	public pygValoracionD: number;
	
	public pygBruto: number;
	
	public rentaCaja: number;
	
	public rentaVal: number;
	
	public rentaBruta: number;
	
	public egresos: number;

	public ingresos: number;
	
	public egresosAcumulados: number;
	
	public pygNeto: number;
	
	public rentaNeta: number;
	
	public saldoCop: number;
	
	public valorGiroTrasladoSalientes: number;

	public valorGiroTrasladoEntrantes: number;
	
	public valorTraslados: number;

	public trasladosAcumulados:number;
	
	public pygTrasladoDiario: number;
	
	public pygTrasladoAcumulado: number;
	
	public pygBrutoConTraslado: number;
	
	public pygNetoConTraslado: number;
	
	public fcDiario: number;
	
	public fcAcumulado: number;
	
	public valorPort: number;
	
	public moneda:Fx;

	public egresosMoneda: number;


	public  saldoPrecierre: number;
	
	public  valorGiroPrecierre: number;
	
	public  precioValoracionAnterior: number;
	
	public  valoracionPrecierreAnterior: number;
	
	public  valoracionPrecierreActual: number;
	
	public  pygValPrecierreMensual: number;
	
	public  pygValPrecierreDiaria: number;
	
	public  pygTrasladoEntrante: number;
	
	public  pygTrasladoSaliente: number;
	
	public  pygValCierreDiaria: number;
	
	public  pygValCierreMensual: number;

	public pygValPrecierreAcumulada: number;

	public pygValCierreAcumulada: number;

	public egresosDiarios: number;
	
	public  ingresosDiarios : number;

	constructor(){
		
	}
}
