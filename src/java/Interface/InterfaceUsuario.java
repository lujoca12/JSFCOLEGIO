/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.DetallePermiso;
import Pojo.Usuario;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceUsuario {
    public boolean registrar(Usuario tUsuario) throws Exception;
    public boolean verificarUsuarioNick(String nick) throws Exception;
    public List<Usuario> getTodosUsuarios() throws Exception;
    public List<Usuario> getDocentes() throws Exception;
    public Usuario getUsuario(String idUsuario) throws Exception;
    public boolean update(Usuario tUsuario) throws Exception;
}
