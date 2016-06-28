/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author server
 */
public class ClsFechaHoras implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idHorario;
    private BigDecimal horas;
    private Date fecha;
    private Character estado;

    public ClsFechaHoras(int idHorario,BigDecimal horas, Date fecha, Character estado) {
        this.idHorario = idHorario;
        this.horas = horas;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }
    
    public BigDecimal getHoras() {
        return horas;
    }

    public void setHoras(BigDecimal horas) {
        this.horas = horas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }
    
    
    
}
