import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ResponseType } from '@angular/http';

@Injectable({
    providedIn: 'root',
  })
export class AuthService {

    private token:any;

    constructor() { }

    public setToken(token:any) {
        this.token=token;
        sessionStorage.setItem("jwtToken",JSON.stringify(this.token));
    }

    public getToken(){
        let token = sessionStorage.getItem("jwtToken");
        if(token){
            this.token=JSON.parse(token);
        }else{
            this.token=null;
        }
        return this.token;
    }

    public isLogged(){
      return this.getToken()!=null;  
    }

}