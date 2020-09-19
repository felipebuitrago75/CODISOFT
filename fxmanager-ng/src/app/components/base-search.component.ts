import { Routes, ActivatedRoute, Params, Router } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService } from 'primeng/components/common/messageservice';
import { BaseFormComponent } from './base-form.component';
import { LazyLoadEvent, MenuItem, ConfirmationService } from 'primeng/primeng';

//se implementa para tener un formulario de filtro y una tabla 
export abstract class BaseSearchComponent<T, R> extends BaseFormComponent<T> {

    public maxRows = 150;

    public totalRecords=1;

    protected loading: boolean = true;

    public hasNext: boolean = true;

    //arreglo vacio
    public rows: R[] = [];

    public rowSelected: R = null;

    public beanFilter: T = null;

    public tableActions: MenuItem[] = [];

    constructor(protected router: Router, protected route: ActivatedRoute, protected location: Location, protected messageService: MessageService, protected confirmationService: ConfirmationService) {
        super(router, route, location, messageService, confirmationService);
    }

    //acciones de click derecho en la tabla
    protected addTableAction(label: string, command: any, icon?: string) {
        if (this.tableActions == null) {
            this.tableActions = [];
        }
        this.tableActions.push({ label: label, command: command, icon: icon });
    }

    public search() {
        this.submit();
    }

    protected createDoFinish(): any {
        return (newRows?: R[]) => {
            this.loading = false;
            if (newRows != null) {

                this.totalRecords= this.totalRecords + newRows.length+1;
                this.hasNext = newRows.length == this.maxRows;
            }
        };
    }

    //procesa la busqueda 
    protected process() {
        this.rows = [];
        this.beanFilter = JSON.parse(JSON.stringify(this.bean));
        this.onLoad(this.beanFilter, 0, this.maxRows, this.createDoFinish());
    }

    public clean() {
        super.clean();
        this.rows = [];
    }

    public onLazyLoad(event: LazyLoadEvent) {
        if (!this.loading && this.hasNext) {
            this.loading = true;
            this.onLoad(this.beanFilter, this.rows.length, this.maxRows, this.createDoFinish());
        }
    }


    public abstract onLoad(bean: T, first: number, max: number, doFinish?);
}