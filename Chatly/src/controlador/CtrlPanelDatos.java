package controlador;

import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import vista.paneles.PanelDatos;

public class CtrlPanelDatos {
    // ****** Vista
    private PanelDatos laVista;
    public JDialog modal;
    
    // ****** Modelos
    
    // ****** Atributos
    
    // ****** Constructores
    public CtrlPanelDatos(PanelDatos vista){
        this.laVista = vista;
    }
    // ****** Construir eventos
    
    // ****** Métodos
    public void mtdInit(){
        //modal.setModal(true);
        //modal.setType(Window.Type.UTILITY);
        modal.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        //modal.setTitle(SrcMyFreeLab.idioma.getProperty("ctrlDatosPersonales.mtdInit.titulo"));
        modal.setResizable(false);
        modal.setSize( laVista.getSize() );
        modal.setPreferredSize(laVista.getSize() );
        modal.setContentPane(laVista);
        
        modal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                modal.setVisible(false);
                modal.dispose();
            }
        });
        
    }
}
