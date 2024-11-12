package com.mycompany.programmingproject;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Incidente {
    private int id;
    private String descripcion;
    private LocalDateTime fechaHora;
    private Usuario reportadoPor;
    private String ubicacion;
    private String estado;
    private ArrayList<String> comentarios;

    public Incidente(int id, String descripcion, Usuario reportadoPor, String ubicacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaHora = LocalDateTime.now();
        this.reportadoPor = reportadoPor;
        this.ubicacion = ubicacion;
        this.estado = "reportado";
        this.comentarios = new ArrayList<>();
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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Usuario getReportadoPor() {
        return reportadoPor;
    }

    public void setReportadoPor(Usuario reportadoPor) {
        this.reportadoPor = reportadoPor;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<String> comentarios) {
        this.comentarios = comentarios;
    }
    
    
}
