
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RequestUtil } from '../util/request.util';

const apiUrl ='/posts';

@Injectable()
export class PostService {

  constructor(private http: HttpClient) { }

 

  getCommentsOfPost(id: string): Observable<any> {
    
    return this.http.get(RequestUtil.URL_BASE+"/hello",{responseType: 'json' });
  }

}