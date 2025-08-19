/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.controllers;

import Parqueo.Web.domain.ZonaParqueo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import Parqueo.Web.service.ZonaParqueoService;

@Controller
public class ZonaParqueoController {

    @Autowired
    private ZonaParqueoService zonaParqueoServicio;

    @GetMapping("/zonasParqueo")
    public String mostrarZonasParqueo(Model model) {
        List<ZonaParqueo> zonas = zonaParqueoServicio.obtenerTodasLasZonas();
        model.addAttribute("zonas", zonas); // Asegúrate de que 'model' es del tipo correcto
        return "zonasParqueo"; // Vista para mostrar zonas de parqueo
    }
}
