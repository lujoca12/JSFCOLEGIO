/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsTablaMaestria;
import Clases.ClsTablaMaestriaPromocion;
import Dao.DaoTMaestrias;
import Dao.DaoTMaterias;
import Dao.DaoTModalidad;
import Dao.DaoTPromocion;
import Pojo.Maestria;
import Pojo.Modalidad;
import Pojo.Precio;
import Pojo.Promocion;
import java.io.Serializable;
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
@Named(value = "mbVModalidad")
@ViewScoped
public class MbVModalidad implements Serializable{

    /**
     * Creates a new instance of MbVModalidad
     */
    private Modalidad tModalidad;
    private boolean msg = false;
    private boolean mostrarEliminados;
    private List<Modalidad> lstModalidad;
    private String modalidadDescripcion;
    public MbVModalidad() {
        tModalidad = new Modalidad();
        cargarTablaModalidad();
    }
    public void cargarTablaModalidad(){
        
        try {
            
            lstModalidad = new ArrayList<>();
            DaoTModalidad daoModalidad = new DaoTModalidad();
            if(modalidadDescripcion == null){
                lstModalidad = daoModalidad.getModalidadD("", mostrarEliminados);
            }else{
                lstModalidad = daoModalidad.getModalidadD(modalidadDescripcion, mostrarEliminados);
            }
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(MbVModalidad.class.getName()).log(Level.SEVERE, null, ex);
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
        DaoTModalidad daoModalidad = new DaoTModalidad();
        boolean repetida = false;
        boolean band = false;
        try {
            tModalidad.setEstado('1');
            repetida = daoModalidad.existe(tModalidad);
            if (!repetida) {
                msg = daoModalidad.registrar(tModalidad);
            }else{
                mensajesError("Registro repetido");
                cargarTablaModalidad();
                return;
            }
            
            if (msg) {
                mensajesOk("Datos procesados correctamente");
                vaciarCajas();
                cargarTablaModalidad();    
            } else {
                mensajesError("Error al procesar datos");
            }
        

        } catch (Exception e) {
            vaciarCajas();
        }
        
    }
    private void vaciarCajas(){
        tModalidad = new Modalidad();
    }
    public void onRowEdit(RowEditEvent event) {
        boolean repetida = false;
        DaoTModalidad daoModalidad = new DaoTModalidad();
        Modalidad modalidad = new Modalidad();
        
        try {
            modalidad = (Modalidad) event.getObject();
            
            repetida = daoModalidad.existe(modalidad);
            if (!repetida) {
                 msg = daoModalidad.registrar(modalidad);
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
            cargarTablaModalidad(); 
        } catch (Exception ex) {
            cargarTablaModalidad(); 
            Logger.getLogger(MbVMaterias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void onRowCancel(RowEditEvent event) {
        
        
    }
    
    public void onDeleteModalidad(Modalidad tModalidades){
        DaoTModalidad daoModalidad = new DaoTModalidad();
        tModalidades.setEstado('0');
        try {
            msg = daoModalidad.registrar(tModalidades);
            cargarTablaModalidad();
        } catch (Exception ex) {
            Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (msg) {
            mensajesOk("Dato eliminado correctamente");
        } else {
            mensajesError("Error al eliminar datos");
        }
    }
    public void onRecuperar(Modalidad tModalidades){
        boolean repetida = false;
        DaoTModalidad daoModalidad = new DaoTModalidad();
        
        tModalidades.setEstado('1');
        
        try {
//            repetida = daoModalidad.existe(tModalidad);
//            if(lstMaestria.size() > 0){
//                repetida = true;
//            }
//            else{
                msg = daoModalidad.registrar(tModalidades);
                cargarTablaModalidad();
//            }
            
        } catch (Exception ex) {
            Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(repetida){
            mensajesError("Registro repetido");
        } else{
            if (msg) {
                mensajesOk("Dato Restaurado correctamente");
            } else {
                mensajesError("No se pudo recuperar el dato");
            }
        }
        
    }

    public String getModalidadDescripcion() {
        return modalidadDescripcion;
    }

    public void setModalidadDescripcion(String modalidadDescripcion) {
        this.modalidadDescripcion = modalidadDescripcion;
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

    public List<Modalidad> getLstModalidad() {
        return lstModalidad;
    }

    public void setLstModalidad(List<Modalidad> lstModalidad) {
        this.lstModalidad = lstModalidad;
    }

    
    
}
