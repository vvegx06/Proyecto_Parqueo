package Parqueo.Web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Permite usar @PreAuthorize en los controladores
public class ProjectConfig {

    // Bean para encriptar contraseñas con BCrypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configura un proveedor de autenticación que usa nuestro UserDetailsService y el encoder
    private DaoAuthenticationProvider daoAuthProvider(UserDetailsService uds, BCryptPasswordEncoder pe) {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(uds);
        provider.setPasswordEncoder(pe);
        return provider;
    }

    // Bean que maneja la autenticación usando nuestro proveedor
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService uds, BCryptPasswordEncoder pe) {
        return new ProviderManager(daoAuthProvider(uds, pe));
    }

    // Configuración principal de seguridad web
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   UserDetailsService uds,
                                                   BCryptPasswordEncoder pe) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // URLs públicas
                .requestMatchers("/", "/index", "/login", "/registro/**",
                                 "/error", "/webjars/**", "/css/**", "/js/**", "/images/**", "/assets/**", "/favicon.ico")
                .permitAll()

                // Listado accesible para cualquier usuario autenticado
                .requestMatchers("/zonas/listado").authenticated()

                // Alta/edición/borrado solo ADMIN
                .requestMatchers("/zonas/nuevo", "/zonas/guardar", "/zonas/editar/**", "/zonas/eliminar/**")
                    .hasRole("ADMIN")

                // Cualquier otra petición requiere autenticación
                .anyRequest().authenticated()
            )
            // Configuración de login
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error")
                .permitAll()
            )
            // Configuración de logout
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            // Añade nuestro proveedor de autenticación
            .authenticationProvider(daoAuthProvider(uds, pe));

        return http.build();
    }
}