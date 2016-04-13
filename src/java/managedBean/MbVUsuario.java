/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsProfesor;
import Clases.Document;
import Dao.DaoTDetallePermiso;
import Dao.DaoTMenu;
import Dao.DaoTTipoUsuario;
import Dao.DaoTUsuario;
import Pojo.DetallePermiso;
import Pojo.Permiso;
import Pojo.TipoUsuario;
import Pojo.Usuario;
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
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;

/**
 *
 * @author server
 */
@Named(value = "mbVUsuario")
@ViewScoped
public class MbVUsuario implements Serializable {

    /**
     * Creates a new instance of MbVUsuario
     */
    private Usuario tUsuario;
    private List<SelectItem> lstUsuario;
    private List<SelectItem> lstTodosUsuarios;
    private String usuario;
    private String telefono;
    private String celular;
    private String clave;
    private int idRol;
    String msg = "";
    boolean band = false;
    private List<Permiso> lstMenus;
    private List<DetallePermiso> lstMenusDetalle;

    private ClsProfesor theme;
    private List<ClsProfesor> lstTheme;
    
    private ClsProfesor themeUsuarios;
    private List<ClsProfesor> lstThemeUsuarios;

    // TreeNode instance
    private TreeNode root;
    private TreeNode[] checkboxSelectedTreeNodes;
    private Document selectedDocument;
    private TreeNode selectedNode;
    
    private int estado;

    public MbVUsuario() {
        tUsuario = new Usuario();
        llenarCboDocentes();
        llenarCboUsuarios();
    }

