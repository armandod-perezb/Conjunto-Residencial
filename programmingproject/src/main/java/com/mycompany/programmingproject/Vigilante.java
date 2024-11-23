package com.mycompany.programmingproject;
import java.util.ArrayList;
import java.util.List;

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

    public void registrarEntrada(){
        String placa = Utilidades.registrarstring("Digite la placa del vehiculo que desea registrar");
        ControlAcceso controlAcceso = ControlAcceso.ObtenerInstancia(new ArrayList<>(), new ArrayList<>());
        controlAcceso.registrarEntrada(this, placa);
    }

    public void registrarSalida(){
        String placa = Utilidades.registrarstring("Digite la placa del vehiculo que desea registrar salida"); 
        ControlAcceso controlAcceso = ControlAcceso.ObtenerInstancia(new ArrayList<>(), new ArrayList<>());
        controlAcceso.registrarSalida(placa);
    }

    public void registrarLlegadaMercancia(Propietario proveedor) {
        String descripcion = Utilidades.registrarstring("Digite la descripción de la mercancia que desea registrar");
        GestionMercancia gestionMercancia = GestionMercancia.ObtenerInstancia(new ArrayList<>());
        gestionMercancia.registrarLlegada(descripcion, proveedor);
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
