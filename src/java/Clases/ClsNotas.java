/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author server
 */
public class ClsNotas implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idEstudiante;
    private String nombresEstudiante;
    private int idMatricula;
    private String n_matricula;
    private Date fechaMatricula;
    private int idPromocion;
    private String periodo;
    private Date resolucion;
    private int idMaestria;
    private String descripMaestria;
    private int n_registro;
    private String nota;
    private String observacion;
    private Date fecha;
    private boolean estado;
    private int idNota;

    public ClsNotas(int idEstudiante, String nombresEstudiante, int idMatricula, String n_matricula, Date fechaMatricula, int idPromocion, String periodo, Date resolucion, int idMaestria, String descripMaestria, int n_registro, String nota, String observacion, Date fecha, boolean estado, int idNota) {
        this.idEstudiante = idEstudiante;
        this.nombresEstudiante = nombresEstudiante;
        this.idMatricula = idMatricula;
        this.n_matricula = n_matricula;
        this.fechaMatricula = fechaMatricula;
        this.idPromocion = idPromocion;
        this.periodo = periodo;
        this.resolucion = resolucion;
        this.idMaestria = idMaestria;
        this.descripMaestria = descripMaestria;
        this.n_registro = n_registro;
        this.nota = nota;
        this.observacion = observacion;
        this.fecha = fecha;
        this.estado = estado;
        this.idNota = idNota;
    }

    

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombresEstudiante() {
        return nombresEstudiante;
    }

    public void setNombresEstudiante(String nombresEstudiante) {
        this.nombresEstudiante = nombresEstudiante;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getN_matricula() {
        return n_matricula;
    }

    public void setN_matricula(String n_matricula) {
        this.n_matricula = n_matricula;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Date getResolucion() {
        return resolucion;
    }

    public void setResolucion(Date resolucion) {
        this.resolucion = resolucion;
    }

    public int getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(int idMaestria) {
        this.idMaestria = idMaestria;
    }

    public String getDescripMaestria() {
        return descripMaestria;
    }

    public void setDescripMaestria(String descripMaestria) {
        this.descripMaestria = descripMaestria;
    }

    public int getN_registro() {
        return n_registro;
    }

    public void setN_registro(int n_registro) {
        this.n_registro = n_registro;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }
    
}
