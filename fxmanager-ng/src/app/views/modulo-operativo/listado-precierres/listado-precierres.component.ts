import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../../services/fx-service';
import { SucursalService } from '../../../services/sucursal-service';
import { CierreService } from '../../../services/cierre-service';
import { TurnoService} from '../../../services/turno-service';
import { DenominacionDTO } from '../../../dtos/denominacionDTO';
import { ParFxDTO} from '../../../dtos/parFxDTO';
import { Fx } from '../../../dtos/fxDTO';
import { PathConstants } from '../../../util/path.constants';
@Component({
    templateUrl: './listado-precierres.component.html'
  })
  export class ListaPrecierresComponent extends BaseCrudSearchComponent<any,any> implements OnInit{
    constructor( protected _sucursalService: SucursalService, 
        protected _turnoService: TurnoService, 
        protected router: Router, 
        protected route: ActivatedRoute, 
        protected location: Location, 
        protected messageService: MessageService, 
        protected confirmationService: ConfirmationService) {
        super(router, route, location, messageService, confirmationService);
      }

      //valida el bean y retorna 
  protected validate(): boolean {
    return true; 

  }

  public onLoad(beanFilter: any, first: number, max: number, doFinish?: any) {
    this._turnoService.getTurnos(0, 100).subscribe( turnos =>{
      let newRows = turnos;    
      this.rows = this.rows.concat(newRows);
      doFinish(newRows);
    });
  }

  public openNew() {
    this.router.navigate(["precierres"]);
  }

  public openEdit(row: any) {
    this.router.navigate(['precierres', row]);
  }

  protected getConfirmMessageDelete(row: any): string {
    return this.getMsg("administration.cierre.confirm.delete.msg",row.id);;
  }

  public onDelete(row: any) {
   
  }

  protected addCrudTableAction() {
    this.addTableAction("Ver Detalles", () => {
        if (this.rowSelected != null) {
            this.openEdit(this.rowSelected.id);
        }
    });
    
}
  }