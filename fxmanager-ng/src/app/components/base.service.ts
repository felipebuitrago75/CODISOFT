import { Http, Response, RequestOptions, Headers, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { MessageService } from 'primeng/components/common/messageservice';
import { MessageUtil } from '../util/message.util';
export class BaseService<T> {

    protected static URL_BASE: string = "";
    protected static CONTENT_TYPE_FORM: string = "application/x-www-form-urlencoded; charset=UTF-8";
    protected static CONTENT_TYPE_JSON: string = "application/json; charset=UTF-8";
    protected static CONTENT_MULTIPART_FORM_DATA: string = "multipart/form-data; charset=UTF-8";
    protected static OK_STATUS: number = 200;
    protected static NOT_CONTENT_STATUS: number = 204;
    protected static EXCEPTION_STATUS: number = 400;
    protected static UNAUTHORIZED_STATUS: number = 401;

    constructor(protected http: Http, protected messageService: MessageService) { }

    protected getMsg(key: string, ...params) {
        return MessageUtil.getMsg(key, params);
    }

    private createExceptionFuction() {
        return exception => {
            let messages: any[] = exception.json();
            let msgs: any[] = [];
            for (let msg of messages) {
                msgs.push({ severity: msg.severity.toLowerCase(), summary: this.getMsg('baseservice.msg.default.exception.summary'), detail: msg.detail });
            }
            this.messageService.addAll(msgs);
        }
    }

    private createUnAuthorizedFuction() {
        return unAuthorized => {
            let msgs = [];
            msgs.push({ severity: 'error', summary: this.getMsg('baseservice.msg.default.unauthorized.summary'), detail: this.getMsg('baseservice.msg.default.unauthorized.detail') });
            this.messageService.addAll(msgs);
        }
    }

    private createErrorFuction() {
        return error => {
            let messages: any[] = [{ severity: 'error', summary: this.getMsg('baseservice.msg.default.error.summary'), detail: this.getMsg('baseservice.msg.default.error.detail') }];
            let msgs = [];
            for (let msg of messages) {
                msgs.push({ severity: msg.severity.toLowerCase(), summary: msg.summary, detail: msg.detail });
            }
            this.messageService.addAll(msgs);
        }
    }

    private proccessRequest(data: any, onSuccess, onException?, onUnAuthorized?, onError?) {
        if (onException == undefined || onException == null) {
            onException = this.createExceptionFuction();
        }
        if (onUnAuthorized == undefined || onUnAuthorized == null) {
            onUnAuthorized = this.createExceptionFuction();
        }
        if (onError == undefined || onError == null) {
            onError = this.createErrorFuction();
        }
        if (data.status == BaseService.OK_STATUS) {
            onSuccess(data);  
        } else if (data.status == BaseService.EXCEPTION_STATUS) {
            onException(data);
        } else if (data.status == BaseService.UNAUTHORIZED_STATUS) {
            onUnAuthorized(data);
        } else if (data.status == BaseService.NOT_CONTENT_STATUS){
            onSuccess(data); 
        } else {
            onError(data);
        }
    }

    private sendForm(resource: string, method: string, formData: FormData, options: RequestOptions, onSuccess, onException, onUnAuthorized, onError) {
        let xhr: XMLHttpRequest = new XMLHttpRequest();
        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4) {
                let data: any = {};
                data.value = xhr.response;
                data.status = xhr.status;
                data.json = function () {
                    return JSON.parse(this.value);
                };
                data.text = function () {
                    return this.value;
                };
                this.proccessRequest(data, onSuccess, onException, onUnAuthorized, onError);
            }
        }
        xhr.open(method, resource, true);
        if (options != null) {
            options.headers.forEach((value: any, key: any) => {
                if (key != "Content-Type" && value != BaseService.CONTENT_MULTIPART_FORM_DATA) {
                    xhr.setRequestHeader(key, value.toString());
                }
            });
        }
        xhr.send(formData);
    }



    protected post(resource: string, body: any, options: RequestOptions, onSuccess, onException?, onUnAuthorized?, onError?): void {
        if (body instanceof FormData) {
            this.sendForm(resource, 'POST', body, options, onSuccess, onException, onUnAuthorized, onError);
        } else {
            this.http.post(resource, body, options)
                .subscribe(data => {
                    this.proccessRequest(data, onSuccess, onException, onUnAuthorized, onError);
                }, error => {
                    this.proccessRequest(error, onSuccess, onException, onUnAuthorized, onError);
                });
        }
    }

    protected put(resource: string, body: any, options: RequestOptions, onSuccess, onException?, onUnAuthorized?, onError?): void {
        if (body instanceof FormData) {
            this.sendForm(resource, 'PUT', body, options, onSuccess, onException, onUnAuthorized, onError);
        } else {
            this.http.put(resource, body, options)
                .subscribe(data => {
                    this.proccessRequest(data, onSuccess, onException, onUnAuthorized, onError);
                }, error => {
                    this.proccessRequest(error, onSuccess, onException, onUnAuthorized, onError);
                });
        }
    }

    protected get(resource: string, params: URLSearchParams, options: RequestOptions, onSuccess, onException?, onUnAuthorized?, onError?): void {
        this.http.get(resource, { search: params, headers: options.headers })
            .subscribe(data => {
                this.proccessRequest(data, onSuccess, onException, onUnAuthorized, onError);
            }, error => {
                this.proccessRequest(error, onSuccess, onException, onUnAuthorized, onError);
            });
    }

    protected delete(resource: string, options: RequestOptions, onSuccess, onException?, onUnAuthorized?, onError?): void {
        this.http.delete(resource, options)
            .subscribe(data => {
                this.proccessRequest(data, onSuccess, onException, onUnAuthorized, onError);
            }, error => {
                this.proccessRequest(error, onSuccess, onException, onUnAuthorized, onError);
            });
    }

}