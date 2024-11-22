package com.mycompany.programmingproject;

import java.util.List;

public class ControlAcceso {
    private List<Vehiculo> VehiculosAutorizados;
    private List<RegistroAcceso> registrosAcceso;
    private static ControlAcceso instancia;

    private ControlAcceso(List<RegistroAcceso> registrosAcceso, List<Vehiculo> VehiculosAutorizados) {
        this.registrosAcceso = registrosAcceso;
        this.VehiculosAutorizados = VehiculosAutorizados;
    }

    public List<RegistroAcceso> getRegistrosAcceso() {
        return registrosAcceso;
    }

    public void setRegistrosAcceso(List<RegistroAcceso> registrosAcceso) {
        this.registrosAcceso = registrosAcceso;
    }
    
    public static ControlAcceso ObtenerInstancia(List<RegistroAcceso> registrosAcceso, List<Vehiculo> VehiculosAutorizados){
        if(instancia==null){
            instancia = new ControlAcceso(registrosAcceso, VehiculosAutorizados);
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
