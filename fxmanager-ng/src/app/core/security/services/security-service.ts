import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';
import { RequestUtil } from '../../../util/request.util';
@Injectable()
export class SecurityService {
    constructor(private http: HttpClient) { }


    public login(username: string, password: string): Observable<any> {


        let httpParams = new HttpParams()
            .append("username", username)
            .append("password", password)
            .append("grant_type", "password");

        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': 'Basic ' + btoa('devglan-client:devglan-secret'),
                'X-Skip-MsgInterceptor': 'true'
            })

        };
        return this.http.post(RequestUtil.URL_BASE + "/security/login", httpParams, httpOptions);
    }


    public crearUser(username:string, password:string, tipo:string): Observable<any> {

        let user:any = {
            username: username,
            password: password,
            role: {
                name : tipo
            },
            salt: "123456",
	        enabled: 0,
	        locked: 0,
	        failedLogins: 0,

        }
       
        return this.http.post(RequestUtil.URL_BASE+"/security/user",user);
    }

    public getData(jwt: any): Observable<any> {
        let httpParams = new HttpParams()
            .append("access_token", jwt.access_token);

        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': jwt.token_type + ' ' + jwt.access_token
            })

        };
        return this.http.get(RequestUtil.URL_BASE + "/hello", httpOptions);
    }

}