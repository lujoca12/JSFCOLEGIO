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
import Pojo.registrarpermisosxusuarios;
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
    private Document selectedDocument;
    
    private DetallePermiso dtp;
    private Permiso permiso = null;
    private Usuario user = null;
    private int idDetallePermiso=0;
    private int idPadre=0;
    private List<DetallePermiso> lstDetalleP= null;
    
    private void iniciaOperacion()
    {
        try{
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        }catch(HibernateException ex){
            
        }
    }

    public Document getSelectedDocument() {
        return selectedDocument;
    }

    public void setSelectedDocument(Document selectedDocument) {
        this.selectedDocument = selectedDocument;
    }
    
    private void manejaExcepcion(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
    @Override
    public String registrar(List<TreeNode> node, int idUsuario){
        
     //   tx.commit();
        char estado = ' ';
        String msg="";
        this.sesion = null;
        this.tx = null;
        int cont = 1;
        try {
           iniciaOperacion();
           
            for (int i = 0; i < node.size(); i++) {
                selectedDocument = (Document) node.get(i).getData();
                
                if(node.get(i).isSelected()){
                    estado = '1';
                }else{
                    estado = '0';
                }
                idDetallePermiso = existeDetallePermiso(idUsuario, selectedDocument.getId());
                if(idDetallePermiso > 0){
                    asignarPermisosxUsuario(selectedDocument, estado, idUsuario, idDetallePermiso);
                }else{
                    idDetallePermiso = getUltimoIdDetallePermiso();
                    asignarPermisosxUsuario(selectedDocument, estado, idUsuario, idDetallePermiso+cont);
                    cont++;
                }
                //recorriendo los hijos
                for (int j = 0; j < node.get(i).getChildCount(); j++) {
                    selectedDocument = (Document) node.get(i).getChildren().get(j).getData();
                    
                    if(node.get(i).getChildren().get(j).isSelected()){
                        estado = '1';
                    }else{
                        estado = '0';
                    }
                        idDetallePermiso = existeDetallePermiso(idUsuario, selectedDocument.getId());
                    if(idDetallePermiso > 0){
                        asignarPermisosxUsuario(selectedDocument, estado, idUsuario, idDetallePermiso);
                    }else{
                        idDetallePermiso = getUltimoIdDetallePermiso();
                        asignarPermisosxUsuario(selectedDocument, estado, idUsuario, idDetallePermiso+cont);
                        cont++;
                    }
                }
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
        user = new Usuario();

        permiso.setId(selectedDocument.getId());
        user.setId(idUsuario);
        //dtp.setId(null);
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
