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

    public void registrarEntrada(Usuario usuario, String placa) {
        for (Vehiculo vehiculo : VehiculosAutorizados) {
            if(vehiculo.getPlaca().equals(placa)){
                if(vehiculo.isAutorizado()){
                    RegistroAcceso registro = new RegistroAcceso(registrosAcceso.size()+1, vehiculo, usuario);
                    registrosAcceso.add(registro);
                    System.out.println("Entrada registrada para el vehículo con placa: " + placa);
                    return;
                }
            }
            
        }
        System.out.println("El vehículo con placa: " + placa + " no está autorizado para ingresar");
        
    }

    public void registrarSalida(String placa) {
        for (RegistroAcceso registro : registrosAcceso) {
            if(registro.getVehiculo().getPlaca().equals(placa) && registro.getFechaHoraSalida() == null){  
                registro.registrarSalida();
                System.out.println("Salida registrada para el vehículo con placa: " + placa);
                return;
            }
        }
    }

    public void verVehiculosAutorizados() {
        for (Vehiculo vehiculo : VehiculosAutorizados) {
            System.out.println("----------------------------------------");
            vehiculo.mostrarDatos();
            System.out.println("----------------------------------------");
        }
    }
    
}
