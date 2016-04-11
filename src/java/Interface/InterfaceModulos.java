/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Modulo;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceModulos {
    public boolean registrar(Modulo tModulo) throws Exception;
    public List<Modulo> getTodosModulo() throws Exception;
    public List<Modulo> getTblModulos() throws Exception;
    public List<Modulo> getCboModulosNotas() throws Exception;
    public List<Modulo> getTblModulosNotas(int idPromocion) throws Exception;
    public Modulo getModulo(String idModulo) throws Exception;
    public boolean update(Modulo tModulo) throws Exception;
    public boolean delete(Modulo tModulo) throws Exception;
    public boolean existe(Modulo tModulo) throws Exception;
}
