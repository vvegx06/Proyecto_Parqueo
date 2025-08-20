package Parqueo.Web.service.impl;

import Parqueo.Web.dao.RegistroEntradaSalidaDao;
import Parqueo.Web.domain.RegistroEntradaSalida;
import Parqueo.Web.service.RegistroEntradaSalidaService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistroEntradaSalidaServiceImpl implements RegistroEntradaSalidaService {

    private final RegistroEntradaSalidaDao dao;

    @Override
    @Transactional(readOnly = true)
    public List<RegistroEntradaSalida> listarTodos() {
        return dao.findAllByOrderByHoraEntradaDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegistroEntradaSalida> listarPorUsuario(Long usuarioId) {
        return dao.listarPorUsuario(usuarioId);
    }

    @Override
    @Transactional
    public RegistroEntradaSalida guardar(RegistroEntradaSalida reg) {
        return dao.save(reg);
    }

    @Override
    @Transactional
    public void marcarSalida(Long registroId) {
        dao.findById(registroId).ifPresent(r -> {
            r.setHoraSalida(LocalDateTime.now());
            dao.save(r);
        });
    }
}