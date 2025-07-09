/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.service.impl;

import Parqueo.Web.dao.VehiculoRepositorio;
import Parqueo.Web.domain.Vehiculo;
import Parqueo.Web.service.VehiculoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoServicioImpl implements VehiculoServicio {
    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;
    @Override
    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return vehiculoRepositorio.findAll();
    }
    @Override
    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        return vehiculoRepositorio.save(vehiculo);
    }
}