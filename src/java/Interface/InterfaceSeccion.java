/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Seccion;
import java.util.List;
/**
 *
 * @author server
 */
public interface InterfaceSeccion {
    public List<Seccion> getTodasSeccions() throws Exception;;

    public boolean registrar(Seccion tSeccion) throws Exception;

    public List<Seccion> getSeccionsxDescripcion(String descripcion) throws Exception;

    public boolean delete(Seccion tSeccion) throws Exception;

    public boolean update(Seccion tSeccion) throws Exception;

    public Seccion getSeccions(String idSeccion) throws Exception;

    public List<Seccion> getSeccion() throws Exception;
}
