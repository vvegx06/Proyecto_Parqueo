/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.service.impl;

import Parqueo.Web.dao.ZonaParqueoRepositorio;
import Parqueo.Web.domain.ZonaParqueo;
import Parqueo.Web.service.ZonaParqueoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZonaParqueoServicioImpl implements ZonaParqueoServicio {
    @Autowired
    private ZonaParqueoRepositorio zonaParqueoRepositorio;
    @Override
    public List<ZonaParqueo> obtenerTodasLasZonas() {
        return zonaParqueoRepositorio.findAll();
    }
}