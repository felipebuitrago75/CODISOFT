import { Component } from '@angular/core';

import { Routes, RouterModule, ActivatedRoute, Params, Router } from '@angular/router';
import { MessageService } from 'primeng/components/common/messageservice';
import { ConfirmationService } from 'primeng/primeng';
import { Location } from '@angular/common';

import { from } from 'rxjs';

import { BaseScreenComponent } from '../../components/base-screen.component';
import { SecurityService } from '../../core/security/services/security-service';
import { AuthService } from '../../core/security/services/auth-service';

@Component({
  templateUrl: 'usuario.component.html'
})
export class UsuarioComponent extends BaseScreenComponent<any>{

  
  public listaRoles: Array<any> = [];

  public username: string= "";

  public password: string= "";

  public tipo: string= "ROLE_OPERADOR";

  constructor(protected router: Router, protected route: ActivatedRoute, protected messageService: MessageService, protected confirmationService: ConfirmationService,protected securityService: SecurityService, protected authService:AuthService) {
    super(router, route, null, messageService, confirmationService);
  }

  ngOnInit() {
    super.ngOnInit();
    this.listaRoles.push({ label: 'ROLE_OPERADOR', value: "ROLE_OPERADOR" });
    this.listaRoles.push({ label: 'ROLE_COORDINADOR', value: 'ROLE_COORDINADOR' });
    this.listaRoles.push({ label: 'ROLE_OBSERVADOR', value: 'ROLE_OBSERVADOR' });
    this.listaRoles.push({ label: 'ROLE_GERENTE', value: 'ROLE_GERENTE' });
  }

  public guardar(){

    if(!this.username || !this.password || !this.tipo){
      return ;
    }
    this.securityService.crearUser(this.username, this.password, this.tipo).subscribe(data=>{
    },error=>{
      this.showMessage(null,"error",this.getMsg("administration.security.login.bad_credentials"));
    });
  }
}