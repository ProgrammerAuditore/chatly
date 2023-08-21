package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.ventanas.VentanaHome;
import vista.ventanas.VentanaPrincipal;

public class CtrlVentanaHome {
    
    // ****** Vista
    VentanaHome laVista;
    
    // ****** Modelos 
    
    // ****** Atributos
    
    // ****** Constructores
    public CtrlVentanaHome( VentanaHome vista ){
        this.laVista = vista;
        this.mtdInit();
    }
    
    // ****** Construir eventos
    private void mtdBuildEventBtnCerrarSesion(){
        MouseListener evtCerrarSesion = null;
        this.laVista.btnHomeCerrarSesion.removeMouseListener(evtCerrarSesion);
        
        evtCerrarSesion = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                mtdBtnCerrarSesion();
            }
        };
        
        this.laVista.btnHomeCerrarSesion.addMouseListener(evtCerrarSesion);
    }
    
    // ****** Métodos
    private void mtdInit(){
        
        // * Definir oyentes
        laVista.setLocationRelativeTo(null);
        mtdBuildEventBtnCerrarSesion();
    }
    
    private void mtdBtnCerrarSesion(){
        this.laVista.setVisible(false);
        this.laVista.dispose();
        
        VentanaPrincipal vtn = new VentanaPrincipal();
        CtrlVentanaPrincipal ctrl = new CtrlVentanaPrincipal(vtn);
        ctrl.laVista.setVisible(true);
        
    }
    
}
