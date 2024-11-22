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
                        Usuario actual = iniciarSesion.getUsuarioActual();
                        break;
                    } else {
                        System.out.println("Credenciales inválidas");
                    }
                }
                        switch (iniciarSesion.getUsuarioActual().getTipo()) {
                            case "propietario":
                            Propietario propietarioactual = (Propietario) iniciarSesion.getUsuarioActual();
                                int propietarioOp = 0;
                                while (propietarioOp != 5) {
                                    propietarioOp = menupropietario();
                                    switch (propietarioOp) {
                                        case 1:
                                            // Realizar pago
                                            propietarioactual.efectuarPagos();
                                            break;
                                        case 2:
                                            // Reportar incidente
                                            propietarioactual.reporteIncidente();
                                            break;
                                        case 3:
                                            // Autorizar vehículo
                                            System.out.println("Autorizar vehículo");
                                            break;
                                        case 4:
                                            // Visualizar mercancía
                                            propietarioactual.mostrarMercancias();
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
                            Vigilante vigilanteactual = (Vigilante) iniciarSesion.getUsuarioActual();
                                int vigilanteOp = 0;
                                while (vigilanteOp != 4) {
                                    vigilanteOp = menuvigilante();
                                    switch (vigilanteOp) {
                                        case 1:
                                            // Registrar incidente
                                            vigilanteactual.RegistrarIncidentes();
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
                            Administrador administradoractual = (Administrador) iniciarSesion.getUsuarioActual();
                                int adminOp = 0;
                                while (adminOp != 5) {
                                    adminOp = menuadministrador();
                                    switch (adminOp) {
                                        case 1:
                                            // Gestionar Pagos
                                            int gestionPagosOp = 0;
                                            while (gestionPagosOp != 3) {
                                                gestionPagosOp = menugestionarpagos();
                                                switch (gestionPagosOp) {
                                                    case 1:
                                                        // Ver pagos pendientes
                                                        System.out.println("Ver pagos pendientes");
                                                        break;
                                                    case 2:
                                                        // Registrar pago
                                                        System.out.println("Registrar pago");
                                                        break;
                                                    case 3:
                                                        // Salir
                                                        break;
                                                    default:
                                                        System.out.println("Opción inválida");
                                                        break;
                                                }
                                            }
                                            break;
                                        case 2:
                                            // Gestionar Incidentes
                                            int gestionarIncidentesOp = 0;
                                            while (gestionarIncidentesOp != 3) {
                                                gestionarIncidentesOp = menugestionarincidentes();
                                                switch (gestionarIncidentesOp) {
                                                    case 1:
                                                        // Ver incidentes reportados
                                                        System.out.println("Ver incidentes reportados");
                                                        break;
                                                    case 2:
                                                        // Registrar resolución de incidente
                                                        System.out.println("Registrar resolución de incidente");
                                                        break;
                                                    case 3:
                                                        // Salir
                                                        break;
                                                    default:
                                                        System.out.println("Opción inválida");
                                                        break;
                                                }
                                            }
                                            break;
                                        case 3:
                                            // Control de Vehículos
                                            int controlVehiculosOp = 0;
                                            while (controlVehiculosOp != 3) {
                                                controlVehiculosOp = menucontrolvehiculos();
                                                switch (controlVehiculosOp) {
                                                    case 1:
                                                        // Ver vehículos autorizados
                                                        System.out.println("Ver vehículos autorizados");
                                                        break;
                                                    case 2:
                                                        // Registrar entrada de vehículo
                                                        System.out.println("Registrar entrada de vehículo");
                                                        break;
                                                    case 3:
                                                        // Salir
                                                        break;
                                                    default:
                                                        System.out.println("Opción inválida");
                                                        break;
                                                }
                                            }
                                            break;
                                        case 4:
                                            // Gestionar Mercancía
                                            int gestionarMercanciaOp = 0;
                                            while (gestionarMercanciaOp != 3) {
                                                gestionarMercanciaOp = menugestionarmercancia();
                                                switch (gestionarMercanciaOp) {
                                                    case 1:
                                                        // Ver mercancías registradas
                                                        System.out.println("Ver mercancías registradas");
                                                        break;
                                                    case 2:
                                                        // Registrar nueva mercancía
                                                        System.out.println("Registrar nueva mercancía");
                                                        break;
                                                    case 3:
                                                        // Salir
                                                        break;
                                                    default:
                                                        System.out.println("Opción inválida");
                                                        break;
                                                }
                                            }
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

    // Menú de gestionar pagos
    static int menugestionarpagos() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Ver pagos pendientes
                2. Registrar pago
                3. Generar reporte de pagos
                4. Salir
                """);
    }

    // Menú de gestionar incidentes
    static int menugestionarincidentes() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Ver incidentes reportados
                2. Registrar resolución de incidente
                3. Generar reporte de incidentes
                4. Salir
                """);
    }

    // Menú de gestionar control de vehículos
    static int menucontrolvehiculos() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Ver vehículos autorizados
                2. Registrar entrada de vehículo
                3. Generar reporte de vehículos
                4. Salir
                """);
    }

    // Menú de gestionar mercancía
    static int menugestionarmercancia() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Ver mercancías registradas
                2. Registrar nueva mercancía
                3. Generar reporte de mercancías
                4. Salir
                """);
    }
}


