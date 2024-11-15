package com.mycompany.programmingproject;

import java.util.ArrayList;

public class Propietario extends Usuario{
    private String numapartamento;
    private boolean alDia;
    private ArrayList<Vehiculo> vehiculosRegistrados;

    public Propietario() {

    }

    public Propietario(int id, String nombre, String email, String contraseña, String tipo,String apartamento) {
        super(id, nombre, email, contraseña, tipo);
        this.numapartamento = apartamento;
        this.alDia = true;
        this.vehiculosRegistrados = new ArrayList<>();
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

    public ArrayList<Vehiculo> getVehiculosRegistrados() {
        return vehiculosRegistrados;
    }

    public void setVehiculosRegistrados(ArrayList<Vehiculo> vehiculosRegistrados) {
        this.vehiculosRegistrados = vehiculosRegistrados;
    }
    
    
}
