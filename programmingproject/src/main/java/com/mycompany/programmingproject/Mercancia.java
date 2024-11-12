package com.mycompany.programmingproject;

import java.time.LocalDateTime;

public class Mercancia {
    private int id;
    private String descripcion;
    private Propietario destinatario;
    private LocalDateTime fechaHoraLlegada;
    private boolean entregada;

    public Mercancia(int id, String descripcion, Propietario destinatario) {
        this.id = id;
        this.descripcion = descripcion;
        this.destinatario = destinatario;
        this.fechaHoraLlegada = LocalDateTime.now();
        this.entregada = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Propietario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Propietario destinatario) {
        this.destinatario = destinatario;
    }

    public LocalDateTime getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public boolean isEntregada() {
        return entregada;
    }

    public void setEntregada(boolean entregada) {
        this.entregada = entregada;
    }

    @Override
    public String toString() {
        return "Mercancia{" + "id=" + id + ", descripcion=" + descripcion + ", destinatario=" + destinatario + ", fechaHoraLlegada=" + fechaHoraLlegada + ", entregada=" + entregada + '}';
    }
    
    
    
}
