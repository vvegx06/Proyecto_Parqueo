package Parqueo.Web.security;

import Parqueo.Web.dao.UsuarioDao;
import Parqueo.Web.domain.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;

// Indica que esta clase es un servicio de Spring
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Dependencia para acceder a la base de datos de usuarios
    private final UsuarioDao usuarioDao;

    // Constructor con inyección de dependencias
    public CustomUserDetailsService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    // Método principal que Spring Security llama para cargar un usuario por su nombre de usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // Busca el usuario en la base de datos usando el DAO
        Usuario u = usuarioDao.findByUsername(username)
                // Si no lo encuentra, lanza una excepción
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Obtiene el nombre del rol del usuario, si existe, y lo limpia de espacios
        String rol = (u.getRol() != null && u.getRol().getNombre() != null)
                ? u.getRol().getNombre().trim()
                : "USER"; // Si no tiene rol, se asigna USER por defecto

        // Asegura que el rol tenga el prefijo "ROLE_" que requiere Spring Security
        if (!rol.startsWith("ROLE_")) {
            rol = "ROLE_" + rol;
        }

        // Construye un objeto UserDetails que Spring Security utilizará
        return User.builder()
                .username(u.getUsername())            // Nombre de usuario
                .password(u.getPassword())            // Contraseña (debe estar encriptada con BCrypt en la BD)
                .authorities(List.of(new SimpleGrantedAuthority(rol))) // Roles/authorities
                .accountExpired(false)                // Cuenta no expirada
                .accountLocked(false)                 // Cuenta no bloqueada
                .credentialsExpired(false)            // Credenciales no expiradas
                .disabled(false)                      // Cuenta habilitada
                .build();
    }
}