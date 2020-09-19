import { Headers, URLSearchParams } from '@angular/http';
import { PathConstants } from './path.constants';
import { SessionUtil } from './session.util';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
export class RequestUtil {

    public static URL_BASE:string=environment.url_base;
    private static router: Router;

    public static setRouter(router: Router) {
        this.router = router;
    }

    public static createURLSearchParams(): URLSearchParams {
        let params: URLSearchParams = new URLSearchParams();
        return params;
    }

    public static verifySession() {

    }


    public static createHeaders(contentType, useJWT: boolean): Headers {
        let headers = new Headers();
        if (contentType != null) {
            headers.append("Content-Type", contentType)
        }
        if (useJWT) {
            headers.append("Authorization", "Bearer " + SessionUtil.getJwt())
        }
        return headers;
    }

}