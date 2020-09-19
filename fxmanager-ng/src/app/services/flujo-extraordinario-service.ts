import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';
import { RequestUtil } from '../util/request.util';
import { FlujoExtraordinarioDTO } from '../dtos/flujoExtraordinarioDTO';
import { TurnoDTO } from '../dtos/turnoDTO';
import { FlujoFiltroDTO } from '../dtos/flujoFiltroDTO';

@Injectable()
export class FlujoExtraordinarioService {
    constructor(private http: HttpClient) { }
    
    public getFlujosExtraordinarios(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE+"/flujosExtraordinarios", httpOptions);
    }

    public getFlujoExtraordinario(id: string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/flujosExtraordinarios/"+encodeURI(id));
    }

    public createFlujoExtraordinario(flujoExtraordinarioDTO:FlujoExtraordinarioDTO): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/flujosExtraordinarios/",flujoExtraordinarioDTO);
    }

    public updateFlujoExtraordinario(id: string, flujoExtraordinarioDTO:FlujoExtraordinarioDTO): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/flujosExtraordinarios/"+encodeURI(id),flujoExtraordinarioDTO);
    }

    public deleteFlujoExtraordinario(id: string): Observable<any> {
        return this.http.delete(RequestUtil.URL_BASE+"/flujosExtraordinarios/"+encodeURI(id));
    }

    public cancelarFlujo(id: string, turno:TurnoDTO): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/cancelarflujosExtraordinarios/"+encodeURI(id),turno);
    }

    public buscarConFiltro(flujoFiltroDTO:FlujoFiltroDTO): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/buscarFlujos/",flujoFiltroDTO);
    }
  
}