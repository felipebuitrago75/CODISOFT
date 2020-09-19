import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../../services/fx-service';
import { SucursalService } from '../../../services/sucursal-service';
import { CierreService } from '../../../services/cierre-service';
import { DenominacionDTO } from '../../../dtos/denominacionDTO';
import { cierreDTO } from '../../../dtos/cierreDTO';
import { ParFxDTO } from '../../../dtos/parFxDTO';
import { Fx } from '../../../dtos/fxDTO';
import { PathConstants } from '../../../util/path.constants';
import { BaseScreenComponent } from '../../../components/base-screen.component';
import { CierreTurnoDTO } from '../../../dtos/cierreTurnoDTO';


@Component({
  templateUrl: './listado-cierres.component.html'
})
export class ListaCierresComponent extends BaseScreenComponent<any> implements OnInit {


  public listaSucursales: Array<any> = [];
  public sucursalFiltro: any = null;
  public fechaFiltro: Date = null;
  public cierre: cierreDTO = null;

  public filaMonedas: Array<any> = [];

  public filaSaldosInicial: Array<any> = [];
  public filaCostoPromInicial: Array<any> = [];
  public filaValorGiroInicial: Array<any> = [];

  public filaCompras: Array<any> = [];
  public filaComprasPrecioProm: Array<any> = [];
  public filaComprasValorGiro: Array<any> = [];

  public filaVentas: Array<any> = [];
  public filaPrecioPromVentas: Array<any> = [];
  public filaVentasValorGiro: Array<any> = [];

  public filaSaldosPrecierre: Array<any> = [];
  public filaValorGiroPrecierre: Array<any> = [];

  public filaIngresos: Array<any> = [];
  public filaEgresos: Array<any> = [];

  public filaTrasladosEntrantes: Array<any> = [];
  public filaTrasladosEntrantesPrecioProm: Array<any> = [];
  public filaTrasladosEntrantesValorGiro: Array<any> = [];

  public filaTrasladosSalientes: Array<any> = [];
  public filaTrasladosSalientesPrecioProm: Array<any> = [];
  public filaTrasladosSalientesValorGiro: Array<any> = [];

  public filaSaldosFinal: Array<any> = [];
  public filaCostoPromFinal: Array<any> = [];
  public filaValorGiroFinal: Array<any> = [];

  public filaPrecioValActual: Array<any> = [];

  public filaPYGCajaMensual: Array<any> = [];
  public filaPYGCajaDiaria: Array<any> = [];

  public filaPYGValoracionDiaria: Array<any> = [];

  public filaPYGValoracionTrasDiarios: Array<any> = [];
  public filaPYGValoracionTrasMensual: Array<any> = [];

  public filaPrecioValoracionAnterior: Array<any> = [];

  public filaValPrecierreAnterior: Array<any> = [];
  public filaValPrecierreActual: Array<any> = [];
  public filaValCierreActual: Array<any> = [];

  public filaPygValPrecierreDiario : Array<any> = [];
  public filaPygValPrecierreAcumulado : Array<any> = [];

  public filaPygValCierreDiario : Array<any> = [];
  public filaPygValCierreAcumulado : Array<any> = [];


  public filaPygTrasladosEntrantes : Array<any> = [];
  public filaPygTrasladosSalientes : Array<any> = [];

  public filaPygBruto : Array<any> = [];

  public filaPygValPrecierreMensual: Array<any> = [];
  public filaPygValCierreMensual: Array<any> = [];
  


  rangeDates: Date[];
  minDate: Date;
  maxDate: Date;
  es: any;
  invalidDates: Array<Date>;

  constructor(protected _sucursalService: SucursalService,
    protected _cierreService: CierreService,
    protected router: Router,
    protected route: ActivatedRoute,
    protected location: Location,
    protected messageService: MessageService,
    protected confirmationService: ConfirmationService) {
    super(router, route, location, messageService, confirmationService);
  }

  //valida el bean y retorna 
  protected validate(): boolean {
    return true;

  }

  ngOnInit() {
    super.ngOnInit();
    this._sucursalService.getSucursales(0, 20).subscribe(data => {
      this.listaSucursales.push({ label: 'SELECCIONE', value: null });
      data.forEach(element => {
        this.listaSucursales.push({ label: element.nombre, value: element.id });
      });
    });


  }


  public openNew() {
    this.router.navigate(["cierres"]);
  }

  public openEdit(row: any) {
    this.router.navigate(['cierres', row.id]);
  }

