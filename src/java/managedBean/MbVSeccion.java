/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTMaestrias;
import Dao.DaoTModalidad;
import Dao.DaoTSeccion;
import Pojo.Modalidad;
import Pojo.Seccion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author server
 */

@Named(value = "mbVSeccion")
@ViewScoped
public class MbVSeccion implements Serializable{

    /**
     * Creates a new instance of MbVSeccion
     */
    private Seccion tSeccion;
    private Modalidad tModalidad;
    private boolean msg = false;
    private boolean mostrarEliminados;
    private List<Seccion> lstSeccion;
    private List<SelectItem> cboSeccion;
    
    public MbVSeccion() {
        tSeccion = new Seccion();
        tModalidad = new Modalidad();
        cargarTablaSeccion();
        cargarCboModalidad();
    }
    public void cargarTablaSeccion(){
        
        try {
            lstSeccion = new ArrayList<>();
            DaoTSeccion daoSeccion = new DaoTSeccion();
            lstSeccion = daoSeccion.getTodasSeccions();
            
        } catch (Exception ex) {
            Logger.getLogger(MbVSeccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cargarCboModalidad() {
        try {
            
            cboSeccion = new ArrayList<>();
            DaoTModalidad daoModalidad = new DaoTModalidad();
            List<Modalidad> modalidad = daoModalidad.getModalidad();
            for (Modalidad m : modalidad) {
                SelectItem item = new SelectItem(m.getId(), m.getDescripcion());
                cboSeccion.add(item);
            }
        } catch (Exception ex) {
            
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
        DaoTSeccion daoSeccion = new DaoTSeccion();
        boolean repetida = false;
        boolean band = false;
        try {
            tSeccion.setEstado('1');
            tSeccion.setModalidad(tModalidad);
            repetida = daoSeccion.existe(tSeccion);
            if (!repetida) {
                msg = daoSeccion.registrar(tSeccion);
            }else{
                mensajesError("Registro repetido");
                cargarTablaSeccion();
                return;
            }
            
            if (msg) {
                mensajesOk("Datos procesados correctamente");
                vaciarCajas();
                cargarTablaSeccion();    
            } else {
                mensajesError("Error al procesar datos");
            }
        

        } catch (Exception e) {
            vaciarCajas();
        }
        
    }
    private void vaciarCajas(){
        tSeccion = new Seccion();
        cargarCboModalidad();
    }
    public void onRowEdit(RowEditEvent event) {
        boolean repetida = false;
        DaoTSeccion daoSeccion = new DaoTSeccion();
        Seccion seccion = new Seccion();
        
        try {
            seccion = (Seccion) event.getObject();
            
            repetida = daoSeccion.existe(seccion);
            if (!repetida) {
                 msg = daoSeccion.registrar(seccion);
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
            cargarTablaSeccion(); 
        } catch (Exception ex) {
            cargarTablaSeccion(); 
            Logger.getLogger(MbVMaterias.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<Seccion> getLstSeccion() {
        return lstSeccion;
    }

    public void setLstSeccion(List<Seccion> lstSeccion) {
        this.lstSeccion = lstSeccion;
    }

    public List<SelectItem> getCboSeccion() {
        return cboSeccion;
    }

    public void setCboSeccion(List<SelectItem> cboSeccion) {
        this.cboSeccion = cboSeccion;
    }
    
    
}
