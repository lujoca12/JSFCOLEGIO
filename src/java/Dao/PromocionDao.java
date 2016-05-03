/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.Maestria;
import Pojo.Promocion;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Seeting
 */
public class PromocionDao {

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

    public List<Promocion> getPromocionesMaestrias(int IdMaestria) throws Exception {
        List<Promocion> lstPermiso = null;
        try {
            this.sesion = null;
            this.tx = null;
            iniciaOperacion();
            String hql = "from Promocion pr inner join fetch pr.maestria m where m.estado='1' and m.id='" + IdMaestria + "' and (year(current_date) >= year(pr.fechaInicio) and year(current_date)<= year(pr.fechaFin)) order by pr.id desc";
            Query query = sesion.createQuery(hql);
            lstPermiso = (List<Promocion>) query.list();
            sesion.close();
        } catch (Exception ex) {
        }
        return lstPermiso;
    }

    public List<Maestria> getMaestriasPromocion() throws Exception {
        List<Maestria> lst = null;
        try {

            this.sesion = null;
            this.tx = null;
            iniciaOperacion();
            
            String hql = "from Maestria m inner join fetch m.promocions p where m.estado='1' and p.estado='1' order by m.descripcion asc";
           
            Query query = sesion.createQuery(hql);
            //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            lst = (List<Maestria>) query.list();
            sesion.close();
        } catch (Exception ex) {
        }
        return lst;
    }
    
    public Promocion getPromocion(String Id){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from Promocion p where p.id = '"+Id+"' order by p.descripcion asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Promocion> lst=(List<Promocion>) query.list();
        Promocion u=null;
        for(Promocion p : lst)
            u=p;
        sesion.close();
        return u; 
    }

}
