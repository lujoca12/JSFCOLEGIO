/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsTitulacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import Dao.*;
import Dao.DaoTitulacion;
import Pojo.*;
import Clases.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author SERVER
 */
@Named(value = "mbVPalabrasClave")
@ViewScoped
public class MbVPalabrasClave implements Serializable{
    
    private int idpc;
    private int idproyecto;
    private String palabra1 = "";
    private String palabra2 = "";
    private String palabra3 = "";
    private String palabra4 = "";
    private String palabra5 = "";
    
    Proyecto tproyecto;
    Usuario tusuario;
    Titulacion ttitulacion;
    PalabrasClave tpalabrasclave;
    
    private List<PalabrasClave> lstpclaves = new ArrayList<>();
    
    ClsPalabrasClave clspalabrasclaves;
    private List<ClsPalabrasClave> lstpalabrasclaves;
    
    ClsTablaTesis clsproyecto;
    private List<ClsTablaTesis> lstproyecto = new ArrayList<>();
    
    private List<Proyecto> lproyecto = new ArrayList<>();

    /**
     * Creates a new instance of MbVPalabrasClave
     */
    public MbVPalabrasClave() {
        llenarCboProyecto();
        llenarPC();
        tpalabrasclave= new PalabrasClave(); tusuario= new Usuario();
        tproyecto= new Proyecto();ttitulacion = new Titulacion();
    }
    private void vaciarCajas(){
        tproyecto= new Proyecto();
        tpalabrasclave = new PalabrasClave();
        palabra1="";palabra2="";palabra3="";palabra4="";palabra5="";
    }

    public List<PalabrasClave> getLstpclaves() {
        return lstpclaves;
    }

    public void setLstpclaves(List<PalabrasClave> lstpclaves) {
        this.lstpclaves = lstpclaves;
    }

   
    
    public String getPalabra1() {
        return palabra1;
    }
    public void setPalabra1(String palabra1) {
        this.palabra1 = palabra1;
    }
    public String getPalabra2() {
        return palabra2;
    }
    public void setPalabra2(String palabra2) {
        this.palabra2 = palabra2;
    }
    public String getPalabra3() {
        return palabra3;
    }
    public void setPalabra3(String palabra3) {
        this.palabra3 = palabra3;
    }
    public String getPalabra4() {
        return palabra4;
    }
    public void setPalabra4(String palabra4) {
        this.palabra4 = palabra4;
    }
    public String getPalabra5() {
        return palabra5;
    }
    public void setPalabra5(String palabra5) {
        this.palabra5 = palabra5;
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

    public void llenarCboProyecto(){
        this.lstproyecto = new ArrayList<ClsTablaTesis>();
         try {            
            DaoTesis daoTmaestria = new DaoTesis();
            lproyecto = daoTmaestria.getTodasProyectoxEstado("E"); 
             
            List<Proyecto> lstMaestria = daoTmaestria.getTodasProyectoxEstado("E");
            this.lstproyecto.clear();
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
    
    public void registrarPC(){
        llenarCboProyecto();
        boolean repetida = false;  
        boolean msg = false;
        //int i=0;
                DaoTesis daotesis = new DaoTesis();
            List<String> palabrasc = new ArrayList<String>();
            palabrasc.add(palabra1);
            palabrasc.add(palabra2);
            palabrasc.add(palabra3);
            palabrasc.add(palabra4);
            palabrasc.add(palabra5);
        try{
            for(int i=0;i<5;i++){
                tproyecto.setId(clsproyecto.getIdTesis());
                tpalabrasclave.setProyecto(tproyecto);
                tpalabrasclave.setEstado('G'); 
                tpalabrasclave.setDescripcion(palabrasc.get(i).toString());  
                msg = daotesis.registrarPalabrasClave(tpalabrasclave);              
            }              
            tproyecto.setId(clsproyecto.getIdTesis());
            tproyecto.setAutor(clsproyecto.getAutor());
            tproyecto.setTitulo(clsproyecto.getTitulo());
            tproyecto.setFechaSustentacion(clsproyecto.getFechaSus());
            tproyecto.setResumen(clsproyecto.getResumen());
            ttitulacion.setId(clsproyecto.getIdtitulacion());
            tproyecto.setTitulacion(ttitulacion);
            tusuario.setId(clsproyecto.getIdusuario());
            tproyecto.setUsuario(tusuario);
            tproyecto.setMaestria(clsproyecto.getMaestria());
            tproyecto.setTutor(clsproyecto.getTutor());
            tproyecto.setEstado('G');
            msg = daotesis.UpdateProyecto(tproyecto);
        }
        catch(Exception ex){
            vaciarCajas();
        }
        
            if(msg){
                vaciarCajas();
                llenarCboProyecto();llenarPC();
                mensajesOk("Datos procesados bien");  
            
            }
            else
                mensajesError("error al intentar procesar");
        
    }
    
    public void llenarPC(){
        DaoTesis daot = new DaoTesis();
        try{
            lstpclaves = daot.getTodasPC();
        }catch(Exception e){}
        
        
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
