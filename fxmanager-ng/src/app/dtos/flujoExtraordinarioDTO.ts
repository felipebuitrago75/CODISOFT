import { Fx } from "./fxDTO";
import { TurnoDTO } from './turnoDTO';
import { DenominacionCantidadDTO } from "./denominacionCantidadDTO";

export class FlujoExtraordinarioDTO {
	public id: number;
	public tipo: string;
	public valor: number;
	public idSoipc: string;
	public fx: Fx;
	public turno: TurnoDTO;
	public fecha: Date;
	public estado: string;
	public criterio: string;
	public descripcion: string;
	public denominacionesYCantidades: Array<DenominacionCantidadDTO> ;

	public naturaleza:string;
	public receptor:string;
	public cuenta:string;
	public concepto:string;
	
	constructor(){
		
	}
}