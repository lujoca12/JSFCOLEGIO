/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceNotas;
import Pojo.Notas;
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
public class DaoTNotas implements InterfaceNotas{
    
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
    public boolean registrar(Notas tNotas) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tNotas);

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
    public List<Notas> getTodasNotas(int idMatricula) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //String hql="from Notas nota inner join fetch nota.modulo modul where nota.modulo = "+idModulo+" and "
          //      + "nota.matricula="+idMatricula+" order by modul.descripcion asc";
        String hql="from Notas nota inner join fetch nota.modulo modul where "
                + "nota.matricula="+idMatricula+" order by modul.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Notas> lstNotas=(List<Notas>) query.list();
        sesion.close();
        return lstNotas;
    }

    @Override
    public Notas getNotas(String idNotas) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Notas tNotas) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
