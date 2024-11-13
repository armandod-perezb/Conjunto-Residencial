package com.mycompany.programmingproject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Programmingproject {

    public static void main(String[] args) {
        int op = 0;
        while(op!=4){
            op = menuiniciosesion();
            switch(op){
                case 1: //Iniciar sesión
                    break;
                case 2: //Registrar usuario
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
                                   1. Menú de administrador
                                   2. Menú de propietario
                                   3. Menú de vigilante
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

