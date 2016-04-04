/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceMenu;
import Pojo.DetallePermiso;
import Pojo.Permiso;
import Pojo.Usuario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author server
 */
public class DaoTMenu implements InterfaceMenu{

    private Session sesion;
    private Transaction tx;
    
    private void iniciaOperacion()
    {
        try{
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        }catch(HibernateException ex){
            
        }
        
    }
     
    private void manejaExcepcion(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
    
    @Override
    public boolean registrar(Permiso tPermiso){
        
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tPermiso);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }

    @Override
    public List<Permiso> getTodosPermisos() throws Exception {
        //Metodo para obtener el menu de usuariosActualizado
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Permiso as p where p.estado = '1' order by p.orden, p.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Permiso> lstPermiso=(List<Permiso>) query.list();
        sesion.close();
        return lstPermiso;
    }

    @Override
    public Permiso getPermiso(String idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Permiso tPermiso) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.update(tPermiso);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    @Override
    public boolean updatePadres(Permiso tPermiso) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.update(tPermiso);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }

    @Override
    public List<Permiso> getMenuNavxUsuarios(Usuario usuario) throws Exception {
        this.sesion = null;
        this.tx = null;
        //int codigo = 1;
        iniciaOperacion();
        //from Usuario as u inner join  u.detallePermisos as dp where u.id=1
        //String hql = "FROM DetallePermiso";
        String hql = "from Permiso as p inner join fetch  p.detallePermisos as dp where dp.usuario=:codigo and p.estado='1' and dp.estado='1' order by p.orden, p.descripcion asc";
        
        Query query = sesion.createQuery(hql).setInteger("codigo", usuario.getId());
        //query.setParameter("id", tx)
        List<Permiso> lstPermiso=(List<Permiso>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    public List<DetallePermiso> getEstadoPermisoUsuario(int usuarioId, int permisoId){
        this.sesion = null;
        this.tx = null;
        boolean band = false;
        iniciaOperacion();
        String hql = "from DetallePermiso as dp where dp.usuario.id =:usuario and dp.permiso.id=:permiso";
        Query query = sesion.createQuery(hql);
        query.setInteger("usuario", usuarioId);
        query.setInteger("permiso", permisoId);
        List<DetallePermiso> lstDetalleP = (List<DetallePermiso>) query.list();
        sesion.close();
        
        return lstDetalleP;
    }

    @Override
    public List<DetallePermiso> getUltimoIdDetallePermiso() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "from DetallePermiso as dp order by dp.id desc";
        Query query = sesion.createQuery(hql);
        List<DetallePermiso> lstDetallePermiso=(List<DetallePermiso>) query.list();
        sesion.close();
        
        return lstDetallePermiso;
    }

    @Override
    public List<Permiso> getPadres() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Permiso as p where p.padre =0 and p.estado='1' order by p.descripcion ASC";
        Query query = sesion.createQuery(hql);
        List<Permiso> lstPermiso=(List<Permiso>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    
}
