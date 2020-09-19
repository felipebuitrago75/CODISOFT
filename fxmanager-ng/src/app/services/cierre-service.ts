import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';
import { RequestUtil } from '../util/request.util';


@Injectable({
    providedIn: 'root',
  })
export class CierreService {

    constructor(private http: HttpClient) { }


    public getCierres(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE+"/cierres", httpOptions);
    }

    public getCierre(id: string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/cierres/"+encodeURI(id));
    }

    public realizarCierre(): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/cierres/",null);
    }

    public realizarCierreDeUnaFecha(fecha:Date): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/realizarCierresDeUnaFecha/",new Date(fecha));
    }
    
    public obtenerCierresDia(idSucursal:string): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/obtenerCierresDia/"+idSucursal, null);
    }
    public obtenerCierresSucursalPorFecha(idSucursal:string, fecha:Date): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/obtenerCierresSucursalPorFecha/"+idSucursal, fecha);
    }

    public obtenerCierresConsolidadoPorFecha(fecha:Date): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/obtenerCierresConsolidadoPorFecha/", fecha);
    }

    public reportePortafolioPorFecha(fecha:Date): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/reportePortafolioPorFecha/", fecha);
    }

    public reporteEstadoActual(): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/reporteEstadoActual/",null);
    }

    public reporteEstadoActualDetallado(): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/reporteEstadoActualDetallado/",null);
    }
    
}