    public String getUsuario() {
        return usuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public TreeNode[] getCheckboxSelectedTreeNodes() {
        return checkboxSelectedTreeNodes;
    }

    public void setCheckboxSelectedTreeNodes(TreeNode[] checkboxSelectedTreeNodes) {
        this.checkboxSelectedTreeNodes = checkboxSelectedTreeNodes;
    }

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

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public Usuario gettUsuario() {
        return tUsuario;
    }

    public void settUsuario(Usuario tUsuario) {
        this.tUsuario = tUsuario;
    }

    public Document getSelectedDocument() {
        return selectedDocument;
    }

    public void setSelectedDocument(Document selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    public void onClickChange() {

        asignarPermisosAusuarios();
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public ClsProfesor getTheme() {
        return theme;
    }

    public void setTheme(ClsProfesor theme) {
        this.theme = theme;
    }

    public List<ClsProfesor> getLstTheme() {
        return lstTheme;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public ClsProfesor getThemeUsuarios() {
        return themeUsuarios;
    }

    public void setThemeUsuarios(ClsProfesor themeUsuarios) {
        this.themeUsuarios = themeUsuarios;
    }

    public List<ClsProfesor> getLstThemeUsuarios() {
        return lstThemeUsuarios;
    }

    
    public void llenarCboDocentes() {
        this.lstTheme = new ArrayList<ClsProfesor>();
        try {
            DaoTUsuario daoTusuario = new DaoTUsuario();

            List<Usuario> lstUsuario = daoTusuario.getDocentes();
            this.lstTheme.clear();
            this.lstTheme.add(new ClsProfesor(-1, "Ninguno", "Ninguno"));
            for (Usuario user : lstUsuario) {
                this.lstTheme.add(new ClsProfesor(user.getId(), user.getApellidos() + " " + user.getNombres(), user.getApellidos() + " " + user.getNombres()));
            }
        } catch (Exception ex) {

        }
    }
    
    public void llenarCboUsuarios() {
        this.lstThemeUsuarios = new ArrayList<ClsProfesor>();
        try {
            DaoTUsuario daoTusuario = new DaoTUsuario();

            List<Usuario> lstUsuario = daoTusuario.getTodosUsuarios();
            this.lstThemeUsuarios.clear();
            this.lstThemeUsuarios.add(new ClsProfesor(-1, "Ninguno", "Ninguno"));
            for (Usuario user : lstUsuario) {
                this.lstThemeUsuarios.add(new ClsProfesor(user.getId(), 
                        user.getApellidos() + " " + user.getNombres()+" ("+user.getTipoUsuario().getDescripcion()+")", 
                        user.getApellidos() + " " + user.getNombres()+" ("+user.getTipoUsuario().getDescripcion()+")"));
            }
        } catch (Exception ex) {

        }
    }

    public List<SelectItem> getLstTodosUsuarios() {
        this.lstTodosUsuarios = new ArrayList<SelectItem>();
        try {
            DaoTTipoUsuario daoTtipoUsuario = new DaoTTipoUsuario();

            List<TipoUsuario> lstTtipoUsuario = daoTtipoUsuario.getTodosTipoUsuarios();
            lstTodosUsuarios.clear();
            for (TipoUsuario tipoUser : lstTtipoUsuario) {
                SelectItem usuarioItem = new SelectItem(tipoUser.getId(), tipoUser.getDescripcion());
                this.lstTodosUsuarios.add(usuarioItem);
            }
        } catch (Exception ex) {

        }
        return lstTodosUsuarios;
    }

    //Metodo para cargar Usuarios
    public List<SelectItem> getLstUsuario() throws Exception {
        this.lstUsuario = new ArrayList<SelectItem>();
        DaoTUsuario daoTUsuario = new DaoTUsuario();

        List<Usuario> lstUser = daoTUsuario.getTodosUsuarios();
        lstUsuario.clear();
        for (Usuario usuario : lstUser) {
            SelectItem usuarioItem = new SelectItem(usuario.getId(), usuario.getApellidos() + " " + usuario.getNombres());
            this.lstUsuario.add(usuarioItem);
        }

        return lstUsuario;
    }

    //Metodo para crear arbol de permisos
    public void asignarPermisosAusuarios() {
        TreeNode node0 = null;

        TreeNode node00 = null;
        try {
            if (usuario.equals("-1")) {
                root = new DefaultTreeNode(new Document("Files", 0, "Folder"), null);
            } else {
                DaoTMenu daoTmenu = new DaoTMenu();
                lstMenus = daoTmenu.getTodosPermisos();
                if (lstMenus != null) {
                    root = new DefaultTreeNode(new Document("Files", 0, "Folder"), null);
                    for (Permiso p : lstMenus) {
                        if (p.getPadre() == 0) {
                            node0 = new DefaultTreeNode(new Document(p.getDescripcion(), p.getId(), "Folder"), root);

                            lstMenusDetalle = daoTmenu.getEstadoPermisoUsuario(Integer.parseInt(usuario), p.getId());
                            if (!lstMenusDetalle.isEmpty()) {
                                if (lstMenusDetalle.get(0).getEstado().equals('1')) {
                                    node0.setSelected(true);
                                } else {
                                    node0.setSelected(false);
                                }
                            }

                            for (Permiso p1 : lstMenus) {
                                if (p.getId() == p1.getPadre()) {
                                    node00 = new DefaultTreeNode(new Document(p1.getDescripcion(), p1.getId(), "Folder"), node0);
                                    node00.setRowKey(String.valueOf(p1.getId()));

                                    daoTmenu = new DaoTMenu();
                                    lstMenusDetalle = daoTmenu.getEstadoPermisoUsuario(Integer.parseInt(usuario), p1.getId());
                                    if (!lstMenusDetalle.isEmpty()) {
                                        if (lstMenusDetalle.get(0).getEstado().equals('1')) {
                                            node00.setSelected(true);
                                        } else {
                                            node00.setSelected(false);
                                        }
                                    }
                                }
                            }
                        }
                    }

                } else {
                    root = new DefaultTreeNode(new Document("Files", 0, "Folder"), null);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Metodo para recorrer los nodos seleccionados   

    public void displaySelectedNodes(TreeNode[] nodes) {
        if (nodes != null && nodes.length > 0) {
            try {

                DaoTDetallePermiso daoTDetPerm = new DaoTDetallePermiso();
                msg = daoTDetPerm.registrar(root.getChildren(), Integer.parseInt(usuario));
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);

            } catch (Exception ex) {
                Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void vaciarCajas() {
        tUsuario = new Usuario();
        clave = "";
        telefono = "";
        celular = "";
    }

    public void registrarDocente() {
        DaoTUsuario daoTusuario = new DaoTUsuario();

        try {
            DaoTTipoUsuario daoTipoUsuario = new DaoTTipoUsuario();
            TipoUsuario tipoUsuario = new TipoUsuario();
            tipoUsuario = (TipoUsuario) daoTipoUsuario.getTipoUsuarios("Prof");

            tUsuario.setClave(Class_Encript.getStringMessageDigest(this.clave, Class_Encript.SHA256));
            tUsuario.setTelefono(telefono.replaceAll("[()-]", ""));
            tUsuario.setCelular(celular.replaceAll("[()-]", ""));
            tUsuario.setEstado('1');
            band = daoTusuario.verificarUsuarioNick(tUsuario.getNick());
            if (band) {
                if (tipoUsuario != null) {
                    tUsuario.setTipoUsuario(tipoUsuario);
                    band = daoTusuario.registrar(tUsuario);
                    vaciarCajas();
                }
            } else {
                mensajesError("Usuario ya existe");
            }

        } catch (Exception e) {
            vaciarCajas();
        }
        if (band) {
            mensajesOk("Datos procesados correctamente");
        } else {
            mensajesError("Error al procesar datos");
        }
    }
    
    public void actualizarDatos(){
        
    }
    
    public void eliminarDatos(){
        
    }

    public void registrarUsuarios() {
        DaoTUsuario daoTusuario = new DaoTUsuario();

        try {
            DaoTTipoUsuario daoTipoUsuario = new DaoTTipoUsuario();
            TipoUsuario tipoUsuario = new TipoUsuario();

            tUsuario.setClave(Class_Encript.getStringMessageDigest(this.clave, Class_Encript.SHA256));
            tUsuario.setTelefono(telefono.replaceAll("[()-]", ""));
            tUsuario.setCelular(celular.replaceAll("[()-]", ""));
            tUsuario.setEstado('1');
            band = daoTusuario.verificarUsuarioNick(tUsuario.getNick());
            if (band) {
                tipoUsuario.setId(idRol);
                tUsuario.setTipoUsuario(tipoUsuario);
                band = daoTusuario.registrar(tUsuario);
                vaciarCajas();
            } else {
                mensajesError("Usuario ya existe");
            }

        } catch (Exception e) {
            vaciarCajas();
        }
        if (band) {
            mensajesOk("Datos procesados correctamente");
        } else {
            mensajesError("Error al procesar datos");
        }
    }
    
    public void cargarDatosDocentes(){
        DaoTUsuario daoTusuario = new DaoTUsuario();
        try {
            if(this.theme == null)
                this.estado = 0;
            else{
                this.estado = 1;
                this.tUsuario = daoTusuario.getUsuario(this.theme.getId());
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mensajesOk(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void mensajesError(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
