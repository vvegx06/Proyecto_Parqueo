/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Parqueo.Web.dao;

import Parqueo.Web.domain.ZonaParqueo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaParqueoRepositorio extends JpaRepository<ZonaParqueo, Long> {
    // Métodos personalizados si es necesario
}