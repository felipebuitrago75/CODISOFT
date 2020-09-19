import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule }   from '@angular/forms';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';


const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};

import { AppComponent } from './app.component';

// Import containers
import { DefaultLayoutComponent } from './containers';
import { SimpleLayoutComponent } from './containers';


import { RegistroOperacionesComponent } from '../app/views/modulo-operativo/registro-operacion/registro-operacion.component';
import { RegistroTransladoComponent } from '../app/views/modulo-operativo/registro-traslado/registro-translado.component';
import { RegistroFondeoComponent } from '../app/views/modulo-operativo/registro-fondeo/registro-fondeo.component';
import { ListaOperacionesComponent } from './views/modulo-operativo/lista-operacion/lista-operacion.component';
import { ListaOperacionesOperadorComponent } from './views/modulo-operativo/lista-operacion-operador/lista-operacion-operador.component';
import { ListaSucursalesComponent } from './views/modulo-operativo/listado-sucursales/listado-sucursales.component';
import { RegistroFlujoExtraordinariosComponent } from './views/modulo-operativo/registro-flujos-extraordinarios/registro-flujos-extraordinarios.component';
import { ListaFlujosExtraordinariosComponent } from './views/modulo-operativo/lista-flujos-extraordinarios/lista-flujos-extraordinarios.component';
import { ListaFlujosExtraordinariosComponentOperador } from './views/modulo-operativo/lista-flujos-extraordinarios-operador/lista-flujos-extraordinarios.component';
import { EmpresaComponent } from '../app/views/modulo-configuracion/empresa.component';
import { SucursalComponent } from '../app/views/modulo-configuracion/sucursal.component';
import { UsuarioComponent } from '../app/views/modulo-configuracion/usuario.component';
import { MonedaComponent } from '../app/views/modulo-configuracion/moneda.component';
import { DarComponent } from '../app/views/modulo-operativo/dar-denominacion/dar.component';
import { RecibirComponent } from '../app/views/modulo-operativo/recibir-denominacion/recibir.component';
import { ListaCajasComponent } from './views/modulo-operativo/listado-cajas/listado-cajas.component';
import { CajaComponent } from './views/modulo-configuracion/caja.component';
import { TurnoComponent } from './views/modulo-operativo/registro-turno/turno.component';
import { ListaOperacionesDetalleComponent } from './views/modulo-operativo/lista-operacion-detalle/lista-operacion-detalle.component';


import { FxService }  from './services/fx-service';
import { SucursalService }  from './services/sucursal-service';
import { CajaService } from './services/caja-service';
import { OperacionService }  from './services/operacion-service';
import { TrasladoService } from './services/traslado-service';
import { FondeoService } from './services/fondeo-service';
import { FlujoExtraordinarioService } from './services/flujo-extraordinario-service';
import { TurnoService } from './services/turno-service';
import { CierreTurnoService } from './services/cierre-turno-service';
import { CierreService } from './services/cierre-service';

import {ButtonModule} from 'primeng/button';
import { GrowlModule } from 'primeng/primeng';
import { MessageService } from 'primeng/components/common/messageservice';
import { ConfirmationService } from 'primeng/primeng';
import {TabViewModule} from 'primeng/tabview';
import {CardModule} from 'primeng/card';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {ChipsModule} from 'primeng/chips';
import {PostService} from './containers/PostService';
import {InputSwitchModule} from 'primeng/inputswitch';
import {SliderModule} from 'primeng/slider';
import {SpinnerModule} from 'primeng/spinner';
import {KeyFilterModule} from 'primeng/keyfilter';
import {DialogModule} from 'primeng/dialog';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {BlockUIModule} from 'primeng/blockui';
import {CalendarModule} from 'primeng/calendar';

import {DataScrollerModule} from 'primeng/datascroller';
import {ContextMenuModule} from 'primeng/contextmenu';


import {SecurityModule} from './core/security/security.module'
import {AuthInterceptor} from './core/security/interceptors/auth-interceptor'
import {MsgInterceptor} from './core/security/interceptors/msg-interceptor'
import { LoadingScreenService } from './services/loader.service';

import { LoadingScreenInterceptor } from './services/loader.interceptor';
import { LoadingScreenComponent } from './views/modulo-configuracion/loader/loader.component';
import { CurrencyMaskModule } from "ng2-currency-mask";

const SMART_MODULES=[
  SecurityModule
];

