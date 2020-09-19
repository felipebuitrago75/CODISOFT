import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { PathConstants } from '../../../util/path.constants';
import { FxService } from '../../../services/fx-service';


@Component({
  templateUrl: './listado-monedas.component.html'
})
export class ListaMonedaComponent extends BaseCrudSearchComponent<any,any> implements OnInit{

  public mostrar:boolean=true;

  constructor( protected router: Router, protected route: ActivatedRoute, 
    protected location: Location, protected messageService: MessageService, 
    protected confirmationService: ConfirmationService,
    protected _monedaService : FxService) {
    super(router, route, location, messageService, confirmationService);

  }

  //valida el bean y retorna 
  protected validate(): boolean {
    return true; 

  }

  public onLoad(beanFilter: any, first: number, max: number, doFinish?: any) {
    this._monedaService.getFxs(first, max).subscribe( fx =>{
      let newRows = fx;    
      this.rows = this.rows.concat(newRows);
      doFinish(newRows);
    });
  }

  public openNew() {
    this.router.navigate(['monedas']);
  }

  public openEdit(row: any) {
    this.router.navigate(['monedas', row.codigo]);
  }

  protected getConfirmMessageDelete(row: any): string {
    return "¿Esta seguro que desea eliminar esta moneda?";
  }

  public onDelete(row: any) {
    this._monedaService.deleteFx(row.codigo).subscribe(data=>{
      this.search();
      this.showMessage(this.getMsg('basecrud.confirm.delete.title.success'), 'success', "Moneda eliminada correctamente");
    });
  }

  
}
