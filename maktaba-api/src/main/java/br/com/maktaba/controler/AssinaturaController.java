package br.com.maktaba.controler;

import br.com.maktaba.model.Assinatura;
import br.com.maktaba.model.Usuario;
import br.com.maktaba.repository.AssinaturaRepository;
import br.com.maktaba.repository.UsuarioRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/assinatura")
public class AssinaturaController {

    private final AssinaturaRepository assinaturaRepository;
    private final UsuarioRepository usuarioRepository;

    public AssinaturaController(AssinaturaRepository assinaturaRepository,
                                UsuarioRepository usuarioRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/planos")
    public String planos() {
        return "planos";
    }

    @PostMapping("/assinar")
    public String assinar(@RequestParam String plano,
                          @AuthenticationPrincipal UserDetails userDetails,
                          Model model) {

        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow();

        // Verifica se já tem assinatura
        assinaturaRepository.findByUsuarioId(usuario.getId()).ifPresent(a -> {
            assinaturaRepository.delete(a);
        });

        Assinatura assinatura = new Assinatura();
        assinatura.setPlano(plano);
        assinatura.setStatus("ATIVA");
        assinatura.setDataInicio(LocalDate.now());
        assinatura.setDataRenovacao(LocalDate.now().plusMonths(1));
        assinatura.setUsuario(usuario);

        assinaturaRepository.save(assinatura);

        return "redirect:/assinatura/status";
    }

    @GetMapping("/status")
    public String status(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow();

        assinaturaRepository.findByUsuarioId(usuario.getId())
                .ifPresent(a -> model.addAttribute("assinatura", a));

        return "assinatura-status";
    }

    @PostMapping("/cancelar")
    public String cancelar(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow();

        assinaturaRepository.findByUsuarioId(usuario.getId()).ifPresent(a -> {
            a.setStatus("CANCELADA");
            assinaturaRepository.save(a);
        });

        return "redirect:/assinatura/status";
    }
}