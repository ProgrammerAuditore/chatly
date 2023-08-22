package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import src.SrcChatly;
import vista.ventanas.VentanaConversaciones;
import vista.ventanas.VentanaHome;

public class CtrlVentanaConversaciones {
    
    // ****** Vista
    public VentanaConversaciones laVista;   
    
    // ****** Modelo
    
    // ****** Atributos
    
    // ****** Constructores
    public CtrlVentanaConversaciones(VentanaConversaciones vista){
        this.laVista = vista;
        mtdInit();
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
