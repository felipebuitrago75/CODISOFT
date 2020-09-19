import { Component, OnInit } from '@angular/core';
import { MessageService, ConfirmationService, MenuItem } from 'primeng/primeng';
import { BaseScreenComponent } from '../../components/base-screen.component'
import { BaseCrudFormComponent } from '../../components/base-crud-form.component';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { preCierreDTO } from '../../dtos/preCierreDTO';
import { CierreService } from '../../services/cierre-service';
import { CierreTurnoService } from '../../services/cierre-turno-service';
import { CierreTurnoDTO } from '../../dtos/cierreTurnoDTO';
import { cierreDTO } from '../../dtos/cierreDTO';
@Component({
    templateUrl: 'precierre.component.html'
})

export class PrecierreComponent extends BaseCrudFormComponent<any> implements OnInit {

    public precierre: preCierreDTO = null;
    public listaFxprecierres: Array<any> = [];
    public displayPrecierre: boolean = false;

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
    public fxsValorSaldosReal: Array<any> = [];
    public filaSaldosDiferencia: Array<any> = [];

    constructor(protected router: Router, protected route: ActivatedRoute,
        protected location: Location, protected messageService: MessageService,
        protected confirmationService: ConfirmationService,
        protected _cierreTurnoService: CierreTurnoService
    ) {
        super(router, route, location, messageService, confirmationService);
    }

    ngOnInit() {
        super.ngOnInit();
        let cierreTurno = new CierreTurnoDTO();
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
        this.fxsValorSaldosReal = [];
        this.filaSaldosDiferencia = [];
    }

    //como se llama el atributo id
    protected getIdParamName(): string {
        return "id";
    }

    //llama el servicio y obtiene el role y lo carga al bean
    protected loadBean(id: any) {
       let cierreT: CierreTurnoDTO= new CierreTurnoDTO();
       cierreT.idTurno=id;
        this._cierreTurnoService.createCierreTurno(cierreT).subscribe(data => {
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
                    }
                });

                this.displayPrecierre = true;
            });
        });
    }

    //valida el bean antes de enviarlo
    public validate(): boolean {
        return true;
    }

    protected save() { }

    public volver(){
        this.router.navigate(["lista-precierres"]);
    }
    public clean() { }

    public update() { }

}