import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../../services/fx-service';
import { SucursalService } from '../../../services/sucursal-service';
import { DenominacionDTO } from '../../../dtos/denominacionDTO';
import { OperacionDTO} from '../../../dtos/operacionDTO';
import { ParFxDTO} from '../../../dtos/parFxDTO';
import { Fx } from '../../../dtos/fxDTO';
import { PathConstants } from '../../../util/path.constants';


@Component({
  templateUrl: './listado-sucursales.component.html'
})
export class ListaSucursalesComponent extends BaseCrudSearchComponent<any,any> implements OnInit{

  constructor( protected _sucursalService: SucursalService, protected router: Router, protected route: ActivatedRoute, protected location: Location, protected messageService: MessageService, protected confirmationService: ConfirmationService) {
    super(router, route, location, messageService, confirmationService);
  }

  //valida el bean y retorna 
  protected validate(): boolean {
    return true; 

  }

  public onLoad(beanFilter: any, first: number, max: number, doFinish?: any) {
    this._sucursalService.getSucursales(first, max).subscribe( sucursales =>{
      let newRows = sucursales;    
      this.rows = this.rows.concat(newRows);
      doFinish(newRows);
    });
  }

  public openNew() {
    this.router.navigate([PathConstants.PATH_SUCURSAL_FROM]);
  }

  public openEdit(row: any) {
    this.router.navigate(['sucursales', row.cod]);
  }

  protected getConfirmMessageDelete(row: any): string {
    return this.getMsg("administration.role.confirm.delete.msg",row.cod);;
  }

  public onDelete(row: any) {
    this._sucursalService.deleteSucursal(row.cod).subscribe(data=>{
      this.search();
      this.showMessage(this.getMsg('basecrud.confirm.delete.title.success'), 'success', this.getMsg('aadministration.delete.msg.success'));
    });
  }

  protected confirmDelete(row: any) {
    this.confirmationService.confirm({
        key: "cdListaSucursal",
        message: this.getConfirmMessageDelete(row),
        accept: () => {
            this.onDelete(row);
        }
    });
}
}
