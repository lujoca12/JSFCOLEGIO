/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsProfesor;
import Clases.Document;

import Dao.DaoTDetallePermiso;
import Dao.DaoTEstudiante;
import Dao.DaoTMaestrias;
import Dao.DaoTMenu;
import Dao.DaoTTipoUsuario;
import Dao.DaoTUsuario;
import Dao.DaoTesis;

import Pojo.Tribunal;
import Pojo.DetalleTribunal;
import Pojo.PalabrasClave;
import Pojo.Tesis;
import Pojo.TipoTribunal;
import Pojo.Usuario;
import Pojo.Maestria;
import Pojo.Estudiante;

import encriptacion.Class_Encript;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import Clases.*;
import Clases.ClsTablaTesis;
import java.util.Date;
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;

/**
 *
 * @author SERVER
 */
@Named(value = "mbVtesis")
@ViewScoped
public class MbVtesis implements Serializable{

    
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
    ClsTablaTesis ClsTablaTesis; 
    private List<ClsTablaTesis> LstTablatesis;

    /**
     * Creates a new instance of MbVtesis
     */
    public MbVtesis() {
        tTesis = new Tesis();
        llenarTablaTesis();
    }
    
    

    private void vaciarCajas(){
        tTesis = new Tesis();
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
            DaoTMaestrias DaoTMaestria = new DaoTMaestrias();

            List<Estudiante> lstTEstudia = DaoEstudia.getEstudiantes();
            List<Maestria> lstTMaestria = DaoTMaestria.getMaestrias();                      
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
                    for(Tesis tesis : lsttesis){
                        LstTablatesis.add(new ClsTablaTesis(tesis.getId(),
                                tesis.getAutor(),
                                tesis.getTitulo(),
                                tesis.getRuta(),
                                tesis.getResumen(),
                                tesis.getMaestria(),
                                tesis.getEstudiante()));                               
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void registrarTesis(){
        DaoTesis daoTesis = new DaoTesis();
        
        try{
        }
        catch (Exception e){
            vaciarCajas();
        }
    }
    
    
}
