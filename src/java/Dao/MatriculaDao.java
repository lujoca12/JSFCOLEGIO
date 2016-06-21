/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.Matricula;
import Pojo.SolicitudInscripcion;
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
public class MatriculaDao {

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

    public boolean insertar(Matricula tMatricula, SolicitudInscripcion SInscripcion) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tMatricula);
            sesion.saveOrUpdate(SInscripcion);
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

    public boolean existeMatricula(String Cedula, String Promocion) {
        boolean existe = false;
        try {
            this.sesion = null;
            this.tx = null;
            iniciaOperacion();
            String hql = "from Matricula m inner join fetch m.solicitudInscripcion si inner join fetch si.estudiante e "
                    + "inner join fetch si.promocion p where e.cedPasaporte='" + Cedula + "' and p.id='" + Promocion + "' ";
            Query query = sesion.createQuery(hql);
            //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<Matricula> lst = (List<Matricula>) query.list();
            existe = !lst.isEmpty();
            sesion.close();
        } catch (Exception ex) {

        }
        return existe;
    }
    public Matricula obtenerMatricula(String Cedula, String Promocion) {
        boolean existe = false;Matricula lst=null;
        try{
            this.sesion = null;
            this.tx = null;
            iniciaOperacion();
            String hql = "from Matricula m inner join fetch m.solicitudInscripcion si inner join fetch si.estudiante e "
                    + "inner join fetch si.promocion p where e.cedPasaporte='" + Cedula + "' and p.id='" + Promocion + "' ";
            Query query = sesion.createQuery(hql);
            //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            lst = (Matricula) query.uniqueResult();
        }catch(Exception ex){
            System.out.println(ex);
        }
            sesion.close();
        
        return lst;
    }
    
    public int obtenerNumeroMatricula(String Cedula, String Promocion) {
        int n = 0;
        
            this.sesion = null;
            this.tx = null;
            iniciaOperacion();
            String hql = "from Matricula m inner join fetch m.solicitudInscripcion si inner join fetch si.estudiante e "
                    + "inner join fetch si.promocion p where e.cedPasaporte='" + Cedula + "' and p.id='" + Promocion + "' ";
            Query query = sesion.createQuery(hql);
            //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            Matricula lst = (Matricula) query.uniqueResult();
            if(lst != null){
                n = Integer.valueOf(lst.getNMatricula());}
            else{
                n=0;}
            sesion.close();
        
        return n;
    }
    
     public List<SolicitudInscripcion> obtenerTodasSolicitudesSinMatriculas() {
        List<SolicitudInscripcion> lst = null;
        try {
            this.sesion = null;
            this.tx = null;
            iniciaOperacion();
            String hql="from SolicitudInscripcion si inner join fetch si.estudiante e inner join fetch si.promocion pr inner join fetch pr.maestria m"
                + " where si.matriculas is empty order by si.fechaRealizacion desc";
            Query query = sesion.createQuery(hql);
            //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            lst = (List<SolicitudInscripcion>) query.list();
            
            sesion.close();
        } catch (Exception ex) {

        }
        return lst;
    }

    public int VerificarNMatricula() {
        boolean existe = false;
        int n=0;
        try {
            this.sesion = null;
            this.tx = null;
            iniciaOperacion();
            String hql = "select max(m.NMatricula) from Matricula m ";
            Query query = sesion.createQuery(hql);
            //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<Matricula> lst = (List<Matricula>) query.list();
            existe = !lst.isEmpty();
            if (existe) {
                n = Integer.valueOf(lst.get(0).getNMatricula())+1;
            } else {
                n = 0;
            }
            sesion.close();
        } catch (Exception ex) {
                System.out.println(ex.toString());
        }
        return n;
    }
}
