/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package Parqueo.Web.dao;

import Parqueo.Web.domain.EspacioParqueo;
import Parqueo.Web.domain.Reserva;
import Parqueo.Web.domain.Usuario;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaDao extends JpaRepository<Reserva, Long> {

    // Chequeo de traslape: existe una reserva en el mismo espacio que se cruza con [inicio, fin]
    boolean existsByEspacioAndEstadoNotAndFinAfterAndInicioBefore(
            EspacioParqueo espacio,
            Parqueo.Web.domain.ReservarEstado estadoExcluido,
            LocalDateTime inicio,
            LocalDateTime fin
    );

    List<Reserva> findByUsuarioOrderByInicioDesc(Usuario usuario);

    List<Reserva> findAllByOrderByInicioDesc();
}