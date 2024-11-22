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

    public void RegistrarIncidentes(){
        GestionIncidentes incidentes = GestionIncidentes.ObtenerInstancia(new ArrayList<>());
        for(Incidente incidente : incidentes.getIncidentes()){
            System.out.println("Verificando la información.......");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println("Error al pausar la ejecución: " + e.getMessage());
                Thread.currentThread().interrupt(); // Restablecer el estado de interrupción
            }
            System.out.println("ID: "+incidente.getId());
            System.out.println("Descripción: "+incidente.getDescripcion());
            System.out.println("Fecha y hora: "+incidente.getFechaHora());
            System.out.println("Reportado por: "+incidente.getReportadoPor().getNombre());
            System.out.println("Ubicación: "+incidente.getUbicacion());
            System.out.println("Estado: "+incidente.getEstado());
            boolean respuesta = Utilidades.registrarboolean("¿Desea registrar este incidente?");
            if(respuesta){
                incidentes.actualizarEstadoIncidente(incidente.getId(), "Registrado");
            }else{
                incidentes.actualizarEstadoIncidente(incidente.getId(), "Archivado");
            }
        }
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
