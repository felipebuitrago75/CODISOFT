import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';


import { PathConstants } from '../../../util/path.constants';
import { TurnoService } from '../../../services/turno-service';
import { TurnoDTO } from '../../../dtos/turnoDTO';
import { TrasladoService } from '../../../services/traslado-service';
import { CajaService } from '../../../services/caja-service';
import { cajaDTO } from '../../../dtos/cajaDTO';
import { SucursalService } from '../../../services/sucursal-service';
import { FxService } from '../../../services/fx-service';
import { TrasladoFiltroDTO } from '../../../dtos/trasladoFiltroDTO';


@Component({
  templateUrl: 'lista-traslados.component.html',
  styleUrls: ['./lista-traslados.component.css']
})
export class ListaTrasladosComponent extends BaseCrudSearchComponent<any, any> implements OnInit {


  public turno: TurnoDTO = null;
  public bloquear = true;
  public caja: cajaDTO = null;

  public listaEstados: Array<any> = [];
  public listaFxs: Array<any> = [];
  public listaSucursales: Array<any> = [];

  public codigoFiltro: any = null;
  public estadoFiltro: any = null;
  public sucursalOrigenFiltro: any = null;
  public sucursalDestinoFiltro: any = null;
  public fechaFiltro: Date = null;

  rangeDates: Date[];
  minDate: Date;
  maxDate: Date;
  es: any;
  invalidDates: Array<Date>

  constructor(protected _trasladosService: TrasladoService,
    protected router: Router, protected route: ActivatedRoute,
    protected location: Location,
    protected messageService: MessageService,
    protected confirmationService: ConfirmationService,
    protected _turnoService: TurnoService,
    protected _sucursalService: SucursalService,
    protected _cajaService: CajaService,
    protected _fxService: FxService) {
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
      this._cajaService.getCaja(this.turno.idCaja.toString()).subscribe(data => {
        this.caja = data;
      });
      this.bloquear = false;
    }

    this._fxService.getFxs(0, 10).subscribe(res => {
      this.listaFxs.push({ label: 'SELECCIONE', value: null });
      res.forEach(element => {
        this.listaFxs.push({ label: element.codigo, value: element.codigo });
      });
    });

    this.listaEstados.push({ label: 'SELECCIONE', value: null });
    this.listaEstados.push({ label: 'EJECUTADO', value: 'EJECUTADO' });
    this.listaEstados.push({ label: 'CANCELADO', value: 'CANCELADO' });

    this._sucursalService.getSucursales(0, 20).subscribe(data => {
      this.listaSucursales.push({ label: 'SELECCIONE', value: null });
      data.forEach(element => {
        this.listaSucursales.push({ label: element.nombre, value: element.cod });
      });
    });