  public buscarConFiltro() {

    this.cierre = null;

    this.filaMonedas = [];

    this.filaSaldosInicial = [];
    this.filaCostoPromInicial = [];
    this.filaValorGiroInicial = [];

    this.filaCompras = [];
    this.filaComprasPrecioProm = [];
    this.filaComprasValorGiro = [];

    this.filaVentas = [];
    this.filaPrecioPromVentas = [];
    this.filaVentasValorGiro = [];

    this.filaIngresos = [];
    this.filaEgresos = [];

    this.filaTrasladosEntrantes = [];
    this.filaTrasladosEntrantesPrecioProm = [];
    this.filaTrasladosEntrantesValorGiro = [];

    this.filaTrasladosSalientes = [];
    this.filaTrasladosSalientesPrecioProm = [];
    this.filaTrasladosSalientesValorGiro = [];

    this.filaSaldosFinal = [];
    this.filaCostoPromFinal = [];
    this.filaValorGiroFinal = [];

    this.filaPrecioValActual = [];

    this.filaPYGCajaMensual = [];
    this.filaPYGCajaDiaria = [];

    this.filaPYGValoracionDiaria = [];

    this.filaPYGValoracionTrasDiarios = [];
    this.filaPYGValoracionTrasMensual = [];

    this.filaSaldosPrecierre = [];
    this.filaValorGiroPrecierre = [];

    this.filaPrecioValoracionAnterior =[];
    this.filaValPrecierreAnterior= [];
    this.filaValPrecierreActual=[];
    this.filaValCierreActual=[];

    this.filaPygValPrecierreDiario=[];
    this.filaPygValPrecierreAcumulado=[];

    this.filaPygValCierreDiario=[];
    this.filaPygValCierreAcumulado=[];

    this.filaPygTrasladosEntrantes=[];
    this.filaPygTrasladosSalientes=[];

    this.filaPygBruto=[];

    this.filaPygValPrecierreMensual=[];
    this.filaPygValCierreMensual=[];

    if (!this.sucursalFiltro || !this.fechaFiltro) {
      this.showMessage("Error", 'error', 'Datos obligatorios no ingresados, por favor ingrese fecha y sucursal');

    } else {

      let ingresosDiarios = 0;
      let egresosDiarios = 0;

      this._cierreService.obtenerCierresSucursalPorFecha(this.sucursalFiltro, this.fechaFiltro).subscribe(data => {
        data.forEach(element => {
          this.cierre = element;
          this.filaMonedas.push(this.cierre.moneda.codigo);
          this.filaSaldosInicial.push(this.cierre.saldoInicial);
          this.filaCostoPromInicial.push(this.cierre.precioPromedioInicial);
          this.filaValorGiroInicial.push(this.cierre.valorCompraInicial);
          this.filaCompras.push(this.cierre.nominalCompra);
          this.filaComprasPrecioProm.push(this.cierre.precioCompra);
          this.filaComprasValorGiro.push(this.cierre.valorCompra);
          this.filaVentas.push(this.cierre.nominalVenta);
          this.filaPrecioPromVentas.push(this.cierre.precioVenta);
          this.filaVentasValorGiro.push(this.cierre.valorVenta);

          if(this.cierre.moneda.codigo=="COP"){
            this.filaIngresos.push(ingresosDiarios);
            this.filaEgresos.push(egresosDiarios);
          }else{
            this.filaIngresos.push(0);
            this.filaEgresos.push(0);
            ingresosDiarios = this.cierre.ingresosDiarios;
            egresosDiarios = this.cierre.egresosDiarios;
          }
  
          this.filaTrasladosEntrantes.push(this.cierre.nominalTrasladosEntrantes);
          this.filaTrasladosEntrantesPrecioProm.push(this.cierre.precioTrasladoEntrantes);
          this.filaTrasladosEntrantesValorGiro.push(this.cierre.valorGiroTrasladoEntrantes);
          this.filaTrasladosSalientes.push(this.cierre.nominalTrasladosSalientes);
          this.filaTrasladosSalientesPrecioProm.push(this.cierre.precioTrasladoSalientes);
          this.filaTrasladosSalientesValorGiro.push(this.cierre.valorGiroTrasladoSalientes);
        
          this.filaSaldosFinal.push(this.cierre.saldoFinal);
          this.filaCostoPromFinal.push(this.cierre.precioPromedioFinal);
          this.filaValorGiroFinal.push(this.cierre.valorCompraFinal);
  
          this.filaPrecioValActual.push(this.cierre.precioValoracion);
          this.filaPYGCajaMensual.push(this.cierre.pygCaja);
          this.filaPYGCajaDiaria.push(this.cierre.pygCajaD);
          this.filaPYGValoracionDiaria.push(this.cierre.pygValoracionD);
          this.filaPYGValoracionTrasDiarios.push(this.cierre.pygTrasladoDiario);
          this.filaPYGValoracionTrasMensual.push(this.cierre.pygTrasladoAcumulado);

          this.filaSaldosPrecierre.push(this.cierre.saldoPrecierre);
          this.filaValorGiroPrecierre.push(this.cierre.valorGiroPrecierre);

          this.filaPrecioValoracionAnterior.push(this.cierre.precioValoracionAnterior);
          this.filaValPrecierreAnterior.push(this.cierre.valoracionPrecierreAnterior);
          this.filaValPrecierreActual.push(this.cierre.valoracionPrecierreActual);
          this.filaValCierreActual.push(this.cierre.valoracion);

          this.filaPygValPrecierreDiario.push(this.cierre.pygValPrecierreDiaria);
          this.filaPygValPrecierreAcumulado.push(this.cierre.pygValPrecierreAcumulada);

          this.filaPygValCierreDiario.push(this.cierre.pygValCierreDiaria);
          this.filaPygValCierreAcumulado.push(this.cierre.pygValCierreAcumulada);

          this.filaPygTrasladosEntrantes.push(this.cierre.pygTrasladoEntrante);
          this.filaPygTrasladosSalientes.push(this.cierre.pygTrasladoSaliente);

          this.filaPygValPrecierreMensual.push(this.cierre.pygValPrecierreMensual);
          this.filaPygValCierreMensual.push(this.cierre.pygValCierreMensual);

          this.filaPygBruto.push(this.cierre.pygBruto);
        });
      });
    }
  }

}
