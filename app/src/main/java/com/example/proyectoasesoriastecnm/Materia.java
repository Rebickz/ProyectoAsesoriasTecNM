package com.example.proyectoasesoriastecnm;

public class Materia {

    private String uid;
    private String nombre;
    private String departamento;
    private String carrera;
    private String semestre;
    private String horario;
    private String fecha;
    private String lugar;
    private String profesor;

    public Materia() {
    }

    public Materia(String nombre, String carrera, String semestre, String horario, String departamento, String fecha, String lugar, String profesor) {
        this.nombre = nombre;
        this.carrera = carrera;
        this.semestre = semestre;
        this.horario = horario;
        this.departamento = departamento;
        this.fecha = fecha;
        this.lugar = lugar;
        this.profesor = profesor;
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
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getProfesor() { return profesor; }

    public void setProfesor(String profesor) { this.profesor = profesor; }

    public String getDepartamento() { return departamento; }

    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getLugar() { return lugar; }

    public void setLugar(String lugar) { this.lugar = lugar; }


    @Override
    public String toString() {
        return "\nDatos Materia: \n" +
                "\nNombre: " + nombre + '\'' +
                "\nProfesor: " + profesor + '\'' +
                "\nSemestre: " + semestre + '\'' +
                "\nCarrera: " + carrera + '\'' +
                "\nDepartamento: " + departamento + '\'' +
                "\nFecha: " + fecha + '\'' +
                "\nLugar: " + lugar + '\'' +
                "\nHorario: " + horario + '\'';
    }
}