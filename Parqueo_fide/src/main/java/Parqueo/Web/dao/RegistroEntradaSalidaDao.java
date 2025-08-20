package Parqueo.Web.dao;

import Parqueo.Web.domain.RegistroEntradaSalida;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistroEntradaSalidaDao extends JpaRepository<RegistroEntradaSalida, Long> {

    // Historial del usuario logueado (vía vehiculo.usuario.id)
    @Query("""
           select r from RegistroEntradaSalida r
           where r.vehiculo.usuario.id = :usuarioId
           order by r.horaEntrada desc
           """)
    List<RegistroEntradaSalida> listarPorUsuario(Long usuarioId);

    // Para admin: todos
    List<RegistroEntradaSalida> findAllByOrderByHoraEntradaDesc();
}