/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Interface.InterfaceTitulacion;
import Pojo.Maestria;
import Pojo.Matricula;
import Pojo.TipoTitulacion;
import Pojo.PalabrasClave;
import Pojo.TipoTitulacion;
import Pojo.Titulacion;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author SERVER
 */
public class DaoTitulacion implements InterfaceTitulacion{
    private Session sesion;
    private Transaction tx;
    private Session sesion1;
    private Transaction tx1;
    private void iniciaOperacion()
    {
        try{
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
            sesion1 = HibernateUtil.getSessionFactory().openSession();
            tx1 = sesion1.beginTransaction();
        }catch(HibernateException ex){
            
        }
        
        
    }

    @Override
    public List<TipoTitulacion> getTodastipoTitulacion() throws Exception {
        
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //Consulta para que me aparezcan solo las tipos de titulacion registradas en el anio actual
        String hql="from TipoTitulacion";
        //Consulta hasta Usuarios
        //String hql="from Maestria m inner join fetch m.promocions pr inner join fetch pr.modulos mod inner join fetch mod.usuario user where m.estado='1' and year(pr.fechaInicio) = year(current_date) and pr.idUsuario=user.id order by m.descripcion asc";
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<TipoTitulacion> lsttipotitu=(List<TipoTitulacion>) query.list();
        sesion.close();
        return lsttipotitu;
    }

    @Override
    public boolean registrarTitulacion(Titulacion t) throws Exception {
        boolean band = false;
        try{
            iniciaOperacion();
            sesion.save(t);
            tx.commit();
            sesion.close();
            band=true;
        }catch(Exception e){
            tx.rollback();
            band=false;
        }
        
        
        return band;
     }

    @Override
    public List<Titulacion> getTitulacionOK() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        // estado
        //A= guardo; E=espera
        String hql="from Titulacion t\n" +
                "where t.estado ='A' and t.nota != null";
        Query query = sesion.createQuery(hql);

        List<Titulacion> lstProyecto=(List<Titulacion>) query.list();
        sesion.close();
        return lstProyecto;
    }

    @Override
    public List<Maestria> obtenermaestria(int matricula) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        // estado
        //A= guardo; E=espera
        String hql="select m.descripcion from Maestria m\n" +
                "inner join m.promocions pr\n" +
                "inner join pr.solicitudInscripcions soli\n" +
                "inner join soli.matriculas matri\n" +
                "where matri.id = '"+matricula+"'\n" +
                "";
        Query query = sesion.createQuery(hql);

        List<Maestria> lstProyecto=(List<Maestria>) query.list();
        sesion.close();
        return lstProyecto;   
    }

    @Override
    public List<Titulacion> getTitulacionNO() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        // estado
        //A= Aprobado;R:Reprobado; E=espera
        String hql="from Titulacion t\n" +
                "where t.estado = 'E'";
        Query query = sesion.createQuery(hql);

        List<Titulacion> lstProyecto=(List<Titulacion>) query.list();
        sesion.close();
        return lstProyecto;
    }

    @Override
    public List<Titulacion> getTitulacionEstudianteMaestria(int estudiante, int maestria) throws Exception {
    this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql= "from Titulacion t\n" +
                "inner join fetch t.matricula m\n" +
                "inner join fetch m.solicitudInscripcion si\n" +
                "inner join fetch si.estudiante e\n" +
                "inner join fetch si.promocion p\n" +
                "inner join fetch p.maestria ma\n" +
                "where t.estado = 'E' and e.id = '"+estudiante+"' and ma.id = '"+maestria+"'";
        Query query = sesion.createQuery(hql);
         List<Titulacion> lstPermiso=(List<Titulacion>) query.list();
        sesion.close();
        return lstPermiso;
    }

    @Override
    public boolean update(Titulacion ttitulacion) throws Exception {
         boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(ttitulacion);
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
    public List<TipoTitulacion> gettipoTitulacionid(int titulacion) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql= "from TipoTitulacion t\n" +
                "inner join fetch t.titulacions ti\n" +
                "where ti.id='"+titulacion+"'";
        Query query = sesion.createQuery(hql);
         List<TipoTitulacion> lstPermiso=(List<TipoTitulacion>) query.list();
        sesion.close();
        return lstPermiso;
    }

    @Override
    public List<Matricula> getMatriculaid(int titulacion) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql= "from Matricula m\n" +
                "inner join fetch m.titulacions t\n" +
                "where t.id ='"+titulacion+"'";
        Query query = sesion.createQuery(hql);
         List<Matricula> lstPermiso=(List<Matricula>) query.list();
        sesion.close();
        return lstPermiso;
    }


}
