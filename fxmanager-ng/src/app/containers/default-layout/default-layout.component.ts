import { Component, Input, OnInit } from '@angular/core';
import { nav_coordinador } from './../../_nav_coordinador';
import { nav_operador } from './../../_nav_operador';
import { nav_observador } from './../../_nav_observador';
import { nav_gerente } from './../../_nav_gerente';
import { nav_super_admin } from './../../_nav_super_admin';

import { PostService } from '../PostService';
import { FxService } from '../../services/fx-service';
import { SucursalService } from '../../services/sucursal-service';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { MessageService, ConfirmationService, MenuItem } from 'primeng/primeng';
import { TurnoService } from '../../services/turno-service';
import { CajaService } from '../../services/caja-service';

import { cajaDTO } from '../../dtos/cajaDTO';
import { SucursalDTO } from '../../dtos/sucursalDTO';
import { TurnoDTO } from '../../dtos/turnoDTO';
import { CierreTurnoService } from '../../services/cierre-turno-service';
import { CierreTurnoDTO } from '../../dtos/cierreTurnoDTO';
import { BaseScreenComponent } from '../../components/base-screen.component';
import { CierreService } from '../../services/cierre-service';
import { FlujoExtraordinarioService } from '../../services/flujo-extraordinario-service';
import { TrasladoService } from '../../services/traslado-service';
import { preCierreDTO } from '../../dtos/preCierreDTO';
import { SecurityService } from '../../core/security/services/security-service';
import { AuthService } from '../../core/security/services/auth-service';

import { RolConstants } from '../../util/rol.constants';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html',
  providers: [SecurityService]
})
export class DefaultLayoutComponent extends BaseScreenComponent<any> implements OnInit {
  public msgs = [];
  public navItems: any = {};
  public sidebarMinimized = true;
  private changes: MutationObserver;
  public element: HTMLElement = document.body;
  public listaFxs: Array<any> = [];
  public listaPreciosSucursal: Array<any> = [];

  public caja: cajaDTO = new cajaDTO();
  public sucursal: SucursalDTO = new SucursalDTO();
  public listaSucursales: Array<SucursalDTO> = [];
  public listaCajas: Array<cajaDTO> = [];
  public turno: TurnoDTO = new TurnoDTO();
  public precierre: preCierreDTO = null;

  public mostrarArqueo = false;

  public nombreUsuario: string = "";

  public fxsValorSaldosReal: Array<any> = [];

  public filaMonedas: Array<any> = [];
  public filaSaldoInicial: Array<any> = [];
  public filaCompras: Array<any> = [];
  public filaVentas: Array<any> = [];
  public filaIngresos: Array<any> = [];
  public filaEgresos: Array<any> = [];
  public filaTrasladosSalientes: Array<any> = [];
  public filaTrasladosEntrantes: Array<any> = [];
  public filaSaldosFinales: Array<any> = [];

  public filaSaldosReales: Array<any> = [];
  public filaSaldosDiferencia: Array<any> = [];

  public listaFxprecierres: Array<any> = [];

  public displayTurno: boolean = false;
  public displaySaldos: boolean = false;
  public displayPrecierre: boolean = false;

  public esArqueo: boolean = false;

  public rol:string ="";

  public dt = new Date();

  public mostrarFecha =false;


  constructor(private postService: PostService,
    protected _fxService: FxService,
    protected _sucursalService: SucursalService,
    protected _cajaService: CajaService,
    protected router: Router,
    protected route: ActivatedRoute,
    protected location: Location,
    protected messageService: MessageService,
    protected _turnoService: TurnoService,
    protected confirmationService: ConfirmationService,
    protected _cierreTurnoService: CierreTurnoService,
    protected _cierreService: CierreService,
    protected _flujoExtraordinarioService: FlujoExtraordinarioService,
    protected _trasladoService: TrasladoService,
    protected authService: AuthService
  ) {
    super(router, route, location, messageService, confirmationService);
    this.changes = new MutationObserver((mutations) => {
      this.sidebarMinimized = document.body.classList.contains('sidebar-minimized');
    });

    this.changes.observe(<Element>this.element, {
      attributes: true
    });
  }

