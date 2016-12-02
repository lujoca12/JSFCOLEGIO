/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsGenerarUserClaves;
import Clases.ClsProfesor;
import Clases.Document;
import Dao.DaoTDetallePermiso;
import Dao.DaoTMenu;
import Dao.DaoTTipoUsuario;
import Dao.DaoTUsuario;
import Dao.postgradoDao;
import Pojo.DetallePermiso;
import Pojo.Permiso;
import Pojo.Postgrado;
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

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    private boolean estadoCorreo;
    private boolean cedOpasap;
    private boolean mostrarEliminados;

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

    public boolean isCedOpasap() {
        return cedOpasap;
    }

    public void setCedOpasap(boolean cedOpasap) {
        this.cedOpasap = cedOpasap;
    }

    public boolean isMostrarEliminados() {
        return mostrarEliminados;
    }

    public void setMostrarEliminados(boolean mostrarEliminados) {
        this.mostrarEliminados = mostrarEliminados;
    }
    
    
    public void llenarCboDocentes() {
        estado =0;
        this.lstTheme = new ArrayList<ClsProfesor>();
        try {
            DaoTUsuario daoTusuario = new DaoTUsuario();

            List<Usuario> lstUsuario = daoTusuario.getDocentes(mostrarEliminados);
            
            
            this.lstTheme.add(new ClsProfesor(-1, "Ninguno", "Ninguno"));
            for (Usuario user : lstUsuario) {
                this.lstTheme.add(new ClsProfesor(user.getId(), 
                        user.getApellidos() + " " + user.getNombres(), 
                        String.valueOf(user.getTipoUsuario().getId())));
            }
        } catch (Exception ex) {

        }
        
    }
    
    public void llenarCboUsuarios() {
        estado =0;
        this.lstThemeUsuarios = new ArrayList<ClsProfesor>();
        try {
            DaoTUsuario daoTusuario = new DaoTUsuario();

            List<Usuario> lstUsuario = daoTusuario.getTodosUsuarios(mostrarEliminados);
            if(this.lstThemeUsuarios.size() > 0)
                this.lstThemeUsuarios.clear();
            
            this.lstThemeUsuarios.add(new ClsProfesor(-1, "Ninguno", "Ninguno"));
            for (Usuario user : lstUsuario) {
                this.lstThemeUsuarios.add(new ClsProfesor(user.getId(), 
                        user.getApellidos() + " " + user.getNombres()+" ("+user.getTipoUsuario().getDescripcion()+")", 
                        String.valueOf(user.getTipoUsuario().getId())));
            }
        } catch (Exception ex) {

        }
    }

    public List<SelectItem> getLstTodosUsuarios() {
        this.lstTodosUsuarios = new ArrayList<SelectItem>();
        try {
            DaoTTipoUsuario daoTtipoUsuario = new DaoTTipoUsuario();

            List<TipoUsuario> lstTtipoUsuario = daoTtipoUsuario.getTodosTipoUsuarios();
            if(lstTodosUsuarios.size() > 0)
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

        List<Usuario> lstUser = daoTUsuario.getTodosUsuarios(mostrarEliminados);
        if(lstUsuario.size() > 0)
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
            if(this.theme == null){
                this.estado = 0; 
                root = new DefaultTreeNode(new Document(0,"Files", 0, "Folder",0,'0'), null);
            } else {
                this.estado = 1;
                DaoTMenu daoTmenu = new DaoTMenu();
                lstMenus = daoTmenu.getTodosPermisos(false);
                if (lstMenus != null) {
                    root = new DefaultTreeNode(new Document(0,"Files", 0, "Folder",0,'0'), null);
                    for (Permiso p : lstMenus) {
                        if (p.getPadre() == 0) {
                            node0 = new DefaultTreeNode(new Document(p.getId(),p.getDescripcion(),p.getPadre(),p.getForm(),p.getOrden(),p.getEstado()), root);

                            lstMenusDetalle = daoTmenu.getEstadoPermisoUsuario(this.theme.getId(), p.getId());
                            if (!lstMenusDetalle.isEmpty()) {
                                if (lstMenusDetalle.get(0).getEstado().equals('1')) {
                                    node0.setSelected(true);
                                } else {
                                    node0.setSelected(false);
                                }
                            }

                            for (Permiso p1 : lstMenus) {
                                if (p.getId() == p1.getPadre()) {
                                    node00 = new DefaultTreeNode(new Document(p1.getId(),p1.getDescripcion(),p1.getPadre(),p1.getForm(),p1.getOrden(),p1.getEstado()), node0);
                                    node00.setRowKey(String.valueOf(p1.getId()));

                                    daoTmenu = new DaoTMenu();
                                    lstMenusDetalle = daoTmenu.getEstadoPermisoUsuario(this.theme.getId(), p1.getId());
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
                    root = new DefaultTreeNode(new Document(0,"Files", 0, "Folder",0,'0'), null);
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
                msg = daoTDetPerm.registrar(root.getChildren(), this.theme.getId());
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
        cedOpasap = false;
    }

    public void registrarDocente() {
        DaoTUsuario daoTusuario = new DaoTUsuario();

        try {
            DaoTTipoUsuario daoTipoUsuario = new DaoTTipoUsuario();
            TipoUsuario tipoUsuario = new TipoUsuario();
            tipoUsuario = (TipoUsuario) daoTipoUsuario.getTipoUsuarios("Prof");

            //tUsuario.setClave(Class_Encript.getStringMessageDigest(this.clave, Class_Encript.SHA256));
            tUsuario.setTelefono(telefono.replaceAll("[()-]", ""));
            tUsuario.setCelular(celular.replaceAll("[()-]", ""));
            tUsuario.setEstado('1');
            
            String usuarioGenerado = ClsGenerarUserClaves.getUsuarioAleatorio(10);
            String claveGenerada = ClsGenerarUserClaves.getPassword(ClsGenerarUserClaves.MINUSCULAS.concat(ClsGenerarUserClaves.MAYUSCULAS).concat(ClsGenerarUserClaves.ESPECIALES),10);
            tUsuario.setClave(Class_Encript.getStringMessageDigest(claveGenerada, Class_Encript.SHA256));
            tUsuario.setNick(usuarioGenerado);
            
            band = daoTusuario.verificarUsuarioNick(tUsuario.getNick());
            if (band) {
                if (tipoUsuario != null) {
                    tUsuario.setTipoUsuario(tipoUsuario);
                    enviarEmail(claveGenerada);
                    if(estadoCorreo){
                        band = daoTusuario.registrar(tUsuario);
                        vaciarCajas();
                    }
                    
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
        DaoTUsuario daoTusuario = new DaoTUsuario();
        try {
            TipoUsuario tipoUser = new TipoUsuario();
            tipoUser.setId(Integer.parseInt(this.theme.getName()));
            tUsuario.setTipoUsuario(tipoUser);
            tUsuario.setTelefono(tUsuario.getTelefono().replaceAll("[()-]", ""));
            tUsuario.setCelular(tUsuario.getCelular().replaceAll("[()-]", ""));
            
//            String usuarioGenerado = ClsGenerarUserClaves.getUsuarioAleatorio(10);
//            String claveGenerada = ClsGenerarUserClaves.getPassword(ClsGenerarUserClaves.MINUSCULAS.concat(ClsGenerarUserClaves.MAYUSCULAS).concat(ClsGenerarUserClaves.ESPECIALES),10);
//            tUsuario.setClave(Class_Encript.getStringMessageDigest(claveGenerada, Class_Encript.SHA256));
//            tUsuario.setNick(usuarioGenerado);
            
            
//             enviarEmail(claveGenerada);
//                    if(estadoCorreo){
                        band = daoTusuario.update(tUsuario);
//                    }
            
        } catch (Exception ex) {
            Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (band) {
            this.estado = 0;
            mensajesOk("Datos actualizados correctamente");
            llenarCboDocentes();
            llenarCboUsuarios();
        } else {
            this.estado = 1;
            mensajesError("Error al actualizar datos");
        }
    }
    
    public void recuperar(){
        DaoTUsuario daoTusuario = new DaoTUsuario();
        try {
            TipoUsuario tipoUser = new TipoUsuario();
            tipoUser.setId(Integer.parseInt(this.theme.getName()));
            tUsuario.setTipoUsuario(tipoUser);
            tUsuario.setTelefono(tUsuario.getTelefono().replaceAll("[()-]", ""));
            tUsuario.setCelular(tUsuario.getCelular().replaceAll("[()-]", ""));
            
            String usuarioGenerado = ClsGenerarUserClaves.getUsuarioAleatorio(10);
            String claveGenerada = ClsGenerarUserClaves.getPassword(ClsGenerarUserClaves.MINUSCULAS.concat(ClsGenerarUserClaves.MAYUSCULAS).concat(ClsGenerarUserClaves.ESPECIALES),10);
            tUsuario.setClave(Class_Encript.getStringMessageDigest(claveGenerada, Class_Encript.SHA256));
            tUsuario.setNick(usuarioGenerado);
            tUsuario.setEstado('1');
            
             enviarEmail(claveGenerada);
                    if(estadoCorreo){
                        band = daoTusuario.update(tUsuario);
                    }
            
        } catch (Exception ex) {
            Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (band) {
            this.estado = 0;
            mensajesOk("Usuario recuperado correctamente");
            llenarCboDocentes();
            llenarCboUsuarios();
        } else {
            this.estado = 1;
            mensajesError("Error al recuperar usuario");
        }
    }
    
    public void eliminarDatos(){
        DaoTUsuario daoTusuario = new DaoTUsuario();
        try {
            TipoUsuario tipoUser = new TipoUsuario();
            tipoUser.setId(Integer.parseInt(this.theme.getName()));
            tUsuario.setTipoUsuario(tipoUser);
            tUsuario.setEstado('0');
            tUsuario.setTelefono(tUsuario.getTelefono().replaceAll("[()-]", ""));
            tUsuario.setCelular(tUsuario.getCelular().replaceAll("[()-]", ""));
            band = daoTusuario.update(tUsuario);
        } catch (Exception ex) {
            Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (band) {
            this.estado = 0;
            mensajesOk("Datos eliminados correctamente");
            llenarCboDocentes();
            llenarCboUsuarios();
        } else {
            this.estado = 1;
            mensajesError("Error al eliminar datos");
        }
    }
        
    public void registrarUsuarios() {
        DaoTUsuario daoTusuario = new DaoTUsuario();

        try {
            DaoTTipoUsuario daoTipoUsuario = new DaoTTipoUsuario();
            TipoUsuario tipoUsuario = new TipoUsuario();

            //tUsuario.setClave(Class_Encript.getStringMessageDigest(this.clave, Class_Encript.SHA256));
            tUsuario.setTelefono(telefono.replaceAll("[()-]", ""));
            tUsuario.setCelular(celular.replaceAll("[()-]", ""));
            tUsuario.setEstado('1');
            
            String usuarioGenerado = ClsGenerarUserClaves.getUsuarioAleatorio(10);
            String claveGenerada = ClsGenerarUserClaves.getPassword(ClsGenerarUserClaves.MINUSCULAS.concat(ClsGenerarUserClaves.MAYUSCULAS).concat(ClsGenerarUserClaves.ESPECIALES),10);
            tUsuario.setClave(Class_Encript.getStringMessageDigest(claveGenerada, Class_Encript.SHA256));
            tUsuario.setNick(usuarioGenerado);
            
            band = daoTusuario.verificarUsuarioNick(tUsuario.getNick());
            if (band) {
                tipoUsuario.setId(idRol);
                tUsuario.setTipoUsuario(tipoUsuario);
//                if(validarCedula()){
                    enviarEmail(claveGenerada);
                    if(estadoCorreo){
                        band = daoTusuario.registrar(tUsuario);
                        vaciarCajas();
                    }
//                }else{
//                    return;
//                }
                
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
    
    public void enviarEmail(String claveGen) throws Exception {
        postgradoDao psgDao = new postgradoDao();
        Postgrado psg = psgDao.getPostgrado();
        if(psg.getEmail() != null || psg.getEmail().isEmpty()){
        final String username = psg.getEmail();
        final String password = psg.getClaveEmail();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            
            
            String msj = "Saludos Sr(a). "+tUsuario.getNombres()+" "+tUsuario.getApellidos()+" \n Bienvenido a la Unidad Educativa Libertador \n usuario: "+tUsuario.getNick()+" \n clave:"+claveGen+"";
                    

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(tUsuario.getEmail()));
            message.setSubject("Unidad Educativa Libertador");
            //message.setText("La entrevista tendrá lugar en " + lugar + " el " + dateFormat.format(fecha));
            message.setText(msj);
            Transport.send(message);
            estadoCorreo = true;
            
        } catch (AddressException e) {
            System.out.println(e.toString());
            FacesMessage m = new FacesMessage("Error", "La dirección de correo no existe");
            FacesContext.getCurrentInstance().addMessage(null, m);
            estadoCorreo = false;
            // ...
        } catch (MessagingException e) {
            // ...
            System.out.println(e.toString());
            FacesMessage m = new FacesMessage("Error", "No se ha podido conectar con el servidor, inténtelo de nuevo");
            FacesContext.getCurrentInstance().addMessage(null, m);
            estadoCorreo = false;
        } catch (Exception ex) {
            
            System.out.println(ex.toString());
            FacesMessage m = new FacesMessage("Error", "No se ha podido guardar los datos");
            FacesContext.getCurrentInstance().addMessage(null, m);
            estadoCorreo = false;
        }
        }else{
            FacesMessage m = new FacesMessage("Error", "No se tiene un correo registrado en el sistema");
            FacesContext.getCurrentInstance().addMessage(null, m);
            estadoCorreo = false;
        }

    }
    
//    private boolean validarCedula() {
//
//        boolean cedulaCorrecta = false;
//        //alert(cedula);
//        try {
//
//            if (tUsuario.getCedPasaporte().length() == 10) // ConstantesApp.LongitudCedula
//            {
//                int tercerDigito = Integer.parseInt(tUsuario.getCedPasaporte().substring(2, 3));
//                if (tercerDigito < 6) {
//// Coeficientes de validación cédula
//// El decimo digito se lo considera dígito verificador
//                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
//                    int verificador = Integer.parseInt(tUsuario.getCedPasaporte().substring(9, 10));
//                    int suma = 0;
//                    int digito = 0;
//                    for (int i = 0; i < coefValCedula.length; i++) {
//                        digito = Integer.parseInt(tUsuario.getCedPasaporte().substring(i, i + 1)) * coefValCedula[i];
//                        suma += ((digito % 10) + (digito / 10));
//                    }
//
//                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
//                        cedulaCorrecta = true;
//                    } else if ((10 - (suma % 10)) == verificador) {
//                        cedulaCorrecta = true;
//                    } else {
//                        cedulaCorrecta = false;
//                    }
//                } else {
//                    cedulaCorrecta = false;
//                }
//            } else {
//                cedulaCorrecta = false;
//            }
//        } catch (NumberFormatException nfe) {
//            cedulaCorrecta = false;
//        } catch (Exception err) {
//            mensajesError("Una excepcion ocurrio en el proceso de validadcion");
//            cedulaCorrecta = false;
//        }
//
//        if (!cedulaCorrecta) {
//            mensajesError("La Cédula ingresada es Incorrecta");
//        }
//        return cedulaCorrecta;
//    }
        
    
    public void cargarDatosDocentes(){
        DaoTUsuario daoTusuario = new DaoTUsuario();
        try {
            if(this.theme == null)
                this.estado = 0;
            else{
                this.estado = 1;
                this.tUsuario = daoTusuario.getUsuarioEdicion(this.theme.getId());
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
