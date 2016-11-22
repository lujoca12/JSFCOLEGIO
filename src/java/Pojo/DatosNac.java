package Pojo;
// Generated 21-nov-2016 21:32:12 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * DatosNac generated by hbm2java
 */
public class DatosNac  implements java.io.Serializable {


     private int id;
     private Estudiante estudiante;
     private Parroquia parroquia;
     private Date fechaNac;

    public DatosNac() {
    }

	
    public DatosNac(int id) {
        this.id = id;
    }
    public DatosNac(int id, Estudiante estudiante, Parroquia parroquia, Date fechaNac) {
       this.id = id;
       this.estudiante = estudiante;
       this.parroquia = parroquia;
       this.fechaNac = fechaNac;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Estudiante getEstudiante() {
        return this.estudiante;
    }
    
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    public Parroquia getParroquia() {
        return this.parroquia;
    }
    
    public void setParroquia(Parroquia parroquia) {
        this.parroquia = parroquia;
    }
    public Date getFechaNac() {
        return this.fechaNac;
    }
    
    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }




}