const PRIME_MODULES = [
  ButtonModule,
  GrowlModule,
  CardModule,
  TableModule,
  DropdownModule,
  ChipsModule,
  InputSwitchModule,
  SliderModule,
  SpinnerModule,
  KeyFilterModule,
  DialogModule,
  ConfirmDialogModule,
  TabViewModule,
  DataScrollerModule,
  ContextMenuModule,
  BlockUIModule,
  CalendarModule,
  

  
 
];

const PRIME_PROVIDER = [
  MessageService,
  ConfirmationService,
  FxService,
  FlujoExtraordinarioService,
  SucursalService,
  CajaService,
  OperacionService,
  TrasladoService,
  FondeoService,
  ContextMenuModule,
  TurnoService,
  CierreTurnoService,
  CierreService,
  LoadingScreenService
  ,
  { provide: HTTP_INTERCEPTORS, useClass: LoadingScreenInterceptor, multi: true }
];

const APP_CONTAINERS = [
  DefaultLayoutComponent,
  SimpleLayoutComponent,
  RegistroOperacionesComponent,
  RegistroTransladoComponent,
  EmpresaComponent,
  SucursalComponent,
  UsuarioComponent,
  MonedaComponent,
  DarComponent,
  RecibirComponent,
  RegistroFondeoComponent,
  ListaOperacionesComponent,
  ListaOperacionesOperadorComponent,
  ListaSucursalesComponent,
  ListaFondeosComponent,
  RegistroFlujoExtraordinariosComponent,
  ListaFlujosExtraordinariosComponent,
  ListaFlujosExtraordinariosComponentOperador,
  CajaComponent,
  ListaCajasComponent,
  TurnoComponent,
  ListaCierresComponent,
  ListaPrecierresComponent,
  CierreComponent,
  ListaTrasladosComponent,
  ListaMonedaComponent,
  CierreComponent,
  PrecierreComponent,
  ArqueoComponent,
  ListaCierresConsolidadoComponent,
  ListaArqueosComponent,
  DetalleArqueoComponent,
  ReportePortafolioComponent,
  ReporteEstadoActualComponent,
  ReporteEstadoActualDetalladoComponent,
  ListaOperacionesDetalleComponent
  

];

import {
  AppAsideModule,
  AppBreadcrumbModule,
  AppHeaderModule,
  AppFooterModule,
  AppSidebarModule,
} from '@coreui/angular';

// Import routing module
import { AppRoutingModule } from './app.routing';

// Import 3rd party components
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { ListaCierresComponent } from './views/modulo-operativo/listado-cierres/listado-cierres.component';
import { ListaPrecierresComponent } from './views/modulo-operativo/listado-precierres/listado-precierres.component';
import { CierreComponent } from './views/modulo-configuracion/cierre.component';
import { ListaTrasladosComponent } from './views/modulo-operativo/lista-traslados/lista-traslados.component';
import { ListaMonedaComponent } from './views/modulo-operativo/listado-monedas/listado-monedas.component';
import { ArqueoComponent } from './views/modulo-operativo/app-arqueo/arqueo.component';
import { PrecierreComponent } from './views/modulo-configuracion/precierre.component';
import { ListaFondeosComponent } from './views/modulo-operativo/lista-fondeos/lista-fondeos.component';
import { ListaCierresConsolidadoComponent } from './views/modulo-operativo/listado-cierres-consolidados/listado-cierres-consolidados';
import { ListaArqueosComponent } from './views/modulo-operativo/listado-arqueos/listado-arqueos.component';
import { DetalleArqueoComponent } from './views/modulo-configuracion/detalle-arqueo.component';
import { ReportePortafolioComponent } from './views/modulo-operativo/reporte-portafolio/reporte-portafolio';
import { ReporteEstadoActualComponent } from './views/modulo-operativo/reporte-estado-actual/reporte-estado-actual';
import { ReporteEstadoActualDetalladoComponent } from './views/modulo-operativo/reporte-estado-actual-detallado/reporte-estado-actual-detallado';







@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    AppAsideModule,
    AppBreadcrumbModule.forRoot(),
    AppFooterModule,
    AppHeaderModule,
    AppSidebarModule,
    PerfectScrollbarModule,
    BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    ChartsModule,
    CurrencyMaskModule,
    ...PRIME_MODULES,
    ...SMART_MODULES
  ],
  declarations: [
    AppComponent,
    LoadingScreenComponent,
    ...APP_CONTAINERS
  ],
  providers: [{
    
    provide: LocationStrategy,
    useClass: HashLocationStrategy
  },PostService, { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  { provide: HTTP_INTERCEPTORS, useClass: MsgInterceptor, multi: true },...PRIME_PROVIDER],
  
  bootstrap: [ AppComponent ]
})
export class AppModule { }
