/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Parqueo.Web.service;

import Parqueo.Web.domain.Vehiculo;
import java.util.List;

public interface VehiculoService {
    List<Vehiculo> obtenerTodosLosVehiculos();
    Vehiculo guardarVehiculo(Vehiculo vehiculo);
}