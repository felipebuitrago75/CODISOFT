import { Fx } from "./fxDTO";
import { DenominacionCantidadDTO } from "./denominacionCantidadDTO";

export class saldoDTO {
	public id: number;
	public moneda: Fx;
	public nominal: number;
	public precioPromedio:number;
	public denominacionesYCantidades: Array<DenominacionCantidadDTO> ;

	constructor(){
		
	}
}
