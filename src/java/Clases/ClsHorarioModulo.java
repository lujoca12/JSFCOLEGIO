/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author server
 */
public class ClsHorarioModulo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int idMaestria;
    private String Maestria;
    private int idModulo;
    private String moduloNombre;
    private String creditos;
    private Date fechaInicio;
    private Date fechaFin;
    private String nModulo;
    private String totalHoras;
    private int idHorario;
    private Date horaInicio;
    private Date horaFin;
    private Date fechaHorario;
    private Character estadoHorario;

    public ClsHorarioModulo(int idMaestria, String Maestria, int idModulo, String moduloNombre, String creditos, Date fechaInicio, Date fechaFin, String nModulo, String totalHoras, int idHorario, Date horaInicio, Date horaFin, Date fechaHorario, Character estadoHorario) {
        this.idMaestria = idMaestria;
        this.Maestria = Maestria;
        this.idModulo = idModulo;
        this.moduloNombre = moduloNombre;
        this.creditos = creditos;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nModulo = nModulo;
        this.totalHoras = totalHoras;
        this.idHorario = idHorario;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaHorario = fechaHorario;
        this.estadoHorario = estadoHorario;
    }

    public int getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(int idMaestria) {
        this.idMaestria = idMaestria;
    }

    public String getMaestria() {
        return Maestria;
    }

    public void setMaestria(String Maestria) {
        this.Maestria = Maestria;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getModuloNombre() {
        return moduloNombre;
    }

    public void setModuloNombre(String moduloNombre) {
        this.moduloNombre = moduloNombre;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getnModulo() {
        return nModulo;
    }

    public void setnModulo(String nModulo) {
        this.nModulo = nModulo;
    }

    public String getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(String totalHoras) {
        this.totalHoras = totalHoras;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Date getFechaHorario() {
        return fechaHorario;
    }

    public void setFechaHorario(Date fechaHorario) {
        this.fechaHorario = fechaHorario;
    }

    public Character getEstadoHorario() {
        return estadoHorario;
    }

    public void setEstadoHorario(Character estadoHorario) {
        this.estadoHorario = estadoHorario;
    }
}
