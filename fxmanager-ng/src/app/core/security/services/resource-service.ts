import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';
import { RequestUtil } from '../../../util/request.util';

@Injectable()
export class ResourceService {
    constructor(private http: HttpClient) { }


    public getResources(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE+"/resources", httpOptions);
    }

    public getResource(name: string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/resources/"+encodeURI(name));
    }

    public createResource(resource:any): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/resources/",resource);
    }

    public updateResource(name: string, resource:any): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/resources/"+encodeURI(name),resource);
    }

    public deleteResource(name: string): Observable<any> {
        return this.http.delete(RequestUtil.URL_BASE+"/resources/"+encodeURI(name));
    }

}