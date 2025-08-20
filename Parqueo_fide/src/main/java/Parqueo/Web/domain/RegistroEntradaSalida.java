package Parqueo.Web.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "registros_entrada_salida")
@Data
public class RegistroEntradaSalida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK a vehiculos(usuario_id existe en vehiculos)
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    // FK a espacios_parqueo
    @ManyToOne
    @JoinColumn(name = "espacio_id")
    private EspacioParqueo espacio;

    @Column(name = "hora_entrada", nullable = false)
    private LocalDateTime horaEntrada;

    @Column(name = "hora_salida")
    private LocalDateTime horaSalida;
}