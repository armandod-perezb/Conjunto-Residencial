package com.mycompany.programmingproject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programmingproject {

    // Declarar listas
    static List<Usuario> administradores = new ArrayList<>();
    static List<Usuario> vigilantes = new ArrayList<>();
    static List<Usuario> propietarios = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Directorio de trabajo actual: " + System.getProperty("user.dir"));
        String fileUsuarios="usuarios.csv";
        String fileMercancias = "mercancias.csv";
        String fileVehiculos = "vehiculos.csv";
        IniciarSesion iniciarSesion = IniciarSesion.getInstancia();
        archivoHandler manejadorArchivos = archivoHandler.getInstancia(fileUsuarios);
        List<Usuario> usuarios  = manejadorArchivos.cargarUsuariosDesdeCSV(fileUsuarios, fileMercancias, fileVehiculos);
        System.out.println("Número de usuarios cargados: " + usuarios.size());
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
        int op = 0;
        while (op != 3) {
            op = menuiniciosesion();
            switch (op) {
                case 1: // Iniciar sesión
                int idIniciarSesion;
                int indexUsuario;
                while (true) {
                    idIniciarSesion = Utilidades.registrarinteger("Digite id de usuario: ");
                    String contraseñaIniciarSesion = Utilidades.registrarstring("Digite contraseña: ");
                    if(Utilidades.CredencialesValidas(usuarios, idIniciarSesion, contraseñaIniciarSesion)) {
                        indexUsuario = Utilidades.hallarIndexUsuario(usuarios, idIniciarSesion);
                        iniciarSesion.iniciarSesion(usuarios.get(indexUsuario));
                        break;
                    } else {
                        System.out.println("Credenciales inválidas");
                    }
                }
                        switch (iniciarSesion.getUsuarioActual().getTipo()) {
                            case "propietario":
                                int propietarioOp = 0;
                                while (propietarioOp != 5) {
                                    propietarioOp = menupropietario();
                                    switch (propietarioOp) {
                                        case 1:
                                            // Realizar pago
                                            System.out.println("Realizar pago");
                                            break;
                                        case 2:
                                            // Reportar incidente
                                            System.out.println("Reportar incidente");
                                            break;
                                        case 3:
                                            // Autorizar vehículo
                                            System.out.println("Autorizar vehículo");
                                            break;
                                        case 4:
                                            // Visualizar mercancía
                                            System.out.println("Visualizar mercancía");
                                            break;
                                        case 5:
                                            // Salir
                                            break;
                                        default:
                                            System.out.println("Opción inválida");
                                            break;
                                    }
                                }
                                break;
                            case "vigilante":
                                int vigilanteOp = 0;
                                while (vigilanteOp != 4) {
                                    vigilanteOp = menuvigilante();
                                    switch (vigilanteOp) {
                                        case 1:
                                            // Registrar incidente
                                            System.out.println("Registrar incidente");
                                            break;
                                        case 2:
                                            // Registrar entrada de vehículo
                                            System.out.println("Registrar entrada de vehículo");
                                            break;
                                        case 3:
                                            // Registrar llegada de mercancía
                                            System.out.println("Registrar llegada de mercancía");
                                            break;
                                        case 4:
                                            // Salir
                                            
                                            break;
                                        default:
                                            System.out.println("Opción inválida");
                                            break;
                                    }
                                }
                                break;
                            case "administrador":
                                int adminOp = 0;
                                while (adminOp != 5) {
                                    adminOp = menuadministrador();
                                    switch (adminOp) {
                                        case 1:
                                            // Gestionar Pagos
                                            System.out.println("Gestionar Pagos");
                                            break;
                                        case 2:
                                            // Gestionar Incidentes
                                            System.out.println("Gestionar Incidentes");
                                            break;
                                        case 3:
                                            // Control de Vehículos
                                            System.out.println("Control de Vehículos");
                                            break;
                                        case 4:
                                            // Gestionar Mercancía
                                            System.out.println("Gestionar Mercancía");
                                            break;
                                        case 5:
                                            // Mostrar información
                                            System.out.println("Mostrar información");
                                            break;
                                        case 6:
                                            // Salir
                                            break;
                                        default:
                                            System.out.println("Opción inválida");
                                            break;
                                    }
                                }
                                break;
                            default:
                                System.out.println("Tipo de usuario inválido");
                                break;
                        }
                break;
                case 2: // Registrar usuario
                    int id;
                    while(true){
                         id= Utilidades.registrarinteger("Digite id de usuario: ");
                        if(Utilidades.contieneUsuario(usuarios, id)){
                            System.out.println("El id ya existe. Registre uno diferente");
                        }else{
                            break;
                        }
                    }
                    String nombre = Utilidades.registrarstring("Digite nombre de usuario: ");
                    String email = Utilidades.registrarstring("Digite email");
                    String contraseña = Utilidades.registrarstring("Digite Contraseña");
                    while(true){
                        boolean control = true;
                            int opciontipo = Utilidades.registrarinteger("""
                                Digite tipo de usuario
                                1. Propietario
                                2. Vigilante
                                3. Administrador
                                4. Salir""");
                        switch (opciontipo) {
                            case 1:
                                String num_apartamento = Utilidades.registrarstring("Digite número de apartamento");
                                usuarios.add(new Propietario(id, nombre, email, contraseña, "propietario", num_apartamento));
                                break;
                            case 2:
                                String turno = Utilidades.registrarstring("Digite turno (diurno/nocturno): ");
                                usuarios.add(new Vigilante(id, nombre, email, contraseña, "vigilante", turno));
                                break;
                            case 3:
                                usuarios.add(new Administrador(id, nombre, email, contraseña, "administrador"));
                                break;
                            default:
                                System.out.println("Dato inválido");
                                control=false;
                                break;
                        }
                        if(control){
                            break;
                        }
                    }
                    break;
                case 3: // Salir
                    break;
            }
        }
    }

    // Menú de inicio de sesión
    static int menuiniciosesion() {
        return Utilidades.registrarinteger("""
                    Seleccione una opción
                    1. Iniciar sesión
                    2. Registrar usuario
                    3. Salir
                """);
    }

    // Menú de administrador
    static int menuadministrador() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Gestionar Pagos
                2. Gestionar Incidentes
                3. Control de Vehículos
                4. Gestionar Mercancía
                5. Mostrar información
                4. Salir
                        """);
    }

    // Menú de propietario
    static int menupropietario() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Realizar pago
                2. Reportar incidente
                3. Autorizar vehículo
                4. Visualizar mercancía
                5. Salir
                        """);
    }

    // Menú de vigilante
    static int menuvigilante() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Registrar incidente
                2. Registrar entrada de vehículo
                3. Registrar llegada de  mercancía
                4. Salir
                        """);
    }
}


