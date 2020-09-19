import { OnInit, OnDestroy } from '@angular/core';
import { Routes, ActivatedRoute, Params, Router } from '@angular/router';
import { MessageService } from 'primeng/components/common/messageservice';
import { ConfirmationService } from 'primeng/primeng';
import { Location } from '@angular/common';
import { MessageUtil } from '../util/message.util';
import { ConstantsUtil } from '../util/constants.util';
export abstract class BaseScreenComponent<T> implements OnInit, OnDestroy {

    public sub: any;

    public calendarLanguage: any;

    protected constants: ConstantsUtil;

    constructor(protected router: Router, protected route: ActivatedRoute, protected location: Location, protected messageService: MessageService, protected confirmationService: ConfirmationService) { }

    //magia
    public ngOnInit(): void {
        this.sub = this.route.params.subscribe(params => {
            this.initParams(params);
        });
        this.constants = new ConstantsUtil();
        this.initLanguage();
    }

    //sobreescribir metodo para obtener los parametros por url 
    protected initParams(params: any) {

    }

    //this.Mesg llama los mensajes 
    protected showMessage(summary: string, severity: string, detail: string) {
        if (summary == null) {
            summary = this.getMsg('basecrud.confirm.save.title.success');
        }
        let messages: any[] = [{ severity: severity, summary: summary, detail: detail }];
        this.messageService.addAll(messages);
    }

    public ngOnDestroy() {
        if (this.sub != null) {
            this.sub.unsubscribe();
        }
    }


    public getMsg(key: string, ...params) {
        return MessageUtil.getMsg(key, params);
    }

    //obtiene los milisegundos y pasa a date
    protected millisToDate(millis): Date {
        return new Date(millis);
    }

    private initLanguage() {
        this.calendarLanguage = {
            firstDayOfWeek: 1,
            dayNames: ["domingo", "lunes", "martes", "miércoles", "jueves", "viernes", "sábado"],
            dayNamesShort: ["dom", "lun", "mar", "mié", "jue", "vie", "sáb"],
            dayNamesMin: ["D", "L", "M", "X", "J", "V", "S"],
            monthNames: ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"],
            monthNamesShort: ["ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"],
            today: 'Hoy',
            clear: 'Borrar'
        };
    }

}