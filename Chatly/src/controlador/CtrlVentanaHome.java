package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import src.SrcChatly;
import vista.paneles.PanelDatos;
import vista.paneles.PanelPassword;
import vista.ventanas.VentanaAmigos;
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
    private void mtdBuildEvents(){
        MouseListener evt = null;
        this.laVista.removeMouseListener(evt);
        
        evt = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                if( e.getSource() == laVista.btnHomeCerrarSesion  ){
                    mtdBtnCerrarSesion();
                } else 
                if( e.getSource() == laVista.btnCoumidad ){
                    mtdBtnComunidad();
                } else 
                if( e.getSource() == laVista.btnConversaciones ){
                    mtdBtnConversaciones();
                } else 
                if( e.getSource() == laVista.btnAmigos ){
                    mtdBtnAmigos();
                } else 
                if( e.getSource() == laVista.menuItemComunidad ){
                    mtdBtnComunidad();
                } else 
                if( e.getSource() == laVista.menuItemConversaciones ){
                    mtdBtnConversaciones();
                } else 
                if( e.getSource() == laVista.menuItemAmigos ){
                    mtdBtnAmigos();
                } else 
                if( e.getSource() == laVista.menuItemDatos ){
                    mtdBtnDatos();
                } else 
                if( e.getSource() == laVista.menuItemPassword ){
                    mtdBtnPassword();
                }
            }
        };
        
        this.laVista.btnHomeCerrarSesion.addMouseListener(evt);
        this.laVista.btnCoumidad.addMouseListener(evt);
        this.laVista.btnConversaciones.addMouseListener(evt);
        this.laVista.btnAmigos.addMouseListener(evt);
        this.laVista.menuItemComunidad.addMouseListener(evt);
        this.laVista.menuItemConversaciones.addMouseListener(evt);
        this.laVista.menuItemAmigos.addMouseListener(evt);
        this.laVista.menuItemDatos.addMouseListener(evt);
        this.laVista.menuItemPassword.addMouseListener(evt);
        this.laVista.addMouseListener(evt);
    }
    
    // ****** Métodos
    private void mtdInit(){
        
        // * Definir oyentes
        laVista.setLocationRelativeTo(null);
        mtdBuildEvents();
        
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
    
    private void mtdBtnAmigos(){
        mtdDestruirVentana();
        SrcChatly.ventanaAmigos = new VentanaAmigos();
        CtrlVentanaAmigos ctrl = new CtrlVentanaAmigos(SrcChatly.ventanaAmigos);
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
