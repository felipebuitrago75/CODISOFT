export class Fx {
	public id : number;
	public codigo:String ;
	public concepto:String ;


	public denominaciones: Array<number> ;

	public Fx( codigo:String , concepto:String ) {
		this.codigo = codigo;
		this.concepto = concepto;
		this.denominaciones = new Array<number>();

	}
	constructor(){
		
	}
}
