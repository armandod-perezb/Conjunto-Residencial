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
        this.entregada = true;
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

    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Destinatario: " + destinatario.getNombre());
        System.out.println("Fecha y Hora de Llegada: " + fechaHoraLlegada);
        System.out.println("Entregada: " + (entregada ? "Sí" : "No"));
    }    
}
