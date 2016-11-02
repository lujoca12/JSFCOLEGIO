package Pojo;
// Generated 02-nov-2016 10:39:37 by Hibernate Tools 4.3.1



/**
 * Archivos generated by hbm2java
 */
public class Archivos  implements java.io.Serializable {


     private int id;
     private RequisitosPromo requisitosPromo;
     private SolicitudInscripcion solicitudInscripcion;
     private String ruta;

    public Archivos() {
    }

	
    public Archivos(int id, RequisitosPromo requisitosPromo, SolicitudInscripcion solicitudInscripcion) {
        this.id = id;
        this.requisitosPromo = requisitosPromo;
        this.solicitudInscripcion = solicitudInscripcion;
    }
    public Archivos(int id, RequisitosPromo requisitosPromo, SolicitudInscripcion solicitudInscripcion, String ruta) {
       this.id = id;
       this.requisitosPromo = requisitosPromo;
       this.solicitudInscripcion = solicitudInscripcion;
       this.ruta = ruta;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public RequisitosPromo getRequisitosPromo() {
        return this.requisitosPromo;
    }
    
    public void setRequisitosPromo(RequisitosPromo requisitosPromo) {
        this.requisitosPromo = requisitosPromo;
    }
    public SolicitudInscripcion getSolicitudInscripcion() {
        return this.solicitudInscripcion;
    }
    
    public void setSolicitudInscripcion(SolicitudInscripcion solicitudInscripcion) {
        this.solicitudInscripcion = solicitudInscripcion;
    }
    public String getRuta() {
        return this.ruta;
    }
    
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }




}


