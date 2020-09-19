import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';
import { RequestUtil } from '../util/request.util';

@Injectable()
export class FxService {
    constructor(private http: HttpClient) { }


    public getFxs(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE+"/fxs", httpOptions);
    }

    public getFx(cod: string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/fxs/"+encodeURI(cod));
    }

    public createFx(fx:any): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/fxs/",fx);
    }

    public updateFx(cod: string, role:any): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/fxs/"+encodeURI(cod),role);
    }

    public deleteFx(cod: string): Observable<any> {
        return this.http.delete(RequestUtil.URL_BASE+"/fxs/"+encodeURI(cod));
    }

}