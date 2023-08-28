package controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.Storage;
import modelo.dao.PerfilDao;
import modelo.dto.PerfilDto;
import src.SrcChatly;
import vista.ventanas.VentanaHome;
import vista.ventanas.VentanaPrincipal;

public class CtrlVentanaPrincipal {

    // * Vista
    public VentanaPrincipal laVista;

    // * Modelos
    private PerfilDao dao;
    private PerfilDto dto;
    
    // * Atributos
    
    // ****** Constructores
    public CtrlVentanaPrincipal(VentanaPrincipal laVista, PerfilDao dao, PerfilDto dto) {
        this.laVista = laVista;
        this.dao = dao;
        this.dto = dto;

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
    
    private void mtdBuildKeyEvents(){
        KeyListener evt = null;
        this.laVista.removeKeyListener(evt);
        
        evt = new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                if( e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == laVista.cmpSingInEmail ){
                    laVista.requestFocusInWindow();
                    mtdBtnSingIn();
                } else 
                if( e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == laVista.cmpSingInPassword ){
                    laVista.requestFocusInWindow();
                    mtdBtnSingIn();
                } else 
                if( e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == laVista.cmpSingUpEmail ){
                    laVista.requestFocusInWindow();
                    mtdBtnSingUp();
                } else 
                if( e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == laVista.cmpSingUpPassword ){
                    laVista.requestFocusInWindow();
                    mtdBtnSingUp();
                } 
            }
        };
        
        this.laVista.cmpSingInEmail.addKeyListener(evt);
        this.laVista.cmpSingInPassword.addKeyListener(evt);
        this.laVista.cmpSingUpEmail.addKeyListener(evt);
        this.laVista.cmpSingUpPassword.addKeyListener(evt);
        this.laVista.addKeyListener(evt);
    }

    private void mtdInit() {

        // * Definir oyentes
        laVista.setLocationRelativeTo(null);
        mtdBuildEvents();
        mtdBuildKeyEvents();
        
    }

    private void mtdBtnSingIn() {
        if(!mtdVerificarDatosSingIn()){
            return;
        } else
        if(!dao.mtdVerificarPerfil(this.dto) || !Storage.fncStorageVerificarUnaCuenta(dto.getsCorreo()) ){
            JOptionPane.showMessageDialog(null, "Usuario no existente!! Registrate !!", "Acceder", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // * Obtenemos los datos de la cuenta y verificamos la contraseña
        dao.mtdObtenerPerfil(this.dto);
        if( this.dto.getsPassword().equals(String.valueOf(this.laVista.cmpSingInPassword.getPassword())) ){
            
            mtdDestruirVentana();
            SrcChatly.ventanaHome = new VentanaHome();
            SrcChatly.dao = dao;
            SrcChatly.dto = dto;
            CtrlVentanaHome home = new CtrlVentanaHome(SrcChatly.ventanaHome);
            home.laVista.setVisible(true);
            
        } else  {
            JOptionPane.showMessageDialog(null, "Correo eléctronico o Contraseña incorrecto !!", "Acceder", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
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
        
        if(msg_tam != msg.length()){
            JOptionPane.showMessageDialog(laVista, msg, "Acceder", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // * Si no hay error se establece los valores a DTO
        this.dto.setsCorreo(this.laVista.cmpSingInEmail.getText().trim());
        this.dto.setsPassword(String.valueOf(this.laVista.cmpSingInPassword.getPassword()).trim());
        
        return true;
    }
    
    private void mtdBtnSingUp(){
        if(!mtdVerificarDatosSingUp()){
            return;
        }else
        if(dao.mtdVerificarPerfil(this.dto) || Storage.fncStorageVerificarUnaCuenta(dto.getsCorreo()) ){
            JOptionPane.showMessageDialog(null, "Usuario ya está registrado!!", "Registrarme", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if( dao.mtdCrearPerfil(dto) && Storage.fncStorageVerificarUnaCuenta(dto.getsCorreo()) ){
            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente!!", "Registrarme", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Hubo un error al crear la cuenta!!\nIntentalo otra vez!!", "Registrarme", JOptionPane.ERROR_MESSAGE);
        }
        
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
        
        if(msg_tam != msg.length()){
            JOptionPane.showMessageDialog(laVista, msg, "Registrarme", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // * Si no hay error se establece los valores a DTO
        this.dto.setsNombres(this.laVista.cmpSingUpFirstNames.getText().trim());
        this.dto.setsApellidos(this.laVista.cmpSingUpLasttNames.getText().trim());
        this.dto.setsCorreo(this.laVista.cmpSingUpEmail.getText().trim());
        this.dto.setsPassword(String.valueOf(this.laVista.cmpSingUpPassword.getPassword()).trim());
        this.dto.setsFotoPerfil("user_default.png");
        
        return true;
    }
    
    private void mtdDestruirVentana(){
        // Se borra la ventana Principal liberando memoria
        this.laVista.setVisible(false); // Desaparece la ventana
        this.laVista.dispose(); // Se libera la memoria
        SrcChatly.ventanaPrincipal = null;
    }

}
