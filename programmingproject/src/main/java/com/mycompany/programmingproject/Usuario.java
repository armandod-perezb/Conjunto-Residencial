package com.mycompany.programmingproject;

import java.util.List;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String contraseña;
    private String tipo;
   
    public Usuario(int id, String nombre, String email, String contraseña, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.tipo = tipo;
    }

    public Usuario() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int hallarIndex(List<Usuario> usuarios, int id){
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getId()==id){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
