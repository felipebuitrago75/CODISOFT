import { Component, OnInit } from '@angular/core';
import { BaseCrudSearchComponent } from '../../../components/base-crud-search.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../../services/fx-service';
import { SucursalService } from '../../../services/sucursal-service';
import { CierreService } from '../../../services/cierre-service';
import { reportePortafolioDTO } from '../../../dtos/reportePortafolioDTO';
import { PathConstants } from '../../../util/path.constants';
import { BaseScreenComponent } from '../../../components/base-screen.component';
import { CierreTurnoDTO } from '../../../dtos/cierreTurnoDTO';


@Component({
  templateUrl: './reporte-portafolio.html'
})
export class ReportePortafolioComponent extends BaseScreenComponent<any> implements OnInit {


  public fechaFiltro: Date = null;

  public filasreporte: Array<reportePortafolioDTO> =null;
  public cantidadFx: number;



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

  }



  public openNew() {
    this.router.navigate(["cierres"]);
  }

  public openEdit(row: any) {
    this.router.navigate(['cierres', row.id]);
  }

  public buscarConFiltro() {

    this.filasreporte = [];


    if (!this.fechaFiltro) {
      this.showMessage("Error", 'error', 'Datos obligatorios no ingresados, por favor ingrese fecha');

    } else {
      this._fxService.getFxs(0, 20).subscribe(fxs => {
        this.cantidadFx = fxs.length;
        console.log(fxs);
        this._cierreService.reportePortafolioPorFecha(this.fechaFiltro).subscribe(data => {
          this.filasreporte = data;
          console.log(data);

        });

      });
    }
  }

}
