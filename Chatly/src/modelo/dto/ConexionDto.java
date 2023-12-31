package modelo.dto;

import java.io.Serializable;

public class ConexionDto implements Serializable{
    private int puerto;
    private String host;
    private String database;
    private String usuario;
    private String pass;
    private boolean useSSL;
    private boolean createDatabaseIfNotExist;

    public ConexionDto() {
    }

    public ConexionDto(Object puerto, String host, String database, String usuario, String pass) {
        this.puerto = Integer.parseInt(String.valueOf(puerto));
        this.host = host;
        this.database = database;
        this.usuario = usuario;
        this.pass = pass;
    }
    
    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(Object puerto) {
        this.puerto = Integer.parseInt(String.valueOf(puerto));
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isUseSSL() {
        return useSSL;
    }

    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }

    public boolean isCreateDatabaseIfNotExist() {
        return createDatabaseIfNotExist;
    }

    public void setCreateDatabaseIfNotExist(boolean autoReconnect) {
        this.createDatabaseIfNotExist = autoReconnect;
    }
    
    @Override
    public String toString() {
        return "ConexionDto{" + 
                "\n puerto=" + puerto + 
                "\n host=" + host + 
                "\n database=" + database + 
                "\n usuario=" + usuario + 
                "\n pass=" + pass + 
                "\n useSSL=" + useSSL + 
                "\n createDatabaseIfNotExist=" + createDatabaseIfNotExist + 
            '}';
    }
    
}
