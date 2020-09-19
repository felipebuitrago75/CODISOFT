import { Component, OnInit } from '@angular/core';
import { MessageService, ConfirmationService, MenuItem } from 'primeng/primeng';
import { BaseScreenComponent } from '../../components/base-screen.component'
import { BaseCrudFormComponent } from '../../components/base-crud-form.component';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { SucursalService } from '../../services/sucursal-service';
import { SucursalDTO } from '../../dtos/sucursalDTO';
import { fxSucursalDTO } from '../../dtos/fxSucursal';
import { FxService } from '../../services/fx-service';

@Component({
  templateUrl: 'sucursal.component.html'
})
export class SucursalComponent extends BaseCrudFormComponent<any> implements OnInit {

  public sucursal: SucursalDTO = new SucursalDTO();
  public listaEstados: Array<any> = [];
  public listaPrecios: Array<fxSucursalDTO> = [];
  public fxSucursalDTO: fxSucursalDTO = new fxSucursalDTO();
  public telefonos: Array<number>;
  public listaFxs:  Array<any> = [];
  public isUpdate: boolean = false;
  public tableActions: MenuItem[] = [];
  public rowSelected: fxSucursalDTO;

  constructor(protected router: Router, protected route: ActivatedRoute,
    protected location: Location, protected messageService: MessageService,
    protected confirmationService: ConfirmationService,
    protected _sucursalService: SucursalService,
    protected _fxService: FxService) {
    super(router, route, location, messageService, confirmationService);
    this.addCrudTableAction();
  }

  ngOnInit() {
    super.ngOnInit();
    this.listaEstados.push({ label: 'Seleccione', value: null });
    this.listaEstados.push({ label: 'ACTIVO', value: '1' });
    this.listaEstados.push({ label: 'INACTIVO', value: '0' });
    this.cargarListaPrecios();
  }

  cargarListaPrecios(){
    this._fxService.getFxs(0,20).subscribe(data=>{    
      this.listaFxs = new Array<any>();
      this.listaFxs.push({'':''})
      data.forEach(element => {
        if(element.codigo == 'COP' ){
        }else{
          this.listaFxs.push(element);
        }
      });
    });
   }

  //como se llama el atributo id
  protected getIdParamName(): string {
    return "id";
  }

  //llama el servicio y obtiene el role y lo carga al bean
  protected loadBean(cod: any) {
    this._sucursalService.getSucursal(cod).subscribe(data => {
      this.sucursal = data;
      if(data.telefonos>0){
        this.telefonos = data.telefonos.split("-");
      }
      this.isUpdate = true;
      this.listaPrecios = data.listaPrecios;
    });
  }

  //valida el bean antes de enviarlo
  public validate(): boolean {
    if (!this.sucursal.cod) {
      this.showMessage(this.getMsg('error'), 'error', "Codigo es requerido");
      return false;
    }
    if (!this.sucursal.nombre) {
      this.showMessage(this.getMsg('error'), 'error', "Nombre es requerido");
      return false;
    }
    if (!this.sucursal.direccion) {
      this.showMessage(this.getMsg('error'), 'error', "Direccion es requerida");
      return false;
    }
    if (!this.sucursal.estado) {
      this.showMessage(this.getMsg('error'), 'error', "Estado es requerido");
      return false;
    }
    if(!this.sucursal.chkPrincipal){
      this.sucursal.chkPrincipal = false;
    }
    if(this.telefonos && this.telefonos.length != 0){
        let telefonos = this.telefonos.join("-");
        this.sucursal.telefonos = telefonos;
    }else{
      this.sucursal.telefonos = null;
    }
    if(this.isUpdate){
      this.sucursal.listaPrecios = this.listaPrecios;
      this.update(this.sucursal.cod, this.sucursal);
    }else{
      this.sucursal.listaPrecios = this.listaPrecios;
      this.save();
    }
    
    return true;
  }

  public validateCrearPrecio(): boolean {
    if (!this.fxSucursalDTO.fx) {
      this.showMessage(this.getMsg('error'), 'error', "Moneda es requerido");
      return false;
    }
    if (!this.fxSucursalDTO.precioCompra) {
      this.showMessage(this.getMsg('error'), 'error', "Precio compra es requerido");
      return false;
    }
    if (!this.fxSucursalDTO.precioVenta) {
      this.showMessage(this.getMsg('error'), 'error', "Precio venta es requerido");
      return false;
    }
    if (!this.fxSucursalDTO.precioValoracion) {
      this.showMessage(this.getMsg('error'), 'error', "Precio valorización es reuqerido");
      return false;
    }
    this.savePrecio();
    return true;
  }

  protected save() {
    this._sucursalService.createSucursal(this.sucursal).subscribe(data => {
      this.showMessage(this.getMsg('exito'), 'success', this.getMsg('administration.sucursal.save.msg.success'));
      this.router.navigate(['lista-sucursales']);
    });
    this.clean();
  }

  public clean() {
    this.sucursal = new SucursalDTO();
    this.telefonos = [];
    this.listaPrecios = [];
  }

  public cleanCreacionPrecio(): void {
    this.fxSucursalDTO = new fxSucursalDTO();
  }

  private savePrecio(): void {
    this.listaPrecios.push(this.fxSucursalDTO);
    this.cleanCreacionPrecio();
  }
  
  public removerPrecio(row: fxSucursalDTO): void {
       let precios = this.listaPrecios.filter( function( e ) {
          return e !== row;
      } );
      this.listaPrecios = precios;
  }

  protected update(cod: any, bean: any) {
    this._sucursalService.updateSucursal(cod, bean).subscribe(data => {
      this.clean();
      this.showMessage(this.getMsg('basecrud.confirm.edit.title.success'), 'success', this.getMsg('administration.sucursal.save.msg.success'));
      this.location.back();
    });
  }


  protected addTableAction(label: string, command: any, icon?: string) {
    if (this.tableActions == null) {
        this.tableActions = [];
    }
    this.tableActions.push({ label: label, command: command, icon: icon });
}

protected addCrudTableAction() {
  this.addTableAction(this.getMsg('basecrud.actions.button.edit'), () => {
      if (this.rowSelected != null) {
          
      }
  });
  this.addTableAction(this.getMsg('basecrud.actions.button.delete'), () => {
      if (this.rowSelected != null) {
         this.removerPrecio(this.rowSelected);
      }
  });
}

}