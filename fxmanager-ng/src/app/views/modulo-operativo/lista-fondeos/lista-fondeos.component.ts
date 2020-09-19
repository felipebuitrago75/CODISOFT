import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';


import { PathConstants } from '../../../util/path.constants';
import { TurnoService } from '../../../services/turno-service';
import { TurnoDTO } from '../../../dtos/turnoDTO';
import { FondeoService } from '../../../services/fondeo-service';
import { CajaService } from '../../../services/caja-service';
import { cajaDTO } from '../../../dtos/cajaDTO';
import { SucursalService } from '../../../services/sucursal-service';
import { FxService } from '../../../services/fx-service';




@Component({
  templateUrl: 'lista-fondeos.component.html',
  styleUrls: ['./lista-fondeos.component.css']
})
export class ListaFondeosComponent extends BaseCrudSearchComponent<any, any> implements OnInit {


 
  public turno: TurnoDTO = null;
  public bloquear = true;
  public caja: cajaDTO = null;

  constructor(protected _fondeoService: FondeoService,
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

  }

  public onLoad(beanFilter: any, first: number, max: number, doFinish?: any) {
    this._fondeoService.gets(first, max).subscribe(roles => {
      let newRows = roles;
      this.rows = this.rows.concat(newRows);
      doFinish(newRows);
    });

  }


  protected addCrudTableAction() {
  }

  public openNew() {
    this.router.navigate(["registro-translados"]);
  }

  public openEdit(row: any) {
  }

  public buscarConFiltro() {
  }

  public onDelete(row: any) {
    //throw new Error("Method not implemented.");
  }
  protected getConfirmMessageDelete(row: any): string {
    return null;
  }

  recargar() {
    this.search();
  }


}
