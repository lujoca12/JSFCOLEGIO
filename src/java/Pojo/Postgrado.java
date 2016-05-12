/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

/**
 *
 * @author chiti
 */
public class Postgrado {
    
    private int id;
    private String email;
    private String claveEmail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClaveEmail() {
        return claveEmail;
    }

    public void setClaveEmail(String claveEmail) {
        this.claveEmail = claveEmail;
    }

    public Postgrado(int id, String email, String clave) {
        this.id = id;
        this.email = email;
        this.claveEmail = clave;
    }

    

    public Postgrado() {
    }
    
    
    
}
