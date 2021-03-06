/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Dao.postgradoDao;
import Pojo.Postgrado;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author chiti
 */
@Named(value = "emailBean")
@ViewScoped
public class emailBean implements Serializable {

    /**
     * Creates a new instance of emailBean
     */
    private String email;
    private String clave;
    private postgradoDao pDao = new postgradoDao();
    private Postgrado p = new Postgrado();
    private int numMatricula;

    public int getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(int numMatricula) {
        this.numMatricula = numMatricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public emailBean() {
    }

    @PostConstruct
    public void init() {
        try {
            p = pDao.getPostgrado();
            if (!"".equals(p.getEmail())) {
                email = p.getEmail();
                clave = p.getClaveEmail();
                numMatricula = p.getNumMatricula();
                FacesMessage message = new FacesMessage("Succesful", p.getEmail());
            FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                p = new Postgrado();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void guardar() {
        try {

            p.setEmail(email);
            p.setClaveEmail(clave);
            p.setNumMatricula(numMatricula);
            pDao.insertar(p);
            FacesMessage message = new FacesMessage("Succesful", "Datos guardados");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage("Erorr", "Error al guardarlos datos ");
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println(ex.toString());
        }
    }

    

}
