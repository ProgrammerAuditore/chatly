package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import vista.ventanas.VentanaHome;
import vista.ventanas.VentanaPrincipal;

public class CtrlVentanaPrincipal {

    // * Vista
    public VentanaPrincipal laVista;

    // * Modelos
    // * Atributos
    // * Catcher
    public CtrlVentanaPrincipal(VentanaPrincipal laVista) {
        this.laVista = laVista;

        // * Inicializar
        mtdInit();
    }

    // * Construir eventos
    private void mtdBuildEventBtnSingUp() {
        MouseListener eventoBtnSingUp = null;
        this.laVista.btnSingIn.removeMouseListener(eventoBtnSingUp);

        eventoBtnSingUp = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mtdBtnSingUp();
            }
        };

        this.laVista.btnSingIn.addMouseListener(eventoBtnSingUp);
    }

    private void mtdInit() {

        // * Definir oyentes
        laVista.setLocationRelativeTo(null);
        mtdBuildEventBtnSingUp();
    }

    private void mtdBtnSingUp() {

        // Se borra la ventana Principal liberando memoria
        this.laVista.setVisible(false); // Desaparece la ventana
        this.laVista.dispose(); // Se libera la memoria

        VentanaHome vtn = new VentanaHome();
        CtrlVentanaHome home = new CtrlVentanaHome(vtn);
        home.laVista.setVisible(true);

        //JOptionPane.showMessageDialog(null, this.laVista.cmpSingInEmail.getText(), "Ola como estás!!!", 0);
    }

}
