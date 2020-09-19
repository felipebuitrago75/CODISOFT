import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';
import { RequestUtil } from '../util/request.util';
import { TurnoDTO } from '../dtos/turnoDTO';
import { preCierreDTO } from '../dtos/preCierreDTO';

@Injectable({
    providedIn: 'root',
})
export class TurnoService {

    constructor(private http: HttpClient) { }

    public setTurno(turno: TurnoDTO) {
        localStorage.setItem("turno", JSON.stringify(turno));
    }

    public getTurno(): TurnoDTO {
        let turnoLocal = localStorage.getItem("turno");
        if (turnoLocal) {
            return JSON.parse(turnoLocal);
        }
        return null;
    }

    public removeTurno(){
        localStorage.removeItem("turno");
    }

    public getTurnoObservable(nombreUsuario:string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/obtenerTurnoActivo/"+encodeURI(nombreUsuario));
    }

    public getTurnos(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE + "/turnos", httpOptions);
    }

    public getTurnosActivosPorSucursal(idSucursal:string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE + "/turnosSucursal/" + encodeURI(idSucursal+""));
    }

    public getCajaPorId(id: string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE + "/turnos/" + encodeURI(id));
    }

    public createTurno(turno: TurnoDTO): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE + "/turnos/", turno);
    }

    public updateTurno(id: string, turno: TurnoDTO): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE + "/turnos/" + encodeURI(id), turno);
    }

    public deleteTurno(id: string): Observable<any> {
        return this.http.delete(RequestUtil.URL_BASE + "/turnos/" + encodeURI(id));
    }

    public cancelarPrecierre(id: string): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE + "/cancelarPrecierre/" + encodeURI(id),null);
    }

    public createRegistroArqueo(preCierre:preCierreDTO): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE + "/createRegistroArqueo/", preCierre);
    }


    

}