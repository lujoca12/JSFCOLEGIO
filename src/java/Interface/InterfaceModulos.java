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
    public List<Modulo> getTblModulos(String moduloDescripcion, boolean mostrar) throws Exception;
    public List<Modulo> getCboModulosNotas(int usuario_id) throws Exception;
    public List<Modulo> getTblModulosNotas(int idPromocion) throws Exception;
    public List<Modulo> getCboModulosAsistencias(int usuario_id) throws Exception;
    public Modulo getModulo(String idModulo) throws Exception;
    public boolean update(Modulo tModulo) throws Exception;
    public boolean delete(Modulo tModulo) throws Exception;
    public boolean existe(Modulo tModulo) throws Exception;
    public List<Modulo> getTblModulosMaestria(int idMaestria) throws Exception;
    public List<Modulo> validacionModulos(Modulo tModulo) throws Exception;
    public List<Modulo> getTodosModulo(int idModulo) throws Exception;
    public List<Modulo> getNumeroModulo(int idPromocion) throws Exception;
    public List<Modulo> getProyectoTesisRegistrado(int idPromocion, String moduloDescripcion) throws Exception;
    public List<Modulo> getValidacionModulos(String moduloDescripcion, String nModulo) throws Exception;
    public List<Modulo> getModulosConfid(int idPromocion, int usuarioId) throws Exception;
}
