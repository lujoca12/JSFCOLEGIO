package Pojo;
// Generated 21-nov-2016 21:32:12 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Maestria generated by hbm2java
 */
public class Maestria  implements java.io.Serializable {


     private int id;
     private String descripcion;
     private Character estado;
     private Set promocions = new HashSet(0);
     private Set actas = new HashSet(0);

    public Maestria() {
    }

	
    public Maestria(int id) {
        this.id = id;
    }
    public Maestria(int id, String descripcion, Character estado, Set promocions, Set actas) {
       this.id = id;
       this.descripcion = descripcion;
       this.estado = estado;
       this.promocions = promocions;
       this.actas = actas;
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
    public Set getPromocions() {
        return this.promocions;
    }
    
    public void setPromocions(Set promocions) {
        this.promocions = promocions;
    }
    public Set getActas() {
        return this.actas;
    }
    
    public void setActas(Set actas) {
        this.actas = actas;
    }




}


