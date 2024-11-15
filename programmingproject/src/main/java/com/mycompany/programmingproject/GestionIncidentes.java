package com.mycompany.programmingproject;

import java.util.List;

public class GestionIncidentes {
    private List<Incidente> incidentes;
    private static GestionIncidentes instancia;

    private GestionIncidentes(List<Incidente> incidentes) {
        this.incidentes = incidentes;
    }

    public List<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(List<Incidente> incidentes) {
        this.incidentes = incidentes;
    }

    public static GestionIncidentes ObtenerInstancia(List<Incidente> incidentes){
        if(instancia==null){
            instancia = new GestionIncidentes(incidentes);
        }
        return instancia;
    }
    
    public Incidente reportarIncidente(String descripcion, Usuario reportadoPor, String ubicacion) {
        Incidente incidente = new Incidente(incidentes.size()+1, descripcion, reportadoPor, ubicacion);
        
        incidentes.add(incidente);
        return incidente;
    }
    
    public void actualizarEstadoIncidente(int id, String nuevoEstado) {
        for (Incidente incidente : incidentes) {
            if(incidente.getId() == id){
                incidente.setEstado(nuevoEstado);
            }
        }
    }
    public void MostrarIncidente(){
        for (Incidente incidente : incidentes) {
            System.out.println("ID: "+incidente.getId());
            System.out.println("Descripción: "+incidente.getDescripcion());
            System.out.println("Fecha y hora: "+incidente.getFechaHora());
            System.out.println("Reportado por: "+incidente.getReportadoPor().getNombre());
            System.out.println("Ubicación: "+incidente.getUbicacion());
            System.out.println("Estado: "+incidente.getEstado());
        }
    }
}
