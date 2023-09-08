package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import src.Info;
import src.Recursos;
import src.SrcChatly;
import vista.ventanas.VentanaAmigos;
import vista.ventanas.VentanaHome;

public class CtrlVentanaAmigos {
    // ****** Vista
    public VentanaAmigos laVista;
    
    // ****** Modelo
    
    // ****** Atributos
    
    // ****** Constructores
    public CtrlVentanaAmigos(VentanaAmigos vista){
        this.laVista = vista;
        this.mtdInit();
    }
     
    // ****** Construir eventos
    private void mtdBuildEventBtnVolver(){
        MouseListener evtBtnVolver = null;
        this.laVista.btnVolver.removeMouseListener(evtBtnVolver);
        
        evtBtnVolver = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                mtdBtnVolver();
            }
        };
        
        this.laVista.btnVolver.addMouseListener(evtBtnVolver);
    }
    
    // ****** Métodos
    private void mtdInit(){
        this.laVista.setLocationRelativeTo(null);
        this.laVista.setIconImage(Recursos.imgIconoDefault());
        SrcChatly.ventanaAmigos.setTitle(Info.NombreSoftware + " - " + SrcChatly.dto.getsCorreo() );
        mtdBuildEventBtnVolver();
    }
    
    private void mtdBtnVolver(){
        mtdDestruirVentana();
        SrcChatly.ventanaHome = new VentanaHome();
        CtrlVentanaHome ctrl = new CtrlVentanaHome(SrcChatly.ventanaHome);
        ctrl.laVista.setVisible(true);
    }
    
    private void mtdDestruirVentana(){
        // Se borra la ventana Principal liberando memoria
        this.laVista.setVisible(false); // Desaparece la ventana
        this.laVista.dispose(); // Se libera la memoria
        SrcChatly.ventanaHome = null;
    }
}
