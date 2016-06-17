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
public class ClsTipoTribunal {
       private int idTipotribunal;
    private String tipotribunalD;
    private Character estadoD;

    public ClsTipoTribunal(int idTipotribunal, String tipotribunalD, Character estadoD) {
        this.idTipotribunal = idTipotribunal;
        this.tipotribunalD = tipotribunalD;
        this.estadoD = estadoD;
    }

    
    public int getIdTipotribunal() {
        return idTipotribunal;
    }

    public void setIdTipotribunal(int idTipotribunal) {
        this.idTipotribunal = idTipotribunal;
    }

    public String getTipotribunalD() {
        return tipotribunalD;
    }

    public void setTipotribunalD(String tipotribunalD) {
        this.tipotribunalD = tipotribunalD;
    }

    public Character getEstadoD() {
        return estadoD;
    }

    public void setEstadoD(Character estadoD) {
        this.estadoD = estadoD;
    }
    
    
}
