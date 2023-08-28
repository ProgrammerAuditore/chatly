package controlador;

import java.awt.Dialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import src.SrcChatly;
import vista.paneles.PanelDatos;

public class CtrlPanelDatos {
    // ****** Vista
    private PanelDatos laVista;
    public JDialog modal;
    
    // ****** Modelos
    
    // ****** Atributos
    
    // ****** Constructores
    public CtrlPanelDatos(PanelDatos vista){
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
        
        mtdEstablecerDatos();
        mtdBuildEvents();
        modal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                modal.setVisible(false);
                modal.dispose();
            }
        });
        
    }
    
    private void mtdEstablecerDatos(){
        this.laVista.cmpNombres.setText(SrcChatly.dto.getsNombres());
        this.laVista.cmpApellidos.setText(SrcChatly.dto.getsApellidos());
        this.laVista.cmpBio.setText(SrcChatly.dto.getsBio());
    }
    
    private void mtdBtnAceptar(){
        if(!mtdVerificarDatos()){
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Datos modificado exitosamente!!", "Cambiar datos", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private boolean mtdVerificarDatos(){
        String msg = "Datos ingresados incorrectamente: \n";
        int msg_tam = msg.length();
        
        if( this.laVista.cmpNombres.getText().isEmpty() 
            || !this.laVista.cmpNombres.isAprobado()){
            msg += "* El campo nombre(s) es incorrecto. \n";
        }
        
        if( this.laVista.cmpApellidos.getText().isEmpty()
            || !this.laVista.cmpApellidos.isAprobado()){
            msg += "* El campo apellido(s) es incorrecto. \n";
        }
        
        if( this.laVista.cmpBio.getText().isEmpty()){
            msg += "* El campo bio es incorrecto. \n";
        }
        
        if( msg.length() != msg_tam ){
            JOptionPane.showMessageDialog(null, msg, "Cambiar datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}
