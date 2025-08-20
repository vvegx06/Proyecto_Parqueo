/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "zonas_parqueo")
@Data
public class ZonaParqueo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoZona tipo; // ABIERTO, DISCAPACITADOS

    @Column(name = "capacidad_total")
    private Integer capacidadTotal;

    public enum TipoZona { ABIERTO, DISCAPACITADOS }
}
