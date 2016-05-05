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
import Dao.DaoTesis;
import Pojo.*;

/**
 *
 * @author SERVER
 */
@Named(value = "mbVPalabrasClave")
@ViewScoped
public class MbVPalabrasClave implements Serializable{
    
    
    private String palabra = "";
    private String descripcion = "";
    private List<String> lstTbPalabras;

    /**
     * Creates a new instance of MbVPalabrasClave
     */
    public MbVPalabrasClave() {
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public List<String> getLstTbPalabras() {
        return lstTbPalabras;
    }

    public void setLstTbPalabras(List<String> lstTbPalabras) {
        this.lstTbPalabras = lstTbPalabras;
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
           DaoTesis daotesis = new DaoTesis();
           List<Tesis> lsttesis = daotesis.getTesisPalabrasclaves(this.palabra);
           if(lsttesis.size() > 0){
               for(Tesis tesis : lsttesis){
                   
               }
           }
           
           
       }
       catch (Exception ex){
       }
                
    }
    
}
