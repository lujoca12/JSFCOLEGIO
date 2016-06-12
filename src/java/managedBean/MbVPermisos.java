/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTMenu;
import Pojo.Permiso;
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

/**
 *
 * @author server
 */
@Named(value = "mbVPermisos")
@ViewScoped
public class MbVPermisos implements Serializable{

    private int nivel;
    private int padre;
    private int orden;
    private String contenedor;
    private String tuDirectorioPagina;
    boolean msg = false;
    
    private List<SelectItem> lstPermiso;
    private List<SelectItem> lstNivel;
    private List<SelectItem> lstOrden;
    private Permiso tPermiso;
    
    public MbVPermisos() {
        tPermiso = new Permiso();
        nivel = 1;
        padre = 0;
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

    public Permiso gettPermiso() {
        return tPermiso;
    }

    public void settPermiso(Permiso tPermiso) {
        this.tPermiso = tPermiso;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
    
    public List<SelectItem> getLstPermiso() {
        this.lstPermiso = new ArrayList<SelectItem>();
        try {
            DaoTMenu daoTmenu = new DaoTMenu();
            
            List<Permiso> lstPer = daoTmenu.getPadres();
            lstPermiso.clear();
            for(Permiso permiso: lstPer){
                SelectItem usuarioItem = new SelectItem(permiso.getId(),permiso.getDescripcion());
                this.lstPermiso.add(usuarioItem);
            }
        } catch (Exception ex) {
            
        }
        return lstPermiso;
    }

    public List<SelectItem> getLstNivel() {
        this.lstNivel = new ArrayList<>();
            lstNivel.clear();
            SelectItem usuarioItem = new SelectItem("1","Padre");
            this.lstNivel.add(usuarioItem);
            usuarioItem = new SelectItem("2","Hijo");
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
    
    
    public void onClickChange(){
        this.getNivel();
    }
    
    public void registrar(){
        DaoTMenu daoTmenu = new DaoTMenu();
        contenedor = "cargar_Elementos";
        try {
            if(nivel == 1){
            tPermiso.setPadre(0);
            }else{
                tPermiso.setPadre(padre);
                String ruta = "";
                ruta += "CargarPaginaURL('"+contenedor+"','/JSFSISFIAC/faces/"+tuDirectorioPagina+"')";
                tPermiso.setForm(ruta);
            }
            tPermiso.setOrden(orden);
            tPermiso.setEstado('1');

            msg =  daoTmenu.registrar(tPermiso);
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
         tPermiso = new Permiso();
        contenedor = "";
        tuDirectorioPagina = "";
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
}
