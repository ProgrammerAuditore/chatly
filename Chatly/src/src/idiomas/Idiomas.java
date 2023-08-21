package src.idiomas;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Idiomas extends Properties{

    public Idiomas(String idioma) {
        switch(idioma){
            case "esp" :
            case "es"  : mtdObtenerIdioma("messages_es.properties"); break;
            default: mtdObtenerIdioma("messages_es.properties"); break;
        } 
    }
    
    private void mtdObtenerIdioma(String idioma){
        try {
            this.load(getClass().getResourceAsStream(idioma));
        } catch (IOException ex) {
            Logger.getLogger(Idiomas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
