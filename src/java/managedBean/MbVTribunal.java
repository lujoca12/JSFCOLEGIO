/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;
import Pojo.*;
import Clases.*;
import Dao.*;
import java.util.ArrayList;
import java.util.List;


import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author SERVER
 */
@Named(value = "mbVTribunal")
@Dependent
public class MbVTribunal {
    private ClsTipoTribunal clstitotribunal;     
     private List<ClsTipoTribunal> lstttipotribunal;  

    /**
     * Creates a new instance of MbVTribunal
     */
    public MbVTribunal() {
        llenarCboTipoTribunal();
    }

    public ClsTipoTribunal getClstitotribunal() {
        return clstitotribunal;
    }
    public void setClstitotribunal(ClsTipoTribunal clstitotribunal) {
        this.clstitotribunal = clstitotribunal;
    }
    public List<ClsTipoTribunal> getLstttipotribunal() {
        return lstttipotribunal;
    }
    public void setLstttipotribunal(List<ClsTipoTribunal> lstttipotribunal) {
        this.lstttipotribunal = lstttipotribunal;
    }    

    
    public void llenarCboTipoTribunal(){
        this.lstttipotribunal = new ArrayList<ClsTipoTribunal>();
         try {
            DaoTribunal daoTusuario = new DaoTribunal();
            
            List<TipoTribunal> lsttipo = daoTusuario.getTodastipoTribunal();
            this.lstttipotribunal.clear();
            this.lstttipotribunal.add(new ClsTipoTribunal(-1,
                    "(Seleccione)",
                    '-'));
            for(TipoTribunal tt: lsttipo){
                this.lstttipotribunal.add(new ClsTipoTribunal(tt.getId(),
                        tt.getDescripcion(), 
                        tt.getEstado()));
            }
        } catch (Exception ex) {
            
        }
    }
    
}
