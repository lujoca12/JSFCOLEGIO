package Pojo;
// Generated 16-may-2016 9:34:07 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Persona generated by hbm2java
 */
public class Persona  implements java.io.Serializable {


     private int id;
     private String nombres;
     private String cargo;
     private Set tribunals = new HashSet(0);

    public Persona() {
    }

	
    public Persona(int id) {
        this.id = id;
    }
    public Persona(int id, String nombres, String cargo, Set tribunals) {
       this.id = id;
       this.nombres = nombres;
       this.cargo = cargo;
       this.tribunals = tribunals;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombres() {
        return this.nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    public String getCargo() {
        return this.cargo;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public Set getTribunals() {
        return this.tribunals;
    }
    
    public void setTribunals(Set tribunals) {
        this.tribunals = tribunals;
    }

}


