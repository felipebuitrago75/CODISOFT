import { Component, OnInit } from '@angular/core';
import { BaseCrudFormComponent } from '../../../../../components/base-crud-form.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FunctionalityService } from '../../../services/functionality-service';
import { MenuService } from '../../../services/menu-service';
import { ResourceService } from '../../../services/resource-service';

@Component({
  selector: 'app-functionality-form',
  templateUrl: './functionality-form.component.html',
  styleUrls: ['./functionality-form.component.scss']
})
export class FunctionalityFormComponent extends BaseCrudFormComponent<any> implements OnInit {

  public menus:any[]=[];

  public resources:any[]=[];

  constructor(protected functionalityService:FunctionalityService,protected menuService:MenuService,protected resourceService:ResourceService,protected router: Router, protected route: ActivatedRoute,
     protected location: Location, protected messageService: MessageService, 
     protected confirmationService: ConfirmationService) {
    super(router, route, location, messageService, confirmationService);
  }

  ngOnInit() {
    super.ngOnInit();
    this.loadMenus();
    this.loadResources();
  }
  
  protected createBean() : any  {
    let bean: any = {} as any;
    bean.resources = [];
    return bean;
  }

  protected loadMenus(){
    this.menuService.getMenusForFunctionalities(0, 1000).subscribe(menus=>{
      this.menus = menus;   
    });
  }

  protected loadResources(){
    this.resourceService.getResources(0, 1000).subscribe(resources=>{
      this.resources = resources;   
    });
  }

  //como se llama el atributo id
  protected getIdParamName(): string {
    return "id";
  }

  //llama el servicio y obtiene el functionality y lo carga al bean
  protected loadBean(name: any) {
    this.functionalityService.getFunctionality(name).subscribe(data=>{
      this.bean = data;
    });
  }

  //valida el bean antes de enviarlo
  protected validate(): boolean {
    return true;
  }

  protected save(bean: any) {
    this.functionalityService.createFunctionality(this.bean).subscribe(data=>{
      this.clean();
      this.showMessage(this.getMsg('basecrud.confirm.save.title.success'), 'success', this.getMsg('administration.functionality.form.save.msg.success'));
    });
  }

  protected update(name:any,bean: any) {
    this.functionalityService.updateFunctionality(name,this.bean).subscribe(data=>{
      this.clean();
      this.showMessage(this.getMsg('basecrud.confirm.edit.title.success'), 'success', this.getMsg('administration.functionality.form.edit.msg.success'));
      this.location.back();
    });
  }


}
