/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Parqueo.Web.service;

import java.util.List;
import Parqueo.Web.domain.ZonaParqueo;

public interface ZonaParqueoService {
    List<ZonaParqueo> listar();
    ZonaParqueo obtener(Long id);
    ZonaParqueo guardar(ZonaParqueo zona);
    void eliminar(Long id);
}