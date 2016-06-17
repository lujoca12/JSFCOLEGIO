/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Precio;
import Pojo.Promocion;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfacePromocion {
    
    public boolean registrar(Promocion tPromocion, BigDecimal precioMatricula, BigDecimal precioColegiatura) throws Exception;
    public List<Promocion> getTodasPromociones() throws Exception;
    public List<Promocion> getPromocionesMaestrias(String maestriaDescripcion, boolean mostrar) throws Exception;
    public List<Promocion> getPromocionesMaestrias(int idMaestria) throws Exception;
    public int getUltimoidPromocion(String descripcion) throws Exception;
    public Promocion getPromocion(int idMaestria) throws Exception;
    public boolean update(Promocion tPromocion, BigDecimal precioMatricula, BigDecimal precioColegiatura) throws Exception;
    public boolean delete(Promocion tPromocion) throws Exception;
    public boolean existe(Promocion tPromocion) throws Exception;
    public List<Precio> getPromocionesPrecios(int idPromocion) throws Exception;
    public List<Promocion> getValidacionPromocionesMaestrias(String maestriaDescripcion, int promocion) throws Exception;
}
