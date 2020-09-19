import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';
import { RequestUtil } from '../util/request.util';

@Injectable()
export class FondeoService {
    constructor(private http: HttpClient) { }


    public gets(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE+"/fondeos", httpOptions);
    }

    public get(id: number): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/fondeos/"+encodeURI(id+""));
    }

    public create(fx:any): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/fondeos/",fx);
    }

    public update(id: number, fondeos:any): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/fondeos/"+encodeURI(id+""),fondeos);
    }

    public delete(id: number): Observable<any> {
        return this.http.delete(RequestUtil.URL_BASE+"/traslados/"+encodeURI(id+""));
    }

}