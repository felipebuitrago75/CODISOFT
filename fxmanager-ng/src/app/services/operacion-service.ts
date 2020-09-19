import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';
import { RequestUtil } from '../util/request.util';
import { OperacionDTO } from '../dtos/operacionDTO';
import { TurnoDTO } from '../dtos/turnoDTO';
import { OperacionFiltroDTO } from '../dtos/operacionFiltroDTO';

@Injectable()
export class OperacionService {
    constructor(private http: HttpClient) { }


    public getOperaciones(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE+"/operaciones", httpOptions);
    }

    public getOperacion(id: string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/operaciones/"+encodeURI(id));
    }

    public createOperacion(operacionDTO:OperacionDTO): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/operaciones/",operacionDTO);
    }

    public updateOperacion(id: string, operacion:OperacionDTO): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/operaciones/"+encodeURI(id),operacion);
    }

    public deleteOperacion(id: string): Observable<any> {
        return this.http.delete(RequestUtil.URL_BASE+"/operaciones/"+encodeURI(id));
    }

    public cancelarOperacion(id: string, turno:TurnoDTO): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/cancelar/operacion/"+encodeURI(id),turno);
    }

    public buscarConFiltro(operacionFiltro:OperacionFiltroDTO): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/buscarOperaciones/",operacionFiltro);
    }

    public getOperacionesPorTurno(id: string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/operacionesPorTurno/"+encodeURI(id));
    }

    public getOperacionesUltimas(): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/operacionesUltimas/");
    }

}