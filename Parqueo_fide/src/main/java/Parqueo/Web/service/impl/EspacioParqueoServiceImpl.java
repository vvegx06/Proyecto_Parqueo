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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import Parqueo.Web.dao.EspacioParqueoDao;
import Parqueo.Web.service.EspacioParqueoService;

@Service
public class EspacioParqueoServiceImpl implements EspacioParqueoService {

    @Autowired
    private EspacioParqueoDao espacioParqueoRepositorio;

    @Override
    public List<EspacioParqueo> obtenerTodos() {
        return espacioParqueoRepositorio.findAll();
    }
}
