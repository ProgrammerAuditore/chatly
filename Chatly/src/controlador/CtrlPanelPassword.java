package controlador;

import java.awt.Dialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import vista.paneles.PanelPassword;

public class CtrlPanelPassword {
    // ****** Vista
    private PanelPassword laVista;
    public JDialog modal;
    
    // ****** Modelos
    
    // ****** Atributos
    
    // ****** Constructores
    public CtrlPanelPassword(PanelPassword vista){
        this.laVista = vista;
    }
    
    // ****** Construir eventos
    private void mtdBuildEvents(){
        MouseListener evt = null;
        this.laVista.removeMouseListener(evt);
        
        evt = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                if( e.getSource() == laVista.btnAceptar ){
                    mtdBtnAceptar();
                }
            }   
        };
        
        this.laVista.btnAceptar.addMouseListener(evt);
        this.laVista.addMouseListener(evt);
    }
    
    // ****** Métodos
    public void mtdInit(){
        //modal.setModal(true);
        //modal.setType(Window.Type.UTILITY);
        modal.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        //modal.setTitle(SrcMyFreeLab.idioma.getProperty("ctrlDatosPersonales.mtdInit.titulo"));
        modal.setResizable(false);
        modal.setSize( laVista.getSize() );
        modal.setPreferredSize(laVista.getSize() );
        modal.setContentPane(laVista);
        
        mtdBuildEvents();
        modal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                modal.setVisible(false);
                modal.dispose();
            }
        });
        
    }

    private void mtdBtnAceptar(){
        if(!mtdVerificarDatos()){
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Contraseña modificado exitosamente!!", "Cambiar contraseña", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private boolean mtdVerificarDatos(){
        String msg = "Datos ingresados incorrectos: \n";
        int msg_tam = msg.length();
        
        if( this.laVista.cmpPasswdActual.isVacia()
            || !this.laVista.cmpPasswdActual.isAprobado()){
            this.laVista.cmpPasswdActual.getEstiloNoAprobado();
            msg += "* El campo contraseña actual debe ser mayor a 3 caracteres. \n";
        }
        
        if( this.laVista.cmpPasswdNueva.isVacia()
            || !this.laVista.cmpPasswdNueva.isAprobado()){
            this.laVista.cmpPasswdNueva.getEstiloNoAprobado();
            msg += "* El campo contraseña nueva debe ser mayor a 3 caracteres. \n";
        }
        
        if( this.laVista.cmpPasswdNuevaRepetir.isVacia()
            || !this.laVista.cmpPasswdNuevaRepetir.isAprobado()){
            this.laVista.cmpPasswdNuevaRepetir.getEstiloNoAprobado();
            msg += "* El campo reptir contraseña debe ser mayor a 3 caracteres. \n";
        }
        
        if( msg.length() != msg_tam ){
            JOptionPane.showMessageDialog(null, msg, "Cambiar contraseña", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
}
