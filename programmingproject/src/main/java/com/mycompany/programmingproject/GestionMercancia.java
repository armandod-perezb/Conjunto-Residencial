package com.mycompany.programmingproject;

import java.util.ArrayList;
import java.util.List;

public class GestionMercancia {
    private List<Mercancia> mercancias;
    private static GestionMercancia instancia;

    private GestionMercancia(List<Mercancia> mercancias) {
        this.mercancias = mercancias;
    }

    public List<Mercancia> getMercancias() {
        return mercancias;
    }

    public void setMercancias(List<Mercancia> mercancias) {
        this.mercancias = mercancias;
    }
    
    public static GestionMercancia ObtenerInstancia(List<Mercancia> mercancias){
        if(instancia == null){
            instancia = new GestionMercancia(mercancias);
        }
        return instancia;
    }
    public Mercancia registrarLlegada(String descripcion, Propietario destinatario) {
        Mercancia mercancia = new Mercancia(mercancias.size()+1, descripcion, destinatario);
        mercancias.add(mercancia);
        return mercancia;
    }

    public void obtenerMercanciasPendientes(Propietario propietario) {
        for (Mercancia mercancia : mercancias) {
            if(mercancia.equals(propietario) && !mercancia.isEntregada()){
                System.out.println(mercancia);
            }
        }
    }
}