  ngOnInit() {
    super.ngOnInit();

    let tokenBody = this.authService.getToken()['access_token'].toString().split(".")[1];
    let body = JSON.parse(atob(tokenBody));
    let authorities = body.authorities[0];

    if(authorities == RolConstants.ROLE_OPERADOR){
      this.navItems = nav_operador;
      this.rol = RolConstants.ROLE_OPERADOR;
    }
    if(authorities == RolConstants.ROLE_COORDINADOR){
      this.navItems = nav_coordinador;
      this.rol=RolConstants.ROLE_COORDINADOR;
    }
    if(authorities == RolConstants.ROLE_OBSERVADOR){
      this.navItems = nav_observador;
      this.rol=RolConstants.ROLE_OBSERVADOR;
    }
    if(authorities == RolConstants.ROLE_GERENTE){
      this.navItems = nav_gerente;
      this.rol=RolConstants.ROLE_GERENTE;
    }
    if(authorities == RolConstants.SUPER_ADMIN){
      this.navItems = nav_super_admin;
      this.rol=RolConstants.SUPER_ADMIN;
    }



    this._fxService.getFxs(0, 10).subscribe(data => {
      this.listaFxs = data;
      this.listaFxs.forEach(element => {
        element.mostrar = false;
        this.listaFxprecierres.push(element);
      });
    });

    this.cargarSucursales();
    this.turno = this._turnoService.getTurno();
    this.obtenerNombreUsuario();

    if (this.turno) {
      this._cajaService.getCaja(this.turno.idCaja.toString()).subscribe(data => {
        this.caja = data;
        this._sucursalService.getSucursal(this.caja.codigoSucursal).subscribe(data => {
          this.sucursal = data;
          this.listaPreciosSucursal = data.listaPrecios;

          /**
           * 
           
          //se obtiene el id del turno
          this._turnoService.getTurnoObservable(this.nombreUsuario).subscribe(turno =>{
            if(turno.id){
              this.turno.id=turno.id;
            }
          });
          */

        });
      });
      
    }
  }

  public obtenerNombreUsuario() {
    let tokenBody = this.authService.getToken()['access_token'].toString().split(".")[1];
    let body = JSON.parse(atob(tokenBody));
    this.nombreUsuario = body.user_name;
  }

  public cargarListaCajas(): void {
    this.listaCajas = [];
    let cajaDefault: cajaDTO = new cajaDTO();
    cajaDefault.nombre = "Seleccione";
    cajaDefault.id = null;
    this.listaCajas.push(cajaDefault);
    this._cajaService.getCajasPorSucursal(this.sucursal.cod).subscribe(data => {
      this.listaCajas = this.listaCajas.concat(...data);
    });
  }

  public cargarSucursales() {
    let sucursalDefault: SucursalDTO = new SucursalDTO();
    sucursalDefault.nombre = "Seleccione";
    sucursalDefault.cod = null;
    this.listaSucursales.push(sucursalDefault);
    this._sucursalService.getSucursales(0, 20).subscribe(data => {
      this.listaSucursales = this.listaSucursales.concat(...data);
    });
  }

  protected guardarTurno() {
    this.obtenerNombreUsuario();
    this.turno = new TurnoDTO();
    this.turno.idCaja = this.caja.id;
    this.turno.nombreUsuario = this.nombreUsuario;
    this.displayTurno = false;
    this._turnoService.createTurno(this.turno).subscribe(data => {
      this.showMessage(this.getMsg('exito'), 'success', "Turno creado correctamente");
      this._turnoService.setTurno(data);
      this._sucursalService.getSucursal(this.sucursal.cod).subscribe(data => {
        this.listaPreciosSucursal = data.listaPrecios;

        this.turno = this._turnoService.getTurno();
         //se obtiene el id del turno
         /**
          * 
         
         this._turnoService.getTurnoObservable(this.nombreUsuario).subscribe(turno =>{
          if(turno.id){
            this.turno.id=turno.id;
          }
        });
         */

      });
      
      this.router.navigate(['']);
    });
  }

  public mostrarSesion() {
    this.displayTurno = true;
  }

  public realizarArqueo() {
    this.mostrarArqueo = true;
    this.listaFxprecierres.forEach(element => {
      element.mostrar = true;
    });
  }

  public realizarArqueoBoton() {

    let turno: TurnoDTO = this._turnoService.getTurno();
    if (turno) {
      this.esArqueo = true;
      this.realizarArqueo();
    } else {
      this.showMessage("Advertencia", 'error', "Debe iniciar turno para realizar un arqueo parcial");
    }


  }

  public cerrarTurno() {
    let cierreTurno = new CierreTurnoDTO();
    let turno: TurnoDTO = this._turnoService.getTurno();
    cierreTurno.idTurno = turno.id;
    this.precierre = null;
    this.filaMonedas = [];
    this.filaSaldoInicial = [];
    this.filaSaldosFinales = [];
    this.filaCompras = [];
    this.filaVentas = [];
    this.filaEgresos = [];
    this.filaIngresos = [];
    this.filaTrasladosSalientes = [];
    this.filaTrasladosEntrantes = [];
    this.filaSaldosReales = [];
    this._cierreTurnoService.createCierreTurno(cierreTurno).subscribe(data => {
      //this._turnoService.removeTurno();
      //this.displayTurno=false;
      //this.turno=null;
      //this.listaFxs=null;
      //this.listaPreciosSucursal=null;
      //this.router.navigate(['']);
      this.precierre = data;

      this.precierre.listaItems.forEach(element => {
        this.filaMonedas.push(element.moneda);
        this.filaSaldoInicial.push(element.saldosInicial);
        this.filaSaldosFinales.push(element.saldosFinal);
        this.filaCompras.push(element.nominalCompras);
        this.filaVentas.push(element.nominalVentas);
        this.filaEgresos.push(element.nominalEgresos);
        this.filaIngresos.push(element.nominalIngresos);
        this.filaTrasladosSalientes.push(element.nominalTrasladosSalientes);
        this.filaTrasladosEntrantes.push(element.nominalTrasladosEntrantes);

        this.fxsValorSaldosReal.forEach(saldoReal => {
          if (saldoReal.fx == element.moneda.codigo) {
            this.filaSaldosReales.push(saldoReal.total);
            this.filaSaldosDiferencia.push(saldoReal.total - element.saldosFinal);
            element.saldosRealEnCaja = saldoReal.total;
          }
        });

      });


      this.displayPrecierre = true;
    });
  }

