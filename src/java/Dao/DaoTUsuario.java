/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceUsuario;
import Pojo.DetallePermiso;
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
public class DaoTUsuario implements InterfaceUsuario{
    private Session sesion;
    private Transaction tx;
    
    private void iniciaOperacion() throws HibernateException
    {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }
    private void manejaExcepcion(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    @Override
    public boolean registrar(Usuario tUsuario) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tUsuario);

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
    public List<Usuario> getTodosUsuarios() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "FROM Usuario";
        Query query = sesion.createQuery(hql);
        List<Usuario> lstUsuarios=(List<Usuario>) query.list();
        sesion.close();
        return lstUsuarios;
    }

    @Override
    public Usuario getUsuario(String idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Usuario tUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> getDocentes() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "from Usuario as u inner join fetch u.tipoUsuario as tu where tu.descripcion like '%Prof%' and u.estado='1' order by u.apellidos asc";
        Query query = sesion.createQuery(hql);
        List<Usuario> lstUsuarios=(List<Usuario>) query.list();
        sesion.close();
        return lstUsuarios;
    }
    
    @Override
    public boolean verificarUsuarioNick(String nick) throws Exception {
        boolean band = false;
        
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "from Usuario as u where u.nick='"+nick+"'";
        Query query = sesion.createQuery(hql);
        List<Usuario> lstUsuarios=(List<Usuario>) query.list();
        if(!lstUsuarios.isEmpty())
            band = false;
        else
            band = true;
        sesion.close();
        
        return band;
    }
    
    
    
}
