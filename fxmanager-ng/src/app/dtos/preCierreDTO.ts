import { preCierreItemDTO } from './preCierreItemDTO';
import { cajaDTO } from './cajaDTO';
import { TurnoDTO } from './turnoDTO';

export class preCierreDTO {
	
	public id:number;
	
	public caja: cajaDTO;
	
	public usuario: any;
	
	public fecha:Date;

	public turno:TurnoDTO;

	public  listaItems:Array<preCierreItemDTO>;
	
	constructor(){
		
	}
}
