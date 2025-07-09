/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.controllers;

import Parqueo.Web.domain.Reserva;
import Parqueo.Web.service.ReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class ReservaControlador {

    @Autowired
    private ReservaServicio reservaServicio;

    @GetMapping("/reservaEspacio")
    public String mostrarReservaEspacio(Model model) {
        List<Reserva> reservas = reservaServicio.obtenerTodasLasReservas();
        model.addAttribute("reservas", reservas); // Asegúrate de que 'model' es del tipo correcto
        return "reservaEspacio"; // Vista para reservar espacio
    }

    @PostMapping("/reservaEspacio")
    public String reservarEspacio(Reserva reserva) {
        reservaServicio.guardarReserva(reserva);
        return "redirect:/reservaEspacio"; // Redirigir después de guardar
    }
}
