import { Fx } from './fxDTO';

export class preCierreItemDTO {
	
	public id:number;
	
	public moneda:Fx;

	public saldosInicial:number;
	
	public saldosFinal:number;

	public saldosRealEnCaja:number;
	
	public nominalCompras:number;
	
	public nominalVentas:number;
	
	public nominalEgresos:number;
	
	public nominalIngresos:number;
	
	public nominalTrasladosSalientes:number;
	
	public nominalTrasladosEntrantes:number;
	
	constructor(){
		
	}
}
