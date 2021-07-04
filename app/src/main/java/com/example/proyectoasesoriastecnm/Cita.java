package com.example.proyectoasesoriastecnm;

public class Cita {

    private String uid;
    private String email;
    private String profesor;
    private String materia;
    private String Horario;

    public Cita() {
    }

    public Cita(String email, String profesor, String materia, String horario) {
        this.email = email;
        this.profesor = profesor;
        this.materia = materia;
        Horario = horario;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

    @Override
    public String toString() {
        return "\nCita: \n" +
                "\nEmail: " + email + '\'' +
                "\nProfesor" + profesor + '\'' +
                "\nMateria" + materia + '\'' +
                "\nHorario" + Horario + '\'';
    }

}