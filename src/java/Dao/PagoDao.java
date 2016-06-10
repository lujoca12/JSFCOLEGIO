/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.Pago;
import Pojo.Precio;
import Pojo.TipoPrecio;
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
public class PagoDao {
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
    
    public boolean insertarPago(Pago pago) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(pago);
            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }

        return band;
    }
    public boolean insertarPrecio(Precio precio) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(precio);
            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }

        return band;
    }
    
    public TipoPrecio getTipoPrecio(String Id){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from TipoPrecio tp where tp.id = '"+Id+"' order by tp.descripcion asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TipoPrecio> lst=(List<TipoPrecio>) query.list();
        TipoPrecio u=null;
        for(TipoPrecio p : lst)
            u=p;
        sesion.close();
        return u; 
    }
    
    public List<Pago> getPagosAprobar() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from Pago p inner join fetch p.matricula m inner join fetch m.solicitudInscripcion si "
                + "inner join fetch si.estudiante inner join fetch si.promocion pr inner join fetch pr.maestria m inner join fetch p.tipoPago"
                + " order by p.fecha";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Pago> lst=(List<Pago>) query.list();
        sesion.close();
        return lst;
    }
    public Pago getPago(int id) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from Pago p where p.id ='"+id+"'";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Pago> lst=(List<Pago>) query.list();
        Pago pago = null;
        for (Pago p :lst)
            pago=p;
        sesion.close();
        return pago;
    }
    
    
}
