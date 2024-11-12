package com.mycompany.juan5112024;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class archivoHandler {
    private String fileName;

    public archivoHandler(String fileName) {
        this.fileName = fileName;
    }

    // Método para leer el archivo y devolver su contenido como una lista de líneas
    public List<String> leerArchivo() {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lineas;
    }

    // Método para agregar una sola línea al final del archivo sin sobrescribir el contenido
    public void agregarLinea(String linea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) { // Modo de adición (append=true)
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al agregar una línea en el archivo: " + e.getMessage());
        }
    }

    // Método para escribir o sobrescribir el archivo con una lista de líneas
    public void escribirArchivo(List<String> lineas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Método para modificar una línea específica en el archivo
    public void modificarLinea(int numeroLinea, String nuevaLinea) {
        List<String> lineas = leerArchivo();
        if (numeroLinea >= 0 && numeroLinea < lineas.size()) {
            lineas.set(numeroLinea, nuevaLinea);
            escribirArchivo(lineas); // Reescribimos el archivo con el cambio
        } else {
            System.err.println("Número de línea fuera del rango");
        }
    }

    // Método para eliminar una línea específica en el archivo
    public void eliminarLinea(int numeroLinea) {
        List<String> lineas = leerArchivo();
        if (numeroLinea >= 0 && numeroLinea < lineas.size()) {
            lineas.remove(numeroLinea); // Remover la línea especificada
            escribirArchivo(lineas); // Reescribimos el archivo sin la línea eliminada
        } else {
            System.err.println("Número de línea fuera del rango");
        }
    }
}
