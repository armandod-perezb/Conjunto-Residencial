package com.mycompany.programmingproject;

import java.util.ArrayList;

public class Administrador extends Usuario {

    public Administrador() {
    }

    public Administrador(int id, String nombre, String email, String contraseña, String tipo) {
        super(id, nombre, email, contraseña, tipo);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", contraseña='" + getContraseña() + '\'' +
                ", tipo='" + getTipo() + '\'' +
                '}';
    }

    public void GenerarPago() {
        Utilidades util = new Utilidades();
        GestionPagos pagos = GestionPagos.ObtenerInstancia(new ArrayList<Pago>());

        double monto = util.registrardouble("Ingrese el monto del pago");
        String concepto = util.registrarstring("Ingrese el concepto del pago");
        pagos.registrarPago(new Propietario(), monto, concepto);
    }
}
