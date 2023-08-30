package modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import modelo.interfaces.keyword_observador;

public class Observador {
    private File path;
    private long path_size=0;
    private long path_size_tmp=0;
    private keyword_observador observardor = null;

    // Método constructor
    public Observador(File path) {
        this.path = path;
    }

    public Observador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Métodos publico 
    public void quitarObservardor(){
        this.observardor = null;
    }
    
    public void fncIniciarObservador(){
        
        if(this.fncVerificarCambiosEnElArchivoPath()){
            
            /* ejecutar el observador si existe */
            if(observardor != null)
                observardor.ejecutar();
                
        }    
    }

    public boolean fncVerificarCambiosEnElArchivoPath() {
        
        this.path_size = 0;
        
        // * Verificar si existe el archivo a observar
        if(new File(path.getAbsoluteFile().toString()).exists()){
            try {
                // * Obtener el tamaño del arcivo
                this.path_size = Files.size(path.toPath());                
                
                // * Verificar si hay algún cambio en el archivo
                if( this.path_size > this.path_size_tmp || this.path_size < this.path_size_tmp  ){
                    
                    // * Salvamos el tamaño original
                    this.path_size_tmp = this.path_size;
                    return true;
                }else return false;
                
            } catch (IOException e) {}
            
        }else
            return false;
                
        return true;
    }
    
    // Métodos getters y setters
    public keyword_observador getObservar() {
        return observardor;
    }

    public void setObservar(keyword_observador observar) {
        this.observardor = observar;
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

}
