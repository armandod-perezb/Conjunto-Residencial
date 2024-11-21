package com.mycompany.programmingproject;

import java.util.ArrayList;

public class ControlAcceso {
    private ArrayList<Vehiculo> VehiculosAutorizados;
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
    
    public void agregarVehiculoAutorizado(Vehiculo vehiculo){
        boolean registrado = false;
        for (Vehiculo vehiculos : VehiculosAutorizados) {
            if(vehiculos.getPlaca().equals(vehiculo.getPlaca())){
                registrado = true;
                return;
            }
        }
        if (registrado) {
            System.out.println("El vehiculo ya se encuentra registrado");
        }else{
            VehiculosAutorizados.add(vehiculo);
        }
    }

    public void registrarEntrada() {
        for (Vehiculo vehiculo2 : VehiculosAutorizados) {
            System.out.println("Placa: "+vehiculo2.getPlaca()+" propietario: "+ vehiculo2.getPropietario().getNombre());
        }
        System.out.println("Digite la placa del vehiculo que desea registrar");
        String placa = Utilidades.registrarstring("Digite la placa del vehiculo");
    }

    public void registrarSalida(int id) {
        
    }
}
