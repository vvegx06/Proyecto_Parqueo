/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.service;

import Parqueo.Web.domain.Usuario;
import java.util.List;

public interface UsuarioService {

    List<Usuario> listar();

    Usuario guardar(Usuario usuario);

    Usuario buscarPorId(Long id);

    void eliminar(Long id);

    Usuario buscarPorUsername(String username);
}
