/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Curso;
import java.math.BigDecimal;
import java.util.List;
/**
 *
 * @author server
 */
public interface InterfaceCurso {
    public List<Curso> getTodosCursos() throws Exception;;

    public boolean registrar(Curso tCurso) throws Exception;

    public List<Curso> getCursosxDescripcion(String descripcion) throws Exception;

    public boolean delete(Curso tCurso) throws Exception;

    public boolean update(Curso tCurso) throws Exception;

    public Curso getCursos(String idCurso) throws Exception;

    public List<Curso> getCursoSeccion() throws Exception;
    
    public boolean existe(Curso tCurso) throws Exception;
    
    public boolean registrarCursoPrecio(Curso tCurso, BigDecimal precioMatricula, BigDecimal precioColegiatura) throws Exception;

}
