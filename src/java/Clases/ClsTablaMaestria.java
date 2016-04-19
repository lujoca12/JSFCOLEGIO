/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;

/**
 *
 * @author server
 */
public class ClsTablaMaestria implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int idMaestria;
    private String descripcionM;
    private char estado;

    public ClsTablaMaestria(int idMaestria, String descripcionM, char estado) {
        this.idMaestria = idMaestria;
        this.descripcionM = descripcionM;
        this.estado = estado;
    }

    public int getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(int idMaestria) {
        this.idMaestria = idMaestria;
    }

    public String getDescripcionM() {
        return descripcionM;
    }

    public void setDescripcionM(String descripcionM) {
        this.descripcionM = descripcionM;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
    
}