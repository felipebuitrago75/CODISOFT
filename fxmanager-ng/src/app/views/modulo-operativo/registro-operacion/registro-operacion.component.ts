import { Component, OnInit ,ViewChild} from '@angular/core';
import { BaseCrudFormComponent } from '../../../components/base-crud-form.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../../services/fx-service';
import { SucursalService } from '../../../services/sucursal-service';
import { OperacionService } from '../../../services/operacion-service';
import { DenominacionDTO } from '../../../dtos/denominacionDTO';
import { OperacionDTO } from '../../../dtos/operacionDTO';
import { ParFxDTO } from '../../../dtos/parFxDTO';
import { Fx } from '../../../dtos/fxDTO';
import { TurnoDTO } from '../../../dtos/turnoDTO';
import { TurnoService } from '../../../services/turno-service';
import { DenominacionCantidadDTO } from '../../../dtos/denominacionCantidadDTO';

import { DarComponent  } from '../dar-denominacion/dar.component';
import { RecibirComponent  } from '../recibir-denominacion/recibir.component';
import { CajaService } from '../../../services/caja-service';
import { cajaDTO } from '../../../dtos/cajaDTO';
import { SucursalDTO } from '../../../dtos/sucursalDTO';
@Component({
  templateUrl: 'registro-operacion.component.html'
})
export class RegistroOperacionesComponent extends BaseCrudFormComponent<any> implements OnInit {

  @ViewChild(DarComponent) darComponent: DarComponent;
  @ViewChild(RecibirComponent) recibirComponent: RecibirComponent;

  public fx: string = "";
  public listaFxs: Array<any> = [];

  public valorTotal: any = 0;

  public operacion: OperacionDTO = new OperacionDTO();

  public descripcion: string;
  public moneda: any;
  public checked: boolean;
  public idSoipc: any;
  public nominal: number;
  public precio: number;
  public valorGiro: number;
  public precioValoracion: number;
  public estado: string;
  public mostrarRecibir: boolean = false;
  public mostrarDar: boolean = false;
  public fechaOperacion: Date;
  public denominacionesRecibidas: DenominacionCantidadDTO[] = [];
  public denominacionesDadas: DenominacionCantidadDTO[] = [];
  public turno: TurnoDTO;
  public bloquear:boolean=false;
  public listaPreciosSucursal: Array<any> = [];
  public caja: cajaDTO = new cajaDTO();
  public sucursal: SucursalDTO = new SucursalDTO();
  public esCompra: boolean = true;

  constructor(protected _fxService: FxService, protected router: Router, protected route: ActivatedRoute,
    protected location: Location, protected messageService: MessageService,
    protected confirmationService: ConfirmationService,
    protected _sucursalService: SucursalService,
    protected _operacionService: OperacionService,
    protected _cajaService: CajaService,
    protected _turnoService: TurnoService) {
    super(router, route, location, messageService, confirmationService);

    this.checked = false;
    this.cargarListaPrecios();
  }

  cargarListaPrecios() {
    this._fxService.getFxs(0, 20).subscribe(data => {
      this.listaFxs = new Array<any>();
      this.listaFxs.push({ '': '' })
      data.forEach(element => {
        if (element.codigo == 'COP') {
        } else {
          this.listaFxs.push(element);
        }
      });


      if (this.turno) {
        this._cajaService.getCaja(this.turno.idCaja.toString()).subscribe(data => {
          this.caja = data;
          this._sucursalService.getSucursal(this.caja.codigoSucursal).subscribe(data => {
            this.sucursal = data;
            this.listaPreciosSucursal = data.listaPrecios;
            /**
             * 
            //se obtiene el id del turno
            this._turnoService.getTurnoObservable(this.nombreUsuario).subscribe(turno =>{
              if(turno.id){
                this.turno.id=turno.id;
              }
            });
            */
          });
        });
      }
    });
  }


  ngOnInit() {
    super.ngOnInit();
    this.turno = this._turnoService.getTurno();
    if (this.turno === null || this.turno === undefined) {
      this.showMessage(this.getMsg('administration.operacion.save.error.turno'), 'error', '');
      this.bloquear=true;
      
    }else{
      this.bloquear=false;
    }
  }

  //como se llama el atributo id
  protected getIdParamName(): string {
    return "id";
  }

  //llama el servicio y obtiene el role y lo carga al bean
  protected loadBean(id: any) {

  }

  //valida el bean antes de enviarlo
  protected validate(): boolean {

    return true;
  }

