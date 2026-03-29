package br.com.maktaba.controler;

import br.com.maktaba.model.Usuario;
import br.com.maktaba.repository.AssinaturaRepository;
import br.com.maktaba.repository.UsuarioRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    private final UsuarioRepository usuarioRepository;
    private final AssinaturaRepository assinaturaRepository;

    public PerfilController(UsuarioRepository usuarioRepository,
                            AssinaturaRepository assinaturaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.assinaturaRepository = assinaturaRepository;
    }

    @GetMapping
    public String perfil(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow();

        model.addAttribute("usuario", usuario);

        assinaturaRepository.findByUsuarioId(usuario.getId())
                .ifPresent(a -> model.addAttribute("assinatura", a));

        return "perfil";
    }
}