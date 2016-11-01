/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Materias;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceMateria {
    public List<Materias> getTodasMaterias() throws Exception;;

    public boolean registrar(Materias tMaterias) throws Exception;

    public List<Materias> getMateriasxDescripcion(String descripcion) throws Exception;

    public boolean delete(Materias tMaterias) throws Exception;

    public boolean update(Materias tMaterias) throws Exception;

    public Materias getMaterias(String idMaterias) throws Exception;

    public List<Materias> getMaterias() throws Exception;
    public boolean existe(Materias tMaterias) throws Exception;
}
