/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTMaestrias;
import Pojo.Modalidad;
import Pojo.Seccion;
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

@Named(value = "mbVSeccion")
@ViewScoped
public class MbVSeccion {

    /**
     * Creates a new instance of MbVSeccion
     */
    private Seccion tSeccion;
    private Modalidad tModalidad;
    private boolean msg = false;
    private boolean mostrarEliminados;
    public MbVSeccion() {
        tSeccion = new Seccion();
        tModalidad = new Modalidad();
        cargarTablaSeccion();
    }
    public void cargarTablaSeccion(){
        
        try {
            
            
        } catch (Exception ex) {
            Logger.getLogger(MbVSeccion.class.getName()).log(Level.SEVERE, null, ex);
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

    public Seccion gettSeccion() {
        return tSeccion;
    }

    public void settSeccion(Seccion tSeccion) {
        this.tSeccion = tSeccion;
    }

    public Modalidad gettModalidad() {
        return tModalidad;
    }

    public void settModalidad(Modalidad tModalidad) {
        this.tModalidad = tModalidad;
    }

    public boolean isMostrarEliminados() {
        return mostrarEliminados;
    }

    public void setMostrarEliminados(boolean mostrarEliminados) {
        this.mostrarEliminados = mostrarEliminados;
    }
    
}
