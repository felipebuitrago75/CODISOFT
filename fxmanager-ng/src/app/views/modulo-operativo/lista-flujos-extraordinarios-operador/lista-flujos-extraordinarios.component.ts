import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../../services/fx-service';
import { Fx } from '../../../dtos/fxDTO';
import { PathConstants } from '../../../util/path.constants';
import { FlujoExtraordinarioService } from '../../../services/flujo-extraordinario-service';
import { TurnoDTO } from '../../../dtos/turnoDTO';
import { TurnoService } from '../../../services/turno-service';
import { FlujoFiltroDTO } from '../../../dtos/flujoFiltroDTO';
import { FLUJO_CONSTANTES } from '../registro-flujos-extraordinarios/flujos.constantes';

@Component({
  templateUrl: 'lista-flujos-extraordinarios.component.html',
})
export class ListaFlujosExtraordinariosComponentOperador extends BaseCrudSearchComponent<any, any> implements OnInit {

  public turno: TurnoDTO = null;

  public listaFxs: Array<any> = [];

  public listaTipo: Array<any> = [];
  public listaEstados: Array<any> = [];
  public listaCriterio: Array<any> = [];

  public codigoFiltro: any = null;
  public estadoFiltro: any = null;
  public tipoFiltro: any = null;
  public criterioFiltro: any = null;
  public fechaFiltro: Date = null;

  rangeDates: Date[];
  minDate: Date;
  maxDate: Date;
  es: any;
  invalidDates: Array<Date>

  constructor(protected flujoExtraordinarioService: FlujoExtraordinarioService, protected router: Router,
    protected route: ActivatedRoute, protected location: Location,
    protected messageService: MessageService, protected confirmationService: ConfirmationService,
    protected _turnoService: TurnoService,
    protected _fxService: FxService) {
    super(router, route, location, messageService, confirmationService);
  }

  ngOnInit() {
    super.ngOnInit();

    this.turno = this._turnoService.getTurno();
    if (this.turno === null || this.turno === undefined) {
      this.showMessage(this.getMsg('administration.operacion.save.error.turno'), 'error', '');
    } else {
    }

    this._fxService.getFxs(0, 10).subscribe(res => {
      this.listaFxs.push({ label: 'SELECCIONE', value: null });
      res.forEach(element => {
        this.listaFxs.push({ label: element.codigo, value: element.codigo });
      });
    });

    this.listaTipo.push({ label: 'SELECCIONE', value: null });
    this.listaTipo.push({ label: 'EGRESO', value: 'EGRESO' });
    this.listaTipo.push({ label: 'INGRESO', value: 'INGRESO' });

    this.listaEstados.push({ label: 'SELECCIONE', value: null });
    this.listaEstados.push({ label: 'EJECUTADO', value: 'EJECUTADO' });
    this.listaEstados.push({ label: 'CANCELADO', value: 'CANCELADO' });

    this.listaCriterio.push({ label: 'Seleccione', value: null });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.NOMINA, value: FLUJO_CONSTANTES.NOMINA });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.PROVEEDORES, value: FLUJO_CONSTANTES.PROVEEDORES });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.SERVICIOS, value: FLUJO_CONSTANTES.SERVICIOS });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.ARRIENDO, value: FLUJO_CONSTANTES.ARRIENDO });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.ADMINISTRACION, value: FLUJO_CONSTANTES.ADMINISTRACION });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.TRANSPORTE, value: FLUJO_CONSTANTES.TRANSPORTE });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.SEGURO, value: FLUJO_CONSTANTES.SEGURO });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.IMPUESTOS, value: FLUJO_CONSTANTES.IMPUESTOS });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.RIESGO, value: FLUJO_CONSTANTES.RIESGO });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.PRESTAMOS, value: FLUJO_CONSTANTES.PRESTAMOS });

    this.listaCriterio.push({ label: FLUJO_CONSTANTES.PPE, value: FLUJO_CONSTANTES.PPE });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.HARDWARE, value: FLUJO_CONSTANTES.HARDWARE });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.SOFTWARE, value: FLUJO_CONSTANTES.SOFTWARE });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.PRESTAMOS, value: FLUJO_CONSTANTES.PRESTAMOS });
    this.listaCriterio.push({ label: FLUJO_CONSTANTES.DIVIDENDOS, value: FLUJO_CONSTANTES.DIVIDENDOS });


    this.inicialCalendario();

    this.buscarConFiltro();
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
    this.router.navigate([PathConstants.PATH_FLUJO_EXTRAORDINARIO_FROM]);
  }

  public openEdit(row: any) {
    this.router.navigate(['registro-flujos', row.id]);
  }

  protected getConfirmMessageDelete(row: any): string {
    return "¿Está seguro que desea cancelar este Flujo extraordinario?";
  }

  public onDelete(row: any) {

    if (this.turno === null || this.turno === undefined) {
      this.showMessage(this.getMsg('administration.operacion.save.error.turno'), 'error', '');
      return;
    }
    if (row.estado == 'EJECUTADO') {
      this.flujoExtraordinarioService.cancelarFlujo(row.id, this.turno).subscribe(data => {
        this.search();
        this.showMessage("Exito", 'success', "Flujo cancelado correctamente");
      });
    } else {
      this.showMessage("No es posible cancelar el flujo extraordinario", 'error', 'no se puede cancelar flujos con estado cancelado');
    }

  }

  protected addCrudTableAction() {
    this.addTableAction("Cancelar Flujo", () => {
      if (this.rowSelected != null) {
        this.confirmDelete(this.rowSelected);
      }

    });
  }

  public buscarConFiltro() {

    let flujoFiltro: FlujoFiltroDTO = new FlujoFiltroDTO();

    if (this.codigoFiltro) {
      flujoFiltro.codigoFx = this.codigoFiltro;
    }
    if (this.estadoFiltro) {
      flujoFiltro.estado = this.estadoFiltro;
    }
    if (this.tipoFiltro) {
      flujoFiltro.tipo = this.tipoFiltro;
    }
    if (this.criterioFiltro) {
      flujoFiltro.criterio = this.criterioFiltro;
    }
    
    let fecha = new Date();
    
    flujoFiltro.fecha = new Date(fecha.getFullYear(), fecha.getMonth(), fecha.getDate());
  
    
    this.flujoExtraordinarioService.buscarConFiltro(flujoFiltro).subscribe(data => {
      this.rows = data;
      console.log(data);
    });
  }

  recargar() {
    this.codigoFiltro = null;
    this.estadoFiltro = null;
    this.tipoFiltro = null;
    this.criterioFiltro = null;
    this.fechaFiltro = null;
    this.search();
  }


  protected confirmDelete(row: any) {
    this.confirmationService.confirm({
      key: "cdListaFlujo",
      message: this.getConfirmMessageDelete(row),
      accept: () => {
        this.onDelete(row);
      }
    });
  }
}