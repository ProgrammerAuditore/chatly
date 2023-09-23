package modelo.dao;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelo.Storage;
import modelo.dto.PerfilDto;
import src.Recursos;
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

        if (!(profile.isDirectory() && profile.exists())
                || !(data.isFile() && data.exists())
                || !(bio.isFile() && bio.exists())) {
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

    public boolean mtdEliminarPerfil(PerfilDto dto) {
        String srcDir = "storage_profiles/%correo%/%nombre%";
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";

        try {

            // * Registrar cuenta en database.profiles
            if (Storage.fncStorageEliminarUnaLinea(srcDir
                    .replaceFirst("%correo%", "database.profiles")
                    .replaceFirst("%nombre%", ""),
                    dto.getsCorreo())) {

                // * Crear archivos de almacenamiento
                String[] archivos = {"data", "chats", "tome", "friends", "notify", "bio", "svg"};
                for (String ext : archivos) {
                    new File(srcFile
                            .replaceAll("%correo%", dto.getsCorreo())
                            .replaceFirst("%ext%", ext)).delete();
                }

                // * Crear ruta de la carpeta de perfil
                String[] directorios = {"chats", "profile", ""};
                for (String nombre : directorios) {
                    new File(srcDir
                            .replaceFirst("%correo%", dto.getsCorreo())
                            .replaceFirst("%nombre%", nombre)).delete();
                }

            }

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

    public int mtdVerificarAmistadPerfil(PerfilDto dtoSesion, PerfilDto dtoPerfil) {
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";
        String path = srcFile
                .replaceAll("%correo%", dtoSesion.getsCorreo())
                .replaceFirst("%ext%", "friends");
        File archivo = new File(path);

        String pathTmp = srcFile
                .replaceAll("%correo%", dtoSesion.getsCorreo())
                .replaceFirst("%ext%", "_tmp00");
        File archivo_tmp = new File(pathTmp);

        try {

            if (archivo_tmp.exists()) {
                archivo.delete();
                archivo_tmp.renameTo(archivo);
            }

            BufferedReader db_profiles = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = db_profiles.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                } else if (linea.trim().contains(dtoPerfil.getsCorreo())) {
                    return this.mtdVerificarEstadoAmistad(linea);
                }
            }

            db_profiles.close();
            db_profiles.close();

        } catch (Exception e) {
        }

        return 0;
    }

    public boolean mtdActualizarEstadoAmistadPerfil(PerfilDto dtoSesion, PerfilDto dtoPerfil, int estadoAmistad) {
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";

        String path = srcFile
                .replaceAll("%correo%", dtoSesion.getsCorreo())
                .replaceFirst("%ext%", "friends");

        String pathTmp = srcFile
                .replaceAll("%correo%", dtoSesion.getsCorreo())
                .replaceFirst("%ext%", "_tmp00");

        File archivo = new File(path);
        File archivo_tmp = new File(pathTmp);

        try {

            if (archivo_tmp.exists()) {
                archivo.delete();
                archivo_tmp.renameTo(archivo);
            }

            if (archivo_tmp.createNewFile()) {
                try (FileWriter sobrescribirArchivo = new FileWriter(pathTmp)) {

                    BufferedReader db_profiles = new BufferedReader(new FileReader(archivo));
                    String linea;
                    while ((linea = db_profiles.readLine()) != null) {
                        if (linea.trim().isEmpty()) {
                            continue;
                        } else if (linea.trim().contains(dtoPerfil.getsCorreo())) {
                            sobrescribirArchivo.write(dtoPerfil.getsCorreo() + " " + this.mtdVerificarEstadoAmistad(estadoAmistad) + "\n");
                        } else {
                            sobrescribirArchivo.write(linea.trim() + "\n");
                        }
                    }

                    sobrescribirArchivo.close();
                    sobrescribirArchivo.close();
                    db_profiles.close();
                    db_profiles.close();
                    archivo.delete();
                    archivo.delete();

                } catch (Exception e) {
                    return false;
                }

                archivo.delete();
                archivo.delete();
                archivo_tmp.renameTo(new File(path));
                archivo_tmp.renameTo(new File(path));

            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean mtdRechazarAmistadPerfil(PerfilDto dtoSesion, PerfilDto dtoPerfil) {
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";

        String path = srcFile
                .replaceAll("%correo%", dtoSesion.getsCorreo())
                .replaceFirst("%ext%", "friends");

        String pathTmp = srcFile
                .replaceAll("%correo%", dtoSesion.getsCorreo())
                .replaceFirst("%ext%", "_tmp00");

        File archivo = new File(path);
        File archivo_tmp = new File(pathTmp);

        try {

            if (archivo_tmp.exists()) {
                archivo.delete();
                archivo_tmp.renameTo(archivo);
            }

            if (archivo_tmp.createNewFile()) {
                try (FileWriter sobrescribirArchivo = new FileWriter(pathTmp)) {

                    BufferedReader db_profiles = new BufferedReader(new FileReader(archivo));
                    String linea;
                    while ((linea = db_profiles.readLine()) != null) {
                        if (linea.trim().isEmpty()) {
                            continue;
                        } else if (linea.trim().contains(dtoPerfil.getsCorreo())) {
                            continue;
                        } else {
                            sobrescribirArchivo.write(linea.trim() + "\n");
                        }
                    }

                    sobrescribirArchivo.close();
                    sobrescribirArchivo.close();
                    db_profiles.close();
                    db_profiles.close();
                    archivo.delete();
                    archivo.delete();

                } catch (Exception e) {
                    return false;
                }

                archivo.delete();
                archivo.delete();
                archivo_tmp.renameTo(new File(path));
                archivo_tmp.renameTo(new File(path));

            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private int mtdVerificarEstadoAmistad(String amistad) {

        if (amistad.contains("*Send*")) {
            return 100;
        } else if (amistad.contains("*Received*")) {
            return 200;
        } else if (amistad.contains("*Done*")) {
            return 1000;
        } else {
            return 0;
        }

    }

    private String mtdVerificarEstadoAmistad(int amistad) {

        if (amistad == 100) {
            return "*Send*";
        } else if (amistad == 200) {
            return "*Received*";
        } else if (amistad == 1000) {
            return "*Done*";
        } else {
            return "";
        }

    }

    public List<PerfilDto> mtdListarPerfiles(PerfilDto dto) {
        List<PerfilDto> perfiles = new ArrayList<>();
        File archivo = new File(Recursos.srcProfilesDatabase);
        PerfilDao dao = new PerfilDao();

        try {

            BufferedReader db_profiles = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = db_profiles.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                } else if (dto.getsCorreo().equals(linea.trim())) {
                    continue;
                }

                PerfilDto dtoPerfil = new PerfilDto();
                dtoPerfil.setsCorreo(linea.trim());

                if (dao.mtdVerificarPerfil(dtoPerfil)) {
                    if (dao.mtdObtenerPerfil(dtoPerfil)) {
                        perfiles.add(dtoPerfil);
                    }
                }
            }

        } catch (Exception e) {
        }

        return perfiles;
    }

    public boolean mtdEnviarSolicitudDeAmistad(PerfilDto dtoSession, PerfilDto dtoPerfil) {
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";

        try {

            // * Registrar solicitud de mistad para dtoSession
            Storage.fncStorageAcoplarUnaLinea(srcFile
                    .replaceAll("%correo%", dtoSession.getsCorreo())
                    .replaceFirst("%ext%", "friends"),
                    dtoPerfil.getsCorreo() + " *Send*");

            // * Registrar solicitud de mistad para dtoPerfil
            Storage.fncStorageAcoplarUnaLinea(srcFile
                    .replaceAll("%correo%", dtoPerfil.getsCorreo())
                    .replaceFirst("%ext%", "friends"),
                    dtoSession.getsCorreo() + " *Received*");

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public void mtdInsertarFotoPerfil(JPanelBackground contenedor, PerfilDto dto, boolean vaciar) {
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";
        String SrcFotoPerfil = srcFile
                .replaceAll("%correo%", dto.getsCorreo())
                .replaceFirst("%ext%", "svg");

        if (vaciar) {
            contenedor.removeAll();
        }

        contenedor.setOpaque(false);
        contenedor.setImgBackgroundEnabled(true);
        contenedor.setImgRutaInternoActivo(false);
        contenedor.setImgBackgroundIn_Ex(true);
        contenedor.setImgRutaExterno(new File(SrcFotoPerfil));

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
        contenedor.setOpaque(false);
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

    public boolean mtdRegistrarNotificacion(PerfilDto dtoPerfil, String notificacion) {
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";

        String srcNotify = srcFile
                .replaceAll("%correo%", dtoPerfil.getsCorreo())
                .replaceFirst("%ext%", "notify");

        // * Verificar que la cuenta perfil y que el archivo .notify de session_activa exista
        if (Storage.fncStorageVerificarUnaCuenta(dtoPerfil.getsCorreo())) {
            String notify = notificacion + ". | " + Recursos.getFechayHora() + "\n";

            Storage.fncStorageAcoplarUnaLinea(srcNotify, notify);
        } else {
            return false;
        }

        return true;
    }

    public boolean mtdViciarNotificaciones(PerfilDto dto) {
        String srcFile = "storage_profiles/%correo%/profile/%correo%.%ext%";

        String srcNotify = srcFile
                .replaceAll("%correo%", dto.getsCorreo())
                .replaceFirst("%ext%", "notify");

        File archivo = new File(srcNotify);

        try {

            archivo.delete();
            archivo.delete();
            archivo.delete();
            archivo.createNewFile();
            archivo.createNewFile();

            if (archivo.exists()) {
                return true;
            }

            return true;
        } catch (IOException ex) {}

        return false;
    }

}
