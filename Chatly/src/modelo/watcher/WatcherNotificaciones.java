package modelo.watcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import modelo.Observador;

public class WatcherNotificaciones extends Observador{
    
    private JList lista_de_notificaciones;
    DefaultListModel notificaciones = new DefaultListModel();
    private String path_stgNotify;
    private String lista_vacio;
    
    public WatcherNotificaciones(File path) {
        super(path);
    }

    public WatcherNotificaciones(String path, JList lista_de_notify) {
        super(new File(path));
        this.path_stgNotify = new File(path).getAbsolutePath();
        this.lista_de_notificaciones = lista_de_notify;
    }
    
    public void Inicializar(){
        
        if(this.fncVerificarCambiosEnElArchivoPath()){
            
            try {
                
                // * Reiniciar todas las notificaciones
                this.lista_de_notificaciones.removeAll();
                this.notificaciones.clear();
                
                // * Cerrar el almacenamiento de .notify 
                File archivo = new File(this.path_stgNotify);
                BufferedReader stgNotify = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = stgNotify.readLine()) != null) {
                    
                    if( !linea.trim().isEmpty() ){
                        // * Registrar todas las notificaciones...
                        this.notificaciones.addElement(linea.trim());
                    }
                    
                }
                
                // * Cerrar el almacenamiento de .notify
                stgNotify.close();
                
                // * Mostrar todas las notificaciones...
                this.lista_de_notificaciones.setModel(notificaciones);
            
            } catch (IOException e) {}

        }
        
        // * Verificar no existe notificaciones para mostrar un mensaje
        if(this.notificaciones.isEmpty()){
            this.notificaciones.addElement( this.lista_vacio );
            this.lista_de_notificaciones.setModel(notificaciones);
        }
         
    }

    public String getPath_stgNotify() {
        return path_stgNotify;
    }

    public void setPath_stgNotify(String path_stgNotify) {
        this.path_stgNotify = path_stgNotify;
    }

    public String getLista_vacio() {
        return lista_vacio;
    }

    public void setLista_vacio(String lista_vacio) {
        this.lista_vacio = lista_vacio;
    }
    
}
