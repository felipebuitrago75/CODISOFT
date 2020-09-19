import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import {RoleService} from '../../../services/role-service'
import {PathConstants} from '../../../../../util/path.constants'
@Component({
  selector: 'app-roles-search',
  templateUrl: './roles-search.component.html',
  styleUrls: ['./roles-search.component.scss']
})
export class RolesSearchComponent extends BaseCrudSearchComponent<any,any> implements OnInit {

  constructor(protected roleService:RoleService, protected router: Router, protected route: ActivatedRoute, protected location: Location, protected messageService: MessageService, protected confirmationService: ConfirmationService) {
    super(router, route, location, messageService, confirmationService);
  }

  //valida el bean y retorna 
  protected validate(): boolean {
    return true; 

  }

  
  public onLoad(beanFilter: any, first: number, max: number, doFinish?: any) {
    this.roleService.getRoles(first, max).subscribe(roles=>{
      let newRows = roles;    
      this.rows = this.rows.concat(newRows);
      doFinish(newRows);
    });
  
  }

  public openNew() {
    this.router.navigate([PathConstants.FULLPATH_ROLE_FORM]);
  }

  public openEdit(row: any) {
    this.router.navigate([PathConstants.FULLPATH_ROLE_FORM, row.name]);
  }

  protected getConfirmMessageDelete(row: any): string {
    return this.getMsg("administration.role.confirm.delete.msg",row.name);;
  }

  public onDelete(row: any) {
    this.roleService.deleteRole(row.name).subscribe(data=>{
      this.search();
      this.showMessage(this.getMsg('basecrud.confirm.delete.title.success'), 'success', this.getMsg('administration.role.form.delete.msg.success'));
    });
  }

 


}
