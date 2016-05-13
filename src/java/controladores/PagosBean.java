/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author chiti
 */
@Named(value = "pagosBean")
@ViewScoped
public class PagosBean implements Serializable  {

    private String cedula;
    /**
     * Creates a new instance of PagosBean
     */
    public PagosBean() {
    }
    
}
