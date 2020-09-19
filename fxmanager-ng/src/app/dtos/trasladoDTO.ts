import { SucursalDTO } from "./sucursalDTO";
import { Fx } from "./fxDTO";
import { TurnoDTO } from "./turnoDTO";
import { DenominacionCantidadDTO } from "./denominacionCantidadDTO";

export class TrasladoDTO {
	public id:number;
	public fecha:Date;
	public sucursalOrigen:SucursalDTO;
	public sucursalDestino:SucursalDTO;
	public tipoTraslado: string;
	public turnoOrigen:TurnoDTO;
	public turnoDestino:TurnoDTO;
	public estado:string;
	public precioTraslado:number;
	public valorGiro:number;
	public fx:Fx;
	public denominacionesYCantidades: Array<DenominacionCantidadDTO> ;

	constructor(){
		
	}
}




