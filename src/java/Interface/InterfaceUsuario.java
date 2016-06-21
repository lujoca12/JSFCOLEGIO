/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.DetallePermiso;
import Pojo.Usuario;
import Pojo.Persona;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceUsuario {
    public boolean registrar(Usuario tUsuario) throws Exception;
    public boolean verificarUsuarioNick(String nick) throws Exception;
    public List<Usuario> getTodosUsuarios(boolean mostrar) throws Exception;
    public List<Usuario> getDocentes(boolean mostrar) throws Exception;
    public Usuario getUsuario(int idUsuario) throws Exception;
    public boolean update(Usuario tUsuario) throws Exception;
    public boolean registrarPersona(Persona tPersona) throws Exception;
    public List<Persona> getPersonas() throws Exception;
    public Usuario getUsuarioEdicion(int idUsuario) throws Exception;
}
