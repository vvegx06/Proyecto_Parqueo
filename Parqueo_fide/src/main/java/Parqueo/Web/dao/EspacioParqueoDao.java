/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.dao;

/**
 *
 * @author sofisantamaria
 */


import Parqueo.Web.domain.EspacioParqueo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspacioParqueoDao extends JpaRepository<EspacioParqueo, Long> {
    boolean existsByCodigo(String codigo);
    List<EspacioParqueo> findByZonaContainingIgnoreCase(String zona);
}