package com.mycompany.programmingproject;

import java.util.ArrayList;

public class Propietario extends Usuario{
    private String apartamento;
    private boolean alDia;
    private ArrayList<Vehiculo> vehiculosRegistrados;

    public Propietario() {

    }

    
    public Propietario(int id, String nombre, String email, String contraseña, String tipo,String apartamento) {
        super(id, nombre, email, contraseña, tipo);
        this.apartamento = apartamento;
        this.alDia = true;
        this.vehiculosRegistrados = new ArrayList<>();
    }
    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
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
