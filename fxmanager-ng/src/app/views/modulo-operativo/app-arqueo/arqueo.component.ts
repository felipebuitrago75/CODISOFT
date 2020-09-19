import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BaseScreenComponent } from '../../../components/base-screen.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../../services/fx-service';
import { DenominacionDTO } from '../../../dtos/denominacionDTO';
import { Fx } from '../../../dtos/fxDTO';
import { CurrencyPipe } from '@angular/common';
import { arqueoDTO } from '../../../dtos/arqueoDTO';
@Component({
    selector: 'app-arqueo',
    templateUrl: 'arqueo.component.html',
    styleUrls: ["./arqueo.component.css"],
    providers: [FxService]

})
export class ArqueoComponent implements OnInit {

    @Input() fx: string;
    @Input() show: boolean=true;

    @Output() PasarDenominaciones = new EventEmitter();

    private _fxService: FxService
    public moneda: any;
    public denominaciones: DenominacionDTO[] = [];
    public ready: boolean = false;
    public total: any = 0;
  

    constructor(private fxService: FxService) {
        this._fxService = fxService;
    }

    ngOnInit() {
        this._fxService.getFx(this.fx).subscribe(data => {
            this.moneda = data;
            data.denominaciones.forEach(element => {
                this.denominaciones.push(new DenominacionDTO(element, 0));

            });
            this.ready = true;
        });

    }

    public mostrar() {

        this.total = 0;
        this.denominaciones.forEach(element => {
            let totalFila: any = element.valor * element.cantidad;
            this.total = this.total + totalFila;
        });
    }

    public getDenominacions() {
        return this.denominaciones.map(d => d.valor);
    }
    public getMinDenominacion(): any {
        return Math.min(...this.getDenominacions());
    }


    public lanzar(event) {
        this.PasarDenominaciones.emit({ denominaciones: this.denominaciones, total : this.total , fx: this.fx, idFx: this.moneda.id});
    }

    public omitir() {
        this.PasarDenominaciones.emit({ denominaciones: undefined });
    }

  
    public eliminarDenominacion(){
        this.denominaciones.forEach(element => {
            element.cantidad=0;
        });
    }

}

