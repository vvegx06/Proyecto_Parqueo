/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Parqueo.Web.service;

import Parqueo.Web.domain.Reserva;
import java.util.List;

public interface ReservaService {
    List<Reserva> obtenerTodasLasReservas();
    Reserva guardarReserva(Reserva reserva);
}