import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Containers
import { DefaultLayoutComponent } from './containers';
import { SimpleLayoutComponent } from './containers';

import { RegistroOperacionesComponent } from './views/modulo-operativo/registro-operacion/registro-operacion.component';
import { RegistroTransladoComponent } from '../app/views/modulo-operativo/registro-traslado/registro-translado.component';
import { RegistroFondeoComponent } from '../app/views/modulo-operativo/registro-fondeo/registro-fondeo.component';

import { EmpresaComponent } from '../app/views/modulo-configuracion/empresa.component';
import { SucursalComponent } from '../app/views/modulo-configuracion/sucursal.component';
import { UsuarioComponent } from '../app/views/modulo-configuracion/usuario.component';
import { MonedaComponent } from '../app/views/modulo-configuracion/moneda.component';
import { SecureRoutingService } from './core/security/services/secure-routing-service';
import { ListaOperacionesComponent } from './views/modulo-operativo/lista-operacion/lista-operacion.component';
import { ListaSucursalesComponent } from './views/modulo-operativo/listado-sucursales/listado-sucursales.component';
import { RegistroFlujoExtraordinariosComponent } from './views/modulo-operativo/registro-flujos-extraordinarios/registro-flujos-extraordinarios.component';
import { ListaFlujosExtraordinariosComponent } from './views/modulo-operativo/lista-flujos-extraordinarios/lista-flujos-extraordinarios.component';
import { CajaComponent } from './views/modulo-configuracion/caja.component';
import { ListaCajasComponent } from './views/modulo-operativo/listado-cajas/listado-cajas.component';
import { TurnoComponent } from './views/modulo-operativo/registro-turno/turno.component';
import { ListaCierresComponent } from './views/modulo-operativo/listado-cierres/listado-cierres.component';
import { ListaPrecierresComponent } from './views/modulo-operativo/listado-precierres/listado-precierres.component';
import { CierreComponent } from './views/modulo-configuracion/cierre.component';
import { ListaTrasladosComponent } from './views/modulo-operativo/lista-traslados/lista-traslados.component';
import { ListaMonedaComponent } from './views/modulo-operativo/listado-monedas/listado-monedas.component';
import { PrecierreComponent } from './views/modulo-configuracion/precierre.component';
import { ListaFondeosComponent } from './views/modulo-operativo/lista-fondeos/lista-fondeos.component';
import { ListaCierresConsolidadoComponent } from './views/modulo-operativo/listado-cierres-consolidados/listado-cierres-consolidados';
import { ListaArqueosComponent } from './views/modulo-operativo/listado-arqueos/listado-arqueos.component';
import { DetalleArqueoComponent } from './views/modulo-configuracion/detalle-arqueo.component';
import { ReportePortafolioComponent } from './views/modulo-operativo/reporte-portafolio/reporte-portafolio';
import { ReporteEstadoActualComponent } from './views/modulo-operativo/reporte-estado-actual/reporte-estado-actual';
import { ReporteEstadoActualDetalladoComponent } from './views/modulo-operativo/reporte-estado-actual-detallado/reporte-estado-actual-detallado';
import { ListaOperacionesOperadorComponent } from './views/modulo-operativo/lista-operacion-operador/lista-operacion-operador.component';
import { ListaFlujosExtraordinariosComponentOperador } from './views/modulo-operativo/lista-flujos-extraordinarios-operador/lista-flujos-extraordinarios.component';
import { ListaOperacionesDetalleComponent } from './views/modulo-operativo/lista-operacion-detalle/lista-operacion-detalle.component';


