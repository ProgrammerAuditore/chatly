package modelo.dao;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelo.Storage;
import modelo.dto.PerfilDto;
import vista.componentes.jpanelbackground.JPanelBackground;

public class PerfilDao {

    public boolean mtdObtenerPerfil(PerfilDto dto) {
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";
        Scanner scanner;

        try {
            // ** Obtener datos de la cuenta
            scanner = new Scanner(new File(srcFile
                    .replaceAll("%correo%", dto.getsCorreo())
                    .replaceFirst("%ext%", "data")));
            scanner.useDelimiter("\n");

            dto.setsNombres(scanner.next().trim());
            dto.setsApellidos(scanner.next().trim());
            dto.setsCorreo(scanner.next().trim());
            dto.setsPassword(scanner.next());
            dto.setsFotoPerfil(scanner.next().trim());
            scanner.close(); // Cerrar archivo

            // ** Obtener bio de la cuenta
            scanner = new Scanner(new File(srcFile
                    .replaceAll("%correo%", dto.getsCorreo())
                    .replaceFirst("%ext%", "bio")));
            scanner.useDelimiter("\n");
            String bio = "";

            while (scanner.hasNextLine()) {
                bio += scanner.nextLine() + "\n";
            }

            dto.setsBio(bio);
            scanner.close(); // Cerrar archivo

        } catch (FileNotFoundException ex) {
            return false;
        }

        return true;
    }

    public boolean mtdActualizarPerfil(PerfilDto dto) {
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";

        try {

            // * Registrar datos de la cuenta en DATA
            FileWriter registrar_datos = new FileWriter(srcFile
                    .replaceAll("%correo%", dto.getsCorreo())
                    .replaceFirst("%ext%", "data"));

            registrar_datos.write(dto.getsNombres() + "\n");
            registrar_datos.write(dto.getsApellidos() + "\n");
            registrar_datos.write(dto.getsCorreo() + "\n");
            registrar_datos.write(dto.getsPassword() + "\n");
            registrar_datos.write(dto.getsFotoPerfil() + "\n");
            registrar_datos.close();

            // * Registrar bio de la cuenta en DATA
            FileWriter registrar_bio = new FileWriter(srcFile
                    .replaceAll("%correo%", dto.getsCorreo())
                    .replaceFirst("%ext%", "bio"));

            registrar_bio.write(dto.getsBio());
            registrar_bio.close();

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean mtdVerificarPerfil(PerfilDto dto) {

        // * Crear ruta de la carpeta de perfil
        String srcProfile = "storage_profiles/" + dto.getsCorreo();
        File profile = new File(srcProfile);

        // * Crear ruta de el archivo .data de perfil
        String srcData = "storage_profiles/" + dto.getsCorreo() + "/profile/" + dto.getsCorreo() + ".data";
        File data = new File(srcData);

        // * Crear ruta de el archivo .bio de perfil
        String srcBio = "storage_profiles/" + dto.getsCorreo() + "/profile/" + dto.getsCorreo() + ".bio";
        File bio = new File(srcBio);

        if (!(profile.isDirectory() && profile.exists() && bio.exists())
                || !(data.isFile() && data.exists())) {
            return false;
        }

        return true;
    }

    public boolean mtdCrearPerfil(PerfilDto dto) {
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
            String[] archivos = {"data", "chats", "tome", "friends", "notify", "bio"};
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

            // * Registrar bio de la cuenta en DATA
            FileWriter registrar_bio = new FileWriter(srcFile
                    .replaceAll("%correo%", dto.getsCorreo())
                    .replaceFirst("%ext%", archivos[5]));

            registrar_bio.write(dto.getsBio());
            registrar_bio.close();

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

    public boolean mtdActualizarFotoPerfil(PerfilDto dto, String srcFotoNueva) {
        // Crear la nueva ruta del foto de perfil (Email + .svg)
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";
        String path = srcFile
                .replaceAll("%correo%", dto.getsCorreo())
                .replaceFirst("%ext%", "svg");

        try {
            FileInputStream in = new FileInputStream(srcFotoNueva);
            FileOutputStream ou = new FileOutputStream(path);
            BufferedInputStream bin = new BufferedInputStream(in);
            BufferedOutputStream bou = new BufferedOutputStream(ou);

            // Establecer el nuevo nombre del foto de perfil (Email + .svg)
            // como ... example@extension 
            dto.setsFotoPerfil(dto.getsCorreo() + ".svg");

            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }

            bin.close();
            bou.close();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public void mtdInsertarFotoPerfil(JPanel contenedor, PerfilDto dto, boolean vaciar) {
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";

        if (vaciar) {
            contenedor.removeAll();
        }

        String SrcFotoPerfil = srcFile
                .replaceAll("%correo%", dto.getsCorreo())
                .replaceFirst("%ext%", "svg");
        ImageIcon icono = new ImageIcon(new File(SrcFotoPerfil).getAbsolutePath());
        JLabel etiquetaImagen = new JLabel();
        etiquetaImagen.setBounds(0, 0, contenedor.getWidth(), contenedor.getHeight());
        etiquetaImagen.setIcon(new ImageIcon(icono.getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_SMOOTH)));
        contenedor.add(etiquetaImagen);

        if (vaciar) {
            contenedor.validate();
        }
        if (vaciar) {
            contenedor.repaint();
        }

    }

    public void mtdEliminarFotoPerfil(JPanelBackground contenedor, PerfilDto dto, boolean vaciar) {
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";
        
        contenedor.removeAll();
        contenedor.setImgBackgroundEnabled(true);
        contenedor.setImgRutaInterno("/storage/img/user_default.png");
        contenedor.setImgRutaInternoActivo(true);
        contenedor.validate();
        contenedor.repaint();
        
        String SrcFotoPerfil = srcFile
                .replaceAll("%correo%", dto.getsCorreo())
                .replaceFirst("%ext%", "svg");
        
        new File(SrcFotoPerfil).delete();
    }

}
