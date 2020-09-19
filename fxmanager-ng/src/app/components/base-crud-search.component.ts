import { Routes, ActivatedRoute, Params, Router } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService } from 'primeng/components/common/messageservice';
import { BaseSearchComponent } from './base-search.component';
import { LazyLoadEvent, MenuItem, ConfirmationService } from 'primeng/primeng';
export abstract class BaseCrudSearchComponent<T, R> extends BaseSearchComponent<T, R>{

    constructor(protected router: Router, protected route: ActivatedRoute, protected location: Location, protected messageService: MessageService, protected confirmationService: ConfirmationService) {
        super(router, route, location, messageService, confirmationService);
    }
    
    public ngOnInit() {
        this.search();
        this.addCrudTableAction();
    }


    protected addCrudTableAction() {
        this.addTableAction(this.getMsg('basecrud.actions.button.edit'), () => {
            if (this.rowSelected != null) {
                this.openEdit(this.rowSelected);
            }
        });
        this.addTableAction(this.getMsg('basecrud.actions.button.delete'), () => {
            if (this.rowSelected != null) {
                this.confirmDelete(this.rowSelected);
            }
        });
    }

    public abstract openNew();

    public abstract openEdit(row: R);

    public abstract onDelete(row: R);

    protected abstract getConfirmMessageDelete(row: R): string;

    protected confirmDelete(row: R) {
        this.confirmationService.confirm({
            message: this.getConfirmMessageDelete(row),
            accept: () => {
                this.onDelete(row);
            }
        });
    }
}