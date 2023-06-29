package Operaciones;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

public class AddFriend {

    public boolean crearAmigo(String nuevoNombre,String nuevoTelefono) {
        try {
            String linea;
            String nuevaLinea;
            int indice;

            File archivo = new File("src/Archivos/friendContact.txt");

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            RandomAccessFile accesoArchivo = new RandomAccessFile(archivo,"rw");
            boolean flag = false;

            while(accesoArchivo.getFilePointer() < accesoArchivo.length()){
                linea = accesoArchivo.readLine();

                String[] lineSplit = linea.split("!");

                if(nuevoNombre.equals(lineSplit[0]) || nuevoTelefono.equals(lineSplit[1])){
                    flag = true;
                    break;
                }
            }

            if(!flag){
                nuevaLinea = nuevoNombre+"!"+nuevoTelefono;
                accesoArchivo.writeBytes(nuevaLinea);
                accesoArchivo.writeBytes(System.lineSeparator());
                accesoArchivo.close();
                return true;
            }else{
                accesoArchivo.close();
                return false;
            }

        }
        catch (IOException ioe) {
            System.out.println(ioe);
            return false;
        }
        catch (NumberFormatException nef) {
            System.out.println(nef);
            return false;
        }
    }
}
