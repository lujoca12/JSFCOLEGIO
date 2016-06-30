/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Dao.UsuarioDAO;
import Pojo.Usuario;
import encriptacion.Class_Encript;
import java.io.Serializable;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author chiti
 */
@Named(value = "perfilUsuarioBean")
@ViewScoped
public class PerfilUsuarioBean implements Serializable {

    /**
     * Creates a new instance of PerfilUsuarioBean
     */
    private Usuario usuario;
    private String nuevaClave="";
    private String nickViejo;
    private String telefono;
    private String celular;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PerfilUsuarioBean() {
    }

    @PostConstruct
    public void init() {
        try {
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            nickViejo = usuario.getNick();
            
//            
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void guardar() {
        boolean exito = false;
        try {
            UsuarioDAO uD = new UsuarioDAO();
            usuario.setTelefono(usuario.getTelefono().replaceAll("[()-]", ""));
            usuario.setCelular(usuario.getCelular().replaceAll("[()-]", ""));
            if (!nickViejo.equals(usuario.getNick())) {
                if (!uD.existeNick(usuario.getNick())) {
                    
                    String encript = Class_Encript.getStringMessageDigest(nuevaClave, Class_Encript.SHA256);
                    usuario.setClave(encript);
                    exito = uD.insertar(usuario,nickViejo);

                } else {
                    FacesMessage m = new FacesMessage("Error", "Ese nick ya existe!! Escoge uno diferente!");
                    FacesContext.getCurrentInstance().addMessage(null, m);
                }

            } else {
                String encript = Class_Encript.getStringMessageDigest(nuevaClave, Class_Encript.SHA256);
                usuario.setClave(encript);
                exito = uD.insertar(usuario,nickViejo);
            }
            if (exito) {
                FacesMessage m = new FacesMessage("Succesful", "Datos Actualizados");
                FacesContext.getCurrentInstance().addMessage(null, m);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
            } else {
                FacesMessage m = new FacesMessage("Error", "No se pudo actualizar los datos");
                FacesContext.getCurrentInstance().addMessage(null, m);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        nuevaClave="";
        nickViejo="";
        
    }

}