  protected save() {

    this.operacion.estado = this.estado;
    this.operacion.fechaOperacion = new Date();
    this.operacion.descripcion = this.descripcion;
    let parFx: ParFxDTO = new ParFxDTO();

    let fxBase: Fx = new Fx();
    fxBase.codigo = "COP";

    let fxOper: Fx = new Fx();
    fxOper.codigo = this.fx;

    parFx.fxBase = fxBase;
    parFx.fxOper = fxOper;
    parFx.valorFxOperacion = this.precio;
    parFx.valorValoracion = this.precioValoracion;

    this.operacion.idSoipc = this.idSoipc;
    this.operacion.nominal = this.nominal;
    this.operacion.tipo = this.esCompra == true ? "COMPRA" : "VENTA";
    this.operacion.parFx = parFx;
    this.operacion.turno = this.turno;


    this.operacion.denominacionesYCantidadesEntregadas=this.denominacionesDadas.filter(d => d.cantidad>0);
    this.operacion.denominacionesYCantidadesRecibidas=this.denominacionesRecibidas.filter(d => d.cantidad>0);

    this._operacionService.createOperacion(this.operacion).subscribe(data => {
      this.clean();
      this.showMessage(this.getMsg('administration.operacion.save.exito'), 'success', this.getMsg('administration.operacion.save.exito.id') + data.id);
      this.router.navigate([""]);
    });
  }

  public clean() {
    this.checked = null;
    this.idSoipc = null;
    this.nominal = null;
    this.precio = null;
    this.valorGiro = null;
    this.precioValoracion = null;
    this.denominacionesDadas = [];
    this.denominacionesRecibidas = [];
    this.moneda = null;

    
    if( this.recibirComponent){
      this.recibirComponent.limpiar();
    }
    if( this.darComponent){
      this.darComponent.limpiar();
    }
  
  }

  protected update(cod: any, bean: any) {
    //this._fxService.updateFx(cod,this.bean).subscribe(data=>{
    //  this.clean();
    //  this.showMessage(this.getMsg('basecrud.confirm.edit.title.success'), 'success', this.getMsg('administration.role.form.edit.msg.success'));
    //  this.location.back();
    //});
  }


  public recibirDenominaciones(event) {
    if (event.denominaciones) {
      this.denominacionesRecibidas = event.denominaciones;
      this.mostrarRecibir = false;
      if (!this.esCompra) {
        this.valorTotal = this.nominal;
      } else {
        this.valorTotal = this.valorGiro;
      }
      this.mostrarDar = true;
    }else{
      this.mostrarRecibir = false;
      this.confirm();
    }

  }

  public darDenominaciones(event) {
    this.denominacionesDadas = event.denominaciones;
    this.mostrarDar = false;
    this.confirm();
  }

  public confirm() {
    this.confirmationService.confirm({
      message: this.getMsg('administration.operacion.confirm.save'),
      key: "cdOperacion",
      accept: () => {
        this.save();
      }
    });
  }

  public cerrarRecibir() {
    this.mostrarRecibir = false;
    this.denominacionesDadas = null;
    this.denominacionesRecibidas = null;
    this.router.navigate([""]);
  }

  public cerrarDar() {
    this.mostrarRecibir = false;
    this.denominacionesDadas = null;
    this.denominacionesRecibidas = null;
    this.router.navigate([""]);
  }

  public llamarRecbir() {
    this.estado= "EJECUTADA";
    this.asignarValores();
    this.mostrarRecibir = true;
  }

  public asignarValores() {
    this.fx = this.moneda.codigo;
    if (this.esCompra) {
      this.valorTotal = this.nominal;
    } else {
      this.valorTotal = this.valorGiro;
    }
  }

  public calcularValorGiroNominal(event) {
    this.valorGiro = 0;
    this.valorGiro = event * this.precio;
  }

  public calcularValorGiroPrecio(event) {
    this.valorGiro = 0;
    this.valorGiro = event * this.nominal;
  }

  public cargarPrecioCompraFx() {

    this.listaPreciosSucursal.forEach(element => {
      if (this.moneda && this.moneda.codigo == element.fx.codigo) {
        this.precio = element.precioCompra;
        this.precioValoracion = element.precioValoracion;
      }
    });
  }

  public cargarPrecioVentaFx() {
    this.listaPreciosSucursal.forEach(element => {
      if (this.moneda && this.moneda.codigo == element.fx.codigo) {
        this.precio = element.precioVenta;
        this.precioValoracion = element.precioValoracion;
      }
    });
  }

  public cargarLimpiar(event) {
    this.clean();
    if (event.index == 0) {
      this.esCompra = true;
    } else {
      this.esCompra = false;
    }
    this.cargarListaPrecios();
  }

  public guardarOrden(){
    this.asignarValores();
    this.estado="PENDIENTE";
    this.confirm();
  }

}