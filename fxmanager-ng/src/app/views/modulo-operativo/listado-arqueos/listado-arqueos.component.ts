import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../../services/fx-service';
import { SucursalService } from '../../../services/sucursal-service';
import { CierreService } from '../../../services/cierre-service';
import { TurnoService } from '../../../services/turno-service';
import { DenominacionDTO } from '../../../dtos/denominacionDTO';
import { ParFxDTO } from '../../../dtos/parFxDTO';
import { Fx } from '../../../dtos/fxDTO';
import { PathConstants } from '../../../util/path.constants';
import { CierreTurnoService } from '../../../services/cierre-turno-service';
import { CajaService } from '../../../services/caja-service';
import { cajaDTO } from '../../../dtos/cajaDTO';
import { UsuarioComponent } from '../../modulo-configuracion/usuario.component';
@Component({
  templateUrl: './listado-arqueos.component.html'
})
export class ListaArqueosComponent extends BaseCrudSearchComponent<any, any> implements OnInit {

  public fechaFiltro: Date = null;
  listaCajas: Array<cajaDTO>= [];
  listaUsuarios: Array<any>= [];

  rangeDates: Date[];
  minDate: Date;
  maxDate: Date;
  es: any;
  invalidDates: Array<Date>;

  constructor(protected _sucursalService: SucursalService,
    protected _turnoService: TurnoService,
    protected router: Router,
    protected route: ActivatedRoute,
    protected location: Location,
    protected messageService: MessageService,
    protected confirmationService: ConfirmationService,
    protected _cierreTurno: CierreTurnoService,
    protected _cajasService: CajaService) {
    super(router, route, location, messageService, confirmationService);
    this.inicialCalendario();

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
  //valida el bean y retorna 
  protected validate(): boolean {
    return true;

  }

  public onLoad(beanFilter: any, first: number, max: number, doFinish?: any) {
  }

  public openNew() {
    this.router.navigate([""]);
  }

  public openEdit(row: any) {
    this.router.navigate(['arqueo', row]);
  }

  protected getConfirmMessageDelete(row: any): string {
    return this.getMsg("administration.cierre.confirm.delete.msg", row.id);;
  }

  public onDelete(row: any) {

  }

  public buscarConFiltro(){
    this._cierreTurno.arqueosHistoricosPorFecha(this.fechaFiltro).subscribe(data => {
      console.log(data);
      this.rows = data;
    });
  }

  protected addCrudTableAction() {
    this.addTableAction("Ver Detalles", () => {
      if (this.rowSelected != null) {
        this.openEdit(this.rowSelected.id);
      }
    });

  }
}