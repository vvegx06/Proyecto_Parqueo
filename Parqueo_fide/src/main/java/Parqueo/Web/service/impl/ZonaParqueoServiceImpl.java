/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import Parqueo.Web.dao.ZonaParqueoDao;
import Parqueo.Web.domain.ZonaParqueo;
import Parqueo.Web.service.ZonaParqueoService;

@Service
@RequiredArgsConstructor
public class ZonaParqueoServiceImpl implements ZonaParqueoService {

    private final ZonaParqueoDao dao;

    @Override
    @Transactional(readOnly = true)
    public List<ZonaParqueo> listar() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ZonaParqueo obtener(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ZonaParqueo guardar(ZonaParqueo zona) {
        return dao.save(zona);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        dao.deleteById(id);
    }
}