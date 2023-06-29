package Operaciones;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class DisplayFriends {

    public ArrayList<String> listaAmigos() {
        ArrayList<String> amigos = new ArrayList<>();

        try {
            File archivo = new File("src/Archivos/friendContact.txt");
            RandomAccessFile accesoArchivo = new RandomAccessFile(archivo, "r");
            String linea,splitLine;
            while ((linea = accesoArchivo.readLine()) != null) {
                amigos.add(linea);
            }

            accesoArchivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return amigos;
    }

    public String obtenerTelefono(String nombre) {
        try {
            File archivo = new File("src/Archivos/friendContact.txt");
            RandomAccessFile accesoArchivo = new RandomAccessFile(archivo, "r");
            String linea;
            while ((linea = accesoArchivo.readLine()) != null) {
                String[] datos = linea.split("!");
                if (datos.length >= 2 && datos[0].equalsIgnoreCase(nombre)) {
                    accesoArchivo.close();
                    return datos[1];
                }
            }

            accesoArchivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
