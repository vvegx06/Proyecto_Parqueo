/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.controllers;

/**
 *
 * @author sofisantamaria
 */


import Parqueo.Web.dao.EspacioParqueoDao;
import Parqueo.Web.domain.EspacioParqueo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/espacios")
public class EspacioParqueoController {

    private final EspacioParqueoDao espacioDao;

    public EspacioParqueoController (EspacioParqueoDao espacioDao) {
        this.espacioDao = espacioDao;
    }

    // LISTADO
    @GetMapping("/listado")
    public String listado(@RequestParam(required = false) String filtroZona, Model model) {
        var lista = (filtroZona == null || filtroZona.isBlank())
                ? espacioDao.findAll()
                : espacioDao.findByZonaContainingIgnoreCase(filtroZona);
        model.addAttribute("espacios", lista);
        model.addAttribute("filtroZona", filtroZona);
        return "espacios/listado";
    }

    // NUEVO
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("espacio", new EspacioParqueo());
        return "espacios/form";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        EspacioParqueo e = espacioDao.findById(id).orElseThrow();
        model.addAttribute("espacio", e);
        return "espacios/form";
    }

    // GUARDAR (crear/actualizar)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("espacio") EspacioParqueo espacio, BindingResult br, Model model) {
        // Validación sencilla de código único
        if (espacio.getCodigo() == null || espacio.getCodigo().isBlank()) {
            br.addError(new FieldError("espacio", "codigo", "El código es obligatorio"));
        } else {
            boolean existe = espacioDao.existsByCodigo(espacio.getCodigo());
            boolean esEdicion = espacio.getId() != null;
            // Permitir mismo código solo si es la misma fila (edición)
            if (existe) {
                var actual = espacioDao.findAll().stream()
                        .filter(e -> e.getCodigo().equalsIgnoreCase(espacio.getCodigo()))
                        .findFirst().orElse(null);
                if (actual != null && (!esEdicion || !actual.getId().equals(espacio.getId()))) {
                    br.addError(new FieldError("espacio", "codigo", "Ya existe un espacio con ese código"));
                }
            }
        }

        if (br.hasErrors()) {
            return "espacios/form";
        }
        if (espacio.getDisponible() == null) espacio.setDisponible(true);
        espacioDao.save(espacio);
        return "redirect:/espacios/listado";
    }

    // ELIMINAR
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        espacioDao.deleteById(id);
        return "redirect:/espacios/listado";
    }
}

