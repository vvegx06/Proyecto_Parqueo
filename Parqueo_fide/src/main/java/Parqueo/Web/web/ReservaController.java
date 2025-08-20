/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.web;

/**
 *
 * @author sofisantamaria
 */

import Parqueo.Web.dao.EspacioParqueoDao;
import Parqueo.Web.dao.ReservaDao;
import Parqueo.Web.dao.UsuarioDao;
import Parqueo.Web.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import javax.validation.Valid;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaDao reservaDao;
    private final EspacioParqueoDao espacioDao;
    private final UsuarioDao usuarioDao;

    public ReservaController(ReservaDao reservaDao, EspacioParqueoDao espacioDao, UsuarioDao usuarioDao) {
        this.reservaDao = reservaDao;
        this.espacioDao = espacioDao;
        this.usuarioDao = usuarioDao;
    }

    private boolean isAdmin(Authentication auth) {
        return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    private Usuario currentUser(Authentication auth) {
        String username = auth.getName();
        return usuarioDao.findByUsername(username).orElseThrow();
    }

    // LISTADO
    @GetMapping("/listado")
    public String listado(Model model, Authentication auth) {
        var reservas = isAdmin(auth)
                ? reservaDao.findAllByOrderByInicioDesc()
                : reservaDao.findByUsuarioOrderByInicioDesc(currentUser(auth));
        model.addAttribute("reservas", reservas);
        return "reservas/listado"; // templates/reservas/listado.html
    }

    // NUEVA RESERVA (form)
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("form", new ReservaForm());
        model.addAttribute("espacios", espacioDao.findAll());
        return "reservas/form"; // templates/reservas/form.html
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("form") @Valid ReservaForm form,
                          BindingResult br,
                          Authentication auth,
                          Model model) {

        // Validaciones básicas
        if (form.getInicio() != null && form.getFin() != null) {
            if (!form.getFin().isAfter(form.getInicio())) {
                br.addError(new FieldError("form", "fin", "La hora de fin debe ser posterior al inicio"));
            }
            if (form.getInicio().isBefore(LocalDateTime.now())) {
                br.addError(new FieldError("form", "inicio", "La hora de inicio debe ser futura"));
            }
        }

        var espacio = (form.getEspacioId() == null) ? null :
                espacioDao.findById(form.getEspacioId()).orElse(null);
        if (espacio == null) {
            br.addError(new FieldError("form", "espacioId", "Debés seleccionar un espacio válido"));
        }

        // Traslapes
        if (!br.hasErrors() && espacio != null) {
            boolean choca = reservaDao.existsByEspacioAndEstadoNotAndFinAfterAndInicioBefore(
                    espacio, ReservarEstado.CANCELADA, form.getInicio(), form.getFin()
            );
            if (choca) {
                br.addError(new FieldError("form", "inicio",
                        "El espacio ya está reservado en ese rango de tiempo"));
            }
        }

        if (br.hasErrors()) {
            model.addAttribute("espacios", espacioDao.findAll());
            return "reservas/form";
        }

        // Persistir
        var reserva = new Reserva();
        reserva.setUsuario(currentUser(auth));
        reserva.setEspacio(espacio);
        reserva.setInicio(form.getInicio());
        reserva.setFin(form.getFin());
        reserva.setEstado(ReservarEstado.ACTIVA);
        reservaDao.save(reserva);

        return "redirect:/reservas/listado";
    }

    // CANCELAR (propietario o admin)
    @PostMapping("/cancelar/{id}")
    public String cancelar(@PathVariable Long id, Authentication auth) {
        var r = reservaDao.findById(id).orElseThrow();
        if (!isAdmin(auth) && !r.getUsuario().getUsername().equals(auth.getName())) {
            // Silencioso: no permitir
            return "redirect:/reservas/listado";
        }
        r.setEstado(ReservarEstado.CANCELADA);
        reservaDao.save(r);
        return "redirect:/reservas/listado";
    }
}