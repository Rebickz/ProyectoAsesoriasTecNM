package com.example.proyectoasesoriastecnm;

public class Materia {

    private String uid;
    private String nombre;
    private String carrera;
    private String semestre;
    private String Horario;

    public Materia() {
    }

    public Materia(String nombre, String carrera, String semestre, String horario) {
        this.nombre = nombre;
        this.carrera = carrera;
        this.semestre = semestre;
        Horario = horario;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

    @Override
    public String toString() {
        return "\nDatos Materia: \n" +
                "\nNombre: " + nombre + '\'' +
                "\nSemestre: " + semestre + '\'' +
                "\nCarrera: " + carrera + '\'' +
                "\nDepartamento: " + "Ciencias básicas " + '\'' +
                "\nLugar: " + "Aula 45 " + '\'' +
                "\nHorario: " + Horario + '\'';
    }
}