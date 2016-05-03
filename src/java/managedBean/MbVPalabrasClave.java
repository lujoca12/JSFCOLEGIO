/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author SERVER
 */
@Named(value = "mbVPalabrasClave")
@ViewScoped
public class MbVPalabrasClave implements Serializable{
    
    
    private String palabra = "";
    private List<String> lstTbPalabras;

    /**
     * Creates a new instance of MbVPalabrasClave
     */
    public MbVPalabrasClave() {
    }
    
    public void consultarTesis() {
        if (this.palabra == "" && this.palabra !=null) {
        } else {
            cargarTblTesis();
        }
    }
    
    public void cargarTblTesis(){
       lstTbPalabras = new ArrayList<>();
       try{
           lstTbPalabras.clear();
           
           
       }
       catch (Exception ex){
       }
                
    }
    
}
