package Parqueo.Web.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Parqueo.Web.dao.VehiculoDao;
import Parqueo.Web.domain.Usuario;
import Parqueo.Web.domain.Vehiculo;
import Parqueo.Web.service.VehiculoService;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoDao vehiculoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> misVehiculos(Usuario u) {
        return vehiculoDao.findByUsuario(u);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePlaca(String placa) {
        return vehiculoDao.existsByPlaca(placa);
    }

    @Override
    @Transactional
    public Vehiculo registrar(Vehiculo v) {
        return vehiculoDao.save(v);
    }
}