/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsMaestria;
import Clases.ClsProfesor;
import Clases.ClsTablaModulosRegistrados;
import Dao.DaoTMaestrias;
import Dao.DaoTModulo;
import Dao.DaoTPromocion;
import Dao.DaoTUsuario;
import Pojo.Maestria;
import Pojo.Modulo;
import Pojo.Promocion;
import Pojo.Usuario;
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
@Named(value = "mbVModulos")
@ViewScoped
public class MbVModulos implements Serializable{

    private List<SelectItem> lstMaestria;
    
    private ClsProfesor theme; 
    private List<ClsProfesor> lstTheme;
    
    private ClsMaestria themeMaestria; 
    private List<ClsMaestria> lstThemeMaestria;
    
    private ClsTablaModulosRegistrados clsTblModulosReg;
    private List<ClsTablaModulosRegistrados> lstTblModulosReg;
    private List<ClsTablaModulosRegistrados> filteredCars;
    
    private int idMaestria;
    private int idDocente;
    private String moduloDescripcion;
    private int credito;
    private boolean msg;
    private Modulo tModulo;
    private List<Modulo> lstModulo;
    
    public MbVModulos() {
        cargarTablaModulos();
        tModulo = new Modulo();
        llenarCboDocente();
        llenarCboMaestria();
        //getTblModuloRegistrados();
    }

    public List<ClsTablaModulosRegistrados> getFilteredCars() {
        return filteredCars;
    }

    public void setFilteredCars(List<ClsTablaModulosRegistrados> filteredCars) {
        this.filteredCars = filteredCars;
    }
    
