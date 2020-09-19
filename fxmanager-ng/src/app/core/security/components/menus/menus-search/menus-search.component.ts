import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import {MenuService} from '../../../services/menu-service'
import {PathConstants} from '../../../../../util/path.constants'
@Component({
  selector: 'app-menus-search',
  templateUrl: './menus-search.component.html',
  styleUrls: ['./menus-search.component.scss']
})
export class MenusSearchComponent extends BaseCrudSearchComponent<any,any> implements OnInit {

  
  constructor(protected menuService:MenuService, protected router: Router, protected route: ActivatedRoute, protected location: Location, protected messageService: MessageService, protected confirmationService: ConfirmationService) {
    super(router, route, location, messageService, confirmationService);
  }

  //valida el bean y retorna 
  protected validate(): boolean {
    return true; 

  }

  
  public onLoad(beanFilter: any, first: number, max: number, doFinish?: any) {
    this.menuService.getMenus(first, max).subscribe(menus=>{
      let newRows = menus;    
      this.rows = this.rows.concat(newRows);
      doFinish(newRows);
    });
  
  }

  public openNew() {
    this.router.navigate([PathConstants.FULLPATH_MENU_FORM]);
  }

  public openEdit(row: any) {
    this.router.navigate([PathConstants.FULLPATH_MENU_FORM, row.name]);
  }

  protected getConfirmMessageDelete(row: any): string {
    return this.getMsg("administration.menu.confirm.delete.msg",row.name);;
  }

  public onDelete(row: any) {
    this.menuService.deleteMenu(row.name).subscribe(data=>{
      this.search();
      this.showMessage(this.getMsg('basecrud.confirm.delete.title.success'), 'success', this.getMsg('administration.menu.form.delete.msg.success'));
    });
  }

 


}
