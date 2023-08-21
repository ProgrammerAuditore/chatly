package index;

import controlador.CtrlVentanaPrincipal;
import javax.swing.JFrame;
import src.Info;
import src.SrcChatly;
import vista.ventanas.VentanaPrincipal;

public class Chatly {
    
    public void mtdTagInit() {
        
        // * Crear la ventana principal con su respectivo patrón de diseño MVC
        SrcChatly.ventana = new VentanaPrincipal();
        CtrlVentanaPrincipal ctrl_p = new CtrlVentanaPrincipal(SrcChatly.ventana);
    
        // * Ejecutar hilos
        
        // * Abrir la ventana del programa
        SrcChatly.ventana.setState(JFrame.ICONIFIED);
        SrcChatly.ventana.setVisible(true);
        try {
            Thread.sleep(600);
        } catch (InterruptedException ex) {}
        SrcChatly.ventana.setAutoRequestFocus(true);
        SrcChatly.ventana.requestFocus();
        SrcChatly.ventana.setExtendedState(JFrame.NORMAL);
        SrcChatly.ventana.setVisible(true);
        
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
