package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.dao.PerfilDao;
import modelo.dto.PerfilDto;
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
    
    // ****** Métodos
    private void mtdInit(){
        this.laVista.setLocationRelativeTo(null);        
        mtdEstablecerDatos();
        mtdBuildMouseListener();
    }
    
    private void mtdEstablecerDatos(){
        this.laVista.cmpNombres.setPlaceholder(this.dto.getsNombres());
        this.laVista.cmpNombres.setText(this.dto.getsNombres());
        
        this.laVista.cmpApellidos.setPlaceholder(this.dto.getsApellidos());
        this.laVista.cmpApellidos.setText(this.dto.getsApellidos());
        
        
        this.laVista.cmpCorreo.setPlaceholder(this.dto.getsCorreo());
        this.laVista.cmpCorreo.setText(this.dto.getsCorreo());
        
        this.laVista.cmpBio.setText(this.dto.getsBio());
        
        if (!this.dto.getsFotoPerfil().contains("user_default.png")) {
            this.dao.mtdInsertarFotoPerfil(this.laVista.cmpFotoPerfil, dto, true);
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
