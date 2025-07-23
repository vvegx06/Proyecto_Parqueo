/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.service.impl;

/**
 *
 * @author sofisantamaria
 */

import Parqueo.Web.domain.EspacioParqueo;
import Parqueo.Web.dao.EspacioParqueoRepositorio;
import Parqueo.Web.service.EspacioParqueoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspacioParqueoServicioImpl implements EspacioParqueoServicio {

    @Autowired
    private EspacioParqueoRepositorio espacioParqueoRepositorio;

    @Override
    public List<EspacioParqueo> obtenerTodos() {
        return espacioParqueoRepositorio.findAll();
    }
}
