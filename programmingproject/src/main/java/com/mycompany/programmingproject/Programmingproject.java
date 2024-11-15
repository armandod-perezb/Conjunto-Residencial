package com.mycompany.programmingproject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Programmingproject {

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
                String tipo = Utilidades.registrarstring("Digite tipo de usuario");
                    break;
                case 3: //Salir
                    break;
            }
        }
    }

    static int menuiniciosesion(){
        return Utilidades.registrarinteger("""
            Seleccione una opción
            1. Iniciar sesión
            2. Registrar usuario
            3. Salir
        """);
    }

    static int menu(){
        return Utilidades.registrarinteger("""
                                   Selecciona una opción
                                   1. Menú de administrador
                                   2. Menú de propietario
                                   3. Menú de vigilante
                                   4. Salir
                                           """);
    }
    
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
    
    static int menupropietario(){
        return Utilidades.registrarinteger("""
                                   Selecciona una opción
                                   1. Menú de administrador
                                   2. Menú de propietario
                                   3. Menú de vigilante
                                   4. Salir
                                           """);
    }
    
    static int menuvigilante(){
        return Utilidades.registrarinteger("""
                                   Selecciona una opción
                                   1. Gestión de pagos
                                   2. Menú de propietario
                                   3. Menú de vigilante
                                   4. Salir
                                           """);
    }
}

