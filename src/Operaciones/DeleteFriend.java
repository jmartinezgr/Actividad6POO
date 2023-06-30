package Operaciones;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DeleteFriend {

    public boolean eliminarAmigo(String nombre) {
        try {
            String linea;
            int indice;

            File archivo = new File("src/Archivos/friendContact.txt");

            if (!archivo.exists()) {
                System.out.println("El archivo no existe.");
                return false;
            }

            File archivoTemporal = new File("src/Archivos/temp.txt");

            RandomAccessFile accesoArchivo = new RandomAccessFile(archivo, "rw");
            RandomAccessFile accesoArchivoTemporal = new RandomAccessFile(archivoTemporal, "rw");

            boolean encontrado = false;

            while ((linea = accesoArchivo.readLine()) != null) {
                String[] lineSplit = linea.split("!");

                if (lineSplit.length == 2) {
                    String nombreContacto = lineSplit[0];

                    if (nombreContacto.equals(nombre)) {
                        encontrado = true;
                        continue;
                    }
                }

                accesoArchivoTemporal.writeBytes(linea);
                accesoArchivoTemporal.writeBytes(System.lineSeparator());
            }

            accesoArchivo.seek(0);
            accesoArchivoTemporal.seek(0);

            while ((linea = accesoArchivoTemporal.readLine()) != null) {
                accesoArchivo.writeBytes(linea);
                accesoArchivo.writeBytes(System.lineSeparator());
            }

            accesoArchivo.setLength(accesoArchivoTemporal.length());

            accesoArchivo.close();
            accesoArchivoTemporal.close();

            archivoTemporal.delete();

            if (encontrado) {
                return true;
            } else {
                return false;
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
            return false;
        }
    }
}

