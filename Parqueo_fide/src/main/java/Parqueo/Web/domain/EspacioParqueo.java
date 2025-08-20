package Parqueo.Web.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "espacio")
@Data
public class EspacioParqueo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String codigo;          // Ej: A-01, B-12

    @Column(length = 50)
    private String zona;            // Ej: Norte, Piso 2

    @Column(nullable = false)
    private Boolean disponible = true;

    @Column(length = 255)
    private String descripcion;
}