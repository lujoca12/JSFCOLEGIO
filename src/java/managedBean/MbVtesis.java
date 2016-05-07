/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTEstudiante;
import Dao.DaoTMaestrias;
import Dao.DaoTUsuario;
import Dao.DaoTesis;

import Pojo.Tesis;
import Pojo.TipoTribunal;
import Pojo.Usuario;
import Pojo.Maestria;
import Pojo.Estudiante;

import controladores.MaestriaBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import Clases.ClsTablaTesis;
import Clases.ClsMaestria;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author SERVER
 */
@Named(value = "mbVtesis")
@ViewScoped
public class MbVtesis implements Serializable{
    
    private boolean msg=false;
    
    private Map<String, String> maestrias;
    private String idMaestria;
    private Map<String, Map<String, String>> data = new HashMap<>();
    private MaestriaBean mBean;

    
     private int id;
     private int idestudiante;
     private int idmaestria;
     private String titulo;
     private String autor;
     private Date fechaSustentacion;
     private String ruta;
     private String resumen;
     private Date fechaSubida;
     
     
    
    private Maestria tMaestria;    
    private Tesis tTesis;
    private Estudiante tEstudiante;
    private ArrayList<TipoTribunal> lstTheme;
    private List<SelectItem> lstTodoMaestria;
    private List<SelectItem> lstEstudiantes;
    
    ClsMaestria clsMaestria;
    private List<ClsMaestria> lstThemeMaestria;
    
    ClsTablaTesis clsTablaTesis; 
    private List<ClsTablaTesis> LstTablatesis;

    /**
     * Creates a new instance of MbVtesis
     */
    public MbVtesis(){
        llenarTablaTesis();
        llenarCboMaestria();
        tMaestria= new Maestria();
        tTesis = new Tesis();
        tEstudiante= new Estudiante();
    }
            
