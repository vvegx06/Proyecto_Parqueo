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
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspacioParqueoRepositorio extends JpaRepository<EspacioParqueo, Long> {
}

