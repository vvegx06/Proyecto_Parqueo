package Parqueo.Web.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vehiculos")
@Data
public class Vehiculo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String placa;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_placa", nullable=false)
    private TipoPlaca tipoPlaca;  // PARTICULAR / OFICIAL / DISCAPACITADO / OTRO

    private String marca;
    private String modelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public enum TipoPlaca { PARTICULAR, OFICIAL, DISCAPACITADO, OTRO }
}