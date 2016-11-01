/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTMaestrias;
import Dao.DaoTMaterias;
import Pojo.Materias;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author server
 */
@Named(value = "mbVMaterias")
@ViewScoped
public class MbVMaterias implements Serializable{

    /**
     * Creates a new instance of MbVMaterias
     */
    private Materias tMaterias;
    private boolean msg = false;
    private boolean mostrarEliminados;
    private List<Materias> lstMaterias;
    public MbVMaterias() {
        tMaterias = new Materias();
        cargarTablaMaterias();
    }
    public void cargarTablaMaterias(){
        
        try {
            lstMaterias = new ArrayList<>();
            DaoTMaterias daoMaterias = new DaoTMaterias();
            lstMaterias = daoMaterias.getTodasMaterias();
            
        } catch (Exception ex) {
            Logger.getLogger(MbVMaterias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void mensajesOk(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    private void mensajesError(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void registrar(){
        DaoTMaterias daoMaterias = new DaoTMaterias();
        boolean repetida = false;
        boolean band = false;
        try {
            BigDecimal bigdec;
            bigdec = new BigDecimal(0);
            tMaterias.setEstado('1');
            tMaterias.setCreditos(bigdec);
            repetida = daoMaterias.existe(tMaterias);
            if (!repetida) {
                msg = daoMaterias.registrar(tMaterias);
            }else{
                mensajesError("Registro repetido");
                cargarTablaMaterias();
                return;
            }
            
            if (msg) {
                mensajesOk("Datos procesados correctamente");
                vaciarCajas();
                cargarTablaMaterias();    
            } else {
                mensajesError("Error al procesar datos");
            }
        

        } catch (Exception e) {
            vaciarCajas();
        }
        
    }
    private void vaciarCajas(){
        tMaterias = new Materias();
        
    }
    public void onRowEdit(RowEditEvent event) {
        boolean repetida = false;
        DaoTMaterias daoMaterias = new DaoTMaterias();
        Materias materias = new Materias();
        
        try {
            materias = (Materias) event.getObject();
            
            repetida = daoMaterias.existe(materias);
            if (!repetida) {
                 msg = daoMaterias.registrar(materias);
            }else{
                mensajesError("Registro repetido");
                //cargarTablaMaterias();
                return;
            }
            if (msg) {
                mensajesOk("Datos procesados correctamente");
                   
            } else {
                mensajesError("Error al procesar datos");
            }
            cargarTablaMaterias(); 
        } catch (Exception ex) {
            cargarTablaMaterias(); 
            Logger.getLogger(MbVMaterias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void onRowCancel(RowEditEvent event) {
        
        
    }

    public List<Materias> getLstMaterias() {
        return lstMaterias;
    }

    public void setLstMaterias(List<Materias> lstMaterias) {
        this.lstMaterias = lstMaterias;
    }
    
    public Materias gettMaterias() {
        return tMaterias;
    }

    public void settMaterias(Materias tMaterias) {
        this.tMaterias = tMaterias;
    }

    public boolean isMostrarEliminados() {
        return mostrarEliminados;
    }

    public void setMostrarEliminados(boolean mostrarEliminados) {
        this.mostrarEliminados = mostrarEliminados;
    }
    
}
