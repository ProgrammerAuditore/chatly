package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import modelo.dao.PerfilDao;
import modelo.dto.PerfilDto;
import src.Info;
import src.Recursos;
import src.SrcChatly;
import vista.ventanas.VentanaComunidad;
import vista.ventanas.VentanaHome;
import vista.ventanas.VentanaPerfil;

public class CtrlVentanaPerfil {
    
    // ****** Vista
    public VentanaPerfil laVista;
    
    // ****** Modelos
    private PerfilDto dto;
    private PerfilDao dao;
    
    // ****** Atributos
    private Integer estadoAmistad;
    
    // ****** Constructores
    public CtrlVentanaPerfil(VentanaPerfil laVista, PerfilDto dto, PerfilDao dao) {
        this.laVista = laVista;
        this.dto = dto;
        this.dao = dao;
        this.mtdInit();
    }
    
    // ****** Construir eventos
    private void mtdBuildMouseListener(){
        MouseListener evt = null;
        this.laVista.addMouseListener(evt);
        
        evt = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                if( e.getSource() == laVista.btnVolver ){
                    mtdBtnVolver();
                } else if (e.getSource() == laVista.btnAmigos && estadoAmistad == 0) {
                    mtdBtnAmigoPlusEnviarAmistad();
                } else if (e.getSource() == laVista.btnAmigos&& estadoAmistad == 100) {
                    mtdBtnAmigoPlusEnviadaAmistad();
                } else if (e.getSource() == laVista.btnAmigos && estadoAmistad == 200) {
                    mtdBtnAmigoPlusRechazarAmistad();
                } else if (e.getSource() == laVista.btnAmigos && estadoAmistad == 1000) {
                    mtdBtnAmigoPlusEliminarAmistad();
                }
            }
        };
        
        this.laVista.btnVolver.addMouseListener(evt);
        this.laVista.btnAmigos.addMouseListener(evt);
        this.laVista.removeMouseListener(evt);
    }
    
    private void mtdBuildWindownListener(){
        WindowListener evt = null;
        this.laVista.removeWindowListener(evt);
        
        evt = new WindowAdapter(){
            @Override
            public void windowOpened(WindowEvent e) {
                mtdBienvenida();
            }
        };
        
       this.laVista.addWindowListener(evt);
    }
    
    // ****** Métodos
    private void mtdInit(){
        this.laVista.setLocationRelativeTo(null);
        this.laVista.setIconImage(Recursos.imgIconoDefault());
        SrcChatly.ventanaPerfil.setTitle(Info.NombreSoftware + " - " + SrcChatly.dto.getsCorreo());
        estadoAmistad = SrcChatly.dao.mtdVerificarAmistadPerfil(SrcChatly.dto, dto);
        mtdVerificarAmistad();
        mtdEstablecerDatos();
        mtdBuildWindownListener();
        mtdBuildMouseListener();
    }
    
    private void mtdVerificarAmistad() {
        if (estadoAmistad == 1000) { // Amigos
            this.laVista.cmpCorreo.setPlaceholder(this.dto.getsCorreo());
            this.laVista.cmpCorreo.setText(this.dto.getsCorreo());
            this.laVista.btnAmigos.setTexto("Son amigos +1");
            this.laVista.btnAmigos.setImgButtonType("success");
            this.estadoAmistad = 1000;
        } else if (estadoAmistad == 100) { // Amistad enviada
            this.laVista.btnAmigos.setTexto("Amistad enviada +1");
            this.estadoAmistad = 100;
        } else if (estadoAmistad == 200) { // Amistad recibida
            this.laVista.btnAmigos.setTexto("Amistad recibida +1");
            this.estadoAmistad = 200;
        } else {
            this.laVista.cmpCorreo.setPlaceholder("Example".replaceAll(".", "*"));
            this.laVista.cmpCorreo.setText("Example".replaceAll(".", "*"));
            this.laVista.btnAmigos.setTexto("Amigos +1");
            this.laVista.btnAmigos.setImgButtonType("danger");
            this.estadoAmistad = 0;
        }
    }
    
    private void mtdEstablecerDatos(){
        this.laVista.cmpNombres.setPlaceholder(this.dto.getsNombres());
        this.laVista.cmpNombres.setText(this.dto.getsNombres());
        
        this.laVista.cmpApellidos.setPlaceholder(this.dto.getsApellidos());
        this.laVista.cmpApellidos.setText(this.dto.getsApellidos());
        
        this.laVista.cmpCorreo.setPlaceholder("Example".replaceAll(".", "*"));
        this.laVista.cmpCorreo.setText("Example".replaceAll(".", "*"));
        
        this.laVista.cmpBio.setText(this.dto.getsBio());
        
        if (!this.dto.getsFotoPerfil().contains("user_default.png")) {
            this.dao.mtdInsertarFotoPerfil(this.laVista.cmpFotoPerfil, dto, true);
        }
        
    }
    
    private void mtdBtnAmigoPlusEnviarAmistad() {

        if (this.dao.mtdEnviarSolicitudDeAmistad(SrcChatly.dto, this.dto) && this.estadoAmistad == 0) {
            JOptionPane.showMessageDialog(null,
                    "Solicitud de amistad enviada a: \n" + this.dto.getsNombreCompleto(),
                    "Solicitud de amistad.", JOptionPane.INFORMATION_MESSAGE);
            this.estadoAmistad = 100; // 100 ; Solicitud enviado
            this.mtdVerificarAmistad();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Errar al enviar solicitud de amistad a: \n" + this.dto.getsNombreCompleto(),
                    "Solicitud de amistad.", JOptionPane.ERROR_MESSAGE);
            this.estadoAmistad = 0; // 0 ; Solicitud no enviado
            this.mtdVerificarAmistad();
        }

    }

    private void mtdBtnAmigoPlusEnviadaAmistad() {

        int resp = JOptionPane.showConfirmDialog(null,
                "¿Deseas cancelar la solicitud de amistadad a: \n" + this.dto.getsNombreCompleto(),
                "Solicitud de amistad.", JOptionPane.YES_NO_CANCEL_OPTION);

        if (resp == JOptionPane.YES_OPTION) {
            if (SrcChatly.dao.mtdRechazarAmistadPerfil(SrcChatly.dto, dto)
                    && this.dao.mtdRechazarAmistadPerfil(dto, SrcChatly.dto)) {
                JOptionPane.showMessageDialog(null,
                        "Solicitud de amistad cancelada a: \n" + this.dto.getsNombreCompleto(),
                        "Solicitud de amistad.", JOptionPane.INFORMATION_MESSAGE);
                this.estadoAmistad = 0; // 0 ; No son amigos
                this.mtdVerificarAmistad();
            }
        }

    }

    private void mtdBtnAmigoPlusRechazarAmistad() {

        int resp = JOptionPane.showConfirmDialog(null,
                "¿Deseas aceptar la solicitud de amistadad de: \n" + this.dto.getsNombreCompleto(),
                "Solicitud de amistad.", JOptionPane.YES_NO_CANCEL_OPTION);

        if (resp == JOptionPane.YES_OPTION) {
            if (SrcChatly.dao.mtdActualizarEstadoAmistadPerfil(SrcChatly.dto, dto, 1000)
                    && this.dao.mtdActualizarEstadoAmistadPerfil(dto, SrcChatly.dto, 1000)) {
                JOptionPane.showMessageDialog(null,
                        "Solicitud de amistad aceptada de: \n" + this.dto.getsNombreCompleto(),
                        "Solicitud de amistad.", JOptionPane.INFORMATION_MESSAGE);
            }
            this.estadoAmistad = 1000; // 1000 ; Son amigos
            this.mtdVerificarAmistad();
        } else 
        if (resp == JOptionPane.NO_OPTION) {
            if (SrcChatly.dao.mtdRechazarAmistadPerfil(SrcChatly.dto, dto)
                    && this.dao.mtdRechazarAmistadPerfil(dto, SrcChatly.dto)) {
                JOptionPane.showMessageDialog(null,
                        "Solicitud de amistad rechazada a: \n" + this.dto.getsNombreCompleto(),
                        "Solicitud de amistad.", JOptionPane.INFORMATION_MESSAGE);
            }
            this.estadoAmistad = 0; // 0 ; No son amigos
            this.mtdVerificarAmistad();
        }

    }
    
    private void mtdBtnAmigoPlusEliminarAmistad() {

        int resp = JOptionPane.showConfirmDialog(null,
                "¿Deseas eliminar la amistadad de: \n" + this.dto.getsNombreCompleto(),
                "Solicitud de amistad.", JOptionPane.YES_NO_CANCEL_OPTION);

        if (resp == JOptionPane.YES_OPTION) {
            if (SrcChatly.dao.mtdRechazarAmistadPerfil(SrcChatly.dto, dto)
                    && this.dao.mtdRechazarAmistadPerfil(dto, SrcChatly.dto)) {
                JOptionPane.showMessageDialog(null,
                        "Solicitud de amistad rechazada a: \n" + this.dto.getsNombreCompleto(),
                        "Solicitud de amistad.", JOptionPane.INFORMATION_MESSAGE);
            }
            this.estadoAmistad = 0; // 0 ; No son amigos
            this.mtdVerificarAmistad();
        } 

    }
    
    private void mtdBienvenida(){
        mtdVerificarAmistad();
       JOptionPane.showMessageDialog(null, "Bienvenido a mural de: \n" + this.dto.getsNombreCompleto()
               , Info.NombreSoftware , JOptionPane.INFORMATION_MESSAGE);
       JOptionPane.showMessageDialog(null, "Deja una firma para el mural de: \n" + this.dto.getsNombreCompleto()
               , Info.NombreSoftware , JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mtdBtnVolver() {
        mtdDestruirVentana();
        SrcChatly.ventanaComunidad = new VentanaComunidad();
        CtrlVentanaComunidad ctrl = new CtrlVentanaComunidad(SrcChatly.ventanaComunidad);
        ctrl.laVista.setVisible(true);
    }
    
    private void mtdDestruirVentana(){
        // Se borra la ventana Principal liberando memoria
        this.laVista.setVisible(false); // Desaparece la ventana
        this.laVista.dispose(); // Se libera la memoria
        SrcChatly.ventanaPerfil = null;
    }

    
}
