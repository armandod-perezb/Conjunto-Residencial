package com.mycompany.programmingproject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestionPagos {
    private ArrayList<Pago> pagos;
    private static GestionPagos instancia;

    private GestionPagos(ArrayList<Pago> pagos) {
        this.pagos = pagos;
    }

    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<Pago> pagos) {
        this.pagos = pagos;
    }

    public static GestionPagos ObtenerInstancia(ArrayList<Pago> pagos){
        if(instancia==null){
            instancia = new GestionPagos(pagos);
        }
        return instancia;
    }
    public Pago registrarPago(Propietario propietario, double monto, String concepto) {
        Pago pago = new Pago(pagos.size()+1, propietario, monto, concepto);
        pagos.add(pago);
        return pago;
    }

    public List<Pago> obtenerPagosPropietario(Propietario propietario) {
        return pagos.stream()
                .filter(p -> p.getPropietario().equals(propietario))
                .collect(Collectors.toList());
    }
}
