package Pojo;
// Generated 21-nov-2016 21:32:12 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Persona generated by hbm2java
 */
public class Persona  implements java.io.Serializable {


     private int id;
     private Cargo cargo;
     private String cedula;
     private String nombres;
     private String apellidos;
     private String titulo;
     private Set tribunals = new HashSet(0);

    public Persona() {
    }

	
    public Persona(int id, Cargo cargo) {
        this.id = id;
        this.cargo = cargo;
    }
    public Persona(int id, Cargo cargo, String cedula, String nombres, String apellidos, String titulo, Set tribunals) {
       this.id = id;
       this.cargo = cargo;
       this.cedula = cedula;
       this.nombres = nombres;
       this.apellidos = apellidos;
       this.titulo = titulo;
       this.tribunals = tribunals;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Cargo getCargo() {
        return this.cargo;
    }
    
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    public String getCedula() {
        return this.cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombres() {
        return this.nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getTitulo() {
        return this.titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Set getTribunals() {
        return this.tribunals;
    }
    
    public void setTribunals(Set tribunals) {
        this.tribunals = tribunals;
    }




}


