package src;

import java.io.File;
import java.util.Properties;
import src.idiomas.Idiomas;
import vista.ventanas.VentanaAmigos;
import vista.ventanas.VentanaComunidad;
import vista.ventanas.VentanaConversaciones;
import vista.ventanas.VentanaHome;
import vista.ventanas.VentanaPrincipal;

public class SrcChatly {
    
    public static VentanaPrincipal ventanaPrincipal;
    public static VentanaHome ventanaHome;
    public static VentanaComunidad ventanaComunidad;
    public static VentanaAmigos ventanaAmigos;
    public static VentanaConversaciones ventanaConversaciones;
    public static Properties idioma = new Idiomas("en");
    public static long ctrlID;
    public static String IdiomaDefinido;
    
    public static void mtdVerInformacionDelSoftware() {
        System.out.println("#DirHome : " + Recursos.dirHome );
        System.out.println("#Path Actual : " + new File(".").getAbsolutePath());
        System.out.println("#SO : " + Recursos.SistemaOs);
        System.out.println("#TimeTmp : " + Recursos.timeTmp);
        System.out.println("#bkgAside : " + Recursos.bkgAside);
        System.out.println("#bkgLogo : " + Recursos.bkgLogo);
        System.out.println("#dataPreferencias : " + Recursos.dataPreferencias().getAbsolutePath());
        System.out.println("#dataConexion : " + Recursos.dataConexion().getAbsolutePath());
        System.out.println("#dataRun : " + Recursos.dataRun().getAbsolutePath());
        //System.out.println("#docVersionesXml : " + Recursos.docVersionesXml);
        System.out.println("#docReporte : " + Recursos.docCotizacionJasper());
        System.out.println("#\n");
    }
    
    public static void mtdTerminar(){
        System.exit(0);
    }
    
}
