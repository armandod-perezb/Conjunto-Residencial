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

    public void mostrarVehiculos(){
        if (vehiculosRegistrados == null) {
            System.out.println("No hay vehiculos registrados");
        }else{
            for (Vehiculo vehiculo : vehiculosRegistrados) {
                System.out.println(vehiculo);
            }
        }
    }

    public void mostrarMercancias(){
        if (mercanciasRegistradas == null) {
            System.out.println("No hay mercancias registradas");
        }else{
            for (Mercancia mercancia : mercanciasRegistradas) {
                System.out.println(mercancia);
            }
        }
    }

    public void efectuarPagos(){
        if(Propietario.this.isAlDia()){
            System.out.println("El propietario esta al dia. No hay pagos pendientes");
            return;

        }
        GestionPagos pagos = GestionPagos.ObtenerInstancia(new ArrayList<>());
        for (Pago pago : pagos.obtenerPagosPropietario(this)) {
            if (pago.getEstado().equals("pendiente")) {
                System.out.println("Usted tiene pago pendiente de $"+pago.getMonto()+" por concepto de "+pago.getConcepto());
                double monto = Utilidades.registrardouble("Ingrese el monto del pago:");
                String mediodepago = Utilidades.registrarstring("Ingrese el medio de pago: ");
                if(monto >= pago.getMonto()){
                    pago.setEstado("efectuado");
                    pago.setMediodepago(mediodepago);
                    System.out.println("Pago realizado con éxito");
                }else{
                    System.out.println("No se pudo realizar el pago debido a que no se canceló el monto completo. Intente de nuevo con el monto correcto");
                }
               
            }
        }
    }

    @Override
    public String toString() {
        return "Id de propietario " + getId() + '\n' +
                "Número de apartamento: '" + numapartamento + '\n' +
                "Al día: " + alDia +
                "Vehiculos registrados: " + vehiculosRegistrados.size() + '\n' +
                "Mercancias registradas: " + mercanciasRegistradas.size() + '\n' +
                '}';
    }
}
