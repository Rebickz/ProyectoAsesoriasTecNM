package com.example.proyectoasesoriastecnm;

public class Usuario {

    private String uid;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String rol;

    public Usuario() {
    }

    public Usuario(String name,String lastName, String email, String password, String rol, String uid){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "\nUsuario\n" +
                "\nNombre:" + name + '\'' +
                "\nCorreo: " + email + '\'' +
                "\nRol: " + rol + '\'';
    }
}
