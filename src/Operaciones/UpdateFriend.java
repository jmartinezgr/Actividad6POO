package Operaciones;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UpdateFriend {
    public boolean updateFriend(String nuevoNombre, String nuevoNumero) {
        try {
            File archivo = new File("src/Archivos/friendContact.txt");
            File temporal = new File("temp.txt");

            RandomAccessFile accessFile = new RandomAccessFile(archivo, "rw");
            RandomAccessFile tempAcceso = new RandomAccessFile(temporal, "rw");

            String nombreNumero;
            boolean flag = false;
            String inputName = "";

            while ((nombreNumero = accessFile.readLine()) != null) {
                String[] linea = nombreNumero.split("!");

                if (linea[0].equals(nuevoNombre) || linea[1].equals(nuevoNumero)) {
                    flag = true;
                    inputName = linea[0];
                    break;
                }
            }

            if (flag) {
                accessFile.seek(0);
                while ((nombreNumero = accessFile.readLine()) != null) {
                    String[] linea = nombreNumero.split("!");

                    if (linea[0].equals(inputName)) {
                        nombreNumero = nuevoNombre + "!" + nuevoNumero;
                    }

                    tempAcceso.writeBytes(nombreNumero);
                    tempAcceso.writeBytes(System.lineSeparator());
                }
                accessFile.setLength(0);
                tempAcceso.seek(0);
                while ((nombreNumero = tempAcceso.readLine()) != null) {
                    accessFile.writeBytes(nombreNumero);
                    accessFile.writeBytes(System.lineSeparator());
                }
                tempAcceso.close();
                accessFile.close();

                temporal.delete();

                return true;
            } else {
                accessFile.close();
                return false;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }
}

