/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author SERVER
 */
public class ClsCargo {
     private static final long serialVersionUID = 1L;
    private int idCargo;
    private String descripcionC;
    private char estado;

    public ClsCargo(int idCargo, String descripcionC, char estado) {
        this.idCargo = idCargo;
        this.descripcionC = descripcionC;
        this.estado = estado;
    }

    
    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getDescripcionC() {
        return descripcionC;
    }

    public void setDescripcionC(String descripcionC) {
        this.descripcionC = descripcionC;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
    
    
    
    
    
}
