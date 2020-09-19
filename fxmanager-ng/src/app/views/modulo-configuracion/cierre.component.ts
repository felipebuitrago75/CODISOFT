import { Component, OnInit } from '@angular/core';
import { MessageService, ConfirmationService, MenuItem } from 'primeng/primeng';
import { BaseScreenComponent } from '../../components/base-screen.component'
import { BaseCrudFormComponent } from '../../components/base-crud-form.component';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { cierreDTO } from '../../dtos/cierreDTO';
import { CierreService } from '../../services/cierre-service';

@Component({
  templateUrl: 'cierre.component.html'
})
export class CierreComponent extends BaseCrudFormComponent<any> implements OnInit {

  public cierre: cierreDTO = new cierreDTO();
  public cierres: Array<cierreDTO> = [];
  
  public filaMonedas: Array<any> = [];
  public filaSaldoInicial: Array<any> = [];
  public filaCompras: Array<any> = [];
  public filaVentas: Array<any> = [];
  public filaIngresos: Array<any> = [];
  public filaEgresos: Array<any> = [];
  public filaTrasladosSalientes: Array<any> = [];
  public filaTrasladosEntrantes: Array<any> = [];
  public filaSaldosFinales: Array<any> = [];
  public filaPrecioValAnterior: Array<any> = [];
  public filaPrecioValActual: Array<any> = [];
  public filaPyGCaja: Array<any> = [];

  constructor(protected router: Router, protected route: ActivatedRoute,
    protected location: Location, protected messageService: MessageService,
    protected confirmationService: ConfirmationService,
    protected _cierreService: CierreService,) {
    super(router, route, location, messageService, confirmationService);
  }

  ngOnInit() {
    super.ngOnInit();
  }

  //como se llama el atributo id
  protected getIdParamName(): string {
    return "id";
  }

  //llama el servicio y obtiene el role y lo carga al bean
  protected loadBean(id: any) {
    this._cierreService.getCierre(id).subscribe(data => {
      this.cierre = data;
      this.cierres.push(this.cierre);
    });
  }

  //valida el bean antes de enviarlo
  public validate(): boolean {
    return true;
  }

  protected save() {}

  public clean() {}

  public update(){}

}