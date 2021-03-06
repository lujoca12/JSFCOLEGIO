/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceUsuario;
import Pojo.DetallePermiso;
import Pojo.Persona;
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
public class DaoTUsuario implements InterfaceUsuario {

    private Session sesion;
    private Transaction tx;

    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    @Override
    public boolean registrar(Usuario tUsuario) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tUsuario);
            String sql = String.format("create user " + tUsuario.getNick() + " superuser password '" + tUsuario.getClave() + "' ;");
            sesion.createSQLQuery(sql).executeUpdate();

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
            System.out.println(e.toString());
        }

        return band;
    }

    @Override
    public List<Usuario> getTodosUsuarios(boolean mostrar) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String estado = "";
        if (mostrar) {
            estado = "0";
        } else {
            estado = "1";
        }
        String hql = "FROM Usuario user inner join fetch user.tipoUsuario tuser where user.estado='" + estado + "' order by user.apellidos asc";
        Query query = sesion.createQuery(hql);
        List<Usuario> lstUsuarios = (List<Usuario>) query.list();
        sesion.close();
        return lstUsuarios;
    }

    @Override
    public Usuario getUsuario(int idUsuario) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "FROM Usuario user where user.id=" + idUsuario + " and user.estado='1'";
        Query query = sesion.createQuery(hql);
        Usuario usuario = (Usuario) query.uniqueResult();
        sesion.close();
        return usuario;
    }

    @Override
    public Usuario getUsuarioEdicion(int idUsuario) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "FROM Usuario user where user.id=" + idUsuario + "";
        Query query = sesion.createQuery(hql);
        Usuario usuario = (Usuario) query.uniqueResult();
        sesion.close();
        return usuario;
    }

    @Override
    public boolean update(Usuario tUsuario) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.update(tUsuario);            
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
    public List<Usuario> getDocentes(boolean mostrar) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String estado = "";
        if (mostrar) {
            estado = "0";
        } else {
            estado = "1";
        }
        String hql = "from Usuario as u inner join fetch u.tipoUsuario as tu where tu.descripcion like '%Prof%' and "
                + "u.estado='" + estado + "' order by u.apellidos asc";
        Query query = sesion.createQuery(hql);
        List<Usuario> lstUsuarios = (List<Usuario>) query.list();
        sesion.close();
        return lstUsuarios;
    }

    @Override
    public boolean verificarUsuarioNick(String nick) throws Exception {
        boolean band = false;

        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "from Usuario as u where u.nick='" + nick + "' and u.estado='1'";
        Query query = sesion.createQuery(hql);
        List<Usuario> lstUsuarios = (List<Usuario>) query.list();
        if (!lstUsuarios.isEmpty()) {
            band = false;
        } else {
            band = true;
        }
        sesion.close();

        return band;
    }

    @Override
    public boolean registrarPersona(Persona tPersona) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tPersona);

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
    public List<Persona> getPersonas() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "from Persona p\n"
                + "inner join fetch p.cargo";
        Query query = sesion.createQuery(hql);
        List<Persona> lstUsuarios = (List<Persona>) query.list();
        sesion.close();
        return lstUsuarios;
    }

}
