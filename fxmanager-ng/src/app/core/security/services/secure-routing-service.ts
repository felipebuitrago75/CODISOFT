import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, Route } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth-service';

@Injectable({
    providedIn: 'root',
  })
export class SecureRoutingService implements CanActivate{

    constructor(protected router: Router, protected authService:AuthService) { }


    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        if(this.authService.isLogged()){
            return true;
        }

        this.router.navigate(['/login']);

        return false;
    }


}