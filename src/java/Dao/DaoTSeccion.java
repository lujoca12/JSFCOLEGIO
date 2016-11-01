/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceSeccion;
import Pojo.Modalidad;
import Pojo.Seccion;
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
public class DaoTSeccion implements InterfaceSeccion{
    
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
    public List<Seccion> getTodasSeccions() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Seccion secc inner join fetch secc.modalidad modal where secc.estado = '1' order by secc.id desc";
        Query query = sesion.createQuery(hql);
        List<Seccion> lstseccion=(List<Seccion>) query.list();
        sesion.close();
        return lstseccion;
    }

    @Override
    public boolean registrar(Seccion tSeccion) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tSeccion);

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
    public boolean existe(Seccion tSeccion) throws Exception {
        this.sesion = null;
        this.tx = null;
        boolean band = false;
        iniciaOperacion();
        String hql="from Seccion secc where secc.descripcion='"+tSeccion.getDescripcion()+"' and secc.estado = '1'";
        Query query = sesion.createQuery(hql);
        List<Seccion> seccion=(List<Seccion>) query.list();
        if(seccion.size() > 0)
            band = true;
        else
            band = false;
        
        sesion.close();
        return band;
    }
    @Override
    public List<Seccion> getSeccionsxDescripcion(String descripcion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Seccion tSeccion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Seccion tSeccion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Seccion getSeccions(String idSeccion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Seccion> getSeccion() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
