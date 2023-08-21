
package controlador;

import index.Chatly;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modelo.dao.EjecucionDao;
import modelo.dto.EjecucionDto;
import src.Recursos;
import vista.ventanas.VentanaPrincipal;

public class CtrlVentanaPrincipal implements ActionListener {

    // * Vista
    private VentanaPrincipal laVista;

    // * Modelos

    // * Atributos
    
    // * Catcher

    public CtrlVentanaPrincipal(VentanaPrincipal laVista) {
        this.laVista = laVista;
        
        // * Inicializar
        mtdInit();
    }

    private void mtdInit() {

        // * Definir oyentes
        laVista.setLocationRelativeTo(null);
        
        laVista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                
            }
            
            @Override
            public void windowClosing(WindowEvent e) {

            }
        });
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

}
