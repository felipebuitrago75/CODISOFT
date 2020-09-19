import { Component, OnInit } from '@angular/core';
import { MessageService, ConfirmationService, MenuItem } from 'primeng/primeng';
import { BaseScreenComponent } from '../../../components/base-screen.component'
import { BaseCrudFormComponent } from '../../../components/base-crud-form.component';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { SucursalService } from '../../../services/sucursal-service';

import { TurnoService } from '../../../services/turno-service';
import { CajaService } from '../../../services/caja-service';

import { cajaDTO } from '../../../dtos/cajaDTO';
import { SucursalDTO } from '../../../dtos/sucursalDTO';
import { TurnoDTO } from '../../../dtos/turnoDTO';
import { CierreTurnoService } from '../../../services/cierre-turno-service';
import { CierreTurnoDTO } from '../../../dtos/cierreTurnoDTO';

@Component({
  templateUrl: 'turno.component.html'
})
export class TurnoComponent extends BaseCrudFormComponent<any> implements OnInit {

  public caja: cajaDTO = new cajaDTO();
  public sucursal: SucursalDTO = new SucursalDTO();
  public listaSucursales: Array<SucursalDTO> = [];
  public listaCajas: Array<cajaDTO> = [];
  public turno:TurnoDTO= new TurnoDTO();

  constructor(protected router: Router, protected route: ActivatedRoute,
    protected location: Location, protected messageService: MessageService,
    protected confirmationService: ConfirmationService,
    protected _cajaService: CajaService,
    protected _turnoService: TurnoService,
    protected _sucursalService: SucursalService, 
    protected _cierreTurnoService: CierreTurnoService) {
    super(router, route, location, messageService, confirmationService);

  }

  ngOnInit() {
    super.ngOnInit();
    this.cargarSucursales();
  }

  cargarSucursales() {
    let sucursalDefault: SucursalDTO = new SucursalDTO();
    sucursalDefault.nombre = "Seleccione";
    sucursalDefault.cod = null;
    this.listaSucursales.push(sucursalDefault);
    this._sucursalService.getSucursales(0, 20).subscribe(data => {
      this.listaSucursales = this.listaSucursales.concat(...data);
    });
  }

  public cargarListaCajas(): void {
     this.listaCajas = [];
     let cajaDefault: cajaDTO = new cajaDTO();
     cajaDefault.nombre = "Seleccione";
     cajaDefault.id = null;
     this.listaCajas.push(cajaDefault);
     this._cajaService.getCajasPorSucursal(this.sucursal.cod).subscribe(data => {
      this.listaCajas = this.listaCajas.concat(...data);
    });
  }

  //como se llama el atributo id
  protected getIdParamName(): string {
    return "id";
  }

  //llama el servicio y obtiene el role y lo carga al bean
  protected loadBean(id: any) {
    this._turnoService.getCajaPorId(id).subscribe(data => {
      this.turno = data;
      //this.isUpdate = true;
    });
  }

  //valida el bean antes de enviarlo
  protected validate(): boolean {
    this.save();
    return true;
    
  }

  protected save() {
    this.turno.idCaja=this.caja.id;
    this.turno.idUsuario=1;
    
    this._turnoService.createTurno(this.turno).subscribe(data => {
      this.showMessage(this.getMsg('exito'), 'success', "Turno creado correctamente");
      this._turnoService.setTurno(data);

    });
    this.clean();
  }

  public cerrarTurno(){
    let cierreTurno = new CierreTurnoDTO();
    let turno:TurnoDTO = this._turnoService.getTurno();
    cierreTurno.idTurno = turno.id;
    this._cierreTurnoService.createCierreTurno(cierreTurno).subscribe(data => {
      this.showMessage(this.getMsg('exito'), 'success', "Turno cerrado correctamente");
    });
  }

  public clean() {
    this.caja = new cajaDTO();
    this.sucursal = new SucursalDTO();
    this.turno= new TurnoDTO();
  }

 
  protected update(id: any, bean: any) {
   
  }


}