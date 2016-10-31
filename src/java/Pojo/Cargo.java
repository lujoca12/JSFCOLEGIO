package Pojo;
// Generated 30-oct-2016 21:58:04 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cargo generated by hbm2java
 */
public class Cargo  implements java.io.Serializable {


     private int id;
     private String descripcion;
     private Character estado;
     private Set personas = new HashSet(0);

    public Cargo() {
    }

	
    public Cargo(int id) {
        this.id = id;
    }
    public Cargo(int id, String descripcion, Character estado, Set personas) {
       this.id = id;
       this.descripcion = descripcion;
       this.estado = estado;
       this.personas = personas;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Character getEstado() {
        return this.estado;
    }
    
    public void setEstado(Character estado) {
        this.estado = estado;
    }
    public Set getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Set personas) {
        this.personas = personas;
    }




}


