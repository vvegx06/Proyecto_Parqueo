package Parqueo.Web.security;

import Parqueo.Web.dao.UsuarioDao;
import Parqueo.Web.domain.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioDao usuarioDao;

    public CustomUserDetailsService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = usuarioDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Obtiene el nombre del rol y asegura el prefijo ROLE_
        String rol = (u.getRol() != null && u.getRol().getNombre() != null)
                ? u.getRol().getNombre().trim()
                : "USER";

        if (!rol.startsWith("ROLE_")) {
            rol = "ROLE_" + rol;
        }

        return User.builder()
                .username(u.getUsername())
                .password(u.getPassword())   // Debe estar en BCrypt en la BD
                .authorities(List.of(new SimpleGrantedAuthority(rol)))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}