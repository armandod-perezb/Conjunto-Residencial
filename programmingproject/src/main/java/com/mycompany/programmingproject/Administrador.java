package com.mycompany.programmingproject;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario {
    public GestionPagos pagos = GestionPagos.ObtenerInstancia(new ArrayList<>());
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

    public void GenerarPago(Propietario propietario) {
        double monto = Utilidades.registrardouble("Ingrese el monto del pago");
        String concepto = Utilidades.registrarstring("Ingrese el concepto del pago");
        this.pagos.registrarPago(propietario, "no definido", monto, concepto);
    }

    public void mostrarPagosPropietario(Propietario propietario){
        List<Pago> pagosPropietario = this.pagos.obtenerPagosPropietario(propietario);
        for (Pago pago : pagosPropietario) {
            System.out.println(pago);
        }
    }
}
