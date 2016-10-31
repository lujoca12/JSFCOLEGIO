/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Modalidad;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceModalidad {
    public List<Modalidad> getTodasModalidades() throws Exception;;

    public boolean registrar(Modalidad tModalidad) throws Exception;

    public List<Modalidad> getModalidadxDescripcion(String descripcion) throws Exception;

    public boolean delete(Modalidad tModalidad) throws Exception;

    public boolean update(Modalidad tModalidad) throws Exception;

    public Modalidad getModalidad(String idModalidad) throws Exception;

    public List<Modalidad> getModalidad() throws Exception;
}
