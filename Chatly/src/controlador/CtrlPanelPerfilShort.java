package controlador;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import modelo.dao.PerfilDao;
import modelo.dto.PerfilDto;
import src.SrcChatly;
import vista.paneles.PanelPerfilShort;

public class CtrlPanelPerfilShort {

    // ***** Vista
    public PanelPerfilShort panelPerfil;

    // ***** Modelo
    private PerfilDao dao;
    private PerfilDto dto;

    // ***** Atributo
    private GridBagConstraints panelConstraints;
    private Integer fila;
    private Integer columna;

    // ***** Contruir eventos
    // ***** Constructores
    public CtrlPanelPerfilShort(PerfilDto dto) {
        this.dto = dto;
    }

    // ***** Métodos
    public void mtdInit() {
        this.dao = new PerfilDao();
        this.panelConstraints = new GridBagConstraints();
        this.panelPerfil = new PanelPerfilShort();
        mtdVerificarAmistad();
        mtdEstablecerDatos();
        mtdEstablecerDimensiones();
    }

    private void mtdVerificarAmistad() {
        if (SrcChatly.dao.mtdVerificarAmistadPerfil(SrcChatly.dto, dto)) {
            this.panelPerfil.btnAmigoPlus.setEnabled(false);
            this.panelPerfil.btnAmigoPlus.setVisible(false);
        }
    }

    private void mtdEstablecerDatos() {
        this.panelPerfil.cmpNombres.setText(dto.getsNombres());
        this.panelPerfil.cmpApellidos.setText(dto.getsApellidos());

        if (!dto.getsFotoPerfil().contains("user_default.png")) {
            dao.mtdInsertarFotoPerfil(this.panelPerfil.cmpFotoPerfil, dto, true);
        }
    }

    private void mtdEstablecerDimensiones() {
        panelConstraints.gridx = columna; // Columna 
        panelConstraints.gridy = fila; // Fila
        panelConstraints.gridheight = 1; // Cantidad de columnas a ocupar
        panelConstraints.gridwidth = 1; // Cantidad de filas a ocupar
        panelConstraints.weightx = 0.0; // Estirar en ancho
        panelConstraints.weighty = 0.0;// Estirar en alto
        panelConstraints.insets = new Insets(20, 10, 20, 10);  //top padding
        panelConstraints.fill = GridBagConstraints.BOTH; // El modo de estirar
        panelPerfil.setVisible(true);
    }

    public PanelPerfilShort getPanelPerfil() {
        return panelPerfil;
    }

    public void setFilaAndColumna(Integer fila, Integer columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public GridBagConstraints getPanelConstraints() {
        return panelConstraints;
    }

}
