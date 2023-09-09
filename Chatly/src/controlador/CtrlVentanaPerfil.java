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
                }
            }
        };
        
        this.laVista.btnVolver.addMouseListener(evt);
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
        mtdEstablecerDatos();
        mtdBuildWindownListener();
        mtdBuildMouseListener();
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
    
    private void mtdBienvenida(){
        mtdVerificarAmistad();
       JOptionPane.showMessageDialog(null, "Bienvenido a mural de: \n" + this.dto.getsNombreCompleto()
               , Info.NombreSoftware , JOptionPane.INFORMATION_MESSAGE);
       JOptionPane.showMessageDialog(null, "Deja una firma para el mural de: \n" + this.dto.getsNombreCompleto()
               , Info.NombreSoftware , JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mtdVerificarAmistad() {
        int estado = SrcChatly.dao.mtdVerificarAmistadPerfil(SrcChatly.dto, dto);
        if ( estado == 1000) {
            this.laVista.cmpCorreo.setText(this.dto.getsCorreo());
        }
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
