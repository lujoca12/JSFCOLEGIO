/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.Postgrado;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author chiti
 */
public class postgradoDao {

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

    public boolean insertar(Postgrado tPostgrado) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tPostgrado);
            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }

        return band;
    }

    public Postgrado getPostgrado() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();

        String hql = "from Postgrado";
        Postgrado p = null;
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        p = (Postgrado) query.uniqueResult();
        
        sesion.close();
        return p;
    }
    
    public int getNMatricula(){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();

        String hql = "from Postgrado";
        Postgrado p = null;
        Query query = sesion.createQuery(hql);
        p = (Postgrado) query.uniqueResult();        
        sesion.close();
        return p.getNumMatricula();
    }
}
