package com.mycompany.programmingproject;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;

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
    public List<String> leerArchivo(String file) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lineas;
    }
    public boolean comprobararchivo(String archivo){
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return false;
        }
        return true;
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
    public void modificarLinea(String file, int numeroLinea, String nuevaLinea) {
        List<String> lineas = leerArchivo(file);
        if (numeroLinea >= 0 && numeroLinea < lineas.size()) {
            lineas.set(numeroLinea, nuevaLinea);
            escribirArchivo(lineas); // Reescribimos el archivo con el cambio
        } else {
            System.err.println("Número de línea fuera del rango");
        }
    }

    // Método para eliminar una línea específica en el archivo
    public void eliminarLinea(String file, int numeroLinea) {
        List<String> lineas = leerArchivo(file);
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

    
    // Método para cargar los datos de un archivo CSV de incidentes y guardarlos en una lista
    public List<Incidente> cargarIncidentesDesdeCSV(String archivoIncidentes, List<Usuario> usuarios) {
        List<Incidente> incidentes = new ArrayList<>();
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoIncidentes))) {
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6) {
                    int id = Integer.parseInt(datos[0]);
                    String descripcion = datos[1];
                    int id_reportadoPor = Integer.parseInt(datos[2]);
                    LocalDateTime fechaHora = LocalDateTime.parse(datos[3]);
                    String ubicacion = datos[4];
                    String estado = datos[5];
                    for (Usuario usuario : usuarios) {
                        if (usuario.getId() == id_reportadoPor) {
                            Incidente incidente = new Incidente(id, descripcion, usuario, ubicacion);
                            incidente.setFechaHora(fechaHora);
                            incidente.setEstado(estado);
                            incidentes.add(incidente);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de incidentes: " + e.getMessage());
        }
        return incidentes;
    }


     // Método para cargar los datos de un archivo CSV de pagos y guardarlos en una lista
     public List<Pago> cargarPagosDesdeCSV(String archivoPagos, List<Propietario> propietarios) {
        List<Pago> pagos = new ArrayList<>();
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoPagos))) {
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 7) {
                    int id = Integer.parseInt(datos[0]);
                    int id_propietario = Integer.parseInt(datos[1]);
                    double monto = Double.parseDouble(datos[2]);
                    String mediodepago = datos[3];
                    LocalDate fecha = LocalDate.parse(datos[4]);
                    String concepto = datos[5];
                    String estado = datos[6];
                    for (Propietario propietario : propietarios) {
                        if (propietario.getId() == id_propietario) {
                            Pago pago = new Pago(id, mediodepago, propietario, monto, concepto);
                            pagos.add(pago);
                            pagos.get(pagos.size() - 1).setFecha(fecha);
                            pagos.get(pagos.size() - 1).setEstado(estado);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de pagos: " + e.getMessage());
        }
        return pagos;
    }

    // Método para cargar los datos de un archivo CSV de registros de acceso y guardarlos en una lista
    public List<RegistroAcceso> cargarRegistrosAccesoDesdeCSV(String archivoRegistros, List<Vehiculo> vehiculos, List<Usuario> usuarios) {
        List<RegistroAcceso> registrosAcceso = new ArrayList<>();
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoRegistros))) {
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 5) {
                    int id = Integer.parseInt(datos[0]);
                    String placaVehiculo = datos[1];
                    LocalDateTime fechaHoraEntrada = LocalDateTime.parse(datos[2]);
                    LocalDateTime fechaHoraSalida = LocalDateTime.parse(datos[3]);
                    int idRegistradoPor = Integer.parseInt(datos[4]);

                    Vehiculo vehiculo = null;
                    for (Vehiculo v : vehiculos) {
                        if (v.getPlaca().equals(placaVehiculo)) {
                            vehiculo = v;
                            break;
                        }
                    }

                    Usuario registradoPor = null;
                    for (Usuario u : usuarios) {
                        if (u.getId() == idRegistradoPor) {
                            registradoPor = u;
                            break;
                        }
                    }
                    RegistroAcceso registroAcceso = new RegistroAcceso(id, vehiculo,registradoPor);
                    registrosAcceso.add(registroAcceso);
                    registrosAcceso.get(registrosAcceso.size() - 1).setFechaHoraEntrada(fechaHoraEntrada);
                    registrosAcceso.get(registrosAcceso.size() - 1).setFechaHoraSalida(fechaHoraSalida);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de registros de acceso: " + e.getMessage());
        }
        return registrosAcceso;
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
                    int id_destinatario = Integer.parseInt(datos[2]);
                    LocalDateTime fechaHoraLlegada = LocalDateTime.parse(datos[3]);
                    boolean entregada = Boolean.parseBoolean(datos[4]);
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
                    String placa = datos[0];
                    String tipo = datos[1];
                    int id_propietario = Integer.parseInt(datos[2]);
                    boolean autorizado = Boolean.parseBoolean(datos[3]);
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
                        propietario.agregarMercancia(mercancia);;
                    }
                }
                for (Vehiculo vehiculo : vehiculos) {
                    if (vehiculo.getPropietario().getId() == propietario.getId()) {
                        propietario.agregarVehiculo(vehiculo);
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
                if (usuario.getTipo().equalsIgnoreCase("propietario")) {
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

    

    // Método para escribir la lista de incidentes en un archivo CSV
    public void escribirIncidentesACSV(String archivoIncidentes, List<Incidente> incidentes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoIncidentes))) {
            for (Incidente incidente : incidentes) {
                String linea = incidente.getId() + "," +
                               incidente.getDescripcion() + "," +
                               incidente.getReportadoPor().getId() + "," +
                               incidente.getFechaHora().toString() + "," +
                               incidente.getUbicacion() + "," +
                               incidente.getEstado();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de incidentes: " + e.getMessage());
        }
    }
    
   

    // Método para escribir la lista de pagos en un archivo CSV
    public void escribirPagosACSV(String archivoPagos, List<Pago> pagos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoPagos))) {
            for (Pago pago : pagos) {
                String linea = pago.getId() + "," +
                               pago.getPropietario().getId() + "," +
                               pago.getMonto() + "," +
                               pago.getFecha().toString();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de pagos: " + e.getMessage());
        }
    }

    // Método para escribir la lista de registros de acceso en un archivo CSV
    public void escribirRegistrosAccesoACSV(String archivoRegistros, List<RegistroAcceso> registrosAcceso) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoRegistros))) {
            for (RegistroAcceso registro : registrosAcceso) {
                String linea = registro.getId() + "," +
                               registro.getVehiculo().getPlaca() + "," +
                               registro.getFechaHoraEntrada().toString() + "," +
                               registro.getFechaHoraSalida().toString() + "," +
                               registro.getRegistradoPor().getId();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de registros de acceso: " + e.getMessage());
        }
    }

    // Método para agregar una mercancia a un archivo CSV existente
    public void agregarMercanciaACSV(String archivoMercancias, Mercancia mercancia) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoMercancias, true))) {
            String linea = mercancia.getId() + "," +
                           mercancia.getDescripcion() + "," +
                           mercancia.getDestinatario().getId() + "," +
                           mercancia.getFechaHoraLlegada().toString() + "," +
                           mercancia.isEntregada();
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al agregar mercancia al archivo: " + e.getMessage());
        }
    }

    // Método para agregar un vehiculo a un archivo CSV existente
    public void agregarVehiculoACSV(String archivoVehiculos, Vehiculo vehiculo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoVehiculos, true))) {
            String linea = vehiculo.getPlaca() + "," +
                           vehiculo.getTipo() + "," +
                           vehiculo.getPropietario().getId() + "," +
                           vehiculo.isAutorizado();
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al agregar vehiculo al archivo: " + e.getMessage());
        }
    }

    // Método para agregar un usuario a un archivo CSV existente
    public void agregarUsuarioACSV(String archivoUsuarios, Usuario usuario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
            String linea = usuario.getId() + "," +
                           usuario.getNombre() + "," +
                           usuario.getEmail() + "," +
                           usuario.getContraseña() + "," +
                           usuario.getTipo();
            if (usuario.getTipo().equalsIgnoreCase("propietario")) {
                Propietario propietario = (Propietario) usuario;
                linea += "," + propietario.getNumapartamento() + "," + propietario.isAlDia();
            } else if (usuario instanceof Vigilante) {
                Vigilante vigilante = (Vigilante) usuario;
                linea += "," + vigilante.getTurno();
            }
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al agregar usuario al archivo: " + e.getMessage());
        }
    }

    // Método para agregar un incidente a un archivo CSV existente
    public void agregarIncidenteACSV(String archivoIncidentes, Incidente incidente) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoIncidentes, true))) {
            String linea = incidente.getId() + "," +
                           incidente.getDescripcion() + "," +
                           incidente.getReportadoPor().getId() + "," +
                           incidente.getFechaHora().toString() + "," +
                           incidente.getUbicacion() + "," +
                           incidente.getEstado();
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al agregar incidente al archivo: " + e.getMessage());
        }
    }

    // Método para agregar un pago a un archivo CSV existente
    public void agregarPagoACSV(String archivoPagos, Pago pago) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoPagos, true))) {
            String linea = pago.getId() + "," +
                           pago.getPropietario().getId() + "," +
                           pago.getMonto() + "," +
                           pago.getFecha().toString();
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al agregar pago al archivo: " + e.getMessage());
        }
    }

    // Método para agregar un registro de acceso a un archivo CSV existente
    public void agregarRegistroAccesoACSV(String archivoRegistros, RegistroAcceso registro) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoRegistros, true))) {
            String fecha = "";
            if(registro.getFechaHoraSalida().toString()==null){
                fecha="2023-10-15T14:30:00";
               }else{
                fecha = registro.getFechaHoraSalida().toString();
               }
            String linea = registro.getId() + "," +
                           registro.getVehiculo().getPlaca() + "," +
                           registro.getFechaHoraEntrada().toString() + "," +
                           fecha + "," +
                           registro.getRegistradoPor().getId();
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al agregar registro de acceso al archivo: " + e.getMessage());
        }
    }
}
