/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Promocion;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfacePromocion {
    
    public boolean registrar(Promocion tPromocion) throws Exception;
    public List<Promocion> getTodasPromociones() throws Exception;
    public int getUltimoidPromocion(String descripcion) throws Exception;
    public Promocion getPromocion(int idMaestria) throws Exception;
    public boolean update(Promocion tPromocion) throws Exception;
    public boolean existe(Promocion tPromocion) throws Exception;
}
