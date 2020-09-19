import { Component, OnInit } from '@angular/core';
import { BaseCrudFormComponent } from '../../../../../components/base-crud-form.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { ResourceService } from '../../../services/resource-service';

@Component({
  selector: 'app-resource-form',
  templateUrl: './resource-form.component.html',
  styleUrls: ['./resource-form.component.scss']
})
export class ResourceFormComponent extends BaseCrudFormComponent<any> implements OnInit {

  

  constructor(protected resourceService:ResourceService,protected router: Router, protected route: ActivatedRoute,
     protected location: Location, protected messageService: MessageService, 
     protected confirmationService: ConfirmationService) {
    super(router, route, location, messageService, confirmationService);
    
  }

  ngOnInit() {
    super.ngOnInit();
  }

  //como se llama el atributo id
  protected getIdParamName(): string {
    return "id";
  }

  //llama el servicio y obtiene el resource y lo carga al bean
  protected loadBean(name: any) {
    this.resourceService.getResource(name).subscribe(data=>{
      this.bean = data;
    });
  }

  //valida el bean antes de enviarlo
  protected validate(): boolean {
    return true;
  }

  protected save(bean: any) {
    this.resourceService.createResource(this.bean).subscribe(data=>{
      this.clean();
      this.showMessage(this.getMsg('basecrud.confirm.save.title.success'), 'success', this.getMsg('administration.resource.form.save.msg.success'));
    });
  }

  protected update(name:any,bean: any) {
    this.resourceService.updateResource(name,this.bean).subscribe(data=>{
      this.clean();
      this.showMessage(this.getMsg('basecrud.confirm.edit.title.success'), 'success', this.getMsg('administration.resource.form.edit.msg.success'));
      this.location.back();
    });
  }


}
