import { Component, OnInit } from '@angular/core';
import { BaseCrudFormComponent } from '../../../components/base-crud-form.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../../services/fx-service';
import { SucursalService } from '../../../services/sucursal-service';


import { DenominacionDTO } from '../../../dtos/denominacionDTO';

import { Fx } from '../../../dtos/fxDTO';
import { SucursalDTO } from '../../../dtos/sucursalDTO';
import { TrasladoDTO } from '../../../dtos/trasladoDTO';
import { TrasladoService } from '../../../services/traslado-service';
import { TurnoService } from '../../../services/turno-service';
import { TurnoDTO } from '../../../dtos/turnoDTO';
import { cajaDTO } from '../../../dtos/cajaDTO';
import { CajaService } from '../../../services/caja-service';
import { DenominacionCantidadDTO } from '../../../dtos/denominacionCantidadDTO';


@Component({
  templateUrl: 'registro-translado.component.html'
})
export class RegistroTransladoComponent extends BaseCrudFormComponent<any> implements OnInit {


  
  public listaSucursalesDestino: Array<SucursalDTO> = [];
  public listaTipos: Array<any> = [];
  public listaEstados: Array<any> = [];
  public traslado: TrasladoDTO=new TrasladoDTO();
  public listaFxs: Array<any> = [];
  public sucursalOrigen: SucursalDTO=new SucursalDTO();
  public turno: TurnoDTO= null;
  public cajaActual: cajaDTO = null;
  public mostrarArqueo= false;
  public activarCampo = false;
  public activarCampoTipoTraslado = false;

  constructor(protected _fxService: FxService, protected router: Router, protected route: ActivatedRoute,
    protected location: Location, protected messageService: MessageService,
    protected confirmationService: ConfirmationService,
    protected _sucursalService: SucursalService,
    protected _trasladoService: TrasladoService,
    protected _turnoService: TurnoService, 
    protected _cajaService: CajaService,
    ) {
    super(router, route, location, messageService, confirmationService);

    this.listaTipos.push({ label: 'Seleccione', value: null });
    this.listaTipos.push({ label: 'PRECIO VALORACION', value: 'PRECIO VALORACION' });
    this.listaTipos.push({ label: 'COSTO PROMEDIO', value: 'COSTO PROMEDIO' });
    this.listaTipos.push({ label: 'PRECIO ESPECIFICO', value: 'PRECIO ESPECIFICO' });

    this.listaEstados.push({ label: 'Seleccione', value: null });
    this.listaEstados.push({ label: 'EN PROCESO', value: 'EN PROCESO' });
    this.listaEstados.push({ label: 'EJECUTADO', value: 'EJECUTADO' });
    this.listaEstados.push({ label: 'CANCELADO', value: 'CANCELADO' });

    this.cargarSucursales();
    this.cargarMonedas();

  }

  cargarSucursales(){
    let sucursalDefault: SucursalDTO = new SucursalDTO();
    sucursalDefault.nombre="Seleccione";
    sucursalDefault.cod=null;
   
    this.listaSucursalesDestino.push(sucursalDefault);
    this._sucursalService.getSucursales(0,20).subscribe(data =>{
      this.listaSucursalesDestino= this.listaSucursalesDestino.concat(...data);
    });
  }

  cargarSucursalOrigen(){
    this._sucursalService.getSucursal(this.cajaActual.codigoSucursal).subscribe(data =>{
      this.sucursalOrigen=data;
    });
  }

  cargarMonedas() {
    this._fxService.getFxs(0,20).subscribe(data=>{    
      this.listaFxs = new Array<any>();
      this.listaFxs.push({ '':'' });
      data.forEach(element => {
        //if(element.codigo == 'COP' ){
        //}else{
          this.listaFxs.push(element);
        //}
      });
    });
  }

