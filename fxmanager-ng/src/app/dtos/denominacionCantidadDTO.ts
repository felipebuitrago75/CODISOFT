export class DenominacionCantidadDTO {
	public valor:number ;
	public cantidad:number ;
	public idFx:number ;

	constructor(valor:number, cantidad:number, idFx:number){
		this.valor=valor;
		this.cantidad=cantidad;
		this.idFx=idFx;
	}

}
