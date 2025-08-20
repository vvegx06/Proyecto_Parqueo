package Parqueo.Web.web;

import Parqueo.Web.dao.UsuarioDao;
import Parqueo.Web.domain.Usuario;
import Parqueo.Web.service.RegistroEntradaSalidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/historial")
@RequiredArgsConstructor
public class HistorialController {

    private final RegistroEntradaSalidaService registroService;
    private final UsuarioDao usuarioDao;

    private Usuario currentUserOrNull() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null || "anonymousUser".equals(auth.getName())) {
            return null;
        }
        // <- OJO: .orElse(null) para convertir Optional<Usuario> a Usuario
        return usuarioDao.findByUsername(auth.getName()).orElse(null);
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        Usuario u = currentUserOrNull();

        if (u == null) {
            // por si acaso (aunque la ruta requiere login)
            model.addAttribute("registros", java.util.List.of());
            model.addAttribute("soloPropios", true);
            return "historial/listado";
        }

        // Si el usuario tiene rol ADMIN -> ve todo
        boolean esAdmin = u.getRol() != null && "ADMIN".equalsIgnoreCase(u.getRol().getNombre());
        if (esAdmin) {
            model.addAttribute("registros", registroService.listarTodos());
            model.addAttribute("soloPropios", false);
        } else {
            model.addAttribute("registros", registroService.listarPorUsuario(u.getId()));
            model.addAttribute("soloPropios", true);
        }

        return "historial/listado";
    }
}