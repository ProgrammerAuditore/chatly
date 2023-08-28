package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.dao.PerfilDao;
import modelo.dto.PerfilDto;
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
    public VentanaHome laVista;

    // ****** Modelos 
    // ****** Atributos
    // ****** Constructores
    public CtrlVentanaHome(VentanaHome vista) {
        this.laVista = vista;
        this.mtdInit();
    }

    // ****** Construir eventos
    private void mtdBuildEvents() {
        MouseListener evt = null;
        this.laVista.removeMouseListener(evt);

        evt = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getSource() == laVista.btnHomeCerrarSesion) {
                    mtdBtnCerrarSesion();
                } else if (e.getSource() == laVista.btnCoumidad) {
                    mtdBtnComunidad();
                } else if (e.getSource() == laVista.btnConversaciones) {
                    mtdBtnConversaciones();
                } else if (e.getSource() == laVista.btnAmigos) {
                    mtdBtnAmigos();
                } else if (e.getSource() == laVista.menuItemComunidad) {
                    mtdBtnComunidad();
                } else if (e.getSource() == laVista.menuItemConversaciones) {
                    mtdBtnConversaciones();
                } else if (e.getSource() == laVista.menuItemAmigos) {
                    mtdBtnAmigos();
                } else if (e.getSource() == laVista.menuItemDatos) {
                    mtdBtnDatos();
                } else if (e.getSource() == laVista.menuItemPassword) {
                    mtdBtnPassword();
                } else if (e.getSource() == laVista.menuItemCambiarFoto) {
                    mtdBtnCambiarFoto();
                } else if (e.getSource() == laVista.menuItemVaciarNotificaciones) {
                    mtdBtnVaciarNotificaciones();
                } else if (e.getSource() == laVista.menuItemVaciarMural) {
                    mtdBtnVaciarMural();
                } else if (e.getSource() == laVista.menuItemEliminarCuenta) {
                    mtdBtnEliminarCuenta();
                } else if (e.getSource() == laVista.btnItemSalir) {
                    mtdBtnSalir();
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
        this.laVista.menuItemVaciarNotificaciones.addMouseListener(evt);
        this.laVista.menuItemVaciarMural.addMouseListener(evt);
        this.laVista.menuItemCambiarFoto.addMouseListener(evt);
        this.laVista.menuItemEliminarCuenta.addMouseListener(evt);
        this.laVista.btnItemSalir.addMouseListener(evt);
        this.laVista.addMouseListener(evt);
    }

    // ****** Métodos
    private void mtdInit() {

        // * Definir oyentes
        laVista.setLocationRelativeTo(null);
        mtdBuildEvents();
        mtdEstablecerDatos();

    }
    
    private void mtdEstablecerDatos(){
        this.laVista.cmpNombres.setText( SrcChatly.dto.getsNombres() );
        this.laVista.cmpApellidos.setText( SrcChatly.dto.getsApellidos());
    }

    private void mtdBtnCerrarSesion() {
        mtdDestruirVentana();
        SrcChatly.ventanaPrincipal = new VentanaPrincipal();
        PerfilDao dao = new PerfilDao();
        PerfilDto dto = new PerfilDto();
        CtrlVentanaPrincipal ctrl = new CtrlVentanaPrincipal(SrcChatly.ventanaPrincipal, dao, dto);
        ctrl.laVista.setVisible(true);
    }

    private void mtdBtnComunidad() {
        mtdDestruirVentana();
        SrcChatly.ventanaComunidad = new VentanaComunidad();
        CtrlVentanaComunidad ctrl = new CtrlVentanaComunidad(SrcChatly.ventanaComunidad);
        ctrl.laVista.setVisible(true);
    }

    private void mtdBtnConversaciones() {
        mtdDestruirVentana();
        SrcChatly.ventanaConversaciones = new VentanaConversaciones();
        CtrlVentanaConversaciones ctrl = new CtrlVentanaConversaciones(SrcChatly.ventanaConversaciones);
        ctrl.laVista.setVisible(true);
    }

    private void mtdBtnAmigos() {
        mtdDestruirVentana();
        SrcChatly.ventanaAmigos = new VentanaAmigos();
        CtrlVentanaAmigos ctrl = new CtrlVentanaAmigos(SrcChatly.ventanaAmigos);
        ctrl.laVista.setVisible(true);
    }

    private void mtdBtnDatos() {
        PanelDatos panel = new PanelDatos();
        CtrlPanelDatos ctrl = new CtrlPanelDatos(panel);
        ctrl.modal = new JDialog(laVista);
        ctrl.mtdInit();
        ctrl.modal.setLocationRelativeTo(laVista);
        ctrl.modal.setVisible(true);
    }

    private void mtdBtnPassword() {
        PanelPassword panel = new PanelPassword();
        CtrlPanelPassword ctrl = new CtrlPanelPassword(panel);
        ctrl.modal = new JDialog(laVista);
        ctrl.mtdInit();
        ctrl.modal.setLocationRelativeTo(laVista);
        ctrl.modal.setVisible(true);
    }

    private void mtdBtnCambiarFoto() {
        // Cambiar foto de perfil
        JFileChooser elegirArchivo = new JFileChooser();
        elegirArchivo.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("gif", "jpg", "jpeg", "png");
        elegirArchivo.addChoosableFileFilter(filter);
        int respuesta = elegirArchivo.showOpenDialog(this.laVista);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            File archivo = elegirArchivo.getSelectedFile();
            if (archivo.length() > 1000000) {
                JOptionPane.showMessageDialog(null, "Lo siento, la imagen tiene que ser menor de 1MB.");
            } else {
                // Mostar mensaje de operacion
                JOptionPane.showMessageDialog(null, "Foto de perfil actualizado exitosamente.");
            }
        }

    }

    private void mtdBtnVaciarNotificaciones() {
        int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que deseas eliminar todas las notificaciones? ",
                "Confirmar...", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Se eliminaron todas las notificaciones, exitosamente.");
        }
    }

    private void mtdBtnVaciarMural() {
        int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que deseas eliminar todas las firmas del mural? ",
                "Confirmar...", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Se eliminaron todas las firmas del mural, exitosamente.");
        }
    }

    private void mtdBtnEliminarCuenta() {
        int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que deseas eliminar la cuenta? ",
                "Confirmar...", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Cuenta eliminado, exitosamente.");
        }
    }

    private void mtdBtnSalir() {
        // * Método para cerrar el programa
        mtdDestruirVentana();
        System.exit(0);
    }

    private void mtdDestruirVentana() {
        // Se borra la ventana Principal liberando memoria
        this.laVista.setVisible(false); // Desaparece la ventana
        this.laVista.dispose(); // Se libera la memoria
        SrcChatly.ventanaHome = null;
    }

}
