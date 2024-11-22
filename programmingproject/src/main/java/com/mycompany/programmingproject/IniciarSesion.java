package com.mycompany.programmingproject;

public class IniciarSesion {
    private static IniciarSesion instancia;
    private Usuario usuarioActual;

    private IniciarSesion() {}

    public static IniciarSesion getInstancia() {
        if (instancia == null) {
            instancia = new IniciarSesion();
        }
        return instancia;
    }

    public void iniciarSesion(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }
}

