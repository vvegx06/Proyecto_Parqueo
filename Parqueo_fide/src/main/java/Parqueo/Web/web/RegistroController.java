package Parqueo.Web.web;

import Parqueo.Web.dao.RolDao;
import Parqueo.Web.dao.UsuarioDao;
import Parqueo.Web.domain.Rol;
import Parqueo.Web.domain.Usuario;
import javax.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroController {

    private final UsuarioDao usuarioDao;
    private final RolDao rolDao;
    private final BCryptPasswordEncoder encoder;

    public RegistroController(UsuarioDao usuarioDao, RolDao rolDao, BCryptPasswordEncoder encoder) {
        this.usuarioDao = usuarioDao;
        this.rolDao = rolDao;
        this.encoder = encoder;
    }

    @GetMapping({"/registro", "/registro/nuevo"})
    public String mostrarFormulario(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new RegistroForm());
        }
        return "registro";
    }

    @PostMapping({"/registro", "/registro/nuevo"})
    public String registrar(@ModelAttribute("form") @Valid RegistroForm form,
                            BindingResult br, Model model) {
        if (usuarioDao.existsByUsername(form.getUsername())) {
            br.addError(new FieldError("form", "username", "Ese usuario ya existe"));
        }
        if (usuarioDao.existsByCorreo(form.getCorreo())) {
            br.addError(new FieldError("form", "correo", "Ese correo ya está en uso"));
        }
        if (br.hasErrors()) return "registro";

        Rol rolUser = rolDao.findByNombre("USER").orElseGet(() -> {
            Rol r = new Rol();
            r.setNombre("USER");
            return rolDao.save(r);
        });

        Usuario u = new Usuario();
        u.setUsername(form.getUsername());
        u.setPassword(encoder.encode(form.getPassword()));
        u.setNombre(form.getNombre());
        u.setCorreo(form.getCorreo());
        u.setRol(rolUser);
        usuarioDao.save(u);

        return "redirect:/login?registered";
    }
}