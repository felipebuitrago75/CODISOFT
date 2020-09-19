export class PathConstants {

    public static PATH_EMPTY: string = "";

    public static PATH_SECURITY: string = "security";
    public static PATH_LOGIN: string = "login";
    public static PATH_REGISTER: string = "register";
    public static PATH_SECURITY_LOGIN: string = PathConstants.PATH_SECURITY + "/" + PathConstants.PATH_LOGIN;
    

 
    public static PATH_HOME: string = "web";
    public static PATH_INICIO: string = "inicio";
    public static PATH_ADMIN: string = "administration";
    public static FULLPATH_PAGINA_INICIO: string = PathConstants.PATH_HOME +"/"+PathConstants.PATH_INICIO;


    
    public static PATH_ROLES_SEARCH: string = "roles";
    public static PATH_ROLE_FORM: string = "role";
    public static PATH_SUCURSAL_FROM: string = "sucursales";
    public static PATH_FLUJO_EXTRAORDINARIO_FROM: string = "registro-flujos";
    public static FULLPATH_ROLES_SEARCH: string = PathConstants.PATH_SECURITY + "/" + PathConstants.PATH_ROLES_SEARCH;
    public static FULLPATH_ROLE_FORM: string = PathConstants.PATH_SECURITY + "/" + PathConstants.PATH_ROLE_FORM;
    public static FULLPATH_FLUJO_EXTRAORDINARIO_FORM: string = PathConstants.PATH_SECURITY + "/" + PathConstants.PATH_FLUJO_EXTRAORDINARIO_FROM;
    public static FULLPATH_SUCURSAL_FORM: string = PathConstants.PATH_SECURITY + "/" + PathConstants.PATH_SUCURSAL_FROM;

    public static PATH_RESOURCES_SEARCH: string = "resources";
    public static PATH_RESOURCE_FORM: string = "resource";
    public static FULLPATH_RESOURCES_SEARCH: string = PathConstants.PATH_SECURITY + "/" + PathConstants.PATH_RESOURCES_SEARCH;
    public static FULLPATH_RESOURCE_FORM: string = PathConstants.PATH_SECURITY + "/" + PathConstants.PATH_RESOURCE_FORM;

    public static PATH_MENUS_SEARCH: string = "menus";
    public static PATH_MENU_FORM: string = "menu";
    public static FULLPATH_MENUS_SEARCH: string = PathConstants.PATH_SECURITY + "/" + PathConstants.PATH_MENUS_SEARCH;
    public static FULLPATH_MENU_FORM: string = PathConstants.PATH_SECURITY + "/" + PathConstants.PATH_MENU_FORM;

    public static PATH_FUNCTIONALITIES_SEARCH: string = "functionalities";
    public static PATH_FUNCTIONALITY_FORM: string = "functionality";
    public static FULLPATH_FUNCTIONALITIES_SEARCH: string = PathConstants.PATH_SECURITY + "/" + PathConstants.PATH_FUNCTIONALITIES_SEARCH;
    public static FULLPATH_FUNCTIONALITY_FORM: string = PathConstants.PATH_SECURITY + "/" + PathConstants.PATH_FUNCTIONALITY_FORM;

    
    
    
}
