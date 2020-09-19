import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BaseScreenComponent } from '../../../components/base-screen.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService, ConfirmationService } from 'primeng/primeng';
import { FxService } from '../../../services/fx-service';
import { DenominacionDTO } from '../../../dtos/denominacionDTO';

import { CurrencyPipe } from '@angular/common';
import { DenominacionCantidadDTO } from '../../../dtos/denominacionCantidadDTO';

@Component({
    selector: 'app-recibir',
    templateUrl: 'recibir.component.html',
    styleUrls: ["./recibir.component.css"],
    providers: [FxService]

})
export class RecibirComponent implements OnInit {

    @Input() fx: string;
    @Input() fxBase: string;
    @Input() valorTotal: any;
    @Input() show: boolean;
    @Input() esCompra: boolean;

    @Output() PasarDenominaciones = new EventEmitter();
    @Output() Cancelar = new EventEmitter();

    private _fxService: FxService
    public moneda: any;
    public denominaciones: DenominacionCantidadDTO[] = [];
    public ready: boolean = false;
    public total: any = 0;
    public correcto: boolean = false;
    public diferencia: any = 0;

    constructor(private fxService: FxService) {
        this._fxService = fxService;
    }

    ngOnInit() {

        if (this.esCompra) {
            this._fxService.getFx(this.fx).subscribe(data => {
                this.moneda = data;
                data.denominaciones.forEach(element => {
                    this.denominaciones.push(new DenominacionCantidadDTO(element, 0, data.id ));

                });
                this.ready = true;
            });
        } else {
            this._fxService.getFx(this.fxBase).subscribe(data => {
                this.moneda = data;
                data.denominaciones.forEach(element => {
                    this.denominaciones.push(new DenominacionCantidadDTO(element, 0, data.id ));

                });
                this.ready = true;
            });
        }

    }

    public mostrar() {

        this.total = 0;
        this.denominaciones.forEach(element => {
            let totalFila: any = element.valor * element.cantidad;
            this.total = this.total + totalFila;
        });
        if (this.total == this.valorTotal) {
            this.correcto = true;
        } else {
            this.correcto = false;
        }

        this.diferencia = this.total - this.valorTotal;
    }

    public getDenominacions() {
        return this.denominaciones.map(d => d.valor);
    }
    public getMinDenominacion(): any {
        return Math.min(...this.getDenominacions());
    }


    public lanzar(event){
        this.PasarDenominaciones.emit( {denominaciones: this.denominaciones , total : this.total} );
    }

    public omitir(){
        this.PasarDenominaciones.emit( {denominaciones: undefined} );
    }

    public cancelar(event){
        this.Cancelar.emit();
    }

    public limpiar(){
        this.denominaciones.forEach(element => {
            element.cantidad=0;
        });
        this.total=0;
        this.correcto=false;
    }

    public eliminarDenominacion(){
        this.denominaciones.forEach(element=>{
            element.cantidad=0;
        });
        this.diferencia = this.total - this.valorTotal;

        if (this.total == this.valorTotal) {
            this.correcto = true;
        } else {
            this.correcto = false;
        }
    }
}

