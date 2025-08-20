/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Parqueo.Web.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import Parqueo.Web.domain.ZonaParqueo;

public interface ZonaParqueoDao extends JpaRepository<ZonaParqueo, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
    List<ZonaParqueo> findByNombreContainingIgnoreCase(String q);
}