package com.mycompany.programmingproject;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class archivoHandler {
    private String fileName;
    private static archivoHandler instancia;

    private archivoHandler(String fileName) {
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

        // Método para buscar en el archivo

        public void buscarEnArchivo(String palabra) {

            try {
                FileReader reader = new FileReader(fileName);
                BufferedReader bufferReader = new BufferedReader(reader);
                String linea;
                boolean encontrado = false;
    
                while ((linea = bufferReader.readLine()) != null) {
                    if (linea.contains(palabra)) {
                        System.out.println("Palabra encontrada: " + linea);
                        encontrado = true;
                    }
                }
    
                if (!encontrado) {
                    System.out.println("La palabra no se encontró en el archivo.");
                }
    
                bufferReader.close();
    
            } catch (IOException e) {
                System.out.println("Error al buscar en el archivo: " + e.getMessage());
            }
        }

    // Método para cargar los datos de un archivo CSV de mercancias y guardarlos en una lista
    public List<Mercancia> cargarMercanciasDesdeCSV(String archivoMercancias, List<Propietario> propietarios) {
        List<Mercancia> mercancias = new ArrayList<>();
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoMercancias))) {
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 5) {
                    int id = Integer.parseInt(datos[0]);
                    String descripcion = datos[1];
                    double valor = Double.parseDouble(datos[2]);
                    int id_destinatario = Integer.parseInt(datos[3]);
                    String tiempo = datos[4];
                    int año = Integer.parseInt(tiempo.substring(0, 4));
                    int mes = Integer.parseInt(tiempo.substring(5, 7));
                    int dia = Integer.parseInt(tiempo.substring(8, 10));
                    int hora = Integer.parseInt(tiempo.substring(11, 13));
                    int minuto = Integer.parseInt(tiempo.substring(14, 16));
                    int segundo = Integer.parseInt(tiempo.substring(17, 19));
                    LocalDateTime fechaHoraLlegada = LocalDateTime.of(año, mes, dia, hora, minuto, segundo);
                    boolean entregada = Boolean.parseBoolean(datos[5]);
                    for (Propietario propietario : propietarios) {
                        if (propietario.getId() == id_destinatario) {
                            mercancias.add(new Mercancia(id, descripcion, propietario));
                        }
                    }
                    mercancias.get(mercancias.size() - 1).setFechaHoraLlegada(fechaHoraLlegada);
                    mercancias.get(mercancias.size() - 1).setEntregada(entregada);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de mercancias: " + e.getMessage());
        }
        return mercancias;
    }

    // Método para cargar los datos de un archivo CSV de vehiculos y guardarlos en una lista
    public List<Vehiculo> cargarVehiculosDesdeCSV(String archivoVehiculos, List<Propietario> propietarios) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoVehiculos))) {
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 4) {
                    int id = Integer.parseInt(datos[0]);
                    String placa = datos[1];
                    String tipo = datos[2];
                    int id_propietario = Integer.parseInt(datos[3]);
                    boolean autorizado = Boolean.parseBoolean(datos[4]);
                    for (Propietario propietario : propietarios) {
                        if (propietario.getId() == id_propietario) {
                            vehiculos.add(new Vehiculo(placa, tipo, propietario, autorizado));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de vehiculos: " + e.getMessage());
        }
        return vehiculos;
    }

    // Método para cargar los datos de un archivo CSV de usuarios y guardarlos en listas según el tipo de usuario
    public List<Usuario> cargarUsuariosDesdeCSV(String archivoUsuarios, String archivoMercancias, String archivoVehiculos) {
        String linea;
        List<Administrador> administradores = new ArrayList<>();
        List<Propietario> propietarios = new ArrayList<>();
        List<Vigilante> vigilantes = new ArrayList<>();
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios))) {
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
                            if (datos.length == 7) {
                                String apartamento = datos[5];
                                String stringalDia = datos[6];
                                boolean alDia = Boolean.parseBoolean(stringalDia);
                                propietarios.add(new Propietario(id, nombre, email, contraseña, tipo, apartamento));
                                propietarios.get(propietarios.size() - 1).setAlDia(alDia);
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
            for (Propietario propietario : propietarios) {
                List<Mercancia> mercancias = cargarMercanciasDesdeCSV(archivoMercancias, propietarios);
                List<Vehiculo> vehiculos = cargarVehiculosDesdeCSV(archivoVehiculos, propietarios);
                for (Mercancia mercancia : mercancias) {
                    if (mercancia.getDestinatario().getId() == propietario.getId()) {
                        propietario.setMercanciasRegistradas(mercancias);
                    }
                }
                for (Vehiculo vehiculo : vehiculos) {
                    if (vehiculo.getPropietario().getId() == propietario.getId()) {
                        propietario.setVehiculosRegistrados(vehiculos);
                    }
                }
            }
            usuarios.addAll(propietarios);
            usuarios.addAll(administradores);
            usuarios.addAll(vigilantes);
            return usuarios;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            return usuarios;
        }
    }

    // Método para escribir la lista de mercancias en un archivo CSV
    public void escribirMercanciasACSV(String archivoMercancias, List<Mercancia> mercancias) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoMercancias))) {
            for (Mercancia mercancia : mercancias) {
                String linea = mercancia.getId() + "," +
                               mercancia.getDescripcion() + "," +
                               mercancia.getDestinatario().getId() + "," +
                               mercancia.getFechaHoraLlegada().toString() + "," +
                               mercancia.isEntregada();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de mercancias: " + e.getMessage());
        }
    }

    // Método para escribir la lista de vehiculos en un archivo CSV
    public void escribirVehiculosACSV(String archivoVehiculos, List<Vehiculo> vehiculos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoVehiculos))) {
            for (Vehiculo vehiculo : vehiculos) {
                String linea = vehiculo.getPlaca() + "," +
                               vehiculo.getTipo() + "," +
                               vehiculo.getPropietario().getId() + "," +
                               vehiculo.isAutorizado();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de vehiculos: " + e.getMessage());
        }
    }

    // Método para escribir la lista de usuarios en un archivo CSV
    public void escribirUsuariosACSV(String archivoUsuarios, List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios))) {
            for (Usuario usuario : usuarios) {
                String linea = usuario.getId() + "," +
                               usuario.getNombre() + "," +
                               usuario.getEmail() + "," +
                               usuario.getContraseña() + "," +
                               usuario.getTipo();
                if (usuario instanceof Propietario) {
                    Propietario propietario = (Propietario) usuario;
                    linea += "," + propietario.getNumapartamento() + "," + propietario.isAlDia();
                } else if (usuario instanceof Vigilante) {
                    Vigilante vigilante = (Vigilante) usuario;
                    linea += "," + vigilante.getTurno();
                }
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de usuarios: " + e.getMessage());
        }
    }
}