    private void vaciarCajas(){
        tTesis = new Tesis();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    

    public Date getFechaSustentacion() {
        return fechaSustentacion;
    }

    public void setFechaSustentacion(Date fechaSustentacion) {
        this.fechaSustentacion = fechaSustentacion;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
    
    

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    

    public ClsMaestria getClsMaestria() {
        return clsMaestria;
    }

    public void setClsMaestria(ClsMaestria clsMaestria) {
        this.clsMaestria = clsMaestria;
    }

    public List<ClsMaestria> getLstThemeMaestria() {
        return lstThemeMaestria;
    }

    public void setLstThemeMaestria(List<ClsMaestria> lstThemeMaestria) {
        this.lstThemeMaestria = lstThemeMaestria;
    }
    

    public ClsTablaTesis getClsTablaTesis() {
        return clsTablaTesis;
    }

    public void setClsTablaTesis(ClsTablaTesis clsTablaTesis) {
        this.clsTablaTesis = clsTablaTesis;
    }
    
    public Map<String, String> getMaestrias() {
        return maestrias;
    }

    public void setMaestrias(Map<String, String> maestrias) {
        this.maestrias = maestrias;
    }

    public String getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(String idMaestria) {
        this.idMaestria = idMaestria;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    public MaestriaBean getmBean() {
        return mBean;
    }

    public void setmBean(MaestriaBean mBean) {
        this.mBean = mBean;
    }
    
    
    
    
    public List<ClsTablaTesis> getLstTablatesis() {
        return LstTablatesis;
    }    

    public Tesis gettTesis() {
        return tTesis;
    }

    public void settTesis(Tesis tTesis) {
        this.tTesis = tTesis;
    }

    public Estudiante gettEstudiante() {
        return tEstudiante;
    }

    public void settEstudiante(Estudiante tEstudiante) {
        this.tEstudiante = tEstudiante;
    }
    

    public Maestria gettMaestria() {
        return tMaestria;
    }

    public void settMaestria(Maestria tMaestria) {
        this.tMaestria = tMaestria;
    }
          
    

    public List<SelectItem> getLstEstudiantes() {
       this.lstEstudiantes = new ArrayList<SelectItem>();
        try {
            
            DaoTEstudiante DaoEstudia = new DaoTEstudiante();
            DaoTMaestrias DaoTMaestria1 = new DaoTMaestrias();

            List<Estudiante> lstTEstudia = DaoEstudia.getEstudiantes();
            List<Maestria> lstTMaestria = DaoTMaestria1.getMaestrias();                      
            lstEstudiantes.clear();
            for (Estudiante tipoUser : lstTEstudia) {
                SelectItem usuarioItem = new SelectItem(tipoUser.getId(), tipoUser.getNombres());
                this.lstEstudiantes.add(usuarioItem);
            }
        } catch (Exception ex) {

        }
        return lstEstudiantes;
        
    }
        
    public List<SelectItem> getLstTodoMaestria() {
       this.lstTodoMaestria = new ArrayList<SelectItem>();
        try {
            DaoTMaestrias DaoTMaestria = new DaoTMaestrias();

            List<Maestria> lstTMaestria = DaoTMaestria.getMaestrias();
            lstTodoMaestria.clear();
            for (Maestria tipoUser : lstTMaestria) {
                SelectItem usuarioItem = new SelectItem(tipoUser.getId(), tipoUser.getDescripcion());
                this.lstTodoMaestria.add(usuarioItem);
            }
        } catch (Exception ex) {

        }
        return lstTodoMaestria;
    }
        
    public void llenarCboTipotribunal() {
        this.lstTheme = new ArrayList<TipoTribunal>();
        try {
            DaoTUsuario daoTusuario = new DaoTUsuario();

            List<Usuario> lstUsuario = daoTusuario.getDocentes();
            this.lstTheme.clear();
            this.lstTheme.add(new TipoTribunal(-1, "Ninguno"));
            for (Usuario user : lstUsuario) {
                this.lstTheme.add(new TipoTribunal(user.getId(), 
                        user.getApellidos() + " " + user.getNombres()));
            }
        } catch (Exception ex) {

        }
    }
    
    public void llenarTablaTesis()
    {
          LstTablatesis = new ArrayList<>();
        try {
            LstTablatesis.clear();            
            DaoTesis daoTesis = new DaoTesis();
            List<Tesis> lsttesis = daoTesis.getTesis();            
            if(lsttesis != null){
                if(lsttesis.size() > 0){
                    for(Tesis tes : lsttesis){   
                        LstTablatesis.add(new ClsTablaTesis(tes.getId(),
                                tes.getAutor(), 
                                tes.getTitulo(), 
                                tes.getFechaSustentacion(),
                                tes.getFechaSubida() , 
                                tes.getRuta(),
                                tes.getResumen(), 
                                tes.getMaestria(),
                                tes.getEstudiante()));
                      //LstTablatesis.add(new ClsTablaTesis(id, autor, titulo, fechaSubida, fechaSubida, ruta, resumen, idMaestria, idEstudiante));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void consultarTesisAutor(){
        if(this.autor==""){
        }else{
            CargarTesisAutor();
        }        
    }
    public void CargarTesisAutor(){
        LstTablatesis= new ArrayList<>();
        try{
            LstTablatesis.clear();
           DaoTesis dtesis = new DaoTesis();
           List<Tesis> lsttesis= dtesis.getTesisxAutor(this.autor);
           if (lsttesis.size() > 0) {
               for(Tesis tesi : lsttesis){
                   LstTablatesis.add(new ClsTablaTesis(
                           tesi.getId(), 
                           tesi.getAutor(), 
                           tesi.getTitulo(),
                           tesi.getFechaSustentacion(), 
                           tesi.getFechaSubida(), 
                           tesi.getRuta(), 
                           tesi.getResumen(), 
                           tesi.getMaestria(), 
                           tesi.getEstudiante()) );
                  // LstTablatesis.add(new ClsTablaTesis(id, autor, titulo, fechaSubida, fechaSubida, ruta, resumen, tMaestria, tEstudiante) );
               }
           }           
        }
        catch (Exception e){
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    public void consultarTesisTitulo(){
        if(this.titulo == null){
        }else{
            CargarTesisTitulo();
        }
    }
     public void CargarTesisTitulo(){
         LstTablatesis= new ArrayList<>();
        try{
            LstTablatesis.clear();
           DaoTesis dtesis = new DaoTesis();
           List<Tesis> lsttesis= dtesis.getTesisxTitulo(this.titulo);
           if (lsttesis.size() > 0) {
               for(Tesis tesi : lsttesis){
                   LstTablatesis.add(new ClsTablaTesis(
                           tesi.getId(), 
                           tesi.getAutor(), 
                           tesi.getTitulo(),
                           tesi.getFechaSustentacion(), 
                           tesi.getFechaSubida(), 
                           tesi.getRuta(), 
                           tesi.getResumen(), 
                           tesi.getMaestria(), 
                           tesi.getEstudiante()) );
                  // LstTablatesis.add(new ClsTablaTesis(id, autor, titulo, fechaSubida, fechaSubida, ruta, resumen, tMaestria, tEstudiante) );
               }
           }           
        }
        catch (Exception e){
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, e);
        }
     }
    
    public void consultarTesisFechaSus(){
        if(this.fechaSustentacion == null){
        }else{
            CargarTesisFechaSust();
        }
    }
    public void CargarTesisFechaSust(){
        LstTablatesis= new ArrayList<>();
        try{
            LstTablatesis.clear();
           DaoTesis dtesis = new DaoTesis();
           List<Tesis> lsttesis= dtesis.getTesisxFechaSust(this.fechaSustentacion);
           if (lsttesis.size() > 0) {
               for(Tesis tesi : lsttesis){
                   LstTablatesis.add(new ClsTablaTesis(
                           tesi.getId(), 
                           tesi.getAutor(), 
                           tesi.getTitulo(),
                           tesi.getFechaSustentacion(), 
                           tesi.getFechaSubida(), 
                           tesi.getRuta(), 
                           tesi.getResumen(), 
                           tesi.getMaestria(), 
                           tesi.getEstudiante()) );
                  // LstTablatesis.add(new ClsTablaTesis(id, autor, titulo, fechaSubida, fechaSubida, ruta, resumen, tMaestria, tEstudiante) );
               }
           }           
        }
        catch (Exception e){
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, e);
        }
    }    
    public void llenarCboMaestria(){
        
        this.lstThemeMaestria = new ArrayList<ClsMaestria>();
         try {
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();
            
            List<Maestria> lstMaestria = daoTmaestria.getMaestrias();
            this.lstThemeMaestria.clear();
            this.lstThemeMaestria.add(new ClsMaestria(-1,"Ninguno","Ninguno",0,0,0));
            
            for(Maestria maestria: lstMaestria){
                this.lstThemeMaestria.add(new ClsMaestria(maestria.getId(),
                        maestria.getDescripcion(),
                        maestria.getDescripcion(),
                        maestria.getId(),
                        0,
                        0));
            }
        } catch (Exception ex) {
            
        }
    }
    
    public void registrarTesis(){
        
        //Variable para saber si esta registrada
        boolean repetida = false;
        
        DaoTesis daoTesis = new DaoTesis();
        tTesis.setAutor(autor);
        
        tTesis.setEstudiante(tEstudiante);
        tTesis.setFechaSubida(fechaSubida);
        tTesis.setFechaSustentacion(fechaSustentacion);
        
        //idmaestria = Integer.getInteger(idMaestria);
        tTesis.setMaestria(tMaestria);
        tTesis.setResumen(resumen);
        tTesis.setRuta(ruta);
        tTesis.setTitulo(titulo);
        
        try{
            List<Tesis> lstTesis=(List<Tesis>) daoTesis.getTesisxTitulo(tTesis.getTitulo());
            if(lstTesis.size() > 0){
                repetida = true;
            }
            else{
                //Si la maestria no existe se la registra
                msg =  daoTesis.registrarTesis(tTesis);
            }
        }
        catch (Exception e){
            vaciarCajas();
        }
        if(repetida){
            mensajesError("Registro repetido");            
        }else{
            vaciarCajas();
            if(msg)
                mensajesOk("Datos procesados bien");
            else
                mensajesError("error al intentar procesar");
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
    
}
