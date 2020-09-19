import { saldoDTO } from './saldoDTO';

export class cajaDTO {
	public id: number;
	public nombre: string;
	public codigoSucursal : string;
	public listaSaldos:  Array<saldoDTO> ;

	constructor(){
		
	}
}
