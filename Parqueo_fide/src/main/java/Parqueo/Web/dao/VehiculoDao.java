package Parqueo.Web.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import Parqueo.Web.domain.Usuario;
import Parqueo.Web.domain.Vehiculo;

public interface VehiculoDao extends JpaRepository<Vehiculo, Long> {
    boolean existsByPlaca(String placa);
    List<Vehiculo> findByUsuario(Usuario usuario);
}