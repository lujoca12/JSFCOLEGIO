/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author server
 */
public class ClsMaterias {
    private int id;    
    private String displayName; 
    private String name;
    private Character estado;

    public ClsMaterias(int id, String displayName, String name, Character estado) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character isEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }
}
