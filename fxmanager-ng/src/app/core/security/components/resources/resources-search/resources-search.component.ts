import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import {ResourceService} from '../../../services/resource-service'
import {PathConstants} from '../../../../../util/path.constants'
@Component({
  selector: 'app-resources-search',
  templateUrl: './resources-search.component.html',
  styleUrls: ['./resources-search.component.scss']
})
export class ResourcesSearchComponent extends BaseCrudSearchComponent<any,any> implements OnInit {

  constructor(protected resourceService:ResourceService, protected router: Router, protected route: ActivatedRoute, protected location: Location, protected messageService: MessageService, protected confirmationService: ConfirmationService) {
    super(router, route, location, messageService, confirmationService);
  }

  //valida el bean y retorna 
  protected validate(): boolean {
    return true; 

  }

  
  public onLoad(beanFilter: any, first: number, max: number, doFinish?: any) {
    this.resourceService.getResources(first, max).subscribe(resources=>{
      let newRows = resources;    
      this.rows = this.rows.concat(newRows);
      doFinish(newRows);
    });
  
  }

  public openNew() {
    this.router.navigate([PathConstants.FULLPATH_RESOURCE_FORM]);
  }

  public openEdit(row: any) {
    this.router.navigate([PathConstants.FULLPATH_RESOURCE_FORM, row.name]);
  }

  protected getConfirmMessageDelete(row: any): string {
    return this.getMsg("administration.resource.confirm.delete.msg",row.name);;
  }

  public onDelete(row: any) {
    this.resourceService.deleteResource(row.name).subscribe(data=>{
      this.search();
      this.showMessage(this.getMsg('basecrud.confirm.delete.title.success'), 'success', this.getMsg('administration.resource.form.delete.msg.success'));
    });
  }

 


}
