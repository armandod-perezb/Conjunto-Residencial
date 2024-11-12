package com.mycompany.programmingproject;

import java.util.ArrayList;
import java.util.List;


public class GestionMercancia {
    private ArrayList<Mercancia> mercancias;

    public GestionMercancia(ArrayList<Mercancia> mercancias) {
        this.mercancias = mercancias;
    }

    public ArrayList<Mercancia> getMercancias() {
        return mercancias;
    }

    public void setMercancias(ArrayList<Mercancia> mercancias) {
        this.mercancias = mercancias;
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
