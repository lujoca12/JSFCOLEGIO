/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author SERVER
 */
public class ClsMatricula {
    private int id;
    private Date fechaMatricula;
    private int dsc_colegiatura;
    private char estado;
    private int id_solicitud;
    private String n_matricula;
    private Date fechagraduacion;

    public ClsMatricula(int id, char estado) {
        this.id = id;
        this.estado = estado;
    }
    
    
    

    public ClsMatricula(int id, Date fechaMatricula, char estado, String n_matricula, Date fechagraduacion) {
        this.id = id;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
        this.n_matricula = n_matricula;
        this.fechagraduacion = fechagraduacion;
    }

    
    public ClsMatricula(int id, Date fechaMatricula, int dsc_colegiatura, char estado, int id_solicitud, String n_matricula, Date fechagraduacion) {
        this.id = id;
        this.fechaMatricula = fechaMatricula;
        this.dsc_colegiatura = dsc_colegiatura;
        this.estado = estado;
        this.id_solicitud = id_solicitud;
        this.n_matricula = n_matricula;
        this.fechagraduacion = fechagraduacion;
    }

    public ClsMatricula() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public int getDsc_colegiatura() {
        return dsc_colegiatura;
    }

    public void setDsc_colegiatura(int dsc_colegiatura) {
        this.dsc_colegiatura = dsc_colegiatura;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public String getN_matricula() {
        return n_matricula;
    }

    public void setN_matricula(String n_matricula) {
        this.n_matricula = n_matricula;
    }

    public Date getFechagraduacion() {
        return fechagraduacion;
    }

    public void setFechagraduacion(Date fechagraduacion) {
        this.fechagraduacion = fechagraduacion;
    }
    
    
}
