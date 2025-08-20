package Parqueo.Web.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import Parqueo.Web.domain.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);

    // Ahora que la entidad tiene 'correo', este método ya compila y funciona
    boolean existsByCorreo(String correo);
}