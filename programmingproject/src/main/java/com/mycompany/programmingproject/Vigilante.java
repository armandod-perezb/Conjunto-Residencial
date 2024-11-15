package com.mycompany.programmingproject;

public class Vigilante extends Usuario {
    private String turno;

    public Vigilante(int id, String nombre, String email, String contraseña, String tipo, String turno) {
        super(id, nombre, email, contraseña, tipo);
        this.turno = turno;
    }

    public Vigilante() {
        super();
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Vigilante{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", contraseña='" + getContraseña() + '\'' +
                ", tipo='" + getTipo() + '\'' +
                ", turno='" + turno + '\'' +
                '}';
    }
}
