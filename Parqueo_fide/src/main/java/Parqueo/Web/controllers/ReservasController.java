/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.controllers;

import Parqueo.Web.domain.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import Parqueo.Web.service.ReservaService;

@Controller
public class ReservasController {

    @Autowired
    private ReservaService reservaServicio;

    @GetMapping("/reservar")
    public String mostrarFormularioReserva(Model model) {
        model.addAttribute("reserva", new Reserva());
        return "reservar";
    }

    @PostMapping("/reservar")
    public String procesarReserva(Reserva reserva) {
        reservaServicio.guardarReserva(reserva);
        return "redirect:/historial";
    }

    @GetMapping("/historial")
    public String mostrarHistorial(Model model) {
        List<Reserva> historial = reservaServicio.obtenerTodasLasReservas();
        model.addAttribute("historial", historial); 
        return "historial";
    }
}
