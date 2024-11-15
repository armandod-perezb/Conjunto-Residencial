package com.mycompany.programmingproject;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class archivoHandler {
    private String fileName;
    private static archivoHandler instancia;

    private archivoHandler() {
        this.fileName = fileName;
    }

    // Método estático para obtener la instancia única de la clase
    public static archivoHandler getInstancia(String fileName) {
        if (instancia == null) {
            instancia = new archivoHandler(fileName);
        }
        return instancia;
    }
    
    // Método para leer el archivo y devolver su contenido como una lista de líneas
    public List<String> leerArchivo() {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName, true))) { // Modo de adición (append=true)
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al agregar una línea en el archivo: " + e.getMessage());
        }
    }

    // Método para escribir o sobrescribir el archivo con una lista de líneas
    public void escribirArchivo(List<String> lineas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))) {
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

    // Método para cargar los datos de un archivo CSV de usuarios y guardarlos en listas según el tipo de usuario
    public void cargarUsuariosDesdeCSV(String archivoCSV, List<Administrador> administradores, List<Propietario> propietarios, List<Vigilante> vigilantes) {
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 5) {
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String email = datos[2];
                    String contraseña = datos[3];
                    String tipo = datos[4];

                    switch (tipo.toLowerCase()) {
                        case "administrador":
                            administradores.add(new Administrador(id, nombre, email, contraseña, tipo));
                            break;
                        case "propietario":
                            if (datos.length == 6) {
                                String apartamento = datos[5];
                                propietarios.add(new Propietario(id, nombre, email, contraseña, tipo, apartamento));
                            }
                            break;
                        case "vigilante":
                            if (datos.length == 6) {
                                String turno = datos[5];
                                vigilantes.add(new Vigilante(id, nombre, email, contraseña, tipo, turno));
                            }
                            break;
                        default:
                            System.err.println("Tipo de usuario desconocido: " + tipo);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    // Método para registrar un nuevo usuario en el archivo CSV
    public void registrarUsuarioEnCSV(int id, String nombre, String email, String contraseña, String tipo, String extra) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(id).append(",").append(nombre).append(",").append(email).append(",").append(contraseña).append(",").append(tipo);
            if (extra != null && !extra.isEmpty()) {
                sb.append(",").append(extra);
            }
            bw.write(sb.toString());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al registrar el usuario en el archivo CSV: " + e.getMessage());
        }
    }
}
