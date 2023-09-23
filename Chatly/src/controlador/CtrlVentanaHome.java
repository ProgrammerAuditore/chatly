package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.dao.PerfilDao;
import modelo.dto.PerfilDto;
import modelo.watcher.WatcherNotificaciones;
import src.Info;
import src.Recursos;
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
    private ActionListener oyente;
    private Timer observador = new Timer(1000, oyente);
    
    // ****** Constructores
    public CtrlVentanaHome(VentanaHome vista) {
        this.laVista = vista;
        this.mtdInit();
    }

    // ****** Construir eventos
    private void mtdBuildEventMouseListener() {
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
                } else if (e.getSource() == laVista.menuItemVerNotificaciones) {
                    mtdBtnVerNotificaciones();
                } else if (e.getSource() == laVista.menuItemVaciarNotificaciones) {
                    mtdBtnVaciarNotificaciones();
                } else if (e.getSource() == laVista.menuItemVaciarMural) {
                    mtdBtnVaciarMural();
                } else if (e.getSource() == laVista.menuItemEliminarCuenta) {
                    mtdBtnEliminarCuenta();
                } else if (e.getSource() == laVista.btnItemSalir) {
                    mtdBtnSalir();
                } else if (e.getSource() == laVista.menuPopFotoEliminar) {
                    mtdBtnEliminarFoto();
                } else if (e.getSource() == laVista.menuPopFotoCambiar) {
                    mtdBtnCambiarFoto();
                } else if (e.getSource() == laVista.cmpFotoPerfil) {
                    laVista.menuPopFoto.show(laVista.cmpFotoPerfil, e.getX(), e.getY());
                } else if (e.getSource() == laVista.cmpFotoPerfil && e.getButton() == MouseEvent.BUTTON3) {
                    laVista.menuPopFoto.show(laVista.cmpFotoPerfil, e.getX(), e.getY());
                }
            }
        };

        this.laVista.cmpFotoPerfil.addMouseListener(evt);
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
        this.laVista.menuItemVerNotificaciones.addMouseListener(evt);
        this.laVista.menuItemEliminarCuenta.addMouseListener(evt);
        this.laVista.btnItemSalir.addMouseListener(evt);
        this.laVista.menuPopFotoCambiar.addMouseListener(evt);
        this.laVista.menuPopFotoEliminar.addMouseListener(evt);
        this.laVista.addMouseListener(evt);
    }
    
    private void mtdBuildEventWindowListener(){
        WindowListener evt = null;
        this.laVista.removeWindowListener(evt);
        
        
        evt = new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                mtdWatcherNotificaciones();
            }
        };
        
        this.laVista.addWindowListener(evt);
    }

    // ****** Métodos
    private void mtdInit() {

        // * Definir oyentes
        this.laVista.setLocationRelativeTo(null);
        this.laVista.setIconImage(Recursos.imgIconoDefault());
        SrcChatly.ventanaHome.setTitle(Info.NombreSoftware + " - " + SrcChatly.dto.getsCorreo() );
        mtdBuildEventMouseListener();
        mtdBuildEventWindowListener();
       
        mtdEstablecerDatos();

    }

    private void mtdEstablecerDatos() {
        this.laVista.cmpCorreo.setText(SrcChatly.dto.getsCorreo());
        this.laVista.cmpCorreo.setPlaceholder(SrcChatly.dto.getsCorreo());
        
        this.laVista.cmpNombres.setText(SrcChatly.dto.getsNombres());
        this.laVista.cmpNombres.setPlaceholder(SrcChatly.dto.getsNombres());
        
        this.laVista.cmpApellidos.setText(SrcChatly.dto.getsApellidos());
        this.laVista.cmpApellidos.setPlaceholder(SrcChatly.dto.getsApellidos());
        
        this.laVista.cmpBio.setText(SrcChatly.dto.getsBio());

        if (!SrcChatly.dto.getsFotoPerfil().contains("user_default.png")) {
            SrcChatly.dao.mtdInsertarFotoPerfil(this.laVista.cmpFotoPerfil, SrcChatly.dto, true);
        }
    }
    
    private void mtdWatcherNotificaciones(){
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";
        String srcNotify = srcFile.replaceAll("%correo%", 
                SrcChatly.dto.getsCorreo())
                .replaceFirst("%ext%", "notify");
        
        WatcherNotificaciones notify = new WatcherNotificaciones(srcNotify, this.laVista.lstNotificaciones);
        notify.setLista_vacio("Sin notificaciones");
                
        try{
             
            ActionListener tarea;
            tarea = (ActionEvent e) -> {
                
                // Observadores o Watchers para notificaciones (Depende de Session)
                notify.Inicializar();
                
            };

           observador.addActionListener(tarea);
           observador.start();
           
        }catch(Exception a){}
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
    
    private void mtdBtnVerNotificaciones(){
        this.laVista.pnlNotificaciones.setVisible(!this.laVista.pnlNotificaciones.isVisible());
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

                if (SrcChatly.dao.mtdActualizarFotoPerfil(SrcChatly.dto, archivo.getAbsolutePath())
                        && SrcChatly.dao.mtdActualizarPerfil(SrcChatly.dto)) {

                    SrcChatly.dao.mtdInsertarFotoPerfil(this.laVista.cmpFotoPerfil, SrcChatly.dto, true);
                    JOptionPane.showMessageDialog(null, "Foto de perfil actualizado exitosamente.");

                }

            }
        }

    }

    private void mtdBtnEliminarFoto() {
        int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que deseas eliminar tu foto de perfil? ",
                "Confirmar...", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            SrcChatly.dto.setsFotoPerfil("user_default.png");
            if (SrcChatly.dao.mtdActualizarPerfil(SrcChatly.dto)) {
                SrcChatly.dao.mtdEliminarFotoPerfil(this.laVista.cmpFotoPerfil, SrcChatly.dto, true);
                JOptionPane.showMessageDialog(null, "Foto de perfil eliminado, exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Lo siento foto de perfil no eliminado.");
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
            if( SrcChatly.dao.mtdEliminarPerfil(SrcChatly.dto) ){
                SrcChatly.dao = null;
                SrcChatly.dto = null;
                this.mtdBtnCerrarSesion();
                JOptionPane.showMessageDialog(null, "Cuenta eliminado, exitosamente.");
            }else {
                JOptionPane.showMessageDialog(null, "Hubo un error al eliminar la cuenta.");
            }
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

    public static void mtdActualizarVentana() {
        VentanaHome.cmpNombres.setText(SrcChatly.dto.getsNombres());
        VentanaHome.cmpApellidos.setText(SrcChatly.dto.getsApellidos());
        VentanaHome.cmpBio.setText(SrcChatly.dto.getsBio());
    }

}
