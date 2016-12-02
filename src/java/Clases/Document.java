package Clases;

import java.io.Serializable;
 
public class Document implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
        
        private int id;
	private String name;
        private int padre;
        private String form;
        private int orden;
        private char estado;

    public Document(int id, String name, int padre, String form, int orden, char estado) {
        this.id = id;
        this.name = name;
        this.padre = padre;
        this.form = form;
        this.orden = orden;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPadre() {
        return padre;
    }

    public void setPadre(int padre) {
        this.padre = padre;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
    
    
    @Override
    public String toString() {
        return name;
    }
}  