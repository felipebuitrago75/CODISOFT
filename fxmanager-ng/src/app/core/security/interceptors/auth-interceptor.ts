import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { RequestUtil } from '../../../util/request.util';
import { AuthService } from '../services/auth-service';

@Injectable({
    providedIn: 'root',
  }) 
export class AuthInterceptor implements HttpInterceptor {

    constructor( protected authService:AuthService) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (!request || !request.url || (/^http/.test(request.url) && !(request.url.startsWith(RequestUtil.URL_BASE)))) {
            return next.handle(request);
        }
        

        const token = this.authService.getToken();
        
        
        if(!!token) {
            request = request.clone({
                setHeaders: {
                    Authorization: 'Bearer ' + token.access_token
                }
            });
        }
        return next.handle(request);
    }

}