    public int getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(int idMaestria) {
        this.idMaestria = idMaestria;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public Modulo gettModulo() {
        return tModulo;
    }

    public void settModulo(Modulo tModulo) {
        this.tModulo = tModulo;
    }
    
    public List<ClsProfesor> getLstTheme() {
        
        return lstTheme;
    }
    
    public ClsProfesor getTheme() {
        return theme;
    }

    public void setTheme(ClsProfesor theme) {
        this.theme = theme;
    }

    public ClsMaestria getThemeMaestria() {
        return themeMaestria;
    }

    public void setThemeMaestria(ClsMaestria themeMaestria) {
        this.themeMaestria = themeMaestria;
    }

    public List<ClsMaestria> getLstThemeMaestria() {
        return lstThemeMaestria;
    }

    public String getModuloDescripcion() {
        return moduloDescripcion;
    }

    public void setModuloDescripcion(String moduloDescripcion) {
        this.moduloDescripcion = moduloDescripcion;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }
    
    public List<ClsTablaModulosRegistrados> getLstTblModulosReg() {
        return lstTblModulosReg;
    }

    public ClsTablaModulosRegistrados getClsTblModulosReg() {
        return clsTblModulosReg;
    }

    public void setClsTblModulosReg(ClsTablaModulosRegistrados clsTblModulosReg) {
        this.clsTblModulosReg = clsTblModulosReg;
    }
    
    public void cargarTablaModulos(){
        lstTblModulosReg = new ArrayList<>();
        try {
            lstTblModulosReg.clear();
            DaoTModulo daoTmodulo = new DaoTModulo();
            lstModulo = daoTmodulo.getTblModulos();
            
            if(lstModulo != null){
                if(lstModulo.size() > 0){
                    for(Modulo modulo : lstModulo){
                        lstTblModulosReg.add(new ClsTablaModulosRegistrados(modulo.getPromocion().getMaestria().getId(), 
                                modulo.getPromocion().getMaestria().getDescripcion()+" (Dir.(a)"+modulo.getPromocion().getUsuarios()+")", 
                                modulo.getPromocion().getId(), modulo.getDescripcion(), 
                                modulo.getUsuario().getId(), 
                                modulo.getUsuario().getApellidos()+" "+modulo.getUsuario().getNombres(), 
                                modulo.getCreditos(), modulo.getId()));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void llenarCboDocente(){
        this.lstTheme = new ArrayList<ClsProfesor>();
         try {
            DaoTUsuario daoTusuario = new DaoTUsuario();
            
            List<Usuario> lstUsuario = daoTusuario.getDocentes();
            this.lstTheme.clear();
            this.lstTheme.add(new ClsProfesor(-1,"Ninguno","Ninguno"));
            for(Usuario user: lstUsuario){
                this.lstTheme.add(new ClsProfesor(user.getId(), user.getApellidos()+" "+user.getNombres(), user.getApellidos()+" "+user.getNombres()));
            }
        } catch (Exception ex) {
            
        }
    }
    
    public void llenarCboMaestria(){
        this.lstThemeMaestria = new ArrayList<ClsMaestria>();
         try {
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();
            DaoTPromocion daoTpromocion = new DaoTPromocion();
            
            List<Promocion> lstPromocion = daoTpromocion.getPromocionesMaestrias();
            this.lstThemeMaestria.clear();
            this.lstThemeMaestria.add(new ClsMaestria(-1,"Ninguno","Ninguno",0));
            
            for(Promocion promocion: lstPromocion){
                
                this.lstThemeMaestria.add(new ClsMaestria(promocion.getId(),promocion.getMaestria().getDescripcion()+" ("+promocion.getUsuarios()+")",promocion.getMaestria().getDescripcion(),promocion.getId()));
            }
        } catch (Exception ex) {
            
        }
    }

    public List<SelectItem> getLstMaestria() {
        this.lstMaestria = new ArrayList<SelectItem>();
        try {
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();
            
            List<Maestria> lstMaestria = daoTmaestria.getTodasMaestrias();
            lstMaestria.clear();
            for(Maestria maestria: lstMaestria){
                SelectItem maestriaItem = new SelectItem(maestria.getId(),maestria.getDescripcion());
                this.lstMaestria.add(maestriaItem);
            }
        } catch (Exception ex) {
            
        }
        return lstMaestria;
    }

    public void registrar(){
        boolean repetida = false;
        try {
            
            DaoTModulo daoTmodulo = new DaoTModulo();
            
            DaoTPromocion daoTpromocion = new DaoTPromocion();
            Promocion promocion = new Promocion();
            promocion.setId(this.themeMaestria.getIdPromocion());
            tModulo.setPromocion(promocion);
            
            Usuario usuario = new Usuario();
            usuario.setId(theme.getId());
            tModulo.setUsuario(usuario);
            
            repetida = daoTmodulo.existe(tModulo);
            if(!repetida)
                msg = daoTmodulo.registrar(tModulo);
            
            cargarTablaModulos();
            
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(repetida){
            mensajesError("Registro repetido");
        }else{
            if(msg)
            mensajesOk("Datos procesados correctamente");
        else
            mensajesError("Error al procesar datos");
            vaciarCajas();
        }
        
         
    }
    
    private void vaciarCajas(){
        idDocente = 0;
        idMaestria = 0;
        tModulo = new Modulo();
        llenarCboDocente();
        //llenarCboMaestria();
        
    }
    
    private void mensajesOk(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    private void mensajesError(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onRowEdit(RowEditEvent event) {
        
        DaoTModulo daoTmodulo = new DaoTModulo();
        Usuario user = new Usuario();
        Promocion promocion = new Promocion();
        
        
        if(theme == null && themeMaestria == null){
            user.setId(((ClsTablaModulosRegistrados) event.getObject()).getIdUsuario());
            promocion.setId(((ClsTablaModulosRegistrados) event.getObject()).getIdPromocion());
        }else if(theme == null && themeMaestria != null){
            promocion.setId(themeMaestria.getIdPromocion());
            user.setId(((ClsTablaModulosRegistrados) event.getObject()).getIdUsuario());
        }else if(themeMaestria == null && theme != null){
            user.setId(theme.getId());
            promocion.setId(((ClsTablaModulosRegistrados) event.getObject()).getIdPromocion());
        }else{
            promocion.setId(themeMaestria.getIdPromocion());
            user.setId(theme.getId());
        }
        
        try {
            Modulo modulo = new Modulo();
            modulo.setPromocion(promocion);
            modulo.setUsuario(user);
            modulo.setId(((ClsTablaModulosRegistrados) event.getObject()).getIdModulo());
            modulo.setDescripcion(((ClsTablaModulosRegistrados) event.getObject()).getModulo());
            modulo.setCreditos(((ClsTablaModulosRegistrados) event.getObject()).getCreditos());
            msg = daoTmodulo.update(modulo);
            cargarTablaModulos();
            
        } catch (Exception ex) {
            Logger.getLogger(MbVTablaPermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(msg)
            mensajesOk("Datos actualizados correctamente");
        else
            mensajesError("Error al actualizar datos");
    }
    public void onRowCancel(RowEditEvent event) {
        
    }
    public void onDelete(ClsTablaModulosRegistrados clsTblModulos){
        DaoTModulo daoTmodulo = new DaoTModulo();
        Modulo modulo = new Modulo();
        modulo.setId(clsTblModulos.getIdModulo());
        try {
            msg = daoTmodulo.delete(modulo);
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaModulos();
        if(msg)
            mensajesOk("Dato eliminado correctamente");
        else
            mensajesError("Error al eliminar dato");
    }
   
}
