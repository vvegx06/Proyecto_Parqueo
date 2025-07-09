/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.service.impl;

import Parqueo.Web.dao.ReservaRepositorio;
import Parqueo.Web.domain.Reserva;
import Parqueo.Web.service.ReservaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServicioImpl implements ReservaServicio {

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    @Override
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepositorio.findAll();
    }

    @Override
    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepositorio.save(reserva);
    }
}
