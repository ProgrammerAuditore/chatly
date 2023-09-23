package controlador;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.dao.PerfilDao;
import modelo.dto.PerfilDto;
import src.SrcChatly;
import vista.paneles.PanelPerfilShort;
import vista.ventanas.VentanaPerfil;

public class CtrlPanelPerfilShort {

    // ***** Vista
    public PanelPerfilShort panelPerfil;

    // ***** Modelo
    private PerfilDao dao;
    private PerfilDto dto;

    // ***** Atributo
    private GridBagConstraints panelConstraints;
    private Integer fila;
    private Integer columna;
    private Integer estadoAmistad;

    // ***** Contruir eventos
    private void mtdBuildEventMouseListener() {
        MouseListener evt = null;
        this.panelPerfil.removeMouseListener(evt);

        evt = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getSource() == panelPerfil.btnVerPerfl) {
                    mtdBtnVerPerfil();
                } else if (e.getSource() == panelPerfil.btnAmigoPlus && estadoAmistad == 0) {
                    mtdBtnAmigoPlusEnviarAmistad();
                } else if (e.getSource() == panelPerfil.btnAmigoPlus && estadoAmistad == 100) {
                    mtdBtnAmigoPlusEnviadaAmistad();
                } else if (e.getSource() == panelPerfil.btnAmigoPlus && estadoAmistad == 200) {
                    mtdBtnAmigoPlusRechazarAmistad();
                }
            }
        };

        this.panelPerfil.btnVerPerfl.addMouseListener(evt);
        this.panelPerfil.btnAmigoPlus.addMouseListener(evt);
        this.panelPerfil.addMouseListener(evt);
    }

    // ***** Constructores
    public CtrlPanelPerfilShort(PerfilDto dto) {
        this.dto = dto;
    }

    // ***** Métodos
    public void mtdInit() {
        this.dao = new PerfilDao();
        this.panelConstraints = new GridBagConstraints();
        this.panelPerfil = new PanelPerfilShort();
        estadoAmistad = SrcChatly.dao.mtdVerificarAmistadPerfil(SrcChatly.dto, dto);
        mtdVerificarAmistad();
        mtdEstablecerDatos();
        mtdEstablecerDimensiones();
        mtdBuildEventMouseListener();
    }

    private void mtdVerificarAmistad() {
        if (estadoAmistad == 1000) { // Amigos
            this.panelPerfil.btnAmigoPlus.setEnabled(false);
            this.panelPerfil.btnAmigoPlus.setVisible(false);
            this.estadoAmistad = 1000;
        } else if (estadoAmistad == 100) { // Amistad enviada
            this.panelPerfil.btnAmigoPlus.setTexto("Amistad enviada +1");
            this.estadoAmistad = 100;
        } else if (estadoAmistad == 200) { // Amistad recibida
            this.panelPerfil.btnAmigoPlus.setTexto("Amistad recibida +1");
            this.estadoAmistad = 200;
        } else {
            this.panelPerfil.btnAmigoPlus.setTexto("Amigos +1");
            this.estadoAmistad = 0;
        }
    }

    private void mtdEstablecerDatos() {
        this.panelPerfil.cmpNombres.setText(dto.getsNombres());
        this.panelPerfil.cmpApellidos.setText(dto.getsApellidos());

        if (!dto.getsFotoPerfil().contains("user_default.png")) {
            dao.mtdInsertarFotoPerfil(this.panelPerfil.cmpFotoPerfil, dto, true);
        }
    }

    private void mtdEstablecerDimensiones() {
        panelConstraints.gridx = columna; // Columna 
        panelConstraints.gridy = fila; // Fila
        panelConstraints.gridheight = 1; // Cantidad de columnas a ocupar
        panelConstraints.gridwidth = 1; // Cantidad de filas a ocupar
        panelConstraints.weightx = 0.0; // Estirar en ancho
        panelConstraints.weighty = 0.0;// Estirar en alto
        panelConstraints.insets = new Insets(20, 10, 20, 10);  //top padding
        panelConstraints.fill = GridBagConstraints.BOTH; // El modo de estirar
        panelPerfil.setVisible(true);
    }

    private void mtdBtnVerPerfil() {
        destruirVetanaComunidad();
        SrcChatly.ventanaPerfil = new VentanaPerfil();
        CtrlVentanaPerfil ctrl = new CtrlVentanaPerfil(SrcChatly.ventanaPerfil, dto, dao);
        ctrl.laVista.setVisible(true);
    }

    private void mtdBtnAmigoPlusEnviarAmistad() {

        if (this.dao.mtdEnviarSolicitudDeAmistad(SrcChatly.dto, this.dto) && this.estadoAmistad == 0) {
            JOptionPane.showMessageDialog(null,
                    "Solicitud de amistad enviada a: \n" + this.dto.getsNombreCompleto(),
                    "Solicitud de amistad.", JOptionPane.INFORMATION_MESSAGE);
            
            this.estadoAmistad = 100; // 100 ; Solicitud enviado
            this.mtdVerificarAmistad();
            SrcChatly.dao.mtdRegistrarNotificacion(SrcChatly.dto, "Has enviado una solicitud de amistad.");
            this.dao.mtdRegistrarNotificacion(this.dto, "Has recibido una solicitud de amistad");
            
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
            SrcChatly.dao.mtdRegistrarNotificacion(SrcChatly.dto, "Has aceptado una solicitud de amistad.");
            this.dao.mtdRegistrarNotificacion(this.dto, "Han aceptado tu solicitud de amistad.");
            
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
            SrcChatly.dao.mtdRegistrarNotificacion(SrcChatly.dto, "Has rechazado una solicitud de amistad.");
            this.dao.mtdRegistrarNotificacion(this.dto, "Han rechazado tu solicitud de amistad.");
            
        }

    }
    
    private void destruirVetanaComunidad() {
        SrcChatly.ventanaComunidad.setVisible(false);
        SrcChatly.ventanaComunidad.dispose();
        SrcChatly.ventanaComunidad = null;
    }

    public PanelPerfilShort getPanelPerfil() {
        return panelPerfil;
    }

    public void setFilaAndColumna(Integer fila, Integer columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public GridBagConstraints getPanelConstraints() {
        return panelConstraints;
    }

}
