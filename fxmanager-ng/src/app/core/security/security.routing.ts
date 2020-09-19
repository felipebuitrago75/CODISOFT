import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { P404Component } from './components/error/404.component';
import { P500Component } from './components/error/500.component';
import { RolesSearchComponent } from './components/roles/roles-search/roles-search.component';
import { RoleFormComponent } from './components/roles/role-form/role-form.component';
import { MenusSearchComponent } from './components/menus/menus-search/menus-search.component';
import { MenuFormComponent } from './components/menus/menu-form/menu-form.component';
import { ResourcesSearchComponent } from './components/resources/resources-search/resources-search.component';
import { ResourceFormComponent } from './components/resources/resource-form/resource-form.component';
import { DefaultLayoutComponent } from '../../containers/default-layout/default-layout.component';
import {PathConstants} from '../../util/path.constants'
import { FunctionalityFormComponent } from './components/functionalities/functionality-form/functionality-form.component';
import { FunctionalitiesSearchComponent } from './components/functionalities/functionalities-search/functionalities-search.component';
// Import Containers

export const routes: Routes = [
  {
    path: '404',
    component: P404Component,
    data: {
      title: 'Page 404'
    }
  },
  {
    path: '500',
    component: P500Component,
    data: {
      title: 'Page 500'
    }
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: {
      title: 'Register Page'
    }
  }, {
    path: '',
    component: DefaultLayoutComponent,
    data: {
      title: 'Security'
    },
    children: [
      {
        path: PathConstants.FULLPATH_ROLES_SEARCH,
        component: RolesSearchComponent,
        data: {
          title: 'Roles'
        }
      },
      {
        path: PathConstants.FULLPATH_ROLE_FORM,
        component: RoleFormComponent,
        data: {
          title: 'New Rol'
        }
      },
      {
        path: PathConstants.FULLPATH_ROLE_FORM+'/:id',
        component: RoleFormComponent,
        data: {
          title: 'Edit Rol'
        }
      },
      {
        path: PathConstants.FULLPATH_RESOURCES_SEARCH,
        component: ResourcesSearchComponent,
        data: {
          title: 'Resources'
        }
      },
      {
        path: PathConstants.FULLPATH_RESOURCE_FORM,
        component: ResourceFormComponent,
        data: {
          title: 'New Resource'
        }
      },
      {
        path: PathConstants.FULLPATH_RESOURCE_FORM+'/:id',
        component: ResourceFormComponent,
        data: {
          title: 'Edit Resource'
        }
      },{
        path: PathConstants.FULLPATH_MENUS_SEARCH,
        component: MenusSearchComponent,
        data: {
          title: 'Menus'
        }
      },
      {
        path: PathConstants.FULLPATH_MENU_FORM,
        component: MenuFormComponent,
        data: {
          title: 'New Menu'
        }
      },
      {
        path: PathConstants.FULLPATH_MENU_FORM+'/:id',
        component: MenuFormComponent,
        data: {
          title: 'Edit Menu'
        }
      },
      {
        path: PathConstants.FULLPATH_FUNCTIONALITIES_SEARCH,
        component: FunctionalitiesSearchComponent,
        data: {
          title: 'Menus'
        }
      },
      {
        path: PathConstants.FULLPATH_FUNCTIONALITY_FORM,
        component: FunctionalityFormComponent,
        data: {
          title: 'New Menu'
        }
      },
      {
        path: PathConstants.FULLPATH_FUNCTIONALITY_FORM+'/:id',
        component: FunctionalityFormComponent,
        data: {
          title: 'Edit Menu'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class SecurityRoutingModule { }
