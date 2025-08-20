/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.domain;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reserva",
       indexes = {
           @Index(name="idx_reserva_espacio", columnList = "espacio_id"),
           @Index(name="idx_reserva_usuario", columnList = "usuario_id"),
           @Index(name="idx_reserva_inicio_fin", columnList = "inicio,fin")
       })
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quién reserva
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Qué espacio
    @ManyToOne(optional = false)
    @JoinColumn(name = "espacio_id")
    private EspacioParqueo espacio;

    @Column(nullable = false)
    private LocalDateTime inicio;

    @Column(nullable = false)
    private LocalDateTime fin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReservarEstado estado = ReservarEstado.ACTIVA;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creadaEn;
}