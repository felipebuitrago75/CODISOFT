import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../components/base-crud-search.component';
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
import { PathConstants } from '../../../util/path.constants';
import { TurnoService } from '../../../services/turno-service';
import { TurnoDTO } from '../../../dtos/turnoDTO';
import { OperacionFiltroDTO } from '../../../dtos/operacionFiltroDTO';
import { CajaService } from '../../../services/caja-service';
import { AuthService } from '../../../core/security/services/auth-service';

import { RolConstants } from '../../../util/rol.constants';

@Component({
  templateUrl: 'lista-operacion-operador.component.html',
  styleUrls: ['./lista-operacion-operador.component.css']
})
export class ListaOperacionesOperadorComponent extends BaseCrudSearchComponent<any, any> implements OnInit {


  public turno: TurnoDTO = null;
  public bloquear = true;
  public listaFxs: Array<any> = [];

  public listaTipo: Array<any> = [];
  public listaEstados: Array<any> = [];

  recibidas: Array<any> = [];

  entregadas: Array<any> = [];


  public codigoFiltro: any = null;
  public estadoFiltro: any = null;
  public tipoFiltro: any = null;
  public fechaFiltro: Date = null;

  rangeDates: Date[];

  minDate: Date;

  maxDate: Date;

  es: any;

  invalidDates: Array<Date>

  mostrarDetalle = false;

  public rol: string = "";

  public nombreUsuario: string = "";

  constructor(protected _operacionService: OperacionService,
    protected router: Router, protected route: ActivatedRoute,
    protected location: Location,
    protected messageService: MessageService,
    protected confirmationService: ConfirmationService,
    protected _sucursalService: SucursalService,
    protected _cajaService: CajaService,
    protected _turnoService: TurnoService,
    protected _fxService: FxService,
    protected authService: AuthService) {
    super(router, route, location, messageService, confirmationService);
  }

  //valida el bean y retorna 
  protected validate(): boolean {
    return true;
  }

  ngOnInit() {
    super.ngOnInit();

    this.turno = this._turnoService.getTurno();
    if (this.turno === null || this.turno === undefined) {
      this.showMessage(this.getMsg('administration.operacion.save.error.turno'), 'error', '');
      this.bloquear = true;
    } else {
      this.bloquear = false;
    }

    this._fxService.getFxs(0, 10).subscribe(res => {
      this.listaFxs.push({ label: 'SELECCIONE', value: null });
      res.forEach(element => {
        this.listaFxs.push({ label: element.codigo, value: element.codigo });
      });
    });

    this.listaTipo.push({ label: 'SELECCIONE', value: null });
    this.listaTipo.push({ label: 'COMPRA', value: 'COMPRA' });
    this.listaTipo.push({ label: 'VENTA', value: 'VENTA' });

    this.listaEstados.push({ label: 'SELECCIONE', value: null });
    this.listaEstados.push({ label: 'EJECUTADA', value: 'EJECUTADA' });
    this.listaEstados.push({ label: 'CANCELADA', value: 'CANCELADA' });

    this.cargarOperaciones();
  }

  public onLoad(){}

  public cargarOperaciones() {

    let tokenBody = this.authService.getToken()['access_token'].toString().split(".")[1];
    let body = JSON.parse(atob(tokenBody));
    this.rol = body.authorities[0];
    this.nombreUsuario = body.user_name;

    if (this.turno) {
      this._operacionService.getOperacionesPorTurno(this.turno.id + "").subscribe(data => {
        this.rows = data;
      });
    }

   
  }

  protected addCrudTableAction() {
  this.addTableAction(this.getMsg('Cancelar Operacion'), () => {
    if (this.rowSelected != null) {
      this.confirmDelete(this.rowSelected);
    }
  });
  this.addTableAction(this.getMsg('Ver Detalle'), () => {
    if (this.rowSelected != null) {
      this.verDetalle(this.rowSelected);
    }
  });
}

  public openNew() {
  this.router.navigate([PathConstants.FULLPATH_ROLE_FORM]);
}

  public openEdit(row: any) {

}

  protected getConfirmMessageDelete(row: any): string {
  return "¿Está seguro que desea cancelar esta operación?";
}

  public onDelete(row: any) {
  if (!this.turno) {
    this.showMessage("No es posible cancelar la Operacion", 'error', 'no se puede cancelar operaciones sin iniciar turno');
    return;
  }
  if (row.estado == 'EJECUTADA') {
    this._operacionService.cancelarOperacion(row.id, this.turno).subscribe(data => {
      this.search();
      this.showMessage("Éxito", 'success', 'Operación cancelada correctamente');
    });
  } else {
    this.showMessage("No es posible cancelar la Operacion", 'error', 'no se puede cancelar operaciones con estado pendiente o canceladas');
  }


}


  public buscarConFiltro() {

  let operacionFilro: OperacionFiltroDTO = new OperacionFiltroDTO();

  if (this.codigoFiltro) {
    operacionFilro.codigoFx = this.codigoFiltro;
  }
  if (this.estadoFiltro) {
    operacionFilro.estado = this.estadoFiltro;
  }
  if (this.tipoFiltro) {
    operacionFilro.tipo = this.tipoFiltro;
  }
  
  this._operacionService.buscarConFiltro(operacionFilro).subscribe(data => {
    this.rows = data;
    console.log(data);
  });
}

recargar() {
  this.codigoFiltro = null;
  this.estadoFiltro = null;
  this.tipoFiltro = null;
  this.fechaFiltro = null;
  this.search();
}

  protected confirmDelete(row: any) {
  this.confirmationService.confirm({
    key: "cdListaOperacion",
    message: this.getConfirmMessageDelete(row),
    accept: () => {
      this.onDelete(row);
    }
  });
}


  public verDetalle(row: any){

  this._operacionService.getOperacion(row.id).subscribe(data => {
    this.rowSelected = data;


    data.denominacionesYCantidadesRecibidas.forEach(element => {


      if (element.idFx == 1) {
        if (data.tipo == "COMPRA") {
          this.entregadas.push(element);
        } else {
          this.recibidas.push(element);
        }
      } else {
        if (data.tipo == "COMPRA") {
          this.recibidas.push(element);
        } else {
          this.entregadas.push(element);

        }
      }

    });

    this._cajaService.getCaja(data.turno.idCaja).subscribe(res => {
      this.rowSelected.caja = res;
      this.mostrarDetalle = true;
    });



  });

}

}
