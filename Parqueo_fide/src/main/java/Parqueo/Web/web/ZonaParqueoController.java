/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.web;

/**
 *
 * @author sofisantamaria
 */


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Parqueo.Web.domain.ZonaParqueo;
import Parqueo.Web.service.ZonaParqueoService;

@Controller
@RequestMapping("/zonas")
@RequiredArgsConstructor
public class ZonaParqueoController {

    private final ZonaParqueoService service;

    // Listado: para cualquier usuario autenticado
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("zonas", service.listar());
        return "zonas/listado";
    }

    // Nuevo: solo ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("zona", new ZonaParqueo());
        model.addAttribute("tipos", ZonaParqueo.TipoZona.values());
        return "zonas/form";
    }

    // Editar: solo ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        ZonaParqueo z = service.obtener(id);
        if (z == null) {
            ra.addFlashAttribute("error", "Zona no encontrada");
            return "redirect:/zonas/listado";
        }
        model.addAttribute("zona", z);
        model.addAttribute("tipos", ZonaParqueo.TipoZona.values());
        return "zonas/form";
    }

    // Guardar: solo ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("zona") ZonaParqueo zona, RedirectAttributes ra) {
        service.guardar(zona);
        ra.addFlashAttribute("ok", "Zona guardada correctamente");
        return "redirect:/zonas/listado";
    }

    // Eliminar: solo ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        service.eliminar(id);
        ra.addFlashAttribute("ok", "Zona eliminada");
        return "redirect:/zonas/listado";
    }
}