    package index;

import src.Recursos;

public class Main {
    
    public static void main(String[] args) {
        // * Inicializar el programa
        //System.out.println("Inicializando programa...");
        Chatly programa  = new Chatly();
        
        switch (args.length) {
            case 0:
                programa.mtdTagInit();
                break;
            case 1:
                switch( args[0] ){
                    case "--init" : programa.mtdTagInit(); break;
                    case "-h" :
                    case "--help" : programa.mtdTagHelp();  break;
                    case "--test" : programa.mtdTagTest();  break;
                }   break;
            default:
                programa.mtdTagInit();
                break;
        }
        
    }
    
}
