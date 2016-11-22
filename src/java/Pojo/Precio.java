package Pojo;
// Generated 21-nov-2016 21:32:12 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * Precio generated by hbm2java
 */
public class Precio  implements java.io.Serializable {


     private int id;
     private Curso curso;
     private TipoPrecio tipoPrecio;
     private BigDecimal valor;
     private Date fechaInicio;
     private Date fechaFin;

    public Precio() {
    }

	
    public Precio(int id, Curso curso, TipoPrecio tipoPrecio) {
        this.id = id;
        this.curso = curso;
        this.tipoPrecio = tipoPrecio;
    }
    public Precio(int id, Curso curso, TipoPrecio tipoPrecio, BigDecimal valor, Date fechaInicio, Date fechaFin) {
       this.id = id;
       this.curso = curso;
       this.tipoPrecio = tipoPrecio;
       this.valor = valor;
       this.fechaInicio = fechaInicio;
       this.fechaFin = fechaFin;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Curso getCurso() {
        return this.curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public TipoPrecio getTipoPrecio() {
        return this.tipoPrecio;
    }
    
    public void setTipoPrecio(TipoPrecio tipoPrecio) {
        this.tipoPrecio = tipoPrecio;
    }
    public BigDecimal getValor() {
        return this.valor;
    }
    
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }




}


