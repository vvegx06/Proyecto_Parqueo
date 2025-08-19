/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.service.impl;

import Parqueo.Web.domain.ZonaParqueo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Parqueo.Web.dao.ZonaParqueoDao;
import Parqueo.Web.service.ZonaParqueoService;

@Service
public class ZonaParqueoServiceImpl implements ZonaParqueoService {
    @Autowired
    private ZonaParqueoDao zonaParqueoRepositorio;
    @Override
    public List<ZonaParqueo> obtenerTodasLasZonas() {
        return zonaParqueoRepositorio.findAll();
    }
}