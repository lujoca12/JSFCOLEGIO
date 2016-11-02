/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfacePromocion;
import Pojo.Precio;
import Pojo.Promocion;
import Pojo.TipoPrecio;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author server
 */
public class DaoTPromocion implements InterfacePromocion{
    
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
    public boolean registrar(Promocion tPromocion, BigDecimal precioMatricula, BigDecimal precioColegiatura) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tPromocion);
            tx.commit();
            sesion.close();
            
//            iniciaOperacion();
//            TipoPrecio tipo_Precio = null;
//            Precio precio = null;
//            for (int i = 1; i < 3; i++) {
//                tipo_Precio = new TipoPrecio();
//                tipo_Precio.setId(i);
//                precio = new Precio();
//                precio.setTipoPrecio(tipo_Precio);
//                precio.setPromocion(tPromocion);
//                if(i == 1)
//                    precio.setValor(precioMatricula);
//                else
//                    precio.setValor(precioColegiatura);
//                
//                sesion.save(precio);
//            }
//            
//            tx.commit();
//            
//            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }

    @Override
    public List<Promocion> getTodasPromociones() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Permiso as p where p.estado = '1' order by p.orden, p.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Promocion> lstPermiso=(List<Promocion>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Promocion> getPromocionesMaestrias(String maestriaDescripcion, boolean mostrar) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String consulta = "";
        
        String estado = "";
        if(mostrar)
            estado = "0";
        else
            estado = "1";
        
        if(maestriaDescripcion.isEmpty())
            consulta = "";
        else
            consulta = "and m.descripcion like '%"+maestriaDescripcion+"%'";
        
        String hql="from Promocion pr inner join fetch pr.maestria m "
                + "where pr.estado = '"+estado+"' and m.estado='1' and "
                + "(year(current_date) >= year(pr.fechaInicio) and "
                + "year(current_date)<= year(pr.fechaFin)) "+consulta+" order by pr.id desc";
        Query query = sesion.createQuery(hql);
        List<Promocion> lstPermiso=(List<Promocion>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Promocion> getValidacionPromocionesMaestrias(String maestriaDescripcion, int promocion) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String consulta = "";
        
        String estado = "1";
        
        consulta = "and m.descripcion = '"+maestriaDescripcion+"'";
        
        String hql="from Promocion pr inner join fetch pr.maestria m "
                + "where pr.estado = '"+estado+"' and m.estado='1' "+consulta+" and pr.descripcion = "+promocion+" order by pr.id desc";
        Query query = sesion.createQuery(hql);
        List<Promocion> lstPermiso=(List<Promocion>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Precio> getPromocionesPrecios(int idPromocion) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        
        String hql="from Precio precio inner join fetch precio.promocion pr inner join fetch precio.tipoPrecio tipoPrecio where pr.id = "+idPromocion+" ";
               
        Query query = sesion.createQuery(hql);
        List<Precio> lstPermiso=(List<Precio>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Promocion> getPromocionesMaestrias(int idMaestria) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        
        
        String hql="from Promocion pr inner join fetch pr.maestria m "
                + "where pr.estado = '1' and m.estado='1' and "
                + "(year(current_date) >= year(pr.fechaInicio) and "
                + "year(current_date)<= year(pr.fechaFin)) and "
                + "m.id = "+idMaestria+" order by pr.id desc";
        Query query = sesion.createQuery(hql);
        List<Promocion> lstPermiso=(List<Promocion>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Promocion> getPromocionesMaestriasEstudiantes(int idMaestria, int idEstudiante) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        
        
        String hql = "from Promocion pr inner join fetch pr.maestria maestr \n"
                + "inner join fetch pr.solicitudInscripcions sol \n"
                + "inner join fetch sol.matriculas matr \n"
                + "inner join fetch sol.estudiante est \n"
                + "where maestr.id = "+idMaestria+" and est.id = "+idEstudiante+" order by pr.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Promocion> lstPermiso=(List<Promocion>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Promocion> getPromocionesMaestriasDocente(int idDocente) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        
        
        String hql="from Promocion pr inner join fetch pr.maestria m inner join fetch pr.modulos mod inner join fetch mod.usuario user \n"
                + "where pr.estado = '1' and m.estado='1' and \n"
                + "(year(current_date) >= year(pr.fechaInicio) and \n"
                + "year(current_date)<= year(pr.fechaFin)) and \n"
                + "user.id = "+idDocente+" and user.estado='1' order by pr.id desc";
        Query query = sesion.createQuery(hql);
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Promocion> lstPermiso=(List<Promocion>) query.list();
        sesion.close();
        return lstPermiso;
    }

    @Override
    public Promocion getPromocion(int idMaestria) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Permiso as p where p.estado = '1' order by p.orden, p.descripcion asc";
        Query query = sesion.createQuery(hql);
        Promocion promocion=(Promocion) query.uniqueResult();
        sesion.close();
        return promocion;
    }
//ojo
    @Override
    public boolean update(Promocion tPromocion, BigDecimal precioMatricula, BigDecimal precioColegiatura) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.update(tPromocion);
            tx.commit();
            sesion.close();
            
            iniciaOperacion();
            
            String hql="from Precio precio inner join fetch precio.promocion pr inner join fetch precio.tipoPrecio tipoPrecio where pr.id = "+tPromocion.getId()+" ";
            Query query = sesion.createQuery(hql);
            List<Precio> lstPrecio=(List<Precio>) query.list();
            sesion.close();
            
            iniciaOperacion();
            
            TipoPrecio tipo_Precio = null;
            Precio precio = null;
            for (int i = 0; i < lstPrecio.size(); i++) {
                
                precio = lstPrecio.get(i);
                
                if(lstPrecio.get(i).getTipoPrecio().getId() == 1)
                    precio.setValor(precioMatricula);
                else if(lstPrecio.get(i).getTipoPrecio().getId() == 2)
                    precio.setValor(precioColegiatura);
                
                sesion.update(precio);
            }
            if (lstPrecio.size() <= 0) {
                for (int i = 1; i < 3; i++) {
                    tipo_Precio = new TipoPrecio();
                    tipo_Precio.setId(i);
                    precio = new Precio();
                    precio.setTipoPrecio(tipo_Precio);
//                    precio.setPromocion(tPromocion);
                    if (i == 1) {
                        precio.setValor(precioMatricula);
                    } else {
                        precio.setValor(precioColegiatura);
                    }

                    sesion.save(precio);
                }
            }

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
    public int getUltimoidPromocion(String descripcion) throws Exception {
        this.sesion = null;
        this.tx = null;
        int ultimoId = 0;
        iniciaOperacion();
        String hql="from Promocion as p inner join fetch p.maestria as m where m.descripcion like '%"+descripcion+"%' and p.estado = '1' order by p.descripcion desc";
        Query query = sesion.createQuery(hql);
        List<Promocion> lstPromocion=(List<Promocion>) query.list();
        if(lstPromocion.size() > 0)
            ultimoId = lstPromocion.get(0).getDescripcion();
        else
            ultimoId = 0;
        
        sesion.close();
        return ultimoId;
    }

    @Override
    public boolean existe(Promocion tPromocion) throws Exception {
        this.sesion = null;
        this.tx = null;
        boolean band = false;
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(tPromocion.getFechaInicio());
//        int anioInicio = calendar.get(Calendar.YEAR);
//        
//        calendar.setTime(tPromocion.getFechaFin());
//        int anioFin = calendar.get(Calendar.YEAR);
        
        iniciaOperacion();
//        String hql="from Promocion as pr where pr.maestria = "+tPromocion.getMaestria().getId()+" and "
//                + "pr.idUsuario = "+tPromocion.getIdUsuario()+" and "
//                + "year(pr.fechaInicio) = "+anioInicio+" and "
//                + "year(pr.fechaFin) = "+anioFin+"";

        String hql="from Promocion as pr where pr.maestria = "+tPromocion.getMaestria().getId()+" and "
                + "pr.idUsuario = "+tPromocion.getIdUsuario()+" and "
                + "pr.fechaInicio = '"+tPromocion.getFechaInicio()+"' and "
                + "pr.fechaFin = '"+tPromocion.getFechaFin()+"' and pr.estado = '1'";
        Query query = sesion.createQuery(hql);
        List<Promocion> promocion=(List<Promocion>) query.list();
        if(promocion.size() > 0)
            band = true;
        else
            band = false;
        
        sesion.close();
        return band;
    }
    
    @Override
    public boolean delete(Promocion tPromocion) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.delete(tPromocion);

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
