package com.example.proyectoasesoriastecnm;

public class Mensaje {

    private String email;
    private String uid;
    private String mensaje;

    public Mensaje() {
    }

    public Mensaje(String email, String uid, String mensaje) {
        this.email = email;
        this.uid = uid;
        this.mensaje = mensaje;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
