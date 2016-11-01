package Pojo;
// Generated 30-oct-2016 21:58:04 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Seccion generated by hbm2java
 */
public class Seccion  implements java.io.Serializable {


     private int id;
     private Modalidad modalidad;
     private String descripcion;
     private Character estado;
     private Set cursos = new HashSet(0);

    public Seccion() {
    }

	
    public Seccion(int id, Modalidad modalidad) {
        this.id = id;
        this.modalidad = modalidad;
    }
    public Seccion(int id, Modalidad modalidad, String descripcion, Character estado, Set cursos) {
       this.id = id;
       this.modalidad = modalidad;
       this.descripcion = descripcion;
       this.estado = estado;
       this.cursos = cursos;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Modalidad getModalidad() {
        return this.modalidad;
    }
    
    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set getCursos() {
        return this.cursos;
    }
    
    public void setCursos(Set cursos) {
        this.cursos = cursos;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }
    



}


