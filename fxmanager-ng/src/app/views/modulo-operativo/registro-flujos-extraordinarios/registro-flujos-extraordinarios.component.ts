import { Component, OnInit } from '@angular/core';
import { BaseCrudFormComponent } from '../../../components/base-crud-form.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../../services/fx-service';
import { Fx } from '../../../dtos/fxDTO';
import { FlujoExtraordinarioService } from '../../../services/flujo-extraordinario-service';
import { FlujoExtraordinarioDTO } from '../../../dtos/flujoExtraordinarioDTO';
import { TurnoService } from '../../../services/turno-service';
import { TurnoDTO } from '../../../dtos/turnoDTO';
import { DenominacionCantidadDTO } from '../../../dtos/denominacionCantidadDTO';
import { FLUJO_CONSTANTES } from './flujos.constantes';
import { SucursalService } from '../../../services/sucursal-service';

@Component({
  templateUrl: 'registro-flujos-extraordinarios.component.html'
})
export class RegistroFlujoExtraordinariosComponent extends BaseCrudFormComponent<any> implements OnInit {

  public listaTipoFlujos: Array<any> = [];
  public listaFxs: Array<Fx> = [];
  public flujoExtraordinario:FlujoExtraordinarioDTO;
  public checked: boolean;
  public isUpdate: boolean = false;
  public turno: TurnoDTO = null;
  public listaCriterios: Array<any> = [];
  public listaNaturaleza: Array<any> = [];
  public listaReceptores:  Array<any> = [];
  public mostrarArqueo= false;

  public fecha:Date= new Date();
  public empresa: string="";
  public nit: string="";
  public operador: string="";
  public beneficiario: string="";


  constructor(protected _sucursalService: SucursalService, protected flujoExtraordinarioService: FlujoExtraordinarioService, protected router: Router,
    protected route: ActivatedRoute, protected location: Location, protected messageService: MessageService,
    protected confirmationService: ConfirmationService, protected _fxService: FxService, protected _turnoService: TurnoService) {
    super(router, route, location, messageService, confirmationService);
  }

  cargarMonedas() {
    let fx: Fx = new Fx();
    fx.codigo = "Seleccione";
    fx.concepto = null;
    this.listaFxs.push(fx);
    this._fxService.getFxs(0, 20).subscribe(data => {
      this.listaFxs = this.listaFxs.concat(...data);
    });
  }

  ngOnInit() {
    super.ngOnInit();
    this.flujoExtraordinario = new FlujoExtraordinarioDTO();
    this.listaTipoFlujos.push({ label: 'Seleccione', value: null });
    this.listaTipoFlujos.push({ label: 'EGRESO', value: 'EGRESO' });
    this.listaTipoFlujos.push({ label: 'INGRESO', value: 'INGRESO' });

    this.listaReceptores.push({ label: 'Seleccione', value: null });
    this.listaReceptores.push({ label: FLUJO_CONSTANTES.GENERAL, value: FLUJO_CONSTANTES.GENERAL });
    this._sucursalService.getSucursales(0, 20).subscribe(data => {
      data.forEach(element => {
        this.listaReceptores.push({ label: "SUCURSAL-"+element.nombre, value: "SUCURSAL-"+element.id });
      });
      this.listaReceptores.push({ label: FLUJO_CONSTANTES.COMERCIAL, value: FLUJO_CONSTANTES.COMERCIAL });
      this.listaReceptores.push({ label: FLUJO_CONSTANTES.ADMINISTRATIVO, value: FLUJO_CONSTANTES.ADMINISTRATIVO });
    });
   
    this.listaNaturaleza.push({ label: 'Seleccione', value: null });
    this.listaNaturaleza.push({ label: FLUJO_CONSTANTES.OPERACION, value: FLUJO_CONSTANTES.OPERACION });
    this.listaNaturaleza.push({ label: FLUJO_CONSTANTES.INVERSION, value: FLUJO_CONSTANTES.INVERSION });
    this.listaNaturaleza.push({ label: FLUJO_CONSTANTES.FINANCIACION, value: FLUJO_CONSTANTES.FINANCIACION });

    this.listaCriterios.push({ label: 'Seleccione', value: null });
    this.cargarMonedas();

    this.turno = this._turnoService.getTurno();
    if (this.turno === null || this.turno === undefined) {
      this.showMessage(this.getMsg('administration.operacion.save.error.turno'), 'error', '');
    }


  }

  //como se llama el atributo id
  protected getIdParamName(): string {
    return "id";
  }

  //llama el servicio y obtiene el role y lo carga al bean
  protected loadBean(cod: any) {
    this.flujoExtraordinarioService.getFlujoExtraordinario(cod).subscribe(data => {
      this.flujoExtraordinario = data;

      if(this.flujoExtraordinario.idSoipc){
          this.checked = true;
      }
      this.isUpdate = true;
    });
  }

