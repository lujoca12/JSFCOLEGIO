/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;
import Pojo.*;
import Dao.*;
import Clases.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author SERVER
 */
@Named(value = "mbVSustentacion")
@Dependent
public class MbVSustentacion {
    Date fechasustentacion;    
    ClsTablaTesis clsproyecto;
    private List<ClsTablaTesis> lstproyecto;
    ClsTipoActa clstacta;
    private List<ClsTipoActa> lsttacta;
    ClsActa clsacta;
    private List<ClsActa> lstacta;

    /**
     * Creates a new instance of MbVSustentacion
     */
    public MbVSustentacion() {
        llenarCboProyecto();
        llenarCboActa();
    }

    public ClsTipoActa getClstacta() {
        return clstacta;
    }

    public void setClstacta(ClsTipoActa clstacta) {
        this.clstacta = clstacta;
    }

    public List<ClsTipoActa> getLsttacta() {
        return lsttacta;
    }

    public void setLsttacta(List<ClsTipoActa> lsttacta) {
        this.lsttacta = lsttacta;
    }
    

    public ClsTablaTesis getClsproyecto() {
        return clsproyecto;
    }
    public void setClsproyecto(ClsTablaTesis clsproyecto) {
        this.clsproyecto = clsproyecto;
    }
    public List<ClsTablaTesis> getLstproyecto() {
        return lstproyecto;
    }
    public void setLstproyecto(List<ClsTablaTesis> lstproyecto) {
        this.lstproyecto = lstproyecto;
    }
    public ClsActa getClsacta() {
        return clsacta;
    }
    public void setClsacta(ClsActa clsacta) {
        this.clsacta = clsacta;
    }
    public List<ClsActa> getLstacta() {
        return lstacta;
    }
    public void setLstacta(List<ClsActa> lstacta) {
        this.lstacta = lstacta;
    }
    public Date getFechasustentacion() {
        return fechasustentacion;
    }
    public void setFechasustentacion(Date fechasustentacion) {
        this.fechasustentacion = fechasustentacion;
    }
    
    public void llenarCboActa(){
          DaoSustentacion daotsustentacion = new DaoSustentacion();
      this.lstacta = new ArrayList<ClsActa>();
      this.lsttacta = new ArrayList<>();
      try{
//          List<Acta> lstact = daotsustentacion.getTodasActa();
//          for(Acta ac : lstact){
//              this.lstacta.add(new ClsActa(ac.getId(),
//                      ac.getMaestria().getId(), 
//                      ac.getTipoActa().getId(), 
//                      ac.getNumeracion(), 
//                      ac.getMaestria().getDescripcion(),
//                      ac.getDescripcion(), 
//                      ac.getTipoActa().getEstado()));
              List<TipoActa> lsta= daotsustentacion.getTipoActa();
              for(TipoActa ac : lsta){
                  this.lsttacta.add(new ClsTipoActa(ac.getId(), 
                          ac.getDescripcion(), 
                          ac.getDescripcion(),
                          ac.getEstado()));
              }
      }catch(Exception e){
      }
    }
    public void llenarCboProyecto(){
     this.lstproyecto = new ArrayList<ClsTablaTesis>();
         try {
            DaoTesis daoTmaestria = new DaoTesis();
            
            List<Proyecto> lstMaestria = daoTmaestria.getTodasProyectoxEstado("G");
            this.lstproyecto.add(new ClsTablaTesis(-1,"(Seleccione)"));
            
            for(Proyecto maestria: lstMaestria){
                 this.lstproyecto.add(new ClsTablaTesis(maestria.getId(), 
                        maestria.getAutor(), maestria.getTitulo(), 
                        maestria.getFechaSustentacion(), maestria.getFechaSubida(), 
                        maestria.getRuta(), maestria.getResumen(), maestria.getMaestria(),
                        maestria.getTutor(), 
                        maestria.getEstado(), 
                        maestria.getTitulacion().getId(),
                        maestria.getUsuario().getId()));
            }
        } catch (Exception ex) {
            
        }
    }

    public void registrar(){
        
    }
     private void mensajesOk(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    private void mensajesError(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
