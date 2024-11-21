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
                    System.out.println("Iniciar sesión (mensaje)");
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
