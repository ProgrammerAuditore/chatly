package index;

import controlador.CtrlVentanaPrincipal;
import javax.swing.JFrame;
import modelo.dao.PerfilDao;
import modelo.dto.PerfilDto;
import src.Info;
import src.SrcChatly;
import vista.ventanas.VentanaPrincipal;

public class Chatly {
    
    public void mtdTagInit() {
        
        // * Crear la ventanaPrincipal principal con su respectivo patrón de diseño MVC
        SrcChatly.ventanaPrincipal = new VentanaPrincipal();
        PerfilDao dao = new PerfilDao();
        PerfilDto dto = new PerfilDto();
        CtrlVentanaPrincipal ctrl_p = new CtrlVentanaPrincipal(SrcChatly.ventanaPrincipal, dao, dto);
    
        // * Ejecutar hilos
        
        // * Abrir la ventanaPrincipal del programa
        SrcChatly.ventanaPrincipal.setState(JFrame.ICONIFIED);
        SrcChatly.ventanaPrincipal.setVisible(true);
        try {
            Thread.sleep(600);
        } catch (InterruptedException ex) {}
        SrcChatly.ventanaPrincipal.setAutoRequestFocus(true);
        SrcChatly.ventanaPrincipal.requestFocus();
        SrcChatly.ventanaPrincipal.setExtendedState(JFrame.NORMAL);
        SrcChatly.ventanaPrincipal.setVisible(true);
        
    }
    
    // * Mostrar mensaje de ayuda en la terminal
    public void mtdTagHelp(){
        System.out.println(Info.NombreSoftware);
        System.out.println(Info.Avatar);
        System.out.println("");
        System.out.println(SrcChatly.idioma.get("MyFreeLab.mtdTagHelp.msg1"));
        
        System.out.print("  --init              ");
        System.out.println(SrcChatly.idioma.get("MyFreeLab.mtdTagHelp.msg2") +" "+Info.NombreSoftware);
        
        System.out.print("  --help, -h          ");
        System.out.println(SrcChatly.idioma.get("MyFreeLab.mtdTagHelp.msg5"));
        
        System.out.println("");
        System.out.println(Info.SitioWeb);
   
    }
    
    // * Inicializar el programa de pruebas
    public void mtdTagTest(){
        SrcChatly.mtdVerInformacionDelSoftware();
    }    
}
