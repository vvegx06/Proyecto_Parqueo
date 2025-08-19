/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.service;

import Parqueo.Web.domain.Rol;
import java.util.List;

public interface RolService {

    List<Rol> listar();

    Rol guardar(Rol rol);

    Rol buscarPorId(Long id);

    void eliminar(Long id);
}
