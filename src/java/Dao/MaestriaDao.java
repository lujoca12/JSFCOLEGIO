/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.Maestria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author chiti
 */
public class MaestriaDao {
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
    
    public Maestria getMaestrias(Maestria maestria) throws Exception{
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        Maestria us= null;
//        try{            
////            String hql = "FROM Usuario WHERE nick='"+usuario.getNick()+ "' and clave='"+usuario.getClave()+"'";
////            Query query = session.createQuery(hql);
////            if(!query.list().isEmpty()){
////                us = (Usuario) query.list().get(0);
//            }
//        }catch (Exception ex){
//            throw ex;
//        }
        return null;
    }
}
