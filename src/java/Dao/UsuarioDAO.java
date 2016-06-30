/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.Postgrado;
import Pojo.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author chiti
 */
public class UsuarioDAO {

    private Session sesion;
    private Transaction tx;

    private void iniciaOperacion() {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        } catch (HibernateException ex) {

        }

    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    public Usuario verificarDatos(Usuario usuario) throws Exception {
        Usuario us = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Usuario user inner join fetch user.tipoUsuario tuser WHERE nick='" + usuario.getNick() + "' and "
                    + "clave='" + usuario.getClave() + "' and user.estado = '1'";
            Query query = sesion.createQuery(hql);
            if (!query.list().isEmpty()) {
                us = (Usuario) query.list().get(0);
            }
            sesion.close();
        } catch (Exception ex) {
            throw ex;
        }
        return us;
    }

    public boolean existeNick(String Nick) throws Exception {
        Usuario us = null;
        boolean existe = false;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Usuario u where u.nick='" + Nick + "' ";
            Query query = sesion.createQuery(hql);
            us = (Usuario) query.uniqueResult();
            existe = us != null;
            sesion.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return existe;
    }

    public boolean insertar(Usuario u,String NickViejo) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(u);
            String sql = String.format("alter user "+NickViejo+" rename to "+u.getNick()+" ; "
                    + "alter user "+u.getNick()+" password '"+u.getClave()+"'");
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

}
