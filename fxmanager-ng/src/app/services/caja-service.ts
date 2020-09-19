import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';
import { RequestUtil } from '../util/request.util';
import { OperacionDTO } from '../dtos/operacionDTO';
import { cajaDTO } from '../dtos/cajaDTO';

@Injectable()
export class CajaService {
    constructor(private http: HttpClient) { }


    public getCajas(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE+"/cajas", httpOptions);
    }

    public getCaja(id: string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/cajas/"+encodeURI(id));
    }

    public createCaja(cajaDTO:cajaDTO): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/cajas/",cajaDTO);
    }

    public updateCaja(id: string, cajaDTO:cajaDTO): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/cajas/"+encodeURI(id),cajaDTO);
    }

    public deleteCaja(id: string): Observable<any> {
        return this.http.delete(RequestUtil.URL_BASE+"/cajas/"+encodeURI(id));
    }

    public getCajasPorSucursal(cod: string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/cajasPorSucursal/"+encodeURI(cod));
    }

}