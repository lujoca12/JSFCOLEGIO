/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author Postgrago
 */
@ManagedBean
public class ValidadorFecha implements Serializable{

    /**
     * Creates a new instance of ValidadorFecha
     * @return 
     */
    private String fechaMax;

    public String getFechaMax() {
        Calendar fecha = Calendar.getInstance();                
        fechaMax ="01/01/"+Integer.toString(fecha.get(Calendar.YEAR)-20);  
        return fechaMax;
    }

    public void setFechaMax(String fechaMax) {
        this.fechaMax = fechaMax;
    }

    
    
}
