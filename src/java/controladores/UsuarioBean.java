/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Dao.UsuarioDAO;
import Pojo.TipoUsuario;
import Pojo.Usuario;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import encriptacion.Class_Encript;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chiti
 */
@RequestScoped
@ManagedBean
public class UsuarioBean {

    /**
     * Creates a new instance of Usuario
     */
    private Usuario usuario = new Usuario();

    private String usuarioNombre;
    private String usuarioRol;

    public UsuarioBean() {
//        this.usuarioNombre = null;
//        this.usuarioRol = null;
        HttpSession miSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        miSession.setMaxInactiveInterval(5000);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioRol() {
        return usuarioRol;
    }

    public void setUsuarioRol(String usuarioRol) {
        this.usuarioRol = usuarioRol;
    }

    public UsuarioBean(String usuarioNombre, String usuarioRol) {
        this.usuarioNombre = usuarioNombre;
        this.usuarioRol = usuarioRol;
    }

    public String asd() {
        String encript = Class_Encript.getStringMessageDigest(this.usuario.getClave(), Class_Encript.SHA256);
        this.usuario.setClave(encript);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", this.usuario.getClave()));
        return "exito";
    }

    public String verificarDatos() throws Exception {
        String resultado = null;
        try {
            
            UsuarioDAO uDAO = new UsuarioDAO();
            Usuario us;
            String encript = Class_Encript.getStringMessageDigest(this.usuario.getClave(), Class_Encript.SHA256);
            this.usuario.setClave(encript);
            us = uDAO.verificarDatos(this.usuario);
            if (us != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                this.usuarioNombre = us.getApellidos() + " " + us.getNombres();
                //usuarioRol = us.getDetallePermisos()
                resultado = "Admin/PanelAdministracion?faces-redirect=true";

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y Clave inválidos.", ""));

            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.toString()));
            resultado="/faces/index?faces-redirect=true";
        }
        return resultado;
    }

    public boolean verificarSesion() {
        boolean estado = false;

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") == null) {
            estado = false;
        } else {
            Usuario i = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            //TipoUsuario t = i.getTipoUsuario();
            if (i.getTipoUsuario().getDescripcion().equals("Administrador") || i.getTipoUsuario().getDescripcion().equals("Secretario(a)")) {
                estado = true;
            }

        }

        return estado;
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        return "/faces/index?faces-redirect=true";
    }

}
