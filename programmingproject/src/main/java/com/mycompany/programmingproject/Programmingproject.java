package com.mycompany.programmingproject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salacomputocentro.ba
 */
public class Programmingproject {

    public static void main(String[] args) {
        int op = 0;
        while(op!=4){
            op = menu();
            switch(op){
                case 1:
                    break;
                case 2:
                    break;
                
            }
        }
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

