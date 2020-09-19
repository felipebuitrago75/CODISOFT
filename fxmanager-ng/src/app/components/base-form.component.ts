import { Routes, RouterModule, ActivatedRoute, Params, Router } from '@angular/router';
import { MessageService } from 'primeng/components/common/messageservice';
import { BaseScreenComponent } from './base-screen.component';
import { ConfirmationService } from 'primeng/primeng';
import { Location } from '@angular/common';
export abstract class BaseFormComponent<T> extends BaseScreenComponent<T> {

    //datos del formulario
    public bean: T = this.createBean();

    constructor(protected router: Router, protected route: ActivatedRoute, protected location: Location, protected messageService: MessageService, protected confirmationService: ConfirmationService) {
        super(router, route, location, messageService, confirmationService);
    }

    protected abstract validate(): boolean;

    protected abstract process();

    protected createBean() : T  {
        let bean: T = {} as T;
        return bean;
    }

    //limpia formulario
    public clean() {
        this.bean = {} as T;
    }

    
    public submit() {
        if (this.validate()) {
            this.process();
        }
    }

}