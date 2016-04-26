/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.Promocion;
import Pojo.Requisitos;
import Pojo.RequisitosPromo;
import java.util.List;
import javax.faces.model.SelectItem;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author chiti
 */
public class PermisosPromoDao {

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

    public String registrar(List<String> Requisitos, List<String> ReqSeleccionados, int IdPromo) throws Exception {
        boolean band = false;
        String msg = "";
        try {
            iniciaOperacion();
            RequisitosPromo rp = new RequisitosPromo();
            Requisitos r = new Requisitos();
            Promocion p = new Promocion();
            for (int i = 0; i < Requisitos.size(); i++) {
                rp = new RequisitosPromo();
                r = new Requisitos();
                p = new Promocion();
                p.setId(IdPromo);
                r.setId(Integer.valueOf(Requisitos.get(i)));
                rp.setRequisitos(r);
                rp.setPromocion(p);
                if (ReqSeleccionados.contains(Requisitos.get(i))) {
                    rp.setEstado('1');
                } else {
                    rp.setEstado('0');
                }

                sesion.saveOrUpdate(rp);
            }

            tx.commit();
            sesion.close();
            msg = "Datos procesados correctamente";
            band = true;
        } catch (Exception e) {
            tx.rollback();
            msg = e.getMessage();
            band = false;
        }

        return msg;
    }

    public List<RequisitosPromo> getEstadoReqPromo(int IdReq, int IdPromo) {
        this.sesion = null;
        this.tx = null;
        boolean band = false;
        iniciaOperacion();
        String hql = "from RequisitosPromo as rp where rp.promocion.id =:promo and dp.requisitos.id=:req";
        Query query = sesion.createQuery(hql);
        query.setInteger("promo", IdPromo);
        query.setInteger("req", IdReq);
        List<RequisitosPromo> result = (List<RequisitosPromo>) query.list();
        sesion.close();

        return result;
    }

}
