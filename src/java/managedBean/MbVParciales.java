/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTMenu;
import Dao.DaoTPonderaciones;
import Pojo.Permiso;
import Pojo.Ponderaciones;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author server
 */
@Named(value = "mbVParciales")
@ViewScoped
public class MbVParciales implements Serializable{

    private int nivel;
    private int padre;
    private int orden;
    private String contenedor;
    private String tuDirectorioPagina;
    boolean msg = false;
    
    private List<SelectItem> lstPermiso;
    private List<SelectItem> lstNivel;
    private List<SelectItem> lstOrden;
    private Ponderaciones tPonderaciones;
    
    
    public MbVParciales() {
        tPonderaciones = new Ponderaciones();
        nivel = 1;
        padre = 0;
    }
    
    public List<SelectItem> getLstNivel() {
        this.lstNivel = new ArrayList<>();
            lstNivel.clear();
            SelectItem usuarioItem = new SelectItem("1","Quimestre/Trimestre");
            this.lstNivel.add(usuarioItem);
            usuarioItem = new SelectItem("2","Parciales");
            this.lstNivel.add(usuarioItem);
        return lstNivel;
    }
    
    public List<SelectItem> getLstOrden() {
        this.lstOrden = new ArrayList<>();
            lstOrden.clear();
            for (int i = 1; i <= 20; i++) {
            SelectItem usuarioItem = new SelectItem(String.valueOf(i),String.valueOf(i));
            this.lstOrden.add(usuarioItem);
        }
        return lstOrden;
    }
    
    public List<SelectItem> getLstPermiso() {
        this.lstPermiso = new ArrayList<SelectItem>();
        try {
            DaoTPonderaciones daoTponderaciones = new DaoTPonderaciones();
            
            List<Ponderaciones> lstPer = daoTponderaciones.getPadres();
            lstPermiso.clear();
            for(Ponderaciones ponderaciones: lstPer){
                SelectItem usuarioItem = new SelectItem(ponderaciones.getId(),ponderaciones.getDescripcion());
                this.lstPermiso.add(usuarioItem);
            }
        } catch (Exception ex) {
            
        }
        return lstPermiso;
    }
    
    public void registrar(){
        DaoTPonderaciones daoTponderaciones = new DaoTPonderaciones();
        try {
            if(nivel == 1){
            tPonderaciones.setClave(0);
            }else{
                tPonderaciones.setClave(padre);
            }
            tPonderaciones.setEstado('1');

            msg =  daoTponderaciones.registrar(tPonderaciones);
            vaciarCajas();
        } catch (Exception e) {
            vaciarCajas();
        }
        
        
        if(msg)
            mensajesOk("Datos procesados correctamente");
        else
            mensajesError("Error al procesar datos");
    }
    
    private void vaciarCajas(){
        tPonderaciones = new Ponderaciones();
        orden = 1;
        padre = 0;
    }
    
    private void mensajesOk(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    private void mensajesError(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onClickChange(){
        this.getNivel();
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPadre() {
        return padre;
    }

    public void setPadre(int padre) {
        this.padre = padre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getContenedor() {
        return contenedor;
    }

    public void setContenedor(String contenedor) {
        this.contenedor = contenedor;
    }

    public String getTuDirectorioPagina() {
        return tuDirectorioPagina;
    }

    public void setTuDirectorioPagina(String tuDirectorioPagina) {
        this.tuDirectorioPagina = tuDirectorioPagina;
    }

//    public List<SelectItem> getLstPermiso() {
//        return lstPermiso;
//    }

    public void setLstPermiso(List<SelectItem> lstPermiso) {
        this.lstPermiso = lstPermiso;
    }

//    public List<SelectItem> getLstNivel() {
//        return lstNivel;
//    }

    public void setLstNivel(List<SelectItem> lstNivel) {
        this.lstNivel = lstNivel;
    }

//    public List<SelectItem> getLstOrden() {
//        return lstOrden;
//    }

    public void setLstOrden(List<SelectItem> lstOrden) {
        this.lstOrden = lstOrden;
    }

    public Ponderaciones gettPonderaciones() {
        return tPonderaciones;
    }

    public void settPonderaciones(Ponderaciones tPonderaciones) {
        this.tPonderaciones = tPonderaciones;
    }
    
    
}
