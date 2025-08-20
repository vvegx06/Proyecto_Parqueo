package Parqueo.Web.service;

import Parqueo.Web.domain.RegistroEntradaSalida;
import java.util.List;

public interface RegistroEntradaSalidaService {
    List<RegistroEntradaSalida> listarTodos();
    List<RegistroEntradaSalida> listarPorUsuario(Long usuarioId);

    // (Opcional) utilidades por si luego las usas
    RegistroEntradaSalida guardar(RegistroEntradaSalida reg);
    void marcarSalida(Long registroId);
}