package vista.componentes.etiqueta;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import src.Recursos;

public class Titulo extends JLabel{
    
    public Titulo() {
        this.init();
    }

    private void init() {
        setText("Titulo");
        setFont(Recursos.fontLabelTitulo );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //this.setFont( Recursos.fontLabelEtiqueta.deriveFont( getFont().getStyle() , getFont().getSize() ) );
        
    }

    @Override
    public void setFont(Font font) {
        super.setFont(Recursos.fontLabelEtiqueta.deriveFont(font.getStyle(), font.getSize()) );
    }
    
}
