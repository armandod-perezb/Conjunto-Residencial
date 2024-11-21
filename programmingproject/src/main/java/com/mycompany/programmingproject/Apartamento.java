package com.mycompany.programmingproject;

public class Apartamento {
    private String numero;
    private Propietario propietario;

    public Apartamento(String numero, Propietario propietario) {
        this.numero = numero;
        this.propietario = propietario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
}