  public confirmarPrecierre() {
    this.turno = this._turnoService.getTurno();
    this._cierreTurnoService.updateTurno(this.turno.id + "").subscribe(data => {
      this.showMessage(this.getMsg('exito'), 'success', "Precierre realizado correctamente");
      this._turnoService.removeTurno();
      this.displayTurno = false;
      this.turno = null;
      //this.listaFxs = null;
      this.listaPreciosSucursal = null;
      this.displayPrecierre = false;
      this.router.navigate(['']);
    });
  }

  public cancelarPrecierre() {
    this.turno = this._turnoService.getTurno();
    //this._turnoService.cancelarPrecierre(this.turno.id.toString()).subscribe(data => {
    if (!this.esArqueo) {
      this.showMessage(this.getMsg('exito'), 'success', "Precierre cancelado correctamente");
    }
    this.precierre = null;
    this.filaMonedas = [];
    this.filaSaldoInicial = [];
    this.filaSaldosFinales = [];
    this.filaCompras = [];
    this.filaVentas = [];
    this.filaEgresos = [];
    this.filaIngresos = [];
    this.filaTrasladosSalientes = [];
    this.filaTrasladosEntrantes = [];
    this.filaSaldosReales = [];
    this.displayPrecierre = false;
    this.displayTurno = false;
    this.fxsValorSaldosReal = [];
    this.filaSaldosDiferencia = [];

    this.esArqueo = false;
    //});
  }

  public cancelarArqueo() {

    this.precierre.turno = this.turno;

    this._turnoService.createRegistroArqueo(this.precierre).subscribe(data => {
      this.cancelarPrecierre();
    });

  }

  public cargarCajaYSaldos() {
    this._cajaService.getCaja(this.caja.id + "").subscribe(dataCaja => {
      this.caja = dataCaja;
    });
  }

  public mostrarSaldos() {
    if (this.turno) {
      this._cajaService.getCaja(this.turno.idCaja + "").subscribe(data => {
        this.caja = data;
        this.displaySaldos = true;
        console.log(JSON.stringify(this.caja));
      });
    } else {
      this.showMessage(this.getMsg('error'), 'error', "Por favor inicie turno primero");
    }
  }

  public actualizarSaldos() {
    if (this.turno) {
      this._cajaService.getCaja(this.turno.idCaja + "").subscribe(data => {
        this.caja = data;
      });
    } else {
      this.showMessage(this.getMsg('error'), 'error', "Por favor inicie turno primero");
    }
  }

  public realizarCierre() {
  
      this.confirmationService.confirm({
        key: "cdRealizarCierre",
        message: "Esta seguro de realizar este cierre",
        accept: () => {
          this._cierreService.realizarCierre().subscribe(data => {
            this.showMessage("Exito", 'success', "Cierre realizado correctamente");
          });
        }
      });
    
    
  }

  //recibe el arqueo de caja
  public recibirValor(event: any) {
    console.log(event);
    event.fx
    let valorSaldoReal = { fx: event.fx, denominaciones: event.denominaciones, total: event.total }
    this.fxsValorSaldosReal.push(valorSaldoReal);

    this.listaFxprecierres.forEach(element => {
      if (element.codigo == event.fx) {
        element.mostrar = false;
      }
    });

    console.log(this.listaFxs);
    console.log(this.fxsValorSaldosReal);
    if (this.listaFxs.length == this.fxsValorSaldosReal.length) {
      this.cerrarTurno();
    }
  }

  public realizarCierreFecha(){
    this.mostrarFecha= true;
  }

  public realizarCierreFechaAceptar(){
    this.confirmationService.confirm({
      key: "cdRealizarCierre",
      message: "Esta seguro de realizar este cierre",
      accept: () => {
        this._cierreService.realizarCierreDeUnaFecha(this.dt).subscribe(data => {
          this.showMessage("Exito", 'success', "Cierre realizado correctamente");
        });
        this.mostrarFecha= false;
      },
      reject: () => {
        this.mostrarFecha= false;
    }
    });
  }

}
