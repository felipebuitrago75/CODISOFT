import { Routes, RouterModule, ActivatedRoute, Params, Router } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService } from 'primeng/components/common/messageservice';
import { BaseFormComponent } from './base-form.component';
import { ConfirmationService } from 'primeng/primeng';

//pensado para crear y modificar
export abstract class BaseCrudFormComponent<T> extends BaseFormComponent<T> {

    public pastId:any;
    public new:boolean=true;

    constructor(protected router: Router, protected route: ActivatedRoute, protected location: Location, protected messageService: MessageService, protected confirmationService: ConfirmationService) {
        super(router, route, location, messageService, confirmationService);
    }

    protected initParams(params: any) {
        this.pastId = params[this.getIdParamName()];
        if (this.pastId != null) {
            this.new=false;
            this.loadBean(this.pastId);
        }
    }

    protected process() {
        if (this.isNew()) {
            this.save(this.bean);
        } else {
            this.update(this.pastId,this.bean);
        }
    }

    public clean() {
        super.clean();
        this.pastId=null;
        this.new=true;
    }

    protected isNew(): boolean{
        return this.new;
    }

    protected abstract getIdParamName(): string;

    protected abstract loadBean(id);

    protected abstract save(bean: T);

    protected abstract update(id:any, bean: T);


}