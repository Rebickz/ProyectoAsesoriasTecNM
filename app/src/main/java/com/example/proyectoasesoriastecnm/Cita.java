package com.example.proyectoasesoriastecnm;

public class Cita {

    private String uid;
    private String carrera;
    private String email;
    private String fecha;
    private String horario;
    private String lugar;
    private String materia;
    private String profesor;
    private String semestre;
    private String status;

    public Cita() {
    }

    public Cita(String carrera  , String email, String fecha, String horario, String lugar, String materia,String profesor, String semestre, String status) {
        this.carrera = carrera;
        this.email = email;
        this.fecha = fecha;
        this.horario = horario;
        this.lugar = lugar;
        this.materia = materia;
        this.profesor = profesor;
        this.semestre = semestre;
        this.status = status;
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
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getCarrera() { return carrera; }

    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getLugar() { return lugar; }

    public void setLugar(String lugar) { this.lugar = lugar; }

    public String getSemestre() { return semestre; }

    public void setSemestre(String semestre) { this.semestre = semestre; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "\nCita: \n" +
                "\nCarrera: " + carrera + '\'' +
                "\nEmail: " + email + '\'' +
                "\nFecha: " + fecha + '\'' +
                "\nHorario: " + horario + '\'' +
                "\nLugar: " + lugar + '\'' +
                "\nMateria" + materia + '\'' +
                "\nProfesor" + profesor + '\'' +
                "\nSemestre: " + semestre + '\'' +
                "\nStatus" + status + '\'';
    }

}