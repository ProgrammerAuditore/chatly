package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;
import modelo.watcher.WatcherPerfiles;
import src.Info;
import src.Recursos;
import src.SrcChatly;
import vista.ventanas.VentanaComunidad;
import vista.ventanas.VentanaHome;

public class CtrlVentanaComunidad {
    
    // ****** Vista
    public VentanaComunidad laVista;
    public WatcherPerfiles watcherPerfiles;
    private ActionListener oyente;
    private Timer observador;
    
    // ****** Modelo
    
    // ****** Atributos
    
    // ****** Constructores
    public CtrlVentanaComunidad (VentanaComunidad vista){
        this.laVista = vista;
        mtdInit();
    }
    
    // ****** Construir eventos
    private void mtdBuildEventBtnVolver(){
        MouseListener evtBtnVolver = null;
        this.laVista.btnVolver.removeMouseListener(evtBtnVolver);
        
        evtBtnVolver = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                mtdBtnVolver();
            }
        };
        
        this.laVista.btnVolver.addMouseListener(evtBtnVolver);
    }
    
    // ****** Métodos
    private void mtdInit(){
        this.laVista.setLocationRelativeTo(null);
        SrcChatly.ventanaComunidad.setTitle(Info.NombreSoftware + " - " + SrcChatly.dto.getsCorreo() );
        mtdBuildEventBtnVolver();
        mtdCargarPerfiles();
    }
    
    private void mtdCargarPerfiles(){
        observador = new Timer(1000, oyente);
        watcherPerfiles = new WatcherPerfiles(Recursos.srcProfilesDatabase, this.laVista.pnlContenedorPerfiles);
        try {

            ActionListener tarea = (ActionEvent e) -> {
                
                /// * Verifica si hubo algun cambio en archivo database.profiles
                watcherPerfiles.Inicializar();
                
                if(this.laVista.isVisible() != true){
                    // Se borra la ventana People liberando memoria
                    this.observador.stop(); // Se detiene los observadores
                    this.laVista.setVisible(false); // Desaparece la ventana
                    this.laVista.dispose(); // Se libera la memoria
                    System.out.println("Observador Finalizado!!!");
                }
                
            };

            observador.addActionListener(tarea);
            observador.start();

        } catch (Exception error) {}
    }
    
    private void mtdBtnVolver(){
        mtdDestruirVentana();
        SrcChatly.ventanaHome = new VentanaHome();
        CtrlVentanaHome ctrl = new CtrlVentanaHome(SrcChatly.ventanaHome);
        ctrl.laVista.setVisible(true);
    }
    
    private void mtdDestruirVentana(){
        // Se borra la ventana Principal liberando memoria
        this.laVista.setVisible(false); // Desaparece la ventana
        this.laVista.dispose(); // Se libera la memoria
        SrcChatly.ventanaHome = null;
    }
    
}
