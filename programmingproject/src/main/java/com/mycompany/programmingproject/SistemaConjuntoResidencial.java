package com.mycompany.programmingproject;

public class SistemaConjuntoResidencial {
    private ControlAcceso controlacceso;
    private GestionIncidentes gestionincidentes;
    private GestionPagos gestionpago;
    private GestionMercancia gestionmercancia;

    public SistemaConjuntoResidencial(ControlAcceso controlacceso, GestionIncidentes gestionincidentes, GestionPagos gestionpago, GestionMercancia gestionmercancia) {
        this.controlacceso = controlacceso;
        this.gestionincidentes = gestionincidentes;
        this.gestionpago = gestionpago;
        this.gestionmercancia = gestionmercancia;
    }

    public ControlAcceso getControlacceso() {
        return controlacceso;
    }

    public void setControlacceso(ControlAcceso controlacceso) {
        this.controlacceso = controlacceso;
    }

    public GestionIncidentes getGestionincidentes() {
        return gestionincidentes;
    }

    public void setGestionincidentes(GestionIncidentes gestionincidentes) {
        this.gestionincidentes = gestionincidentes;
    }

    public GestionPagos getGestionpago() {
        return gestionpago;
    }

    public void setGestionpago(GestionPagos gestionpago) {
        this.gestionpago = gestionpago;
    }

    public GestionMercancia getGestionmercancia() {
        return gestionmercancia;
    }

    public void setGestionmercancia(GestionMercancia gestionmercancia) {
        this.gestionmercancia = gestionmercancia;
    }
    
    
}