export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
  },
  {
    path: '',
    component: DefaultLayoutComponent,
    canActivate:[SecureRoutingService],
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'registro-operaciones',
        component: RegistroOperacionesComponent,
        data: {
          title: 'Registro Operaciones'
        }
      },
     
      {
        path: 'registro-operaciones/:id',
        component: RegistroOperacionesComponent,
        data: {
          title: 'Editar Operaciones'
        }
      },
      {
        path: 'lista-operaciones',
        component: ListaOperacionesComponent,
        data: {
          title: 'Lista Operaciones'
        }
      },
      {
        path: 'lista-operaciones-detalle',
        component: ListaOperacionesDetalleComponent,
        data: {
          title: 'Lista Operaciones Detalle'
        }
      },
      
      {
        path: 'lista-operaciones-op',
        component: ListaOperacionesOperadorComponent,
        data: {
          title: 'Lista Operaciones'
        }
      },
      
      {
        path: 'lista-cierres',
        component: ListaCierresComponent,
        data: {
          title: 'Lista Cierres'
        }
      },
      
      {
        path: 'lista-cierres-consolidados',
        component: ListaCierresConsolidadoComponent,
        data: {
          title: 'Lista Cierres Consolidados'
        }
      },

      {
        path: 'reporte-portafolio',
        component: ReportePortafolioComponent,
        data: {
          title: 'Reporte Portafolio'
        }
      },
      {
        path: 'reporte-estado-actual',
        component: ReporteEstadoActualComponent,
        data: {
          title: 'Reporte Estado Actual'
        }
      },
      {
        path: 'reporte-estado-actual-detallado',
        component: ReporteEstadoActualDetalladoComponent,
        data: {
          title: 'Reporte Estado Actual Detallado'
        }
      },
      {
        path: 'lista-precierres',
        component: ListaPrecierresComponent,
        data: {
          title: 'Lista Precierres'
        }
      },
      {
        path: 'lista-arqueos',
        component: ListaArqueosComponent,
        data: {
          title: 'Lista arqueos'
        }
      },
      {
        path: 'cierres/:id',
        component: CierreComponent,
        data: {
          title: 'Detalle cierre'
        }
      },
      {
        path: 'precierres/:id',
        component: PrecierreComponent,
        data: {
          title: 'Detalle precierre'
        }
      },
      {
        path: 'arqueo/:id',
        component: DetalleArqueoComponent,
        data: {
          title: 'Detalle Arqueo Historico'
        }
      },
      {
        path: 'lista-flujos-extraordinarios',
        component: ListaFlujosExtraordinariosComponent,
        data: {
          title: 'Lista flujos extraordinarios'
        }
      },
      {
        path: 'lista-flujos-extraordinarios-op',
        component: ListaFlujosExtraordinariosComponentOperador,
        data: {
          title: 'Lista flujos extraordinarios'
        }
      },
      
      {
        path: 'registro-translados',
        component: RegistroTransladoComponent,
        data: {
          title: 'Registro Translados'
        }
      },
      {
        path: 'registro-fondeos',
        component: RegistroFondeoComponent,
        data: {
          title: 'Registro Fondeos'
        }
      },
      {
        path: 'registro-flujos',
        component: RegistroFlujoExtraordinariosComponent,
        data: {
          title: 'Registro flujos extraordinarios'
        }
      },
      {
        path: 'registro-flujos/:id',
        component: RegistroFlujoExtraordinariosComponent,
        data: {
          title: 'Registro flujos extraordinarios'
        }
      },
      {
        path: 'empresa',
        component: EmpresaComponent,
        data: {
          title: 'Empresa'
        }
      },
      {
        path: 'sucursales',
        component: SucursalComponent,
        data: {
          title: 'sucursales'
        }
      },
      {
        path: 'sucursales/:id',
        component: SucursalComponent,
        data: {
          title: 'Editar sucursales'
        }
      },
      {
        path: 'lista-sucursales',
        component: ListaSucursalesComponent,
        data: {
          title: 'lista sucursales'
        }
      },
      {
        path: 'lista-traslados',
        component: ListaTrasladosComponent,
        data: {
          title: 'lista traslados'
        }
      },
      {
        path: 'lista-fondeos',
        component: ListaFondeosComponent,
        data: {
          title: 'lista fondeos'
        }
      },
      {
        path: 'cajas',
        component: CajaComponent,
        data: {
          title: 'cajas'
        }
      },
      {
        path: 'cajas/:id',
        component: CajaComponent,
        data: {
          title: 'Editar cajas'
        }
      },
      {
        path: 'lista-cajas',
        component: ListaCajasComponent,
        data: {
          title: 'lista cajas'
        }
      },
      {
        path: 'turno',
        component: TurnoComponent,
        data: {
          title: 'Turno'
        }
      },
      
      {
        path: 'usuarios',
        component: UsuarioComponent,
        data: {
          title: 'usuarios'
        }
      },
      {
        path: 'monedas',
        component: MonedaComponent,
        data: {
          title: 'monedas'
        }
      },
      {
        path: 'monedas/:id',
        component: MonedaComponent,
        data: {
          title: 'monedas'
        }
      },
      {
        path: 'lista-monedas',
        component: ListaMonedaComponent,
        data: {
          title: 'lista monedas'
        }
      },
     /* {
        path: 'base',
        loadChildren: './core/base/base.module#BaseModule'
      },
      {
        path: 'buttons',
        loadChildren: './core/buttons/buttons.module#ButtonsModule'
      },
      {
        path: 'charts',
        loadChildren: './core/chartjs/chartjs.module#ChartJSModule'
      },
      {
        path: 'dashboard',
        loadChildren: './core/dashboard/dashboard.module#DashboardModule'
      },
      {
        path: 'icons',
        loadChildren: './core/icons/icons.module#IconsModule'
      },
      {
        path: 'notifications',
        loadChildren: './core/notifications/notifications.module#NotificationsModule'
      },
      {
        path: 'theme',
        loadChildren: './core/theme/theme.module#ThemeModule'
      },
      {
        path: 'widgets',
        loadChildren: './core/widgets/widgets.module#WidgetsModule'
      }*/
    ]
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
