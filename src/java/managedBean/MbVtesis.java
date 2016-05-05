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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

/**
 *
 * @author SERVER
 */
@Named(value = "mbVtesis")
@ViewScoped
public class MbVtesis implements Serializable{
    
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
    
        
    private Tesis tTesis;
    private String estudiante;
    private ArrayList<TipoTribunal> lstTheme;
    private List<SelectItem> lstTodoMaestria;
    private List<SelectItem> lstEstudiantes;
    ClsTablaTesis clsTablaTesis; 
    private List<ClsTablaTesis> LstTablatesis;

    /**
     * Creates a new instance of MbVtesis
     */
    public MbVtesis(){
        llenarTablaTesis();
        tTesis = new Tesis();
    }
            
    private void vaciarCajas(){
        tTesis = new Tesis();
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
       
    public String getEstudiante() {
        return estudiante;
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
                lsttesis.clear();
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void registrarTesis(){
        DaoTesis daoTesis = new DaoTesis();
        tTesis.setAutor(autor);
        
//        tTesis.setEstudiante(estudiante);
        tTesis.setFechaSubida(fechaSubida);
        tTesis.setFechaSustentacion(fechaSustentacion);
        
        idmaestria = Integer.getInteger(idMaestria);
//        tTesis.setMaestria(idmaestria);
        tTesis.setResumen(resumen);
        tTesis.setRuta(ruta);
        tTesis.setTitulo(titulo);
        
        try{
        }
        catch (Exception e){
            vaciarCajas();
        }
    }
    
    
}
