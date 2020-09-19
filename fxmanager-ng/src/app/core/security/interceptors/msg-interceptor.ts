import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse } from '@angular/common/http';
import { RequestUtil } from '../../../util/request.util';
import { AuthService } from '../services/auth-service';
import { MessageUtil } from '../../../util/message.util';
import { MessageService } from 'primeng/components/common/messageservice';
import { Router } from '@angular/router';

export const InterceptorSkipHeader = 'X-Skip-MsgInterceptor';

@Injectable({
    providedIn: 'root',
})
export class MsgInterceptor implements HttpInterceptor {

    constructor(protected messageService: MessageService, protected router: Router) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (!request.headers.has(InterceptorSkipHeader)) {
            return next.handle(request)
                .pipe(
                    tap(event => {
                        if (event instanceof HttpResponse) {
                        }
                    }, error => {
                        if (error.error.body) {
                            this.showMessage(this.getMsg("basecrud.confirm.save.title.error"), 'error', error.error.body);
                        } else if (error.status == 403) {
                            this.showMessage(this.getMsg("basecrud.confirm.save.title.error"), 'error', this.getMsg("global.security.error.access.denied"));

                        } else if (error.status == 401) {
                            this.showMessage("Sesión expirada", 'error',"Por seguridad se ha expirado su sesión");
                            this.router.navigate(['login']);
                        }
                        else {
                            this.showMessage(this.getMsg("basecrud.confirm.save.title.error"), 'error', this.getMsg("global.security.error.general"));
                        }
                    })
                );
        } else {
            return next.handle(request);
        }
    }

    public getMsg(key: string, ...params) {
        return MessageUtil.getMsg(key, params);
    }

    protected showMessage(summary: string, severity: string, detail: string) {
        if (summary == null) {
            summary = this.getMsg('basecrud.confirm.save.title.success');
        }
        let messages: any[] = [{ severity: severity, summary: summary, detail: detail }];
        this.messageService.addAll(messages);
    }

}