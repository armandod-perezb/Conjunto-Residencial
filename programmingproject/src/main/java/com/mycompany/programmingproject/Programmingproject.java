package com.mycompany.programmingproject;

import java.util.ArrayList;
import java.util.List;

public class Programmingproject {


    public static void main(String[] args) {
        System.out.println("Directorio de trabajo actual: " + System.getProperty("user.dir"));
        // Declarar variables
        String fileIncidentes = ("incidentes.csv");
        String filePagos = ("pagos.csv");
        String fileRegistroAcceso = ("registroacceso.csv");
        String fileUsuarios="usuarios.csv";
        String fileMercancias = "mercancias.csv";
        String fileVehiculos = "vehiculos.csv";
        IniciarSesion iniciarSesion = IniciarSesion.getInstancia();
        archivoHandler manejadorArchivos = archivoHandler.getInstancia(fileUsuarios);
        List<Usuario> usuarios  = manejadorArchivos.cargarUsuariosDesdeCSV(fileUsuarios, fileMercancias, fileVehiculos);
        List<Propietario> propietarios = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getTipo().equals("propietario")) {
                propietarios.add((Propietario) usuario);
            }
            
        }
        List<Mercancia> mercancias = manejadorArchivos.cargarMercanciasDesdeCSV(fileMercancias, propietarios);
        List<Incidente> incidentesCSV = manejadorArchivos.cargarIncidentesDesdeCSV(fileIncidentes, usuarios);
        List<Pago> pagosCSV = manejadorArchivos.cargarPagosDesdeCSV(filePagos, propietarios);
        List<Vehiculo> vehiculosCSV = manejadorArchivos.cargarVehiculosDesdeCSV(fileVehiculos, propietarios);
        List<RegistroAcceso> registroAccesoCSV = manejadorArchivos.cargarRegistrosAccesoDesdeCSV(fileRegistroAcceso, vehiculosCSV, usuarios);
        System.out.println("Número de usuarios cargados: " + usuarios.size());
        GestionMercancia gestionMercancia = GestionMercancia.ObtenerInstancia(mercancias);
        GestionIncidentes gestionIncidentes = GestionIncidentes.ObtenerInstancia(incidentesCSV);
        GestionPagos gestionPagos = GestionPagos.ObtenerInstancia(pagosCSV);
        ControlAcceso controlAcceso = ControlAcceso.ObtenerInstancia(registroAccesoCSV, vehiculosCSV);
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
        int op = 0;
        while (op != 3) { // Update the condition to exclude the new option
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
                                while (propietarioOp != 6) {
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
                                            propietarioactual.agregarVehiculo();
                                            break;
                                        case 4:
                                            // Visualizar mercancía
                                            propietarioactual.mostrarMercancias();
                                            break;
                                        case 5:
                                            // Visualizar vehículos autorizados
                                            propietarioactual.mostrarVehiculos();
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
                            case "vigilante":
                            Vigilante vigilanteactual = (Vigilante) iniciarSesion.getUsuarioActual();
                                int vigilanteOp = 0;
                                while (vigilanteOp != 5) {
                                    vigilanteOp = menuvigilante();
                                    switch (vigilanteOp) {
                                        case 1:
                                            // Registrar incidente
                                            vigilanteactual.RegistrarIncidentes();
                                            break;
                                        case 2:
                                            // Registrar entrada de vehículo
                                            vigilanteactual.registrarEntrada();
                                            break;
                                        case 3:
                                            // Registrar salida de vehículo
                                            vigilanteactual.registrarSalida();
                                            break;
                                        case 4: // Registrar llegada de mercancía
                                            int idPropietario = Utilidades.registrarinteger("Digite id de propietario: ");
                                            if(Utilidades.contieneUsuario(usuarios, idPropietario)){
                                                int indexpropietario = Utilidades.hallarIndexUsuario(usuarios, idPropietario);
                                                Propietario propietariodestinatario = (Propietario) usuarios.get(indexpropietario);
                                                vigilanteactual.registrarLlegadaMercancia(propietariodestinatario);
                                                System.out.println("Mercancía registrada con éxito");

                                            }else{
                                                System.out.println("El propietario no existe");
                                            }
                                            break;
                                        case 5: // Salir
                                            System.out.println("Saliendo...");
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
                                while (adminOp != 7) { // Update the condition to include the new option
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
                                                        GestionPagos pagos = GestionPagos.ObtenerInstancia(new ArrayList<>());
                                                        pagos.mostrarPagosPendientes();
                                                        break;
                                                    case 2:
                                                        // Registrar pago
                                                        int idPropietario = Utilidades.registrarinteger("Digite id de propietario: ");
                                                        if(Utilidades.contieneUsuario(usuarios, idPropietario)){
                                                            int indexPropietario = Utilidades.hallarIndexUsuario(usuarios, idPropietario);
                                                            Propietario propietario = (Propietario) usuarios.get(indexPropietario);
                                                            administradoractual.GenerarPago(propietario);
                                                            System.out.println("Pago registrado con éxito");
                                                        } else {
                                                            System.out.println("El propietario no existe");
                                                        }
                                                        break;
                                                    case 3: // Salir
                                                        System.out.println("Saliendo...");
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
                                            GestionIncidentes incidentes = GestionIncidentes.ObtenerInstancia(new ArrayList<>());
                                            while (gestionarIncidentesOp != 3) {
                                                gestionarIncidentesOp = menugestionarincidentes();
                                                switch (gestionarIncidentesOp) {
                                                    case 1:
                                                        // Ver incidentes reportados
                                                        incidentes.MostrarIncidente();
                                                        break;
                                                    case 2:
                                                        // Registrar resolución de incidente
                                                        int idIncidente = Utilidades.registrarinteger("Digite id de incidente: ");
                                                        boolean existe = false;
                                                        for (Incidente incidente : incidentes.getIncidentes()) {
                                                            if (incidente.getId() == idIncidente) {
                                                                existe = true;
                                                                incidente.setEstado("Resuelto");
                                                                System.out.println("Incidente resuelto con éxito");
                                                            }
                                                        }
                                                        if(!existe){
                                                            System.out.println("El incidente no existe");
                                                        }
                                                        break;
                                                    case 3: // Salir
                                                        System.out.println("Saliendo...");
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
                                            while (controlVehiculosOp != 4) {
                                                controlVehiculosOp = menucontrolvehiculos();
                                                switch (controlVehiculosOp) {
                                                    case 1:
                                                        // Ver vehículos autorizados
                                                        controlAcceso.verVehiculosAutorizados();
                                                        break;
                                                    case 2:
                                                        // Registrar entrada de vehículo
                                                        administradoractual.registrarEntrada();
                                                        break;
                                                    case 3:
                                                        // Registrar salida de vehículo
                                                        administradoractual.registrarSalida();
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
                                        case 4:
                                            // Gestionar Mercancía
                                            int gestionarMercanciaOp = 0;
                                            while (gestionarMercanciaOp != 3) {
                                                gestionarMercanciaOp = menugestionarmercancia();
                                                switch (gestionarMercanciaOp) {
                                                    case 1:
                                                        // Ver mercancías registradas
                                                        gestionMercancia.mostrarMercancias();
                                                        break;
                                                    case 2:
                                                        // Registrar nueva mercancía
                                                        int idPropietario = Utilidades.registrarinteger("Digite id de propietario: ");
                                                        if(Utilidades.contieneUsuario(usuarios, idPropietario)){
                                                            int indexPropietario = Utilidades.hallarIndexUsuario(usuarios, idPropietario);
                                                            Propietario propietario = (Propietario) usuarios.get(indexPropietario);
                                                            String descripcion = Utilidades.registrarstring("Digite la descripción de la mercancía: ");
                                                            gestionMercancia.registrarLlegada(descripcion,propietario);
                                                            System.out.println("Mercancía registrada con éxito");
                                                        } else {
                                                            System.out.println("El propietario no existe");
                                                        }
                                                        break;
                                                    case 3:
                                                        // Salir
                                                        System.out.println("Saliendo...");
                                                        break;
                                                    default:
                                                        System.out.println("Opción inválida");
                                                        break;
                                                }
                                            }
                                            break;
                                        case 5:
                                            // Exportar datos
                                            List<Propietario> propietariosexportar = new ArrayList<>();
                                            for (Usuario usuario : usuarios) {
                                                if(usuario.getTipo().equalsIgnoreCase("propietario")){
                                                    propietariosexportar.add((Propietario) usuario);
                                                }
                                            }
                                            exportarDatos(usuarios, propietariosexportar, gestionMercancia.getMercancias(),  controlAcceso.getVehiculosAutorizados(), gestionIncidentes.getIncidentes(), gestionPagos.getPagos(), controlAcceso.getRegistrosAcceso());
                                            break;
                                        case 6:
                                            // Gestionar CSV
                                            gestionarCSV();
                                            break;
                                        case 7:
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
                default:
                    System.out.println("Opción inválida");
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

    // Método para exportar datos
    static void exportarDatos(List<Usuario> usuarios, List<Propietario> propietarios, List<Mercancia> mercancias, 
    List<Vehiculo> vehiculos, List<Incidente> incidentes, List<Pago> pagos, List<RegistroAcceso> registroAccesos) {
        String fileIncidentes = ("incidentes.csv");
        String filePagos = ("pagos.csv");
        String fileRegistroAcceso = ("registroacceso.csv");
        String fileUsuarios="usuarios.csv";
        String fileMercancias = "mercancias.csv";
        String fileVehiculos = "vehiculos.csv";
        archivoHandler handler = archivoHandler.getInstancia("");

        if(handler.comprobararchivo(fileIncidentes)){
            handler.escribirIncidentesACSV(fileIncidentes, incidentes);
        } else {
            for (Incidente incidente : incidentes) {
                handler.agregarIncidenteACSV(fileIncidentes, incidente);
            }
        }

        if(handler.comprobararchivo(filePagos)){
            handler.escribirPagosACSV(filePagos, pagos);
        } else {
            for (Pago pago : pagos) {
                handler.agregarPagoACSV(filePagos, pago);
            }
        }

        if(handler.comprobararchivo(fileRegistroAcceso)){
            handler.escribirRegistrosAccesoACSV(fileRegistroAcceso, registroAccesos);
        } else {
            for (RegistroAcceso registro : registroAccesos) {
                handler.agregarRegistroAccesoACSV(fileRegistroAcceso, registro);
            }
        }

        if(handler.comprobararchivo(fileUsuarios)){
            handler.escribirUsuariosACSV(fileUsuarios, usuarios);
        } else {
            for (Usuario usuario : usuarios) {
                handler.agregarUsuarioACSV(fileUsuarios, usuario);
            }
        }

        if(handler.comprobararchivo(fileMercancias)){
            handler.escribirMercanciasACSV(fileMercancias, mercancias);
        } else {
            for (Mercancia mercancia : mercancias) {
                handler.agregarMercanciaACSV(fileMercancias, mercancia);
            }
        }

        if(handler.comprobararchivo(fileVehiculos)){
            handler.escribirVehiculosACSV(fileVehiculos, vehiculos);
        } else {
            for (Vehiculo vehiculo : vehiculos) {
                handler.agregarVehiculoACSV(fileVehiculos, vehiculo);
            }
        }
        System.out.println("Exportando datos...");
    }

    // Menú de administrador
    static int menuadministrador() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Gestionar Pagos
                2. Gestionar Incidentes
                3. Control de Vehículos
                4. Gestionar Mercancía
                5. Exportar datos
                6. Gestionar CSV
                7. Salir
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
                5. Visualizar vehículos autorizados
                6. Salir
                        """);
    }

    // Menú de vigilante
    static int menuvigilante() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Registrar incidente
                2. Registrar entrada de vehículo
                3. Registrar salida de vehículo
                4. Registrar llegada de  mercancía
                5. Salir
                        """);
    }

    // Menú de gestionar pagos
    static int menugestionarpagos() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Ver pagos pendientes
                2. Registrar pago
                3. Salir
                """);
    }

    // Menú de gestionar incidentes
    static int menugestionarincidentes() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Ver incidentes reportados
                2. Registrar resolución de incidente
                3. Salir
                """);
    }

    // Menú de gestionar control de vehículos
    static int menucontrolvehiculos() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Ver vehículos autorizados
                2. Registrar entrada de vehículo
                3. Registrar salida de vehículo
                4. Salir
                """);
    }

    // Menú de gestionar mercancía
    static int menugestionarmercancia() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Ver mercancías registradas
                2. Registrar nueva mercancía
                3. Salir
                """);
    }

    // Método para gestionar CSV
    static void gestionarCSV() {
        int csvOp = 0;
        while (csvOp != 3) {
            csvOp = menuCSV();
            switch (csvOp) {
                case 1:
                    // Modificar línea
                    String filePath = "usuarios.csv";
                    archivoHandler handler = archivoHandler.getInstancia("");
                    List<String> archivo = handler.leerArchivo(filePath);
                    int lineNumber = Utilidades.registrarinteger("Digite el número de línea a modificar: ");
                    
                    if(lineNumber >= 0 && lineNumber <= archivo.size()){
                        String[] datos = archivo.get(lineNumber).split(",");
                        String nombre = Utilidades.registrarstring("Digite nuevo nombre: ");
                        String email = Utilidades.registrarstring("Digite nuevo email: ");
                        String contraseña = Utilidades.registrarstring("Digite nueva contraseña: ");
                        datos[1] = nombre;
                        datos[2] = email;
                        datos[3] = contraseña;
                        String newLine = String.join(",", datos);
                        handler.modificarLinea(filePath, lineNumber, newLine);
                        }
                    break;
                case 2:
                    // Eliminar línea
                    filePath = Utilidades.registrarstring("Digite el nombre del archivo CSV: ");
                    lineNumber = Utilidades.registrarinteger("Digite el número de línea a eliminar: ");
                    archivoHandler.getInstancia("").eliminarLinea(filePath, lineNumber);
                    break;
                case 3:
                    // Salir
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    // Menú de gestionar CSV
    static int menuCSV() {
        return Utilidades.registrarinteger("""
                Selecciona una opción
                1. Modificar usuario
                2. Eliminar línea
                3. Salir
                """);
    }
}


