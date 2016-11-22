/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceMenu;
import Interface.InterfacePonderacion;
import Pojo.Ponderaciones;
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
public class DaoTPonderaciones implements InterfacePonderacion{

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
    public boolean registrar(Ponderaciones tPonderaciones) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tPonderaciones);

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
    public List<Ponderaciones> getPadres() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Ponderaciones as p where p.clave =0 and p.estado='1' order by p.descripcion ASC";
        Query query = sesion.createQuery(hql);
        List<Ponderaciones> lstPonderaciones=(List<Ponderaciones>) query.list();
        sesion.close();
        return lstPonderaciones;
    }

    @Override
    public List<Ponderaciones> getTodosPonderacioness(boolean mostrar) throws Exception {
        //Metodo para obtener el menu de usuariosActualizado
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        
        String consulta = "";
        String estado = "";
        if(mostrar)
            estado = "";
        else
            estado = "where p.estado = '1'";
        
        String hql="from Ponderaciones as p "+estado+" order by p.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Ponderaciones> lstPonderaciones=(List<Ponderaciones>) query.list();
        sesion.close();
        return lstPonderaciones;
    }

    @Override
    public Ponderaciones getPonderaciones(String idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Ponderaciones tPonderaciones) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.update(tPonderaciones);

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
    public boolean updatePadres(Ponderaciones tPonderaciones) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.update(tPonderaciones);

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
