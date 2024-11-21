package com.mycompany.programmingproject;

public class Vehiculo {
    private String placa;
    private String tipo;
    private Propietario propietario;
    private boolean autorizado;

    public Vehiculo(String placa, String tipo, Propietario propietario, boolean autorizado) {
        this.placa = placa;
        this.tipo = tipo;
        this.propietario = propietario;
        this.autorizado = autorizado;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", tipo='" + tipo + '\'' +
                ", propietario=" + propietario.getNombre() + // Avoid calling toString on Propietario
                ", autorizado=" + autorizado +
                '}';
    }
}
