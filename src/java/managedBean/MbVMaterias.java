/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTMaestrias;
import Pojo.Materias;
import java.io.Serializable;
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
    public MbVMaterias() {
        tMaterias = new Materias();
        cargarTablaMaterias();
    }
    public void cargarTablaMaterias(){
        
        try {
            
            
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
        
    }
    public void onRowEdit(RowEditEvent event) {
        boolean repetida = false;
        DaoTMaestrias daoTmaestrias = new DaoTMaestrias();
        
        try {
            
        } catch (Exception ex) {
            Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void onRowCancel(RowEditEvent event) {
        
        
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
