package com.mycompany.programmingproject;

import java.util.ArrayList;

public class ControlAcceso {
    private ArrayList<RegistroAcceso> registrosAcceso;
    private static ControlAcceso instancia;

    public ControlAcceso(ArrayList<RegistroAcceso> registrosAcceso) {
        this.registrosAcceso = registrosAcceso;
    }

    public ArrayList<RegistroAcceso> getRegistrosAcceso() {
        return registrosAcceso;
    }

    public void setRegistrosAcceso(ArrayList<RegistroAcceso> registrosAcceso) {
        this.registrosAcceso = registrosAcceso;
    }
    
    public static ControlAcceso ObtenerInstancia(ArrayList<RegistroAcceso> registrosAcceso){
        if(instancia==null){
            instancia = new ControlAcceso(registrosAcceso);
        }
        return instancia;
    }
    public void registrarSalida(int id) {
        for (RegistroAcceso registroAcceso : registrosAcceso) {
            if(registroAcceso.getId() == id){
                registroAcceso.registrarSalida();
            }
        }
    }
}
