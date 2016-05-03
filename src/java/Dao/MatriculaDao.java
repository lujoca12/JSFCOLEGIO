/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.Matricula;
import Pojo.SolicitudInscripcion;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author chiti
 */
public class MatriculaDao {
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
    public boolean insertar(Matricula tMatricula,SolicitudInscripcion SInscripcion) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tMatricula);
            sesion.update(SInscripcion);
            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    public boolean rechazar(SolicitudInscripcion SInscripcion) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            
            sesion.update(SInscripcion);
            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
}
