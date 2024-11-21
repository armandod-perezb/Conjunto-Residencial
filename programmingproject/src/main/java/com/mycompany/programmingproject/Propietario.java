package com.mycompany.programmingproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Propietario extends Usuario{
    private String numapartamento;
    private boolean alDia;
    private List<Vehiculo> vehiculosRegistrados;
    private List<Mercancia> mercanciasRegistradas;

    public Propietario() {

    }

    public Propietario(int id, String nombre, String email, String contraseña, String tipo,String apartamento) {
        super(id, nombre, email, contraseña, tipo);
        this.numapartamento = apartamento;
        this.alDia = true;
    }
    public String getNumapartamento() {
        return numapartamento;
    }

    public void setNumapartamento(String apartamento) {
        this.numapartamento = apartamento;
    }

    public boolean isAlDia() {
        return alDia;
    }

    public void setAlDia(boolean alDia) {
        this.alDia = alDia;
    }

    public List<Vehiculo> getVehiculosRegistrados() {
        return vehiculosRegistrados;
    }

    public void setVehiculosRegistrados(List<Vehiculo> vehiculosRegistrados) {
        vehiculosRegistrados = new ArrayList<>();
        this.vehiculosRegistrados = vehiculosRegistrados;
    }

    public List<Mercancia> getMercanciasRegistradas() {
        return mercanciasRegistradas;
    }

    public void setMercanciasRegistradas(List<Mercancia> mercanciasRegistradas) {
        mercanciasRegistradas = new ArrayList<>();
        this.mercanciasRegistradas = mercanciasRegistradas;
    }
    
    public void agregarVehiculo(Vehiculo vehiculo) {
        if (vehiculosRegistrados == null) {
            vehiculosRegistrados = new ArrayList<>();
        }
        vehiculosRegistrados.add(vehiculo);
    }

    public void agregarMercancia(Mercancia mercancia) {
        if (mercanciasRegistradas == null) {
            mercanciasRegistradas = new ArrayList<>();
        }
        mercanciasRegistradas.add(mercancia);
    }
    public void reporteIncidente(){
        GestionIncidentes incidentes = GestionIncidentes.ObtenerInstancia(new ArrayList<>());
        String descripcion = Utilidades.registrarstring("Digite la descripción del incidente: ");
        String ubicacion = Utilidades.registrarstring("Digite la ubicación del incidente: ");
        incidentes.reportarIncidente(descripcion, this, ubicacion);
        incidentes.MostrarIncidente();
    }
}
