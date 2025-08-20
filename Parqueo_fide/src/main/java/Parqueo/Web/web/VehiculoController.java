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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Parqueo.Web.dao.UsuarioDao;
import Parqueo.Web.domain.Usuario;
import Parqueo.Web.domain.Vehiculo;
import Parqueo.Web.service.VehiculoService;

@Controller
@RequestMapping("/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {

    private final VehiculoService vehiculoService;
    private final UsuarioDao usuarioDao;

    private Usuario currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // admin, juan, etc.
        return usuarioDao.findByUsername(username).orElseThrow();
    }

    @GetMapping("/registrar")
    public String form(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        model.addAttribute("tipos", Vehiculo.TipoPlaca.values());
        return "vehiculos/registrar";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute("vehiculo") Vehiculo vehiculo,
                            RedirectAttributes ra) {
        Usuario u = currentUser();

        // Normalizar placa y validaciones básicas
        if (vehiculo.getPlaca() == null || vehiculo.getPlaca().isBlank()) {
            ra.addFlashAttribute("error", "La placa es obligatoria");
            return "redirect:/vehiculos/registrar";
        }
        vehiculo.setPlaca(vehiculo.getPlaca().trim().toUpperCase());

        if (vehiculo.getTipoPlaca() == null) {
            ra.addFlashAttribute("error", "Debés seleccionar un tipo de placa");
            return "redirect:/vehiculos/registrar";
        }

        if (vehiculoService.existePlaca(vehiculo.getPlaca())) {
            ra.addFlashAttribute("error", "Ya existe un vehículo con esa placa");
            return "redirect:/vehiculos/registrar";
        }

        vehiculo.setUsuario(u);
        vehiculoService.registrar(vehiculo);
        ra.addFlashAttribute("ok", "Vehículo registrado correctamente");
        return "redirect:/vehiculos/mis";
    }

    @GetMapping("/mis")
    public String mis(Model model) {
        Usuario u = currentUser();
        model.addAttribute("lista", vehiculoService.misVehiculos(u));
        return "vehiculos/mis";
    }
}