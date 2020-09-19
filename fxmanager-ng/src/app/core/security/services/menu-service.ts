import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';
import { RequestUtil } from '../../../util/request.util';

@Injectable()
export class MenuService {
    constructor(private http: HttpClient) { }


    public getMenus(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE+"/menus", httpOptions);
    }

    public getMenusForParent(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE+"/menus/for/parents", httpOptions);
    }

    public getMenusForFunctionalities(first: number, max: number): Observable<any> {
        let httpParams = new HttpParams()
            .append("first", first.toString()).append("max", max.toString());
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            params: httpParams
        };
        return this.http.get(RequestUtil.URL_BASE+"/menus/for/functionalities", httpOptions);
    }

    public getMenu(name: string): Observable<any> {
        return this.http.get(RequestUtil.URL_BASE+"/menus/"+encodeURI(name));
    }

    public createMenu(menu:any): Observable<any> {
        return this.http.post(RequestUtil.URL_BASE+"/menus/",menu);
    }

    public updateMenu(name: string, menu:any): Observable<any> {
        return this.http.put(RequestUtil.URL_BASE+"/menus/"+encodeURI(name),menu);
    }

    public deleteMenu(name: string): Observable<any> {
        return this.http.delete(RequestUtil.URL_BASE+"/menus/"+encodeURI(name));
    }

}