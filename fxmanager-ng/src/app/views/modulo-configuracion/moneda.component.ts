import { Component, OnInit } from '@angular/core';
import { BaseCrudFormComponent } from '../../components/base-crud-form.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../services/fx-service';

@Component({
  templateUrl: 'moneda.component.html',
  styleUrls: ['./moneda.component.scss']
})
export class MonedaComponent extends BaseCrudFormComponent<any> implements OnInit {

  public fx:string;
  public listaFxs:Array<any> =[];

  constructor(protected _fxService:FxService,protected router: Router, protected route: ActivatedRoute,
    protected location: Location, protected messageService: MessageService, 
    protected confirmationService: ConfirmationService) {
   super(router, route, location, messageService, confirmationService);

   _fxService.getFxs(0,10).subscribe(data=>{
    this.listaFxs = data;
  });
 }

 ngOnInit() {
  super.ngOnInit();
}

//como se llama el atributo id
protected getIdParamName(): string {
  return "id";
}

//llama el servicio y obtiene el role y lo carga al bean
protected loadBean(cod: any) {
  
  this._fxService.getFx(cod).subscribe(data=>{
    this.bean = data;
  });
}

//valida el bean antes de enviarlo
protected validate(): boolean {
  if(this.bean.codigo==null || this.bean.codigo.length==0){
    this.showMessage(this.getMsg('basecrud.confirm.save.title.success'), 'error', this.getMsg('administration.moneda.form.input.codigo.required'));
    return false;
  }
  if(this.bean.concepto==null || this.bean.concepto.length==0){
    this.showMessage(this.getMsg('basecrud.confirm.save.title.success'), 'error', this.getMsg('administration.moneda.form.input.concepto.required'));
    return false;
  }
  if(this.bean.denominaciones==null || this.bean.denominaciones.length==0){
    this.showMessage(this.getMsg('basecrud.confirm.save.title.success'), 'error', this.getMsg('administration.moneda.form.input.denominaciones.required'));
    return false;
  }
  return true;
}

protected save(bean: any) {
    this.showMessage(this.getMsg('basecrud.confirm.save.title.success'), 'success', this.getMsg('administration.moneda.form.save.msg.success'));
  this._fxService.createFx(this.bean).subscribe(data=>{
    this.clean();
  });
}

protected update(cod:any,bean: any) {
  this._fxService.updateFx(cod,this.bean).subscribe(data=>{
    this.clean();
    this.showMessage(this.getMsg('basecrud.confirm.edit.title.success'), 'success', this.getMsg('administration.moneda.form.edit.msg.success'));
    this.location.back();
  });
}

}