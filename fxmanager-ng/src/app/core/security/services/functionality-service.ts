import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';
import { RequestUtil } from '../../../util/request.util';

@Injectable()
export class FunctionalityService {
    constructor(private http: HttpClient) { }


    public getFunctionalities(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE+"/functionalities", httpOptions);
    }

    public getFunctionality(name: string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/functionalities/"+encodeURI(name));
    }

    public createFunctionality(functionality:any): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/functionalities/",functionality);
    }

    public updateFunctionality(name: string, functionality:any): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/functionalities/"+encodeURI(name),functionality);
    }

    public deleteFunctionality(name: string): Observable<any> {
        return this.http.delete(RequestUtil.URL_BASE+"/functionalities/"+encodeURI(name));
    }

}