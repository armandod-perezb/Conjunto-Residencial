package com.mycompany.programmingproject;

import java.util.Scanner;

public class Utilidades {
    // se declara el objeto de scanner
    private static final Scanner leer = new Scanner(System.in);

    public Utilidades() {
    }

    // método para validar los integers
    public static boolean validacion(String a) {
        return a.matches("[0-9]+");
    }

    // método para registrar integer
    public static int registrarinteger(String mensaje) {
        String val;
        while (true) {
            val = registrarstring(mensaje);
            if (validacion(val)) {
                break;
            } else {
                System.out.println("Digite numeros enteros positivos");
            }
        }

        return Integer.parseInt(val);
    }

    // método para registrar string
    public static String registrarstring(String mensaje) {
        System.out.println(mensaje);
        String string = leer.nextLine();
        return string;
    }

    // Registrar double
    public static Double registrardouble(String mensaje) {
        boolean parametro = false;
        double var = 0;
        while (true) {
            try {
                System.out.println(mensaje);
                var = leer.nextDouble();
                parametro = true;

                leer.nextLine();
            } catch (Exception e) {
                System.out.println("Parámetro inválido, digite el parámetro correcto");
                leer.nextLine();
                parametro = false;
            }
            if (parametro) {
                break;
            }

        }
        return var;
    }

    // Registrar boolean
    public static boolean registrarboolean(String pregunta) {
        String opcion;
        while (true) {
            System.out.println(pregunta);
            System.out.println("1. Sí");
            System.out.println("2. No");
            opcion = leer.nextLine();
            if (validacion(opcion)) {
                switch (Integer.parseInt(opcion)) {
                    case 1:
                        return true;
                    case 2:
                        return false;
                    default:
                        System.out.println("Parámetro incorrecto");
                        break;
                }
            } else {
                System.out.println("Parámetro incorrecto");
            }
        }

    }

}