    this.inicialCalendario();
  }

  public onLoad(beanFilter: any, first: number, max: number, doFinish?: any) {
    this._trasladosService.gets(first, max).subscribe(roles => {
      let newRows = roles;
      this.rows = this.rows.concat(newRows);
      doFinish(newRows);
    });

  }

  inicialCalendario() {
    this.es = {
      firstDayOfWeek: 1,
      dayNames: ["domingo", "lunes", "martes", "miércoles", "jueves", "viernes", "sábado"],
      dayNamesShort: ["dom", "lun", "mar", "mié", "jue", "vie", "sáb"],
      dayNamesMin: ["D", "L", "M", "X", "J", "V", "S"],
      monthNames: ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"],
      monthNamesShort: ["ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"],
      today: 'Hoy',
      clear: 'Borrar'
    }

    let today = new Date();
    let month = today.getMonth();
    let year = today.getFullYear();
    let prevMonth = (month === 0) ? 11 : month - 1;
    let prevYear = (prevMonth === 11) ? year - 1 : year;
    let nextMonth = (month === 11) ? 0 : month + 1;
    let nextYear = (nextMonth === 0) ? year + 1 : year;
    this.minDate = new Date();
    this.minDate.setMonth(prevMonth);
    this.minDate.setFullYear(prevYear);
    this.maxDate = new Date();
    this.maxDate.setMonth(nextMonth);
    this.maxDate.setFullYear(nextYear);

    let invalidDate = new Date();
    invalidDate.setDate(today.getDate() - 1);
    this.invalidDates = [today, invalidDate];
  }

  protected addCrudTableAction() {
    this.addTableAction(this.getMsg('Cancelar traslado'), () => {
      if (this.rowSelected != null) {
        this.confirmDelete(this.rowSelected);
      }
    });
    this.addTableAction(this.getMsg('Aceptar traslado'), () => {
      if (this.rowSelected != null) {
        this.confirmAceptar(this.rowSelected);
      }
    });
  }

  public openNew() {
    this.router.navigate(["registro-translados"]);
  }

  public openEdit(row: any) {

  }

  protected confirmDelete(row: any) {
    this.confirmationService.confirm({
      message: "¿Está seguro que desea cancelar este traslado?",
      accept: () => {
        this.onDelete(row);
      }
    });
  }

  protected confirmAceptar(row: any) {
    this.confirmationService.confirm({
      message: "¿Está seguro que desea aceptar este traslado?",
      accept: () => {
        this.onAceptar(row);
      }
    });
  }
  protected getConfirmMessageDelete(row: any): string {
    return "¿Está seguro que desea cancelar esta traslado?";
  }

  public onDelete(row: any) {
    if (row.estado == 'EN PROCESO') {
      this._trasladosService.cancelarTraslados(row.id, this.turno).subscribe(data => {
        this.search();
        this.showMessage("Éxito", 'success', 'Traslado cancelado correctamente');
      });
    } else {
      this.showMessage("No es posible cancelar el traslado", 'error', 'no se puede cancelar Traslados con estado ejecutado o cancelado');
    }
  }

  public onAceptar(row: any) {
    if (row.estado == 'EN PROCESO') {
      row.turnoDestino = this.turno;

      if (this.caja && (this.caja.codigoSucursal === row.sucursalDestino.cod)) {
        row.estado = "EJECUTADO";
        this._trasladosService.update(row.id, this.turno).subscribe(data => {
          this.search();
          this.showMessage("Éxito", 'success', 'Traslado aceptado correctamente');
        });
      } else {
        this.showMessage("No es posible aceptar el traslado", 'error', 'no esta autorizado para aceptar este traslado, se espera que el traslado sea aceptado en la sucursal ' + row.sucursalDestino.nombre);
      }
    } else {
      this.showMessage("No es posible aceptar el traslado", 'error', 'no se puede aceptar Traslados con estado cancelado o ejecutado');
    }
  }

  public buscarConFiltro() {

    let trasladoFiltro: TrasladoFiltroDTO = new TrasladoFiltroDTO();

    if (this.codigoFiltro) {
      trasladoFiltro.codigoFx = this.codigoFiltro;
    }
    if (this.estadoFiltro) {
      trasladoFiltro.estado = this.estadoFiltro;
    }
    if (this.sucursalOrigenFiltro) {
      trasladoFiltro.codigoSucursalOrigen = this.sucursalOrigenFiltro;
    }
    if (this.sucursalDestinoFiltro) {
      trasladoFiltro.codigoSucursalDestino = this.sucursalDestinoFiltro;
    }
    if (this.fechaFiltro) {
      trasladoFiltro.fecha = this.fechaFiltro;
    }
    this._trasladosService.buscarConFiltro(trasladoFiltro).subscribe(data => {
      this.rows = data;
      console.log(data);
    });
  }

  recargar() {
    this.codigoFiltro = null;
    this.estadoFiltro = null;
    this.sucursalOrigenFiltro = null;
    this.sucursalDestinoFiltro = null;
    this.fechaFiltro = null;

    this.search();
  
  }



}
