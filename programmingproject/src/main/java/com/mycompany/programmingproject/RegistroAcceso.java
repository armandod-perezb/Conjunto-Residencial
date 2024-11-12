package com.mycompany.programmingproject;

import java.time.LocalDateTime;

public class RegistroAcceso {
    private int id;
    private Vehiculo vehiculo;
    private LocalDateTime fechaHoraEntrada;
    private LocalDateTime fechaHoraSalida;
    private Usuario registradoPor;

    public RegistroAcceso(int id, Vehiculo vehiculo, Usuario registradoPor) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.fechaHoraEntrada = LocalDateTime.now();
        this.registradoPor = registradoPor;
    }
    
    public void registrarSalida() {
        this.fechaHoraSalida = LocalDateTime.now();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDateTime getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public void setFechaHoraEntrada(LocalDateTime fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public Usuario getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(Usuario registradoPor) {
        this.registradoPor = registradoPor;
    }
    
    
    
    
}
