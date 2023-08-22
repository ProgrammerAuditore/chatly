package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import src.SrcChatly;
import vista.paneles.PanelDatos;
import vista.paneles.PanelPassword;
import vista.ventanas.VentanaComunidad;
import vista.ventanas.VentanaConversaciones;
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
    
    private void mtdBuildEventBtnComunidad(){
        MouseListener evtComunidad = null;
        this.laVista.btnCoumidad.removeMouseListener(evtComunidad);
        
        evtComunidad = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                mtdBtnComunidad();
            }
        };
        
        this.laVista.btnCoumidad.addMouseListener(evtComunidad);
    }
    
    private void mtdBuildEventMenuItemComunidad(){
        MouseListener evtComunidad = null;
        this.laVista.menuItemComunidad.removeMouseListener(evtComunidad);
        
        evtComunidad = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                mtdBtnComunidad();
            }
        };
        
        this.laVista.menuItemComunidad.addMouseListener(evtComunidad);
    }
    
    private void mtdBuildEventBtnConversaciones(){
        MouseListener evtComunidad = null;
        this.laVista.btnConversaciones.removeMouseListener(evtComunidad);
        
        evtComunidad = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                mtdBtnConversaciones();
            }
        };
        
        this.laVista.btnConversaciones.addMouseListener(evtComunidad);
    }
    
    private void mtdBuildEventMenuItemConversaciones(){
        MouseListener evtComunidad = null;
        this.laVista.menuItemConversaciones.removeMouseListener(evtComunidad);
        
        evtComunidad = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                mtdBtnConversaciones();
            }
        };
        
        this.laVista.menuItemConversaciones.addMouseListener(evtComunidad);
    }
    
    private void mtdBuildEventMenuItemDatos(){
        MouseListener evtDatos = null;
        this.laVista.menuItemDatos.removeMouseListener(evtDatos);
        
        evtDatos = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                mtdBtnDatos();
            }
        };
        
        this.laVista.menuItemDatos.addMouseListener(evtDatos);
    }
    
    private void mtdBuildEventMenuItemPassword(){
        MouseListener evtDatos = null;
        this.laVista.menuItemPassword.removeMouseListener(evtDatos);
        
        evtDatos = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                mtdBtnPassword();
            }
        };
        
        this.laVista.menuItemPassword.addMouseListener(evtDatos);
    }
    
    // ****** Métodos
    private void mtdInit(){
        
        // * Definir oyentes
        laVista.setLocationRelativeTo(null);
        mtdBuildEventBtnCerrarSesion();
        mtdBuildEventBtnComunidad();
        mtdBuildEventMenuItemComunidad();
        mtdBuildEventBtnConversaciones();
        mtdBuildEventMenuItemConversaciones();
        mtdBuildEventMenuItemDatos();
        mtdBuildEventMenuItemPassword();
    }
    
    private void mtdBtnCerrarSesion(){
        mtdDestruirVentana();
        SrcChatly.ventanaPrincipal = new VentanaPrincipal();
        CtrlVentanaPrincipal ctrl = new CtrlVentanaPrincipal(SrcChatly.ventanaPrincipal);
        ctrl.laVista.setVisible(true);
    }
    
    private void mtdBtnComunidad(){
        mtdDestruirVentana();
        SrcChatly.ventanaComunidad = new VentanaComunidad();
        CtrlVentanaComunidad ctrl = new CtrlVentanaComunidad(SrcChatly.ventanaComunidad);
        ctrl.laVista.setVisible(true);
    }
    
    private void mtdBtnConversaciones(){
        mtdDestruirVentana();
        SrcChatly.ventanaConversaciones = new VentanaConversaciones();
        CtrlVentanaConversaciones ctrl = new CtrlVentanaConversaciones(SrcChatly.ventanaConversaciones);
        ctrl.laVista.setVisible(true);
    }
    
    private void mtdBtnDatos(){
        PanelDatos panel = new PanelDatos();
        CtrlPanelDatos ctrl = new CtrlPanelDatos(panel);
        ctrl.modal = new JDialog(laVista);
        ctrl.mtdInit();
        ctrl.modal.setLocationRelativeTo(laVista);
        ctrl.modal.setVisible(true);
    }
    
    private void mtdBtnPassword(){
        PanelPassword panel = new PanelPassword();
        CtrlPanelPassword ctrl = new CtrlPanelPassword(panel);
        ctrl.modal = new JDialog(laVista);
        ctrl.mtdInit();
        ctrl.modal.setLocationRelativeTo(laVista);
        ctrl.modal.setVisible(true);
    }
   
    private void mtdDestruirVentana(){
        // Se borra la ventana Principal liberando memoria
        this.laVista.setVisible(false); // Desaparece la ventana
        this.laVista.dispose(); // Se libera la memoria
        SrcChatly.ventanaHome = null;
    }
    
}
