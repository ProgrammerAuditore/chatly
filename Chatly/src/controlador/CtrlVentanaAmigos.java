package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.Timer;
import modelo.dto.PerfilDto;
import modelo.watcher.WatcherPerfiles;
import src.Info;
import src.Recursos;
import src.SrcChatly;
import vista.ventanas.VentanaAmigos;
import vista.ventanas.VentanaHome;

public class CtrlVentanaAmigos {
    // ****** Vista
    public VentanaAmigos laVista;
    public WatcherPerfiles watcherPerfiles;
    private ActionListener oyente;
    private Timer observador;
    
    // ****** Modelo
    
    // ****** Atributos
    private List<PerfilDto> listaPerfiles;
    
    // ****** Constructores
    public CtrlVentanaAmigos(VentanaAmigos vista){
        this.laVista = vista;
        this.mtdInit();
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
        this.laVista.cmpBuscar.requestFocusInWindow();
        this.laVista.pnlContenedorPerfiles.setLayout(new GridBagLayout());
        this.laVista.setLocationRelativeTo(null);
        this.laVista.setIconImage(Recursos.imgIconoDefault());
        SrcChatly.ventanaAmigos.setTitle(Info.NombreSoftware + " - " + SrcChatly.dto.getsCorreo() );
        //SrcChatly.dao.mtdListarAmigos(SrcChatly.dto);
        mtdBuildEventBtnVolver();
        mtdCargarPerfilesDao();
    }
    
    private void mtdCargarPerfilesDao() {
        int totalPerfiles = 0;
        this.laVista.pnlContenedorPerfiles.removeAll();
        listaPerfiles = SrcChatly.dao.mtdListarAmigos(SrcChatly.dto);

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
    
}
