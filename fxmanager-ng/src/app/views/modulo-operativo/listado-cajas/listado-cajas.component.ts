import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { PathConstants } from '../../../util/path.constants';
import { CajaService } from '../../../services/caja-service';


@Component({
  templateUrl: './listado-cajas.component.html'
})
export class ListaCajasComponent extends BaseCrudSearchComponent<any,any> implements OnInit{

  constructor( protected _cajaService: CajaService, protected router: Router, protected route: ActivatedRoute, protected location: Location, protected messageService: MessageService, protected confirmationService: ConfirmationService) {
    super(router, route, location, messageService, confirmationService);
  }

  //valida el bean y retorna 
  protected validate(): boolean {
    return true; 

  }

  public onLoad(beanFilter: any, first: number, max: number, doFinish?: any) {
    this._cajaService.getCajas(first, max).subscribe( cajas =>{
      let newRows = cajas;    
      this.rows = this.rows.concat(newRows);
      doFinish(newRows);
    });
  }

  public openNew() {
    this.router.navigate(['cajas']);
  }

  public openEdit(row: any) {
    this.router.navigate(['cajas', row.id]);
  }

  protected getConfirmMessageDelete(row: any): string {
    return this.getMsg("administration.role.confirm.delete.msg",row.nombre);;
  }

  public onDelete(row: any) {
    this._cajaService.deleteCaja(row.id).subscribe(data=>{
      this.search();
      this.showMessage(this.getMsg('basecrud.confirm.delete.title.success'), 'success', this.getMsg('administration.role.form.delete.msg.success'));
    });
  }
}
