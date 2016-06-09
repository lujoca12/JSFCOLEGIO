/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTMenu;
import Pojo.DetallePermiso;
import Pojo.Permiso;
import Pojo.Usuario;
import java.awt.Menu;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;


/**
 *
 * @author Usuario08
 */
@Named(value = "mbVMenu")
@SessionScoped
public class MbVMenu implements Serializable {

    /**
     * Creates a new instance of MbVMenu
     */
    private Permiso tPermiso;
    private List<Permiso> lstMenus;
    
    private MenuModel menuModel = null;
    private String name="";
    
    @PostConstruct
    public void load() {
        this.listarMenusNavxUsuarios();
        menuModel = null;
        menuModel = new DefaultMenuModel();
        this.establecerMenuxUsuarios();
    }
    
    public void listarMenusNavxUsuarios(){
        try {
            if(lstMenus != null)
                lstMenus.clear();
            
            DaoTMenu daoTMenu = new DaoTMenu();
            Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");            
            lstMenus = daoTMenu.getMenuNavxUsuarios(usuario);
        } catch (Exception ex) {
            addMessage("Success", ex.toString());
           // Logger.getLogger(MbVMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Permiso> listarPermisos(){
        try {
            
            DaoTMenu daoTMenu = new DaoTMenu();
            
            lstMenus = daoTMenu.getTodosPermisos();
        } catch (Exception ex) {
            addMessage("Success", ex.toString());
           // Logger.getLogger(MbVMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstMenus;
    }
    public void establecerMenuxUsuarios(){
        
        try {

            for (Permiso m : lstMenus) {
                
                if(m.getPadre() == 0){
                    //aqui va el padre
                    DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getDescripcion());
                    for(Permiso i : lstMenus){
                            if(m.getId() == i.getPadre()){
                                DefaultMenuItem item = new DefaultMenuItem(i.getDescripcion());
                                //item.setIcon("ui-icon-disk");
                                item.setOnclick(i.getForm());
                                firstSubmenu.addElement(item);
                            }
                    }
                    menuModel.addElement(firstSubmenu);
                }
            }
        } catch (Exception ex) {
            addMessage("Success", ex.toString());
        }
    }
    private String seguridad(){
        String respuesta = "";
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") == null) {
                
            }
        return respuesta;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    

    public List<Permiso> getLstMenus() {
        return lstMenus;
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
