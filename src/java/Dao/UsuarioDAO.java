/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;



import Pojo.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author chiti
 */
public class UsuarioDAO {
    private Session session;
    
    public Usuario verificarDatos(Usuario usuario) throws Exception{
        Usuario us= null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Usuario user inner join fetch user.tipoUsuario tuser WHERE nick='"+usuario.getNick()+ "' and clave='"+usuario.getClave()+"'";
            Query query = session.createQuery(hql);
            if(!query.list().isEmpty()){
                us = (Usuario) query.list().get(0);
            }
        }catch (Exception ex){
            throw ex;
        }
        return us;
    }
    
    
}
