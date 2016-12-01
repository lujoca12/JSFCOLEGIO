/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceDetallePermiso;
import Pojo.DetallePermiso;
import Pojo.Permiso;
import Pojo.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Clases.Document;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.TreeNode;
import util.HibernateUtil;

/**
 *
 * @author server
 */
public class DaoTDetallePermiso implements InterfaceDetallePermiso{

    private Session sesion;
    private Transaction tx;
    private Document selectedDocPadre;
    private Document selectedDocHijo;
    
    private DetallePermiso dtp;
    private Permiso permiso;
    private Usuario user;
    private int idDetPermHijo;
    private int idDetPermPadre;
    private int idPadre;
    private List<DetallePermiso> lstDetalleP;
    
    private void iniciaOperacion()
    {
        try{
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        }catch(HibernateException ex){
            
        }
    }

    public DetallePermiso getDtp() {
        return dtp;
    }

    public void setDtp(DetallePermiso dtp) {
        this.dtp = dtp;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public int getIdDetPermHijo() {
        return idDetPermHijo;
    }

    public void setIdDetPermHijo(int idDetPermHijo) {
        this.idDetPermHijo = idDetPermHijo;
    }

    public int getIdDetPermPadre() {
        return idDetPermPadre;
    }

    public void setIdDetPermPadre(int idDetPermPadre) {
        this.idDetPermPadre = idDetPermPadre;
    }

    public int getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }

    public List<DetallePermiso> getLstDetalleP() {
        return lstDetalleP;
    }

    public void setLstDetalleP(List<DetallePermiso> lstDetalleP) {
        this.lstDetalleP = lstDetalleP;
    }
    
    public Document getSelectedDocPadre() {
        return selectedDocPadre;
    }

    public void setSelectedDocPadre(Document selectedDocPadre) {
        this.selectedDocPadre = selectedDocPadre;
    }

    public Document getSelectedDocHijo() {
        return selectedDocHijo;
    }

    public void setSelectedDocHijo(Document selectedDocHijo) {
        this.selectedDocHijo = selectedDocHijo;
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
    @Override
    public String registrar(List<TreeNode> node, int idUsuario){
        
     //   tx.commit();
        permiso = new Permiso();
        user = new Usuario();
        
        char estado = ' ';
        String msg="";
        boolean band = false;
        this.sesion = null;
        this.tx = null;
        try {
           iniciaOperacion();
           
            for (int i = 0; i < node.size(); i++) {
                selectedDocPadre = (Document) node.get(i).getData();
                
                
                //recorriendo los hijos
                for (int j = 0; j < node.get(i).getChildCount(); j++) {
                    selectedDocHijo = (Document) node.get(i).getChildren().get(j).getData();
                    
                    if(node.get(i).getChildren().get(j).isSelected()){
                        band = true;
                        estado = '1';
                    }else{
                        estado = '0';
                    }
                    
                       idDetPermHijo = existeDetallePermiso(idUsuario, selectedDocHijo.getId());
                    if(idDetPermHijo > 0){
                        asignarPermisosxUsuario(selectedDocHijo, estado, idUsuario, idDetPermHijo);
                    }else{
                       // idDetallePermiso = getUltimoIdDetallePermiso();
                        asignarPermisosxUsuario(selectedDocHijo, estado, idUsuario, 0);
                    }
                }
                if(band){
                    estado = '1';
                }else{
                    estado = '0';
                }
                idDetPermPadre = existeDetallePermiso(idUsuario, selectedDocPadre.getId());
                if(idDetPermPadre > 0){
                    asignarPermisosxUsuario(selectedDocPadre, estado, idUsuario, idDetPermPadre);
                }else{
//                    idDetallePermiso = getUltimoIdDetallePermiso();
                    asignarPermisosxUsuario(selectedDocPadre, estado, idUsuario, 0);
                }
                band = false;
            }
            
            tx.commit();
            msg = "Datos procesados correctamente";
            sesion.close();
        } catch (Exception e) {
            msg= e.getMessage();
            tx.rollback();
        }
        return msg;
    }
    
    private void asignarPermisosxUsuario(Document selectedDocument, char estado, int idUsuario, int idDtp){
        //iniciaOperacion();
        permiso = new Permiso();
        dtp = new DetallePermiso();
        permiso.setId(selectedDocument.getId());
        user.setId(idUsuario);
        //dtp.setId(null);
        if(idDtp > 0)
            dtp.setId(idDtp);
        
        dtp.setEstado(estado);
        dtp.setPermiso(permiso);
        dtp.setUsuario(user);
        sesion.saveOrUpdate(dtp);
        
        
       
        //sesion.close();
    }
    
    private int existeDetallePermiso(int usuarioId, int permisoId){
        int idDtp = 0;
        DaoTMenu daoTmenu = new DaoTMenu();
        lstDetalleP = daoTmenu.getEstadoPermisoUsuario(usuarioId, permisoId);
        if(!lstDetalleP.isEmpty()){
            idDtp = lstDetalleP.get(0).getId();
        }
        return idDtp;
    }
    private int getUltimoIdDetallePermiso(){
        int ultimoRegistro = 0;
        try {
            
            DaoTMenu daoTmenu = new DaoTMenu();
            
            lstDetalleP = daoTmenu.getUltimoIdDetallePermiso();
            if(!lstDetalleP.isEmpty()){
                ultimoRegistro = lstDetalleP.get(0).getId();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(DaoTDetallePermiso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ultimoRegistro;
    }

    @Override
    public List<DetallePermiso> getTodosDetPermisos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DetallePermiso getPermiso(String idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(DetallePermiso tDetPermiso) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