  ngOnInit() {
    super.ngOnInit();
    this.turno = this._turnoService.getTurno();
    if (this.turno === null || this.turno === undefined) {
      this.showMessage(this.getMsg('administration.operacion.save.error.turno'), 'error', '');
    }else{
      this._cajaService.getCaja(this.turno.idCaja.toString()).subscribe(data=>{
        this.cajaActual=data;
        this.cargarSucursalOrigen();
      });
      
    }
  }

  //como se llama el atributo id
  protected getIdParamName(): string {
    return "id";
  }

  //llama el servicio y obtiene el role y lo carga al bean
  protected loadBean(cod: any) {
  }

  //valida el bean antes de enviarlo
  public validate(): boolean {

    if (!this.traslado.fx || !this.traslado.fx.concepto) {
      this.showMessage(this.getMsg('error'), 'error', "Moneda es requerido");
      return false;
    }
    if (!this.traslado.precioTraslado) {
      this.showMessage(this.getMsg('error'), 'error', "Precio traslado es requerido");
      return false;
    }
    if (!this.sucursalOrigen || !this.sucursalOrigen.cod) {
      this.showMessage(this.getMsg('error'), 'error', "Sucursal origen es requerido");
      return false;
    }
    if (!this.traslado.sucursalDestino || !this.traslado.sucursalDestino.cod) {
      this.showMessage(this.getMsg('error'), 'error', "Sucursal destino es requerido");
      return false;
    }
    if (this.sucursalOrigen.cod == this.traslado.sucursalDestino.cod) {
      this.showMessage(this.getMsg('error'), 'error', "Las sucursales destino debe ser diferente a sucursal origen");
      return false;
    }
    if (!this.traslado.tipoTraslado) {
      this.showMessage(this.getMsg('error'), 'error', "Tipo traslado es requerido");
      return false;
    }
    if (!this.traslado.valorGiro) {
      this.showMessage(this.getMsg('error'), 'error', "Valor giro es requerido");
      return false;
    }

    this.save();
    return true;
  }

  protected save() {
    this.traslado.estado="EN PROCESO";
    this.traslado.turnoOrigen= this.turno;
    this.traslado.sucursalOrigen=this.sucursalOrigen;
    this.confirmationService.confirm({
      message: this.getMsg('administration.traslado.confirm.titulo'),
      accept: () => {
        this._trasladoService.create(this.traslado).subscribe(data => {
          this.showMessage(this.getMsg('exito'), 'success', this.getMsg('administration.traslado.save.exito'));
          this.router.navigate([""]);
        });
      }
    });
    this.clean();
  }

  public clean() {
  }

  protected update(cod: any, bean: any) {
  }

  mostrarArqueoFuncion(){
    if(this.traslado.fx){
      this.mostrarArqueo=true;
    }
    
  }

  public recibirValor(event: any){
    console.log(event);
    let denominacionesYCantidades: Array<DenominacionCantidadDTO> = event.denominaciones.map(denominacion=>{
      let denominacionCantidadDTO: DenominacionCantidadDTO = new DenominacionCantidadDTO(denominacion.valor,denominacion.cantidad,event.idFx);
      return denominacionCantidadDTO;
    });
    this.traslado.denominacionesYCantidades=denominacionesYCantidades;
    this.traslado.valorGiro=event.total;
    this.mostrarArqueo=false;
  }

  
  public cambioTipo(event:any){
    console.log(event.value);
    if(this.traslado.fx){
      this.activarCampoTipoTraslado=true;
    }else{
      this.activarCampoTipoTraslado=false;
    }

    if(event.value=="PRECIO ESPECIFICO"){
      this.activarCampo=true;
    }else{
      if(event.value=="PRECIO VALORACION" ||  this.traslado.tipoTraslado=="PRECIO VALORACION"  ){
  
        var moneda;
        
        this.sucursalOrigen.listaPrecios.forEach(element => {
          if(element.fx.codigo==this.traslado.fx.codigo){
            moneda=element;
          }
        });
        if(!moneda){
          this.traslado.precioTraslado=1;
        }else{
          this.traslado.precioTraslado=moneda.precioValoracion;
        }
        
      }
      this.activarCampo=false;
    }
  }
}