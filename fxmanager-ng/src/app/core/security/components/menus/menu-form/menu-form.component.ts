import { Component, OnInit } from '@angular/core';
import { BaseCrudFormComponent } from '../../../../../components/base-crud-form.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { MenuService } from '../../../services/menu-service';

@Component({
  selector: 'app-menu-form',
  templateUrl: './menu-form.component.html',
  styleUrls: ['./menu-form.component.scss']
})
export class MenuFormComponent extends BaseCrudFormComponent<any> implements OnInit {

  public menus:any[]=[];

  constructor(protected menuService:MenuService,protected router: Router, protected route: ActivatedRoute,
     protected location: Location, protected messageService: MessageService, 
     protected confirmationService: ConfirmationService) {
    super(router, route, location, messageService, confirmationService);
  }

  ngOnInit() {
    super.ngOnInit();
    this.loadMenus();
  }

  protected loadMenus(){
    this.menuService.getMenusForParent(0, 1000).subscribe(menus=>{
      this.menus = menus;   
    });
  }

  //como se llama el atributo id
  protected getIdParamName(): string {
    return "id";
  }

  //llama el servicio y obtiene el menu y lo carga al bean
  protected loadBean(name: any) {
    this.menuService.getMenu(name).subscribe(data=>{
      this.bean = data;
    });
  }

  //valida el bean antes de enviarlo
  protected validate(): boolean {
    if(this.bean.target!=null && this.bean.target.length==0){
      this.bean.target=null;
    }
    return true;
  }

  protected save(bean: any) {
    this.menuService.createMenu(this.bean).subscribe(data=>{
      this.clean();
      this.showMessage(this.getMsg('basecrud.confirm.save.title.success'), 'success', this.getMsg('administration.menu.form.save.msg.success'));
    });
  }

  protected update(name:any,bean: any) {
    this.menuService.updateMenu(name,this.bean).subscribe(data=>{
      this.clean();
      this.showMessage(this.getMsg('basecrud.confirm.edit.title.success'), 'success', this.getMsg('administration.menu.form.edit.msg.success'));
      this.location.back();
    });
  }


}
