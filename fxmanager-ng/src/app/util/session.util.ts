import { Headers, URLSearchParams } from '@angular/http';
import { PathConstants } from './path.constants';
import { Router } from '@angular/router';
export class SessionUtil {

    private static user: any;

    private static jwt: string;

    public static getUser() {
        SessionUtil.jwt=localStorage.getItem("jwt");
        let parts: any[] = SessionUtil.jwt.split(".");
        let usersData = atob(parts[1]);
        SessionUtil.user = JSON.parse(usersData);
        return SessionUtil.user;
    }

    public static setJwt(jwt: string) {
        localStorage.setItem("jwt",jwt);
        SessionUtil.jwt = jwt;
        let parts: any[] = SessionUtil.jwt.split(".");
        let usersData = atob(parts[1]);
        SessionUtil.user = JSON.parse(usersData);
    }

    public static getJwt() {
        SessionUtil.jwt=localStorage.getItem("jwt");
        return SessionUtil.jwt;
    }

}