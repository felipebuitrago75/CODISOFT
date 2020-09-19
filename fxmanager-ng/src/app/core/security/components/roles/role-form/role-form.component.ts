import { Component, OnInit } from '@angular/core';
import { BaseCrudFormComponent } from '../../../../../components/base-crud-form.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { RoleService } from '../../../services/role-service';
import { FunctionalityService } from '../../../services/functionality-service';
import {AccessTypes} from '../../../domain/AccessType';

@Component({
  selector: 'app-role-form',
  templateUrl: './role-form.component.html',
  styleUrls: ['./role-form.component.scss']
})
export class RoleFormComponent extends BaseCrudFormComponent<any> implements OnInit {

  private functionalities:any[];

  private AccessTypes=AccessTypes;

  private accessTypesSelectItems:any[]=[];

  constructor(protected roleService:RoleService,protected functionalityService:FunctionalityService,protected router: Router, protected route: ActivatedRoute,
     protected location: Location, protected messageService: MessageService, 
     protected confirmationService: ConfirmationService) {
    super(router, route, location, messageService, confirmationService);
  }

  ngOnInit() {
    super.ngOnInit();
    this.initAccesTypes();
    this.loadFunctionalities();
    
  }

  private initAccesTypes(){
    for (const key in AccessTypes) {
      if (AccessTypes.hasOwnProperty(key)) {
        const value = AccessTypes[key];
        this.accessTypesSelectItems.push({label:value,value:key});
      }
    }
  }

  protected createBean() : any  {
    let bean: any = {} as any;
    bean.permissions = [];
    return bean;
  }

  protected loadFunctionalities(){
    this.functionalityService.getFunctionalities(0, 1000).subscribe(functionalities=>{
      this.functionalities = functionalities;   
    });
  }

  public addPermission(){
    let permissions=[];
    for (const key in AccessTypes) {
      if (AccessTypes.hasOwnProperty(key)) {
        const value = AccessTypes[key];
        permissions.push(key);
      }
    }
    this.bean.permissions.push({functionality:null,accessTypes:permissions});
  }

  public removePermission(data){
    const index =  this.bean.permissions.indexOf(data);
    this.bean.permissions.splice(index, 1);   
  }

  //como se llama el atributo id
  protected getIdParamName(): string {
    return "id";
  }

  //llama el servicio y obtiene el role y lo carga al bean
  protected loadBean(name: any) {
    this.roleService.getRole(name).subscribe(data=>{
      this.bean = data;
    });
  }

  //valida el bean antes de enviarlo
  protected validate(): boolean {
    return true;
  }

  protected save(bean: any) {
    this.roleService.createRole(this.bean).subscribe(data=>{
      this.clean();
      this.showMessage(this.getMsg('basecrud.confirm.save.title.success'), 'success', this.getMsg('administration.role.form.save.msg.success'));
    });
  }

  protected update(name:any,bean: any) {
    this.roleService.updateRole(name,this.bean).subscribe(data=>{
      this.clean();
      this.showMessage(this.getMsg('basecrud.confirm.edit.title.success'), 'success', this.getMsg('administration.role.form.edit.msg.success'));
      this.location.back();
    });
  }


}
