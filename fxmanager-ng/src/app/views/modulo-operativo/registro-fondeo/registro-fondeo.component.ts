import { Component, OnInit } from '@angular/core';
import { BaseCrudFormComponent } from '../../../components/base-crud-form.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../../services/fx-service';
import { SucursalService } from '../../../services/sucursal-service';


import { DenominacionDTO } from '../../../dtos/denominacionDTO';

import { Fx } from '../../../dtos/fxDTO';
import { SucursalDTO } from '../../../dtos/sucursalDTO';
import { FondeoDTO } from '../../../dtos/fondeoDTO';
import { FondeoService } from '../../../services/fondeo-service';
import { DenominacionCantidadDTO } from '../../../dtos/denominacionCantidadDTO';
import { TrasladoService } from '../../../services/traslado-service';
import { TurnoService } from '../../../services/turno-service';
import { CajaService } from '../../../services/caja-service';
import { TurnoDTO } from '../../../dtos/turnoDTO';
import { cajaDTO } from '../../../dtos/cajaDTO';


@Component({
  templateUrl: 'registro-fondeo.component.html'
})
export class RegistroFondeoComponent extends BaseCrudFormComponent<any> implements OnInit {



  public listaTurnosDestino: Array<TurnoDTO> = [];
  public fondeo: FondeoDTO = new FondeoDTO();
  public listaFxs: Array<any> = [];
  public turnoDestino: TurnoDTO = null;
  public turno: TurnoDTO = null;
  public cajaActual: cajaDTO = null;
  public mostrarArqueo = false;

  constructor(protected _fxService: FxService, protected router: Router, protected route: ActivatedRoute,
    protected location: Location, protected messageService: MessageService,
    protected confirmationService: ConfirmationService,
    protected _sucursalService: SucursalService,
    protected _turnoService: TurnoService,
    protected _fondeoService: FondeoService,
    protected _cajaService: CajaService,
  ) {
    super(router, route, location, messageService, confirmationService);

  }


  obtenerTurnoSucursal() {
    this._sucursalService.getSucursal(this.cajaActual.codigoSucursal).subscribe(data => {
      let sucursalOrigen = data;
      if (!sucursalOrigen) {
        this.showMessage(this.getMsg('error'), 'error', "Debe iniciar turno");
      } else {
        this._turnoService.getTurnosActivosPorSucursal(sucursalOrigen.id).subscribe(data => {
          let turnoDefault: TurnoDTO = new TurnoDTO();
          turnoDefault.id = null;
          this.listaTurnosDestino.push(turnoDefault);
            this.listaTurnosDestino = this.listaTurnosDestino.concat(...data);
        });
      }

    });
  }

  cargarMonedas() {
    this._fxService.getFxs(0, 20).subscribe(data => {
      this.listaFxs = new Array<any>();
      this.listaFxs.push({ '': '' });
      data.forEach(element => {
        if (element.codigo == 'COP') {
        } else {
          this.listaFxs.push(element);
        }
      });
    });
  }

  ngOnInit() {
    super.ngOnInit();
    this.turno = this._turnoService.getTurno();
    if (this.turno === null || this.turno === undefined) {
      this.showMessage(this.getMsg('administration.operacion.save.error.turno'), 'error', '');
    } else {
      this._cajaService.getCaja(this.turno.idCaja.toString()).subscribe(data => {
        this.cajaActual = data;
        this.obtenerTurnoSucursal();
        this.cargarMonedas();
      });

    }
  }

  //como se llama el atributo id
  protected getIdParamName(): string {
    return "id";
  }

  //llama el servicio y obtiene el role y lo carga al bean
  protected loadBean(cod: any) {
  }

  //valida el bean antes de enviarlo
  public validate(): boolean {

    if (!this.fondeo.fx || !this.fondeo.fx.concepto) {
      this.showMessage(this.getMsg('error'), 'error', "Moneda es requerido");
      return false;
    }
    if (this.turno.id == this.fondeo.turnoDestino.id) {
      this.showMessage(this.getMsg('error'), 'error', "El turno destino debe ser diferente al turno origen");
      return false;
    }
    if (!this.fondeo.turnoDestino) {
      this.showMessage(this.getMsg('error'), 'error', "debe selecionar turno destino");
      return false;
    }
    if (!this.turno) {
      this.showMessage(this.getMsg('error'), 'error', "debe iniciar turno");
      return false;
    }
    if (!this.fondeo.valorGiro) {
      this.showMessage(this.getMsg('error'), 'error', "Valor giro es requerido");
      return false;
    }

    this.save();
    return true;
  }

  protected save() {
    this.fondeo.estado = "REALIZADO";
    this.fondeo.turnoOrigen = this.turno;
    this.confirmationService.confirm({
      message: "¿Esta seguro de realizar esta accion?",
      accept: () => {
        this._fondeoService.create(this.fondeo).subscribe(data => {
          this.showMessage(this.getMsg('exito'), 'success', "Fondeo realizado con exito");
          this.router.navigate([""]);
        });
      }
    });
    this.clean();
  }

  public clean() {
  }

  protected update(cod: any, bean: any) {
  }

  mostrarArqueoFuncion() {
    if (this.fondeo.fx) {
      this.mostrarArqueo = true;
    }

  }

  public recibirValor(event: any) {
    console.log(event);
    let denominacionesYCantidades: Array<DenominacionCantidadDTO> = event.denominaciones.map(denominacion => {
      let denominacionCantidadDTO: DenominacionCantidadDTO = new DenominacionCantidadDTO(denominacion.valor, denominacion.cantidad, event.idFx);
      return denominacionCantidadDTO;
    });
    this.fondeo.denominacionesYCantidades = denominacionesYCantidades;
    this.fondeo.valorGiro = event.total;
    this.mostrarArqueo = false;
  }


}