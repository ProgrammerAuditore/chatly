package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    // * Esto son espacio que dejan entre los mensajes en el chat
    public final static String espacios = "\n\n";
    public final static int longitud = 60;
    public final static String identificador_amigo1 = " - Somos amigos.";
    public final static String identificador_amigo2 = " - Amigo+1 Recibido.";
    public final static String identificador_amigo3 = " - Amigo+1 Enviado.";
    public final static String identificador_boots = " *boot";
    public static final String extension_rs = "@gobim.dev";

    public static boolean fncStorageVerificarUnaCuenta(String encontrar_cuenta) {
        String srcProfiles = "storage_profiles/database.profiles";
        
        // Si el File no existe y el String es vacio retorna false
        try {
            
            BufferedReader db_archivo = new BufferedReader(new FileReader(new File(srcProfiles)));
            String linea;

            while ((linea = db_archivo.readLine()) != null) {

                // Si encuentra la cuenta se rompe el bucle
                if (linea.contains(encontrar_cuenta) && !linea.isEmpty()) {
                    return true;
                } 
                
            }

            db_archivo.close();

        } catch (IOException e) {
        }

        return false;
    }

    public static boolean fncStorageAcoplarUnaLinea(String pathA, String linea) {
        // Si el File no existe y el String es vacio retorna false
        if (new File(pathA).exists() || !linea.isEmpty()) {
            try {
                BufferedWriter escribir = new BufferedWriter(new FileWriter(pathA, true));
                // ** Antes **; + "\n"
                escribir.append(linea + "\n");
                escribir.close();
            } catch (IOException e) {
            }

        } else {
            return false;
        }

        return true;
    }

}
