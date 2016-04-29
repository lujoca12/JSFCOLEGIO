/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.Maestria;
import Pojo.Requisitos;
import Pojo.RequisitosPromo;
import java.io.Serializable;
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
public class RequisitosDao {
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
    public boolean insertar(Requisitos tRequisitos) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tRequisitos);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    public List<Requisitos> getTodosRequisitos() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from Requisitos r where r.estado='1' order by r.descripcion asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Requisitos> lstRequisito=(List<Requisitos>) query.list();
        sesion.close();
        return lstRequisito;
    }
    
         
     public List<RequisitosPromo> getRequisitosPromocion(String IdPromo,String IdMaestria) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from RequisitosPromo rp inner join fetch rp.requisitos r inner join fetch rp.promocion pr inner join fetch pr.maestria m where rp.estado='1' and pr.id='"+IdPromo+"' and m.id='"+IdMaestria+"' "; 
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<RequisitosPromo> lstRequisito=(List<RequisitosPromo>) query.list();
        sesion.close();
        return lstRequisito;
    }
    
    
}
