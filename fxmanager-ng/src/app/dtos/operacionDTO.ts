import { ParFxDTO } from './parFxDTO';
import { TurnoDTO } from './turnoDTO';
import { DenominacionCantidadDTO } from './denominacionCantidadDTO';


export class OperacionDTO {
	public id: number;
	public fechaOperacion:Date;
	public tipo: string;
	public nominal: number;
	public estado: string;
	public idSoipc: string;
	public parFx: ParFxDTO;
	public descripcion: string;
	public turno: TurnoDTO;
	public turnoEdicion: TurnoDTO;

	public denominacionesYCantidadesEntregadas: Array<DenominacionCantidadDTO> ;
	
	public denominacionesYCantidadesRecibidas:  Array<DenominacionCantidadDTO> ;
	constructor(){
		
	}


	
}




