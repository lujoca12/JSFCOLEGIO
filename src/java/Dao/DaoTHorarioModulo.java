/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceHorarioModulo;
import Pojo.HorarioModulo;
import java.util.Calendar;
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
public class DaoTHorarioModulo implements InterfaceHorarioModulo{
    
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
    public boolean registrar(HorarioModulo tHorarioModulo) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tHorarioModulo);

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
    public List<HorarioModulo> getTodosHorarios() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from HorarioModulo";
        Query query = sesion.createQuery(hql);
        List<HorarioModulo> lstHorarioModulo =(List<HorarioModulo>) query.list();
        sesion.close();
        return lstHorarioModulo;
    }
    
    @Override
    public List<HorarioModulo> getFechaHorasModulos(int idModulo) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from HorarioModulo horario inner join fetch horario.modulo mod where mod.id = "+idModulo+" order by horario.fecha asc";
        Query query = sesion.createQuery(hql);
        List<HorarioModulo> lstHorarioModulo =(List<HorarioModulo>) query.list();
        sesion.close();
        return lstHorarioModulo;
    }

    @Override
    public List<HorarioModulo> getTblHorarios(String moduloDescripcion) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String consulta = "";
        
        if(moduloDescripcion.isEmpty())
            consulta = "";
        else
            consulta = "where mod.modulo like '%"+moduloDescripcion+"%'";
        
        String hql = "from HorarioModulo horario \n"
                + "inner join fetch horario.modulo mod \n"
                + "inner join fetch mod.promocion pr \n"
                + "inner join fetch pr.maestria maest \n"
                + ""+consulta+" order by horario.id desc";
        Query query = sesion.createQuery(hql);
        List<HorarioModulo> lstHorarioModulo =(List<HorarioModulo>) query.list();
        sesion.close();
        return lstHorarioModulo;
    }
    
    @Override
    public String getTotalHorasAsignadas(int idModulo) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "select sum(horario.hora) from HorarioModulo horario inner join horario.modulo mod where mod.id = "+idModulo+"";
        Query query = sesion.createQuery(hql);
        Object resultado = query.uniqueResult();
        sesion.close();
        
        if(resultado != null)
            return resultado.toString();
        else
            return "";
        
    }

    @Override
    public HorarioModulo getHorario(String idHorarioModulo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(HorarioModulo tHorarioModulo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(HorarioModulo tHorarioModulo) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.delete(tHorarioModulo);

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
    public boolean existe(HorarioModulo tHorarioModulo) throws Exception {
        boolean band = false;
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tHorarioModulo.getHoraInicio());
        
        int horaI, minutosI, segundosI;
        int horaF, minutosF, segundosF;
        
        horaI = calendar.get(Calendar.HOUR_OF_DAY);
        minutosI = calendar.get(Calendar.MINUTE);
        segundosI = calendar.get(Calendar.SECOND);
        
        calendar.setTime(tHorarioModulo.getHoraFin());
        horaF = calendar.get(Calendar.HOUR_OF_DAY);
        minutosF = calendar.get(Calendar.MINUTE);
        segundosF = calendar.get(Calendar.SECOND);
        
        //String horaInicio = horaI + ":" + minutosI + ":" + segundosI;
//        String hql="from HorarioModulo horario inner join fetch horario.modulo mod where horario.horaInicio= '"+horaI + ':' + minutosI + ':' + segundosI+"' "
//                + "and horario.horaFin= '"+horaF + ':' + minutosF + ':' + segundosF+"' "
//                + "and horario.fecha='"+tHorarioModulo.getFecha()+"' "
//                + "and mod.id ="+tHorarioModulo.getModulo().getId()+" ";

String hql="from HorarioModulo horario inner join fetch horario.modulo mod where "
                + "horario.fecha='"+tHorarioModulo.getFecha()+"' "
                + "and mod.id ="+tHorarioModulo.getModulo().getId()+" ";
        Query query = sesion.createQuery(hql);
        List<HorarioModulo> lstHorarioModulo =(List<HorarioModulo>) query.list();
        if(lstHorarioModulo.size() > 0)
            band = true;
        sesion.close();
        
        return band;
    }
    
}
