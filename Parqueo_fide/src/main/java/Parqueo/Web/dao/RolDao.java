/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parqueo.Web.dao;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import Parqueo.Web.domain.Rol;

public interface RolDao extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}