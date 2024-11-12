package com.mycompany.programmingproject;

import java.util.ArrayList;

public class GestionIncidentes {
    private ArrayList<Incidente> incidentes;

    public GestionIncidentes(ArrayList<Incidente> incidentes) {
        this.incidentes = incidentes;
    }

    public ArrayList<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(ArrayList<Incidente> incidentes) {
        this.incidentes = incidentes;
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
}
