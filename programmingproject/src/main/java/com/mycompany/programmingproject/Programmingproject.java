package com.mycompany.programmingproject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programmingproject {

    // Declare lists using polymorphism
    static List<Usuario> administradores = new ArrayList<>();
    static List<Usuario> vigilantes = new ArrayList<>();
    static List<Usuario> propietarios = new ArrayList<>();

    public static void main(String[] args) {
        
        int op = 0;
        while(op!=3){
            op = menuiniciosesion();
            switch(op){
                case 1: //Iniciar sesión
                System.out.println("Iniciar sesión (mensaje)");
                    break;
                case 2: //Registrar usuario
                String nombre = Utilidades.registrarstring("Digite nombre de usuario: ");
                String email = Utilidades.registrarstring("Digite email");
                String contraseña = Utilidades.registrarstring("Digite Contraseña");
                int opciontipo= Utilidades.registrarinteger("""
                    Digite tipo de usuario
                    1. Propietario
                    2. Vigilante
                    3. Administrador
                    4. Salir""");
                String tipousuario;
                switch(opciontipo){
                    case 1:
                    tipousuario="propietario";
                    propietarios.add(new Propietario(0, nombre, email, contraseña, tipousuario, "apartamento"));
                    break;
                    case 2:
                    tipousuario="vigilante";
                    vigilantes.add(new Vigilante(0, nombre, email, contraseña, tipousuario, "turno"));
                    break;
                    case 3:
                    tipousuario="administrador";
                    administradores.add(new Administrador(0, nombre, email, contraseña, tipousuario));
                    break;
                    default:
                    System.out.println("Dato inválido");
                    break;
                }
                    break;
                case 3: //Salir
                    break;
            }
        }
    }
    //Menú de inicio de sesión
    static int menuiniciosesion(){
        return Utilidades.registrarinteger("""
            Seleccione una opción
            1. Iniciar sesión
            2. Registrar usuario
            3. Salir
        """);
    }
    //Menú de administrador
    static int menuadministrador(){
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
    //Menú de propietario
    static int menupropietario(){
        return Utilidades.registrarinteger("""
                                   Selecciona una opción
                                   1. Realizar pago
                                   2. Reportar incidente
                                   3. Autorizar vehículo
                                   4. Visualizar mercancía 
                                   5. Salir
                                           """);
    }
    //Menú de vigilante
    static int menuvigilante(){
        return Utilidades.registrarinteger("""
                                   Selecciona una opción
                                   1. Registrar incidente
                                   2. Registrar entrada de vehículo
                                   3. Registrar llegada de  mercancía
                                   4. Salir
                                           """);
    }
}

