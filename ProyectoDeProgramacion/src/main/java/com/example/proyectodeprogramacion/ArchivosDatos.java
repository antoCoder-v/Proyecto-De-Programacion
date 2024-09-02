package com.example.proyectodeprogramacion;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.IOException;

public class ArchivosDatos {

    // Guarda un objeto de tipo Datos en un archivo
    public static void guardarDatos(Datos datos, String archivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(datos);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException ex) {
            System.out.println("Error al guardar los datos: " + ex.getMessage());
        }
    }

    // Lee un objeto de tipo Datos desde un archivo y retorna los valores
    public static double[] leerDatos(String tipoDato, String archivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            Datos datos = (Datos) in.readObject();
            if (datos.getTipoDato().equals(tipoDato)) {
                    return datos.getValores();
            }else{
                System.out.println("No se encontró el tipo de dato especificado.");
                return null;
            }
            
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer los datos: " + e.getMessage());
            return null;
        }
    }
}