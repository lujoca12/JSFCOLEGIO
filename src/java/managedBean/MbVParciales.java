/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTCurso;
import Dao.DaoTMenu;
import Dao.DaoTModulo;
import Dao.DaoTPonderaciones;
import Pojo.Curso;
import Pojo.Permiso;
import Pojo.PonderacionFecha;
import Pojo.Ponderaciones;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    private List<SelectItem> lstParciales;
    private List<SelectItem> lstNivel;
    private List<SelectItem> lstOrden;
    private Ponderaciones tPonderaciones;
    private PonderacionFecha tPondFechas;
    private List<PonderacionFecha> lstTblPondFechas;
    private boolean mostrarEliminados;
    
    private Date fechaInicio;
    private Date fechaFin;
    
    private String ponderacionDescripcion;
    
    public MbVParciales() {
        tPonderaciones = new Ponderaciones();
        tPondFechas = new PonderacionFecha();
        nivel = 1;
        padre = 0;
        cargarParciales();
        cargarTablaPondFecha();
    }
    
    private void cargarParciales(){
        this.lstParciales = new ArrayList<SelectItem>();
        try {
            DaoTPonderaciones daoTponderaciones = new DaoTPonderaciones();
            
            List<Ponderaciones> lstPer = daoTponderaciones.getParciales();
            lstParciales.clear();
            for (int i = 0; i < lstPer.size(); i++) {
                if(lstPer.get(i).getClave() == 0){
                    SelectItem usuarioItem = new SelectItem(lstPer.get(i).getId(),lstPer.get(i).getDescripcion());
                    this.lstParciales.add(usuarioItem);
                
                for(Ponderaciones ponderaciones: lstPer){
                    if(ponderaciones.getClave()== lstPer.get(i).getId()){
                        usuarioItem = new SelectItem(ponderaciones.getId(),ponderaciones.getDescripcion()+" "+lstPer.get(i).getDescripcion());
                        this.lstParciales.add(usuarioItem);
                    }
                }
               }
            }
        } catch (Exception ex) {
            
        }
        
    }
    
    public void cargarTablaPondFecha() {
        lstTblPondFechas = new ArrayList<>();
        try {

            DaoTPonderaciones daoTponderaciones = new DaoTPonderaciones();
            if (ponderacionDescripcion == null) {
                lstTblPondFechas = daoTponderaciones.getTblPonderacionFecha("", mostrarEliminados);
            } else {
                lstTblPondFechas = daoTponderaciones.getTblPonderacionFecha(ponderacionDescripcion, mostrarEliminados);
            }

        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void registrarPonderaciones(){
        DaoTPonderaciones daoTponderaciones = new DaoTPonderaciones();
        try {
            
            tPondFechas.setPonderaciones(tPonderaciones);
            tPondFechas.setEstado('1');

            msg =  daoTponderaciones.registrarPondFecha(tPondFechas);
            
            
        } catch (Exception e) {
            vaciarCajas();
        }
        
        
        if(msg){
            mensajesOk("Datos procesados correctamente");
            vaciarCajas();
        }
        else
            mensajesError("Error al procesar datos");
        cargarTablaPondFecha();
    }
    
    private void vaciarCajas(){
        tPonderaciones = new Ponderaciones();
        tPondFechas = new PonderacionFecha();
        orden = 1;
        padre = 0;
    }
    
    public void onRowEdit(RowEditEvent event) {
        //boolean repetida = false;
        DaoTPonderaciones daoTponderaciones = new DaoTPonderaciones();
        PonderacionFecha pondFecha = new PonderacionFecha();
        
        try {
            pondFecha = (PonderacionFecha) event.getObject();
            
            //repetida = daoCurso.existe(curso);
            //if (!repetida) {
                 msg = daoTponderaciones.registrarPondFecha(pondFecha);
            //}else{
              //  mensajesError("Registro repetido");
                //cargarTablaMaterias();
                //return;
            //}
            if (msg) {
                mensajesOk("Datos actualizados correctamente");
                   
            } else {
                mensajesError("Error al procesar datos");
            }
            cargarTablaPondFecha(); 
        } catch (Exception ex) {
            cargarTablaPondFecha(); 
            
        }
        
    }
    
    public void onRowCancel(RowEditEvent event) {
        
        
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

    public List<SelectItem> getLstParciales() {
        return lstParciales;
    }

    public void setLstParciales(List<SelectItem> lstParciales) {
        this.lstParciales = lstParciales;
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

    public PonderacionFecha gettPondFechas() {
        return tPondFechas;
    }

    public void settPondFechas(PonderacionFecha tPondFechas) {
        this.tPondFechas = tPondFechas;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

    public List<PonderacionFecha> getLstTblPondFechas() {
        return lstTblPondFechas;
    }

    public void setLstTblPondFechas(List<PonderacionFecha> lstTblPondFechas) {
        this.lstTblPondFechas = lstTblPondFechas;
    }

    public boolean isMostrarEliminados() {
        return mostrarEliminados;
    }

    public void setMostrarEliminados(boolean mostrarEliminados) {
        this.mostrarEliminados = mostrarEliminados;
    }

    public String getPonderacionDescripcion() {
        return ponderacionDescripcion;
    }

    public void setPonderacionDescripcion(String ponderacionDescripcion) {
        this.ponderacionDescripcion = ponderacionDescripcion;
    }
    
    
}
