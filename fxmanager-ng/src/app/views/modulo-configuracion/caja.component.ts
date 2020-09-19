import { Component, OnInit, AfterViewInit, AfterViewChecked } from '@angular/core';
import { MessageService, ConfirmationService, MenuItem } from 'primeng/primeng';
import { BaseScreenComponent } from '../../components/base-screen.component'
import { BaseCrudFormComponent } from '../../components/base-crud-form.component';
import { Location } from '@angular/common';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { SucursalService } from '../../services/sucursal-service';
import { saldoDTO } from '../../dtos/saldoDTO';
import { FxService } from '../../services/fx-service';
import { cajaDTO } from '../../dtos/cajaDTO';
import { Fx } from '../../dtos/fxDTO';
import { CajaService } from '../../services/caja-service';
import { SucursalDTO } from '../../dtos/sucursalDTO';
import { DenominacionCantidadDTO } from '../../dtos/denominacionCantidadDTO';

@Component({
  templateUrl: 'caja.component.html'
})
export class CajaComponent extends BaseCrudFormComponent<any> implements OnInit {

  public caja: cajaDTO = new cajaDTO();
  public listaSucursales: Array<SucursalDTO> = [];
  public listaSaldos: Array<saldoDTO> = [];
  public saldoDTO: saldoDTO = new saldoDTO();
  public listaFxs: Array<any> = [];
  public sucursal: SucursalDTO = new SucursalDTO();
  public isUpdate: boolean = false;
  public tableActions: MenuItem[] = [];
  public rowSelected: saldoDTO;
  public mostrarArqueo = false;
  public labelBoton: string = "Crear";
  public desabilitarDropdownMoneda: Boolean = false;

  constructor(protected router: Router, protected route: ActivatedRoute,
    protected location: Location, protected messageService: MessageService,
    protected confirmationService: ConfirmationService,
    protected _cajaService: CajaService,
    protected _sucursalService: SucursalService,
    protected _fxService: FxService,
    protected rutaActiva: ActivatedRoute) {
    super(router, route, location, messageService, confirmationService);
    this.addCrudTableAction();
  }

  ngOnInit() {
    super.ngOnInit();
    this.cargarListaPrecios();
    this.cargarSucursales();
  }

  cargarListaPrecios() {
    this._fxService.getFxs(0, 20).subscribe(data => {
      this.listaFxs = new Array<any>();
      this.listaFxs.push({ '': '' })
      data.forEach(element => {
        this.listaFxs.push(element);
      });
    });
  }

  cargarSucursales() {
    let sucursalDefault: SucursalDTO = new SucursalDTO();
    sucursalDefault.nombre = "Seleccione";
    sucursalDefault.cod = null;
    this.listaSucursales.push(sucursalDefault);
    this._sucursalService.getSucursales(0, 20).subscribe(data => {
      this.listaSucursales = this.listaSucursales.concat(...data);
    });
  }

  //como se llama el atributo id
  protected getIdParamName(): string {
    return "id";
  }

  //llama el servicio y obtiene el role y lo carga al bean
  protected loadBean(cod: any) {
    this._cajaService.getCaja(cod).subscribe(data => {
      this.caja = data;
      this.isUpdate = true;
      this.sucursal.cod = data.codigoSucursal;
      this.listaSaldos = data.listaSaldos;
    });
  }

  //valida el bean antes de enviarlo
  public validate(): boolean {
    if (!this.caja.nombre) {
      this.showMessage(this.getMsg('error'), 'error', "Nombre es requerido");
      return false;
    }
    if (!this.sucursal.cod) {
      this.showMessage(this.getMsg('error'), 'error', "Sucursal es requerido");
      return false;
    }
    if (this.isUpdate) {
      this.caja.listaSaldos = this.listaSaldos;
      this.update(this.caja.id, this.caja);
    } else {
      this.caja.codigoSucursal = this.sucursal.cod;
      this.caja.listaSaldos = this.listaSaldos;
      this.save();
    }

    return true;
  }

  public validateCrearSaldo(): boolean {
    if (!this.saldoDTO.moneda) {
      this.showMessage(this.getMsg('error'), 'error', "Moneda es requerido");
      return false;
    }
    if (!this.saldoDTO.nominal) {
      this.showMessage(this.getMsg('error'), 'error', "Nominal es requerido");
      return false;
    }
    if (!this.saldoDTO.precioPromedio) {
      this.showMessage(this.getMsg('error'), 'error', "Precio promedio es requerido");
      return false;
    }

    if (this.labelBoton !== "Confirmar") {
      let bandera: boolean = false;
      this.listaSaldos.forEach(element => {
        if (element.moneda.codigo == this.saldoDTO.moneda.codigo) {
          bandera = true;
        }
      });

      if (bandera) {
        this.showMessage(this.getMsg('error'), 'error', "Ya existe saldo de esa moneda en esta caja");
        return false;
      }
    }else{
      this.cleanCreacionSaldo();
      return true;
    }

    this.saveSaldo();
    return true;
  }

  protected save() {
    this._cajaService.createCaja(this.caja).subscribe(data => {
      this.showMessage(this.getMsg('exito'), 'success', this.getMsg('administration.caja.form.save.msg.success'));
    });
    this.clean();
  }

  public clean() {
    this.caja = new cajaDTO();
    this.sucursal = new SucursalDTO();
  }

  public cleanCreacionSaldo(): void {
    this.saldoDTO = new saldoDTO();
    this.desabilitarDropdownMoneda=false;
  }

  private saveSaldo(): void {
    this.listaSaldos.push(this.saldoDTO);
    this.cleanCreacionSaldo();
  }

  public removerSaldo(row: saldoDTO): void {
    let saldos = this.listaSaldos.filter(function (e) {
      return e !== row;
    });
    this.listaSaldos = saldos;
  }

  protected update(cod: any, bean: any) {
    this._cajaService.updateCaja(cod, bean).subscribe(data => {
      this.clean();
      this.showMessage(this.getMsg('basecrud.confirm.edit.title.success'), 'success', this.getMsg('administration.caja.form.edit.msg.success'));
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
        this.saldoDTO = this.rowSelected;
        this.labelBoton = "Confirmar";
        this.desabilitarDropdownMoneda = true;
      }
    });
    this.addTableAction(this.getMsg('basecrud.actions.button.delete'), () => {
      if (this.rowSelected != null) {
        this.removerSaldo(this.rowSelected);
      }
    });
  }

  mostrarArqueoFuncion() {
    if (this.saldoDTO.moneda) {
      this.mostrarArqueo = true;
    }

  }

  public recibirValor(event: any) {
    console.log(event);
    let denominacionesYCantidades: Array<DenominacionCantidadDTO> = event.denominaciones.map(denominacion => {
      let denominacionCantidadDTO: DenominacionCantidadDTO = new DenominacionCantidadDTO(denominacion.valor, denominacion.cantidad, event.idFx);
      return denominacionCantidadDTO;
    });
    this.saldoDTO.denominacionesYCantidades = denominacionesYCantidades;
    this.saldoDTO.nominal = event.total;
    this.mostrarArqueo = false;
  }

}