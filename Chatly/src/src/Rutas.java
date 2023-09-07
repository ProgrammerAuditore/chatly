package src;

class Rutas {
    
    // ****** Obtener recursos desde la raíz el ejecutable .jar (Recursos Externo)
    public static String pathDataConexion = "chatly/conn"; // * sin utilidad
    public static String pathDataEjecucion = "chatly/.run"; // * sin utilidad
    public static String pathDataPreferencias = "chatly/.pconfig"; // * sin utilidad
    public static String pathSharedLinux = "/opt/chatly/shared";
    public static String pathSharedWin = "shared";
    
    // * Archivos importantes *WARNING*
    public static final String default_img = "user_default.png";
    public static final String path_profiles = "storage_profiles/database.profiles";
    public static final String path_tmp_profiles = "storage_profiles/_db.profiles";
    
    // * Rutas importantes *WARNING*  
    public static final String storage_prof = "storage_profiles/profile/";
    public static final String storage_profiles = "storage_profiles/";
    public static final String extesion_chatmp = "tmp_";
    public static final String storage_profile = "/profile/";
    public static final String storage_chats = "/chats/";
    
    // * Extensiones importantes *WARNING*
    public static final String extesion_friends = ".friends";
    public static final String extesion_chats = ".chats";
    public static final String extesion_tome = ".tome";
    public static final String extesion_notify = ".notify";
    public static final String extesion_svg = ".svg";
    public static final String extesion_data = ".data";
    
    // Este ruta es necesario usarlo con getClass().getResource();
    public static final String path_user_default = "img/user_default.png";
    
    // ****** Obtener recursos desde el ejecutable .jar (Recursos Interno)
    public static String pathBkgDefault =  "/storage/img/background_main.png";
    public static String pahtContenedorBotones = "/storage/buttons/";
    public static String pathContenedorFuentes = "/storage/fonts/";
    public static String pathContenedorImagenes = "/storage/img/";
    public static String pathContenedorIconos = "/storage/icons/";
    public static String pathBkgAside = "/storage/img/background_aside.png";
    public static String pathBkgConexionOff = "/storage/img/logo-mysql-off.png";
    public static String pathBkgConexionOn = "/storage/img/logo-mysql-on.png";
    public static String pathBkgPortada = "/storage/img/panel_logo.png";
    public static String pathIconoDefultMs = "/storage/img/chatly-30x30.png";
    public static String pathIconoDefultApple = "/storage/img/chatly-30x30.png";
    public static String pathDocumentoVersionesXml = "/storage/etc/MyFreeLab_Versions.xml";
    //public static String pathBkgSplash = "/storage/img/banner.png";
    
}
