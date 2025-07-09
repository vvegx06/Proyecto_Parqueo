/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.controllers;

import Parqueo.Web.domain.Vehiculo;
import Parqueo.Web.service.VehiculoServicio;
import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VehiculoControlador {

    @Autowired
    private VehiculoServicio vehiculoServicio;

    @GetMapping("/registroVehiculo")
    public String mostrarRegistroVehiculo(Model model) {
        return "registroVehiculo"; // Vista para registrar vehículos
    }

    @PostMapping("/registroVehiculo")
    public String registrarVehiculo(Vehiculo vehiculo) {
        vehiculoServicio.guardarVehiculo(vehiculo);
        return "redirect:/registroVehiculo"; // Redirigir después de guardar
    }
}
