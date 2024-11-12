package com.mycompany.programmingproject;
    
import java.time.LocalDate;

public class Pago {
    private int id;
    private Propietario propietario;
    private double monto;
    private LocalDate fecha;
    private String concepto;
    private String estado;

    public Pago(int id, Propietario propietario, double monto, String concepto) {
        this.id = id;
        this.propietario = propietario;
        this.monto = monto;
        this.fecha = LocalDate.now();
        this.concepto = concepto;
        this.estado = "pendiente";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
