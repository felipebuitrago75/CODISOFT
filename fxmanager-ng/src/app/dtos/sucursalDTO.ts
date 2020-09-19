import { fxSucursalDTO } from "./fxSucursal";
import { cajaDTO } from "./cajaDTO";

export class SucursalDTO {

	public cod: string
	public nombre: string
	public direccion: string
	public estado: string
	public chkPrincipal:boolean;
	public telefonos: string;
	public listaPrecios: Array<fxSucursalDTO> ;


	constructor(){
		
	}
}




