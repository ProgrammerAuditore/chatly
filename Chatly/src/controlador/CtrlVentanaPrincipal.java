package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import src.SrcChatly;
import vista.ventanas.VentanaHome;
import vista.ventanas.VentanaPrincipal;

public class CtrlVentanaPrincipal {

    // * Vista
    public VentanaPrincipal laVista;

    // * Modelos
    
    // * Atributos
    
    // ****** Constructores
    public CtrlVentanaPrincipal(VentanaPrincipal laVista) {
        this.laVista = laVista;

        // * Inicializar
        mtdInit();
    }

    // * Construir eventos
    private void mtdBuildEvents(){
        MouseListener evt = null;
        this.laVista.removeMouseListener(evt);
        
        evt = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                if( e.getSource() == laVista.btnSingIn ){
                    mtdBtnSingIn();
                } else 
                if ( e.getSource() == laVista.btnSingUp ){
                    mtdBtnSingUp();
                }
            }
        };
        
        this.laVista.btnSingIn.addMouseListener(evt);
        this.laVista.btnSingUp.addMouseListener(evt);
        this.laVista.addMouseListener(evt);
    }

    private void mtdInit() {

        // * Definir oyentes
        laVista.setLocationRelativeTo(null);
        mtdBuildEvents();
    }

    private void mtdBtnSingIn() {
        if(!mtdVerificarDatosSingIn()){
            return;
        }
        
        mtdDestruirVentana();
        SrcChatly.ventanaHome = new VentanaHome();
        CtrlVentanaHome home = new CtrlVentanaHome(SrcChatly.ventanaHome);
        home.laVista.setVisible(true);
    }
    
    private boolean mtdVerificarDatosSingIn(){
        String msg = "Datos ingresados incorrectos: \n";
        int msg_tam = msg.length();
        
        if( this.laVista.cmpSingInEmail.getText().isEmpty()
            || !this.laVista.cmpSingInEmail.isAprobado()){
            msg += "* El campo correo eléctronico es incorrecto. \n";
        }
        
        if( this.laVista.cmpSingInPassword.isVacia()
            || !this.laVista.cmpSingInPassword.isAprobado()){
            msg += "* El campo contraseña debe ser mayor a 3 caracteres. \n";
        }
        
        if(msg_tam == msg.length()){
            return true;
        }else {
            JOptionPane.showMessageDialog(laVista, msg, "Acceder", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private void mtdBtnSingUp(){
        if(!mtdVerificarDatosSingUp()){
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente!!", "Registrarme", JOptionPane.INFORMATION_MESSAGE);
    }   
    
    private boolean mtdVerificarDatosSingUp(){
        String msg = "Datos ingresados incorrectos: \n";
        int msg_tam = msg.length();
        
        if( this.laVista.cmpSingUpFirstNames.getText().isEmpty() 
            || !this.laVista.cmpSingUpFirstNames.isAprobado()){
            msg += "* El campo nombre(s) es incorrecto. \n";
        }
        
        if( this.laVista.cmpSingUpLasttNames.getText().isEmpty()
            || !this.laVista.cmpSingUpLasttNames.isAprobado()){
            msg += "* El campo apellido(s) es incorrecto. \n";
        }
        
        if( this.laVista.cmpSingUpEmail.getText().isEmpty()
            || !this.laVista.cmpSingUpEmail.isAprobado()){
            msg += "* El campo correo eléctronico es incorrecto. \n";
        }
        
        if( this.laVista.cmpSingUpPassword.isVacia()
            || !this.laVista.cmpSingUpPassword.isAprobado()){
            msg += "* El campo contraseña debe ser mayor a 3 caracteres. \n";
        }
        
        if(msg_tam == msg.length()){
            return true;
        }else {
            JOptionPane.showMessageDialog(laVista, msg, "Registrarme", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private void mtdDestruirVentana(){
        // Se borra la ventana Principal liberando memoria
        this.laVista.setVisible(false); // Desaparece la ventana
        this.laVista.dispose(); // Se libera la memoria
        SrcChatly.ventanaPrincipal = null;
    }

}
