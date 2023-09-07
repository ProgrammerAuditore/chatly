package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private void mtdBuildEventMouseListener() {
        MouseListener evt = null;
        this.laVista.removeMouseListener(evt);

        evt = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getSource() == laVista.btnRefrescar) {
                    if (laVista.cmpBuscar.getText().isEmpty()) {
                        mtdCargarPerfilesDao();
                    } else if (laVista.cmpBuscar.getText().length() <= 30) {
                        mtdVaciarContenedor();
                        mtdFiltrarBusqueda(laVista.cmpBuscar.getText());
                    }
                } else if (e.getSource() == laVista.btnVolver) {
                    mtdBtnVolver();
                }
            }
        };

        this.laVista.btnRefrescar.addMouseListener(evt);
        this.laVista.btnVolver.addMouseListener(evt);
        this.laVista.addMouseListener(evt);
    }

    private void mtdBuildEventBtnBuscar() {
        KeyListener evtBtnBuscar = null;
        this.laVista.cmpBuscar.removeKeyListener(evtBtnBuscar);

        evtBtnBuscar = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char letra = e.getKeyChar();
                if (Character.isAlphabetic(letra) || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    mtdVaciarContenedor();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (laVista.cmpBuscar.getText().isEmpty()) {
                    mtdCargarPerfilesDao();
                } else if (laVista.cmpBuscar.getText().length() <= 30) {
                    mtdVaciarContenedor();
                    mtdFiltrarBusqueda(laVista.cmpBuscar.getText());
                } else {
                    JOptionPane.showMessageDialog(laVista, "Ingrese solo 30 caracteres.");
                }
            }
        };

        this.laVista.cmpBuscar.addKeyListener(evtBtnBuscar);
    }

    // ****** Métodos
    private void mtdInit() {
        this.laVista.cmpBuscar.requestFocusInWindow();
        this.laVista.pnlContenedorPerfiles.setLayout(new GridBagLayout());
        this.laVista.setLocationRelativeTo(null);
        this.laVista.setIconImage(Recursos.imgIconoDefault());
        SrcChatly.ventanaComunidad.setTitle(Info.NombreSoftware + " - " + SrcChatly.dto.getsCorreo());
        mtdBuildEventBtnBuscar();
        mtdBuildEventMouseListener();
        mtdCargarPerfilesDao();
    }

    private void mtdCargarPerfilesDao() {
        int totalPerfiles = 0;
        this.laVista.pnlContenedorPerfiles.removeAll();
        listaPerfiles = SrcChatly.dao.mtdListarPerfiles(SrcChatly.dto);

        totalPerfiles = listaPerfiles.size();
        if (totalPerfiles <= 0) {
            mtdMensaje("Sin resultados de busqueda...");
            return;
        }

        int columna = 0, fila = 0; // Establecer contadores para columnas y filas
        int perfiles = 3; // Establecer cantida de producto a mostrar por fila
        for (int i = 0; i < totalPerfiles; i++) {
            if (SrcChatly.dto.getsCorreo().equals(listaPerfiles.get(i).getsCorreo())) {
                continue;
            }

            CtrlPanelPerfilShort card = new CtrlPanelPerfilShort(listaPerfiles.get(i));
            card.setFilaAndColumna(fila, columna);
            card.mtdInit();

            this.laVista.pnlContenedorPerfiles.add(card.panelPerfil, card.getPanelConstraints());
            columna++;

            if (columna >= perfiles) {
                columna = 0;
                fila++;
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
    private void mtdFiltrarBusqueda(String busqueda) {
        int totalPerfiles = 0;
        this.laVista.pnlContenedorPerfiles.removeAll();

        List<PerfilDto> resultados = listaPerfiles.stream()
                .filter(perfil -> perfil.getsNombres().toLowerCase().contains(busqueda.toLowerCase())
                || perfil.getsApellidos().toLowerCase().contains(busqueda.toLowerCase())
                || perfil.getsNombreCompleto().toLowerCase().contains(busqueda.toLowerCase())
                || perfil.getsCorreo().toLowerCase().equalsIgnoreCase(busqueda.toLowerCase()))
                .collect(Collectors.toList());

        totalPerfiles = resultados.size(); // Establecer total de perfiles
        if (totalPerfiles <= 0) {
            mtdMensaje("Sin resultados de busqueda...");
            return;
        }

        int columna = 0, fila = 0; // Establecer contadores para columnas y filas
        int perfiles = 3; // Establecer cantida de producto a mostrar por fila
        for (int i = 0; i < totalPerfiles; i++) {
            if (SrcChatly.dto.getsCorreo().equals(resultados.get(i).getsCorreo())) {
                continue;
            }

            CtrlPanelPerfilShort card = new CtrlPanelPerfilShort(resultados.get(i));
            card.setFilaAndColumna(fila, columna);
            card.mtdInit();

            this.laVista.pnlContenedorPerfiles.add(card.panelPerfil, card.getPanelConstraints());
            columna++;

            if (columna >= perfiles) {
                columna = 0;
                fila++;
            }
        }

        this.laVista.pnlContenedorPerfiles.validate();
        this.laVista.pnlContenedorPerfiles.revalidate();
        this.laVista.pnlContenedorPerfiles.repaint();

    }

    private void mtdMensaje(String msg) {
        mtdVaciarContenedor();
        //JPanel mensaje = new JPanel();
        JLabel titulo = new JLabel();

        titulo.setForeground(Color.WHITE);
        titulo.setBounds(290, 10, 220, 20);
        titulo.setText(msg);
        titulo.setFont(new Font("Arial ", Font.PLAIN, 12));
        //mensaje.add(titulo);
        laVista.pnlContenedorPerfiles.add(titulo);

        laVista.pnlContenedorPerfiles.validate();
        laVista.pnlContenedorPerfiles.repaint();
    }

    private void mtdVaciarContenedor() {
        laVista.pnlContenedorPerfiles.removeAll();
        laVista.pnlContenedorPerfiles.validate();
        laVista.pnlContenedorPerfiles.revalidate();
        laVista.pnlContenedorPerfiles.repaint();
    }

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
