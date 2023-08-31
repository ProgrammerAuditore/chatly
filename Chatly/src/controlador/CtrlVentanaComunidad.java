package controlador;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.Timer;
import modelo.dto.PerfilDto;
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
    private List<PerfilDto> listaPerfiles;

    // ****** Constructores
    public CtrlVentanaComunidad(VentanaComunidad vista) {
        this.laVista = vista;
        mtdInit();
    }

    // ****** Construir eventos
    private void mtdBuildEventBtnVolver() {
        MouseListener evtBtnVolver = null;
        this.laVista.btnVolver.removeMouseListener(evtBtnVolver);

        evtBtnVolver = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mtdBtnVolver();
            }
        };

        this.laVista.btnVolver.addMouseListener(evtBtnVolver);
    }

    // ****** Métodos
    private void mtdInit() {
        this.laVista.pnlContenedorPerfiles.setLayout(new GridBagLayout());
        this.laVista.setLocationRelativeTo(null);
        SrcChatly.ventanaComunidad.setTitle(Info.NombreSoftware + " - " + SrcChatly.dto.getsCorreo());
        mtdBuildEventBtnVolver();
        mtdCargarPerfilesDao();
    }

    private void mtdCargarPerfilesDao() {
        int totalPerfiles = 0;
        this.laVista.pnlContenedorPerfiles.removeAll();

        listaPerfiles = SrcChatly.dao.mtdListarPerfiles();
        totalPerfiles = listaPerfiles.size();

        if (totalPerfiles > 0) {

            int columna = 0, fila = 0; // Establecer contadores para columnas y filas
            int perfiles = 3; // Establecer cantida de producto a mostrar por fila
            for (int i = 0; i < totalPerfiles; i++) {
                if( SrcChatly.dto.getsCorreo().equals(listaPerfiles.get(i).getsCorreo()) ) continue;
                
                CtrlPanelPerfilShort card = new CtrlPanelPerfilShort(listaPerfiles.get(i));
                card.setFilaAndColumna(fila, columna);
                card.mtdInit();

                this.laVista.pnlContenedorPerfiles.add(card.panelPerfil, card.getPanelConstraints());
                columna++;

                if (columna >= perfiles) {
                    columna = 0;
                    fila++;
                }
                
                try { Thread.sleep(60); } catch (InterruptedException ex) { }
            }

        }

        this.laVista.pnlContenedorPerfiles.validate();
        this.laVista.pnlContenedorPerfiles.revalidate();
        this.laVista.pnlContenedorPerfiles.repaint();
    }

//    private void mtdCargarPerfilesWatcher() {
//        observador = new Timer(1000, oyente);
//        watcherPerfiles = new WatcherPerfiles(Recursos.srcProfilesDatabase, this.laVista.pnlContenedorPerfiles);
//        try {
//
//            ActionListener tarea = (ActionEvent e) -> {
//
//                /// * Verifica si hubo algun cambio en archivo database.profiles
//                watcherPerfiles.Inicializar();
//
//                if (this.laVista.isVisible() != true) {
//                    // Se borra la ventana People liberando memoria
//                    this.observador.stop(); // Se detiene los observadores
//                    this.laVista.setVisible(false); // Desaparece la ventana
//                    this.laVista.dispose(); // Se libera la memoria
//                    System.out.println("Observador Finalizado!!!");
//                }
//
//            };
//
//            observador.addActionListener(tarea);
//            observador.start();
//
//        } catch (Exception error) {
//        }
//    }

    private void mtdBtnVolver() {
        mtdDestruirVentana();
        SrcChatly.ventanaHome = new VentanaHome();
        CtrlVentanaHome ctrl = new CtrlVentanaHome(SrcChatly.ventanaHome);
        ctrl.laVista.setVisible(true);
    }

    private void mtdDestruirVentana() {
        // Se borra la ventana Principal liberando memoria
        this.laVista.setVisible(false); // Desaparece la ventana
        this.laVista.dispose(); // Se libera la memoria
        SrcChatly.ventanaHome = null;
    }

}
