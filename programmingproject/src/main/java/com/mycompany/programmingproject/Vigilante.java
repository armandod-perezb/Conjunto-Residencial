package com.mycompany.programmingproject;
import java.util.ArrayList;
import java.util.List;

public class Vigilante extends Usuario {
    List<Incidente> incidentes;
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

    public List<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(List<Incidente> incidentes) {
        this.incidentes = incidentes;
    }

    public void AgregarIncidente(){
        GestionIncidentes incidentes = GestionIncidentes.ObtenerInstancia(new ArrayList<>());
        String descripcion = Utilidades.registrarstring("Digite la descripción del incidente: ");
        String ubicacion = Utilidades.registrarstring("Digite la ubicación del incidente: ");
        incidentes.reportarIncidente(descripcion, this, ubicacion);
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

    public void reporteIncidente(){
        GestionIncidentes incidentes = GestionIncidentes.ObtenerInstancia(new ArrayList<>());
        String descripcion = Utilidades.registrarstring("Digite la descripción del incidente: ");
        String ubicacion = Utilidades.registrarstring("Digite la ubicación del incidente: ");
        incidentes.reportarIncidente(descripcion, this, ubicacion);
        incidentes.MostrarIncidente();
    }
}
