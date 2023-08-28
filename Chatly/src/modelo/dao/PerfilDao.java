package modelo.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import modelo.Storage;
import modelo.dto.PerfilDto;

public class PerfilDao {
    
    public boolean mtdObtenerPerfil(PerfilDto dto){
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";
        Scanner scanner;
        
        try {
            // ** Obtener datos de la cuenta
            scanner = new Scanner(new File( srcFile
                        .replaceAll("%correo%", dto.getsCorreo())
                        .replaceFirst("%ext%", "data") ));
            scanner.useDelimiter("\n");
            
            dto.setsNombres(scanner.next().trim());
            dto.setsApellidos(scanner.next().trim());
            dto.setsCorreo(scanner.next().trim());
            dto.setsPassword(scanner.next());
            dto.setsFotoPerfil(scanner.next().trim());
            scanner.close(); // Cerrar archivo
            
            // ** Obtener bio de la cuenta
            scanner = new Scanner(new File( srcFile
                        .replaceAll("%correo%", dto.getsCorreo())
                        .replaceFirst("%ext%", "bio") ));
            scanner.useDelimiter("\n");
            String bio = "";
            
            while(scanner.hasNextLine()){
                bio += scanner.nextLine() + "\n";
            }
            
            dto.setsBio(bio);
            scanner.close(); // Cerrar archivo
            
        } catch (FileNotFoundException ex) {
            return false;
        }
        
        return true;
    }

    public boolean mtdVerificarCuenta(PerfilDto dto) {

        // * Crear ruta de la carpeta de perfil
        String srcProfile = "storage_profiles/" + dto.getsCorreo();
        File profile = new File(srcProfile);

        // * Crear ruta de el archivo .data de perfil
        String srcData = "storage_profiles/" + dto.getsCorreo() + "/profile/" + dto.getsCorreo() + ".data";
        File data = new File(srcData);

        if (!(profile.isDirectory() && profile.exists())
                || !(data.isFile() && data.exists())) {
            return false;
        }

        return true;
    }

    public boolean mtdCrearCuenta(PerfilDto dto) {
        String srcDir = "storage_profiles/%correo%/%nombre%";
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";

        try {

            // * Crear ruta de la carpeta de perfil
            String[] directorios = {"", "profile", "chats"};
            for (String nombre : directorios) {
                new File(srcDir
                        .replaceFirst("%correo%", dto.getsCorreo())
                        .replaceFirst("%nombre%", nombre)).mkdir();
            }

            // * Crear archivos de almacenamiento
            String[] archivos = {"data", "chats", "tome", "friends", "notify"};
            for (String ext : archivos) {
                new File(srcFile
                        .replaceAll("%correo%", dto.getsCorreo())
                        .replaceFirst("%ext%", ext)).createNewFile();
            }

            // * Registrar datos de la cuenta en DATA
            FileWriter registrar_datos = new FileWriter(srcFile
                    .replaceAll("%correo%", dto.getsCorreo())
                    .replaceFirst("%ext%", archivos[0]));

            registrar_datos.write(dto.getsNombres() + "\n");
            registrar_datos.write(dto.getsApellidos() + "\n");
            registrar_datos.write(dto.getsCorreo() + "\n");
            registrar_datos.write(dto.getsPassword() + "\n");
            registrar_datos.write(dto.getsFotoPerfil() + "\n");
            registrar_datos.close();
            
            // * Registrar cuenta en database.profiles
            Storage.fncStorageAcoplarUnaLinea(srcDir
                    .replaceFirst("%correo%", "database.profiles")
                    .replaceFirst("%nombre%", ""), 
                    dto.getsCorreo());

        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
