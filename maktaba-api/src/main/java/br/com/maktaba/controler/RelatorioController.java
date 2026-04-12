package br.com.maktaba.controler;

import br.com.maktaba.model.Assinatura;
import br.com.maktaba.repository.AssinaturaRepository;
import br.com.maktaba.repository.LivroRepository;
import br.com.maktaba.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class RelatorioController {

    private final AssinaturaRepository assinaturaRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;

    public RelatorioController(AssinaturaRepository assinaturaRepository,
                               LivroRepository livroRepository,
                               UsuarioRepository usuarioRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Assinatura> todas = assinaturaRepository.findAll();

        long ativas = todas.stream().filter(a -> "ATIVA".equals(a.getStatus())).count();
        long pausadas = todas.stream().filter(a -> "PAUSADA".equals(a.getStatus())).count();
        long canceladas = todas.stream().filter(a -> "CANCELADA".equals(a.getStatus())).count();

        double faturamento = todas.stream()
                .filter(a -> "ATIVA".equals(a.getStatus()))
                .mapToDouble(a -> switch (a.getPlano()) {
                    case "BASICO" -> 19.90;
                    case "STANDARD" -> 34.90;
                    case "PREMIUM" -> 49.90;
                    default -> 0;
                }).sum();

        long totalLivros = livroRepository.count();
        long totalUsuarios = usuarioRepository.count();

        model.addAttribute("ativas", ativas);
        model.addAttribute("pausadas", pausadas);
        model.addAttribute("canceladas", canceladas);
        model.addAttribute("faturamento", String.format("%.2f", faturamento));
        model.addAttribute("totalLivros", totalLivros);
        model.addAttribute("totalUsuarios", totalUsuarios);
        model.addAttribute("assinaturas", todas);

        return "admin-dashboard";
    }
}