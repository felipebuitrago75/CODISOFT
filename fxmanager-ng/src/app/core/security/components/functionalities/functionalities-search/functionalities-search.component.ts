import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import {FunctionalityService} from '../../../services/functionality-service'
import {PathConstants} from '../../../../../util/path.constants'
@Component({
  selector: 'app-functionalities-search',
  templateUrl: './functionalities-search.component.html',
  styleUrls: ['./functionalities-search.component.scss']
})
export class FunctionalitiesSearchComponent extends BaseCrudSearchComponent<any,any> implements OnInit {

  
  constructor(protected functionalityService:FunctionalityService, protected router: Router, protected route: ActivatedRoute, protected location: Location, protected messageService: MessageService, protected confirmationService: ConfirmationService) {
    super(router, route, location, messageService, confirmationService);
  }

  //valida el bean y retorna 
  protected validate(): boolean {
    return true; 

  }

  
  public onLoad(beanFilter: any, first: number, max: number, doFinish?: any) {
    this.functionalityService.getFunctionalities(first, max).subscribe(functionalities=>{
      let newRows = functionalities;    
      this.rows = this.rows.concat(newRows);
      doFinish(newRows);
    });
  
  }

  public openNew() {
    this.router.navigate([PathConstants.FULLPATH_FUNCTIONALITY_FORM]);
  }

  public openEdit(row: any) {
    this.router.navigate([PathConstants.FULLPATH_FUNCTIONALITY_FORM, row.name]);
  }

  protected getConfirmMessageDelete(row: any): string {
    return this.getMsg("administration.functionality.confirm.delete.msg",row.name);;
  }

  public onDelete(row: any) {
    this.functionalityService.deleteFunctionality(row.name).subscribe(data=>{
      this.search();
      this.showMessage(this.getMsg('basecrud.confirm.delete.title.success'), 'success', this.getMsg('administration.functionality.form.delete.msg.success'));
    });
  }

 


}
