package modelo.watcher;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import modelo.Observador;
import modelo.dao.PerfilDao;
import modelo.dto.PerfilDto;
import src.Recursos;
import vista.componentes.jpanelbackground.JPanelBackground;

public class WatcherPerfiles extends Observador{
    
    private JPanelBackground pnContenedorPerfiles;
    private JFrame laVista;
    private int coordenadaY = 20;
    
    public WatcherPerfiles(File path) {
        super(path);
    }
    
    public WatcherPerfiles(String path, JPanelBackground perfiles) {
        super(new File(path));
        this.pnContenedorPerfiles = perfiles;
    }
    
    public void Inicializar(){
        if(this.fncVerificarCambiosEnElArchivoPath()){

            try {
                
                pnContenedorPerfiles.removeAll();
                File archivo = new File(Recursos.srcProfilesDatabase);
                PerfilDao dao = new PerfilDao();
                PerfilDto dto = new PerfilDto();
                BufferedReader db_profiles = new BufferedReader(new FileReader(archivo));
                String linea;

                while ((linea = db_profiles.readLine()) != null) {
                
                    dto.setsCorreo(linea);
                    if(dao.mtdVerificarPerfil(dto)){
                        if(dao.mtdObtenerPerfil(dto)){
                            System.out.println(dto.toString() + "\n\n");
                        }
                    }
                    
                }
                
                if( pnContenedorPerfiles.getComponentCount() == 0 ){
                    
                    // * Insertar una etiqueta por defecto
                    JLabel a = new JLabel("No hay perfiles que mostrar aún...");
                    a.setForeground(Color.WHITE);
                    a.setBounds(290, 10, 220, 20);
                    pnContenedorPerfiles.add(a);
                    pnContenedorPerfiles.validate();
                    pnContenedorPerfiles.repaint();
                    
                }

                pnContenedorPerfiles.setPreferredSize(new Dimension(0, coordenadaY));
                pnContenedorPerfiles.validate();
                pnContenedorPerfiles.revalidate();
                pnContenedorPerfiles.repaint();
                coordenadaY = 20;
                db_profiles.close();
                
            } catch (IOException e) {}
            
        }
    }
    
    public void setPnContenedorPerfiles(JPanelBackground pnContenedorPerfiles) {
        this.pnContenedorPerfiles = pnContenedorPerfiles;
    }
    
    public void setLaVista(JFrame laVista) {
        this.laVista = laVista;
    }
    
}
