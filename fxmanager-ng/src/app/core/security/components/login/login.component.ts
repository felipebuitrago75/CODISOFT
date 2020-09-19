import { Component } from '@angular/core';
import { BaseScreenComponent } from '../../../../components/base-screen.component';
import { Routes, RouterModule, ActivatedRoute, Params, Router } from '@angular/router';
import { MessageService } from 'primeng/components/common/messageservice';
import { ConfirmationService } from 'primeng/primeng';
import { Location } from '@angular/common';
import { SecurityService } from '../../services/security-service';
import { from } from 'rxjs';
import { AuthService } from '../../services/auth-service';
@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html',
  providers:[SecurityService]
})
export class LoginComponent extends BaseScreenComponent<any>{

  public msgs;
  
  public username: string;

  public password: string;

  constructor(protected router: Router, protected route: ActivatedRoute, protected location: Location, protected messageService: MessageService, protected confirmationService: ConfirmationService,protected securityService: SecurityService, protected authService:AuthService) {
    super(router, route, location, messageService, confirmationService);
  }

  public goRegister() {
    this.router.navigate(['/register']);
  }

  public login() {
    sessionStorage.clear();
    localStorage.clear();
    this.securityService.login(this.username,this.password).subscribe(data=>{
      this.authService.setToken(data);
      let token:string=data.access_token;
      let vector:string[]= token.split(".");
      let jsonData = atob(vector[1]);
      this.router.navigate(['/']);
    },error=>{
      this.showMessage(null,"error",this.getMsg("administration.security.login.bad_credentials"));
    });
  }


}
