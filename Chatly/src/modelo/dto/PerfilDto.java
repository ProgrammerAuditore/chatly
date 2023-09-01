package modelo.dto;

import java.io.Serializable;

public class PerfilDto implements Serializable{

    private String sNombres;
    private String sApellidos;
    private String sCorreo;
    private String sPassword;
    private String sBio;
    private String sFotoPerfil;

    public String getsNombres() {
        return sNombres;
    }

    public void setsNombres(String sNombres) {
        this.sNombres = sNombres;
    }

    public String getsApellidos() {
        return sApellidos;
    }

    public void setsApellidos(String sApellidos) {
        this.sApellidos = sApellidos;
    }

    public String getsCorreo() {
        return sCorreo;
    }

    public void setsCorreo(String sCorreo) {
        this.sCorreo = sCorreo;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getsBio() {
        return sBio;
    }

    public void setsBio(String sBio) {
        this.sBio = sBio;
    }

    public String getsFotoPerfil() {
        return sFotoPerfil;
    }

    public void setsFotoPerfil(String sFotoPerfil) {
        this.sFotoPerfil = sFotoPerfil;
    }
    
    public String getsNombreCompleto(){
        return this.sNombres + " " + this.sApellidos;
    }

    @Override
    public String toString() {
        return "PerfilDto{" + "sNombres=" + sNombres + ", sApellidos=" + sApellidos + ", sCorreo=" + sCorreo + ", sPassword=" + sPassword + ", sBio=" + sBio + ", sFotoPerfil=" + sFotoPerfil + '}';
    }
    
}
