import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';
import { RequestUtil } from '../util/request.util';

@Injectable()
export class SucursalService {
    constructor(private http: HttpClient) { }


    public getSucursales(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE+"/sucursales", httpOptions);
    }

    public getSucursal(cod: string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/sucursales/"+encodeURI(cod));
    }

    public createSucursal(sucursal:any): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/sucursales/",sucursal);
    }

    public updateSucursal(cod: string, sucursal:any): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/sucursales/"+encodeURI(cod),sucursal);
    }

    public deleteSucursal(cod: string): Observable<any> {
        return this.http.delete(RequestUtil.URL_BASE+"/sucursales/"+encodeURI(cod));
    }

}