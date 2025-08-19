/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.service;

/**
 *
 * @author sofisantamaria
 */

import Parqueo.Web.domain.EspacioParqueo;
import java.util.List;

public interface EspacioParqueoService {
    List<EspacioParqueo> obtenerTodos();
}
