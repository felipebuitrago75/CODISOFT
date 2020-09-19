import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';
import { RequestUtil } from '../util/request.util';
import { CierreTurnoDTO } from '../dtos/cierreTurnoDTO';

@Injectable({
    providedIn: 'root',
  })
export class CierreTurnoService {

    constructor(private http: HttpClient) { }


    public getCierresTurnos(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE+"/cierreturnos", httpOptions);
    }

    public getCierreTurno(id: string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/cierreturnos/"+encodeURI(id));
    }

    public createCierreTurno(cierreTurnoDTO: CierreTurnoDTO): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/cierreturnos/",cierreTurnoDTO);
    }

    public updateTurno(id: string): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/cierreturnos/"+encodeURI(id),new CierreTurnoDTO());
    }

    public arqueosHistoricosPorFecha(fecha:Date): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/arqueosHistoricos/", fecha);
    }

    public arqueosHistoricosId(id:string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/arqueosHistoricos/"+encodeURI(id));
    }

     
}