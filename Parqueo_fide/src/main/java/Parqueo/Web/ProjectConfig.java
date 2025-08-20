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
@EnableMethodSecurity // <--- importante para @PreAuthorize
public class ProjectConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private DaoAuthenticationProvider daoAuthProvider(UserDetailsService uds, BCryptPasswordEncoder pe) {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(uds);
        provider.setPasswordEncoder(pe);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService uds, BCryptPasswordEncoder pe) {
        return new ProviderManager(daoAuthProvider(uds, pe));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   UserDetailsService uds,
                                                   BCryptPasswordEncoder pe) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/index",
                    "/login", "/registro", "/registro/**",
                    "/error",
                    "/webjars/**", "/css/**", "/js/**", "/images/**", "/assets/**", "/favicon.ico"
                ).permitAll()

                // zonas: listado accesible a cualquier autenticado
                .requestMatchers("/zonas/listado").authenticated()

                // alta/edición/borrado solo ADMIN
                .requestMatchers("/zonas/nuevo", "/zonas/guardar", "/zonas/editar/**", "/zonas/eliminar/**")
                    .hasRole("ADMIN")

                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .authenticationProvider(daoAuthProvider(uds, pe));

        return http.build();
    }
}