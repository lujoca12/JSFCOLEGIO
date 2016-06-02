/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Clases.ClsNotas;
import Interface.InterfaceNotas;
import Interface.InterfacePagos;
import Pojo.TipoPago;
import Pojo.Pago;
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
public class PagosDao implements InterfacePagos{
    
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
    
    
    @Override
    public List<Pago> getTodosPagos(int idMatricula) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //String hql="from Notas nota inner join fetch nota.modulo modul where nota.modulo = "+idModulo+" and "
          //      + "nota.matricula="+idMatricula+" order by modul.descripcion asc";
        String hql="from Pago p where p.matricula='"+idMatricula+"' order by p.fecha asc";
        Query query = sesion.createQuery(hql);
        List<Pago> lstNotas=(List<Pago>) query.list();
        sesion.close();
        return lstNotas;
    }
    @Override
     public TipoPago getTipoPagoBanco() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //String hql="from Notas nota inner join fetch nota.modulo modul where nota.modulo = "+idModulo+" and "
          //      + "nota.matricula="+idMatricula+" order by modul.descripcion asc";
        String hql="from TipoPago tp where tp.descripcion ='banco'";
        Query query = sesion.createQuery(hql);
        TipoPago p = null ;
        List<TipoPago> lst=(List<TipoPago>) query.list();
        if(!lst.isEmpty())
            p = lst.get(0);
        sesion.close();
        return p;
    }

    @Override
    public boolean registrar(Pago pago) throws Exception {
        boolean band = false;
        try {
                                   
            iniciaOperacion();
           sesion.save(pago);
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
