import { SucursalDTO } from "./sucursalDTO";
import { Fx } from "./fxDTO";
import { TurnoDTO } from "./turnoDTO"
import { DenominacionCantidadDTO } from "./denominacionCantidadDTO";

export class FondeoDTO {
	public id:number;
	public fecha:Date;
	public turnoOrigen:TurnoDTO ;
	public turnoDestino:TurnoDTO;
	public estado:string;
	public valorGiro:number;
	public fx:Fx;
	public denominacionesYCantidades: Array<DenominacionCantidadDTO> ;

	constructor(){
		
	}
}




