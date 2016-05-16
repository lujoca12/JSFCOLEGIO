package Pojo;
// Generated 16-may-2016 9:34:07 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * Tribunal generated by hbm2java
 */
public class Tribunal  implements java.io.Serializable {


     private int id;
     private Persona persona;
     private Sustentacion sustentacion;
     private TipoTribunal tipoTribunal;
     private BigDecimal nota;

    public Tribunal() {
    }

	
    public Tribunal(int id) {
        this.id = id;
    }
    public Tribunal(int id, Persona persona, Sustentacion sustentacion, TipoTribunal tipoTribunal, BigDecimal nota) {
       this.id = id;
       this.persona = persona;
       this.sustentacion = sustentacion;
       this.tipoTribunal = tipoTribunal;
       this.nota = nota;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public Sustentacion getSustentacion() {
        return this.sustentacion;
    }
    
    public void setSustentacion(Sustentacion sustentacion) {
        this.sustentacion = sustentacion;
    }
    public TipoTribunal getTipoTribunal() {
        return this.tipoTribunal;
    }
    
    public void setTipoTribunal(TipoTribunal tipoTribunal) {
        this.tipoTribunal = tipoTribunal;
    }
    public BigDecimal getNota() {
        return this.nota;
    }
    
    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }




}


