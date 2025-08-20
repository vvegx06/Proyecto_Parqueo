package Parqueo.Web.service;

import java.util.List;
import Parqueo.Web.domain.Usuario;
import Parqueo.Web.domain.Vehiculo;

public interface VehiculoService {
    List<Vehiculo> misVehiculos(Usuario u);
    boolean existePlaca(String placa);
    Vehiculo registrar(Vehiculo v);
}