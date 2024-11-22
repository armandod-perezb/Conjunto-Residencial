package com.mycompany.programmingproject;

import java.util.ArrayList;
import java.util.List;

public class GestionPagos {
    private List<Pago> pagos;
    private static GestionPagos instancia;

    private GestionPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public static GestionPagos ObtenerInstancia(List<Pago> pagos){
        if(instancia==null){
            instancia = new GestionPagos(pagos);
        }
        return instancia;
    }
    public Pago registrarPago(Propietario propietario, String mediodepago, double monto, String concepto) {
        Pago pago = new Pago(pagos.size()+1, mediodepago, propietario, monto, concepto);
        pagos.add(pago);
        return pago;
    }

    public List<Pago> obtenerPagosPropietario(Propietario propietario) {
        List<Pago> pagosPropietario = new ArrayList<>();
        for (Pago pago : pagos) {
            if (pago.getPropietario().equals(propietario)) {
                pagosPropietario.add(pago);
            }
        }
        return pagosPropietario;
    }
}
