package com.mycompany.programmingproject;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario {
    private List<Pago> pagos;
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
        GestionPagos pagos = GestionPagos.ObtenerInstancia(new ArrayList<Pago>());
        double monto = Utilidades.registrardouble("Ingrese el monto del pago");
        String concepto = Utilidades.registrarstring("Ingrese el concepto del pago");
        String mediodepago = Utilidades.registrarstring("Ingrese el medio de pago");
        this.pagos.add(new Pago(1, mediodepago, new Propietario(), monto, concepto));
    }
}