  //valida el bean antes de enviarlo
  public validate(): boolean {

    
    if(this.checked){
      if (!this.flujoExtraordinario.idSoipc) {
        this.showMessage(this.getMsg('error'), 'error', "idSoipc es requerido");
        return false;
      }
      var aux={"idSoipc": null, "fecha": null, "empresa": null, "nit": null, "operador": null, "beneficiario": null };
      aux.idSoipc=this.flujoExtraordinario.idSoipc;
      aux.fecha=this.fecha;
      aux.empresa=this.empresa;
      aux.operador=this.operador;
      aux.beneficiario=this.beneficiario;
      this.flujoExtraordinario.idSoipc=JSON.stringify(aux);
    }
    if (!this.flujoExtraordinario.tipo) {
      this.showMessage(this.getMsg('error'), 'error', "tipo es requerido");
      return false;
    }
    if (!this.flujoExtraordinario.naturaleza) {
      this.showMessage(this.getMsg('error'), 'error', "naturaleza es requerido");
      return false;
    }
    if (!this.flujoExtraordinario.criterio) {
      this.showMessage(this.getMsg('error'), 'error', "criterio es requerido");
      return false;
    }
    if (!this.flujoExtraordinario.receptor) {
      this.showMessage(this.getMsg('error'), 'error', "receptor es requerido");
      return false;
    }
    if (!this.flujoExtraordinario.receptor) {
      this.showMessage(this.getMsg('error'), 'error', "receptor es requerido");
      return false;
    }
    
    if (!this.flujoExtraordinario.valor) {
      this.showMessage(this.getMsg('error'), 'error', "Valor es requerida");
      return false;
    }
    if (!this.flujoExtraordinario.fx) {
      this.showMessage(this.getMsg('error'), 'error', "Moneda es requerida");
      return false;
    }
    
    if(!this.turno){
      this.showMessage(this.getMsg('error'), 'error', "Requiere iniciar Turno");
      return false;
    }else{
      this.flujoExtraordinario.turno= this.turno;
    }
    this.guardarFlujo();
    return true;
  }

  save(){

  }

  protected guardarFlujo() {
    
        this.flujoExtraordinarioService.createFlujoExtraordinario(this.flujoExtraordinario).subscribe(data => {
          this.clean();
          this.showMessage(this.getMsg('exito'), 'success', this.getMsg('administration.flujo.extraordinaro.save.exito'));
          this.location.back();
        });
     
  }

  public clean() {
    this.flujoExtraordinario = new FlujoExtraordinarioDTO();
    this.isUpdate = false;
    this.checked = false;
  }

  protected update(cod: any, bean: any) {
    this.confirmationService.confirm({
      key:"cdRegistroFlujo",
      message: this.getMsg('administration.flujo.extraordinario.confirm.update'),
      accept: () => {
        this.flujoExtraordinarioService.updateFlujoExtraordinario(cod,bean).subscribe(data => {
          this.clean();
          this.showMessage(this.getMsg('exito'), 'success', this.getMsg('administration.flujo.extraordinaro.update.exito'));
          this.location.back();
        });
      }
    });
  }

  mostrarArqueoFuncion(){
    if (this.flujoExtraordinario.fx.codigo!=="COP") {
      this.showMessage(this.getMsg('error'), 'error', "Moneda debe ser COP");
      return false;
    }
    if(this.flujoExtraordinario.fx){
      this.mostrarArqueo=true;
    }
    
  }

  public recibirValor(event: any){
    console.log(event);
    let denominacionesYCantidades: Array<DenominacionCantidadDTO> = event.denominaciones.map(denominacion=>{
      let denominacionCantidadDTO: DenominacionCantidadDTO = new DenominacionCantidadDTO(denominacion.valor,denominacion.cantidad,event.idFx);
      return denominacionCantidadDTO;
    });
    this.flujoExtraordinario.denominacionesYCantidades=denominacionesYCantidades;
    this.flujoExtraordinario.valor=event.total;
    this.mostrarArqueo=false;
  }

  public cambioNaturaleza(event:any){
    console.log(event.value);
    if(event.value==FLUJO_CONSTANTES.OPERACION){
      this.listaCriterios  = [];

      this.listaCriterios.push({ label: 'Seleccione', value: null });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.NOMINA, value: FLUJO_CONSTANTES.NOMINA });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.PROVEEDORES, value: FLUJO_CONSTANTES.PROVEEDORES });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.SERVICIOS, value: FLUJO_CONSTANTES.SERVICIOS });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.ARRIENDO, value: FLUJO_CONSTANTES.ARRIENDO });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.ADMINISTRACION, value: FLUJO_CONSTANTES.ADMINISTRACION });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.TRANSPORTE, value: FLUJO_CONSTANTES.TRANSPORTE });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.SEGURO, value: FLUJO_CONSTANTES.SEGURO });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.IMPUESTOS, value: FLUJO_CONSTANTES.IMPUESTOS });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.RIESGO, value: FLUJO_CONSTANTES.RIESGO });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.PRESTAMOS, value: FLUJO_CONSTANTES.PRESTAMOS });
  
    }else if(event.value==FLUJO_CONSTANTES.INVERSION){
      this.listaCriterios  = [];
      this.listaCriterios.push({ label: 'Seleccione', value: null });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.PPE, value: FLUJO_CONSTANTES.PPE });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.HARDWARE, value: FLUJO_CONSTANTES.HARDWARE });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.SOFTWARE, value: FLUJO_CONSTANTES.SOFTWARE });

    }else if(event.value==FLUJO_CONSTANTES.FINANCIACION){
      this.listaCriterios  = [];
      this.listaCriterios.push({ label: 'Seleccione', value: null });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.PRESTAMOS, value: FLUJO_CONSTANTES.PRESTAMOS });
      this.listaCriterios.push({ label: FLUJO_CONSTANTES.DIVIDENDOS, value: FLUJO_CONSTANTES.DIVIDENDOS });
    
    }else{
      this.listaCriterios  = [];
      this.listaCriterios.push({ label: 'Seleccione', value: null });
    }
  }

  
}