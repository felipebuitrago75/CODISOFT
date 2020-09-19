import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { P404Component } from './components/error/404.component';
import { P500Component } from './components/error/500.component';
import { SecurityRoutingModule } from './security.routing';
import { RolesSearchComponent } from './components/roles/roles-search/roles-search.component';
import { ResourcesSearchComponent } from './components/resources/resources-search/resources-search.component';
import { ResourceFormComponent } from './components/resources/resource-form/resource-form.component';
import { MenusSearchComponent } from './components/menus/menus-search/menus-search.component';
import { MenuFormComponent } from './components/menus/menu-form/menu-form.component';
import { FunctionalitiesSearchComponent } from './components/functionalities/functionalities-search/functionalities-search.component';
import { FunctionalityFormComponent } from './components/functionalities/functionality-form/functionality-form.component';


import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {TableModule} from 'primeng/table';
import {DataScrollerModule} from 'primeng/datascroller';
import {ContextMenuModule} from 'primeng/contextmenu';
import {DropdownModule} from 'primeng/dropdown';
import {MultiSelectModule} from 'primeng/multiselect';
import { GrowlModule } from 'primeng/primeng';

import {RoleService} from './services/role-service';
import {ResourceService} from './services/resource-service';
import {MenuService} from './services/menu-service';
import {FunctionalityService} from './services/functionality-service';
import { RoleFormComponent } from './components/roles/role-form/role-form.component'
import {AuthInterceptor} from './interceptors/auth-interceptor';
import {MsgInterceptor} from './interceptors/msg-interceptor';

const PRIME_MODULES=[
  TableModule,ConfirmDialogModule,DataScrollerModule,ContextMenuModule,DropdownModule,
  MultiSelectModule,GrowlModule
  
];

@NgModule({
  imports: [
    CommonModule,
    SecurityRoutingModule,
    FormsModule,

    ...PRIME_MODULES
  ],
  declarations: [LoginComponent,RegisterComponent,P404Component,P500Component,
     RolesSearchComponent, RoleFormComponent,
     ResourceFormComponent, ResourcesSearchComponent,
     MenuFormComponent,MenusSearchComponent,
     FunctionalityFormComponent,FunctionalitiesSearchComponent
  ],
  providers: [AuthInterceptor,MsgInterceptor,RoleService,ResourceService,MenuService,FunctionalityService]
})
export class SecurityModule { }
