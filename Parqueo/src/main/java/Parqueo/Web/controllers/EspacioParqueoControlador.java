/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.controllers;

/**
 *
 * @author sofisantamaria
 */

import Parqueo.Web.domain.EspacioParqueo;
import Parqueo.Web.service.EspacioParqueoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EspacioParqueoControlador {

    @Autowired
    private EspacioParqueoServicio espacioParqueoServicio;

    @GetMapping("/disponibilidad")
    public String mostrarDisponibilidad(Model model) {
        List<EspacioParqueo> espacios = espacioParqueoServicio.obtenerTodos();
        model.addAttribute("espacios", espacios);
        return "disponibilidad";
    }
}

