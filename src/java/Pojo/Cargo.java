/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Seeting
 */
public class Cargo implements Serializable {

    private int id;
    private String descripcion;
    private Character estado;
    private Set personas = new HashSet(0);

    public Cargo() {
    }

    public Cargo(int id) {
        this.id = id;
    }

    public Cargo(int id, String descripcion, Character estado,Set personas) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.personas = personas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Set getPersonas() {
        return personas;
    }

    public void setPersonas(Set personas) {
        this.personas = personas;
    }
    
    

}
