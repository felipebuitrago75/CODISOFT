export const navItems = [
  {
    name: 'INICIO',
    url: '/dashboard',
    icon: 'icon-speedometer',
    
  },
  {
    title: true,
    name: 'Módulo Operativo'
  },
  {
    name: 'Registro Operaciones',
    url: '/registro-operaciones',
    icon: 'icon-pencil'
  },
  {
    name: 'Lista Operaciones',
    url: '/lista-operaciones',
    icon: 'icon-pencil'
  },
  {
    name: 'Lista Cierres',
    url: '/lista-cierres',
    icon: 'icon-pencil'
  },
  {
    name: 'Lista flujos extraordinarios',
    url: '/lista-flujos-extraordinarios',
    icon: 'icon-pencil'
  },
  {
    name: 'Registro Traslados',
    url: '/registro-translados',
    icon: 'icon-pencil'
  },

  {
    name: 'Lista Traslados',
    url: '/lista-traslados',
    icon: 'icon-pencil'
  },
  {
    name: 'Registro Fondeo',
    url: '/registro-fondeos',
    icon: 'icon-pencil'
  },
  {
    name: 'Registro Ingresos - Egresos',
    url: '/registro-flujos',
    icon: 'icon-pencil'
  },
  
  {
    name: 'Arqueo Parcial',
    url: '/theme/colors',
    icon: 'icon-chart'
  },
  {
    name: 'Revisión Operaciones',
    url: '/theme/colors',
    icon: 'cui-magnifying-glass'
  },
  {
    name: 'Precierre',
    url: '/theme/colors',
    icon: 'icon-check'
  },
  {
    name: 'Cierre Consolidado',
    url: '/theme/colors',
    icon: 'icon-check'
  },
 
  {
    title: true,
    name: 'Módulo Administrativo'
  },
  {
    divider: true
  },
  {
    name: 'Reportes Financieros',
    url: '/base',
    icon: 'icon-chart',
    children: [
      {
        name: 'Reporte Flujo de Caja (Detallado - Consolidado)',
        url: '/base/cards',
        icon: 'icon-chart'
      },
      {
        name: 'Reporte Desempeño (Detallado - Consolidado)',
        url: '/base/carousels',
        icon: 'icon-chart'
      },
      {
        name: 'Reporte Saldos (Consolidado - Detallado)',
        url: '/base/collapses',
        icon: 'icon-chart'
      },
      {
        name: 'Reporte Capital Promedio',
        url: '/base/forms',
        icon: 'icon-chart'
      },     
    ]
  },
  {
    name: 'Consulta Operaciones',
    url: '/buttons',
    icon: 'cui-magnifying-glass',
    children: [
      {
        name: 'Vistas predeterminadas',
        url: '/buttons/buttons',
        icon: 'cui-magnifying-glass'
      }
    ]
  },
  {
    name: 'Consulta Clientes',
    url: '/charts',
    icon: 'cui-magnifying-glass'
  },
  {
    name: 'Consulta Cierres',
    url: '/icons',
    icon: 'icon-star',
    children: [
      {
        name: 'Tapa Cierre',
        url: '/icons/coreui-icons',
        icon: 'cui-magnifying-glass',
      }
    ]
  },
  {
    name: 'Reporte Auditoría',
    url: '/notifications',
    icon: 'icon-chart',
    children: [
      {
        name: 'Vistas de Seguimiento',
        url: '/notifications/alerts',
        icon: 'icon-chart'
      }
    ]
  },
  {
    divider: true
  },
  {
    title: true,
    name: 'Módulo Configuración',
  },
  {
    name: 'Empresa',
    url: '/empresa',
    icon: 'fa fa-industry',
  },
  {
    name: 'Sucursales',
    url: '/sucursales',
    icon: 'icon-home',
  },
  {
    name: 'Lista Sucursales',
    url: '/lista-sucursales',
    icon: 'icon-home',
  },
  {
    name: 'Cajas',
    url: '/cajas',
    icon: 'icon-home',
  },
  {
    name: 'Lista cajas',
    url: '/lista-cajas',
    icon: 'icon-home',
  },
  {
    name: 'Usuarios',
    url: '/usuarios',
    icon: 'icon-people',
  },
  {
    name: 'Turno',
    url: '/turno',
    icon: 'icon-people',
  },
  {
    name: 'Monedas',
    url: '/monedas',
    icon: 'fa fa-money',
  },
  {
    name: 'Contable',
    url: '/pages',
    icon: 'icon-pie-chart',
  },
  {
    name: 'Flujos',
    url: '/pages',
    icon: 'icon-equalizer',
  },
  {
    name: 'Ciiu',
    url: '/pages',
    icon: 'icon-star',
  },
  {
    name: 'Auditoría',
    url: '/pages',
    icon: 'cui-magnifying-glass',
  },
  {
    name: 'Administracion de Seguridad',
    url: '/notifications',
    icon: 'icon-chart',
    children: [
      {
        name: 'Roles',
        url: '/security/roles',
        icon: 'icon-chart'
      },
      {
        name: 'Resources',
        url: '/security/resources',
        icon: 'icon-chart'
      },
      {
        name: 'Menus',
        url: '/security/menus',
        icon: 'icon-chart'
      },
      {
        name: 'Funcionalidades',
        url: '/security/functionalities',
        icon: 'icon-chart'
      }
    ]
  },
];