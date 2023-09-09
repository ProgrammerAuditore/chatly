package src;

public enum EstadoAmistad {
    
    DONE("*DONE*",1000),
    RECEIVED("*RECEIVED*",200),
    SEND("*SEND*",100);
    
    private final String simbolo;
    private final int valor;

    EstadoAmistad(String nombre, int numero) {
        this.simbolo = nombre;
        this.valor = numero;
    }

    public String getSimbolo() {
        return simbolo;
    }
    
    public int getValor() {
        return valor;
       
    }
    
}