package Parqueo.Web.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios") // <- AJUSTA si tu tabla se llama distinto
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre visible del usuario
    @Column(nullable = false)
    private String nombre;

    // Username único para login
    @Column(unique = true, nullable = false)
    private String username;

    // Contraseña (BCrypt)
    @Column(nullable = false)
    private String password;

    // Correo ÚNICO (como en tu BD: correo VARCHAR(100) UNIQUE NOT NULL)
    @Column(name = "correo", unique = true, nullable = false, length = 100)
    private String correo;

    // Relación con rol
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    /* IMPORTANTE:
       - NO definas setters “manuales” que lancen UnsupportedOperationException.
       - Lombok @Data ya genera getters/setters válidos.
    */
}