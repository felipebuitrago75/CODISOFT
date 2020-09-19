import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../../services/fx-service';
import { SucursalService } from '../../../services/sucursal-service';
import { CierreService } from '../../../services/cierre-service';
import { reporteEstadoActualDTO } from '../../../dtos/reporteEstadoActual';
import { PathConstants } from '../../../util/path.constants';
import { BaseScreenComponent } from '../../../components/base-screen.component';
import { CierreTurnoDTO } from '../../../dtos/cierreTurnoDTO';


@Component({
  templateUrl: './reporte-estado-actual-detallado.html'
})
export class ReporteEstadoActualDetalladoComponent extends BaseScreenComponent<any> implements OnInit {


  public fechaFiltro: Date = new Date();

  public filasreporte: Array<reporteEstadoActualDTO> = null;
  public filasTotales: Array<reporteEstadoActualDTO> = [];
  public cantidadFx: number;


  constructor(protected _sucursalService: SucursalService,
    protected _cierreService: CierreService,
    protected router: Router,
    protected route: ActivatedRoute,
    protected location: Location,
    protected messageService: MessageService,
    protected confirmationService: ConfirmationService,
    protected _fxService: FxService) {
    super(router, route, location, messageService, confirmationService);
  }

  //valida el bean y retorna 
  protected validate(): boolean {
    return true;

  }

  ngOnInit() {
    super.ngOnInit();
    this.buscarConFiltro();
  }



  public openNew() {
    this.router.navigate([""]);
  }

  public openEdit(row: any) {

  }

  public buscarConFiltro() {

    this.filasreporte = [];

    this._fxService.getFxs(0, 20).subscribe(fxs => {
      this.cantidadFx = 2;

      this._cierreService.reporteEstadoActualDetallado().subscribe(data => {
        this.filasreporte = data;
        console.log(data);

        fxs.forEach(element => {

          let reporte = new reporteEstadoActualDTO();
          reporte.codigoMoneda = element.codigo;
          reporte.nominal = 0;
          this.filasreporte.filter(fila => fila.codigoMoneda == element.codigo).forEach(resul => {
            reporte.nominal += resul.nominal;
          });

          this.filasTotales.push(reporte);

        });
      });
    });
  }


}
