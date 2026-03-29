package br.com.maktaba.controler;

import br.com.maktaba.model.Role;
import br.com.maktaba.model.Usuario;
import br.com.maktaba.repository.RoleRepository;
import br.com.maktaba.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository repository,
                             RoleRepository roleRepository,
                             PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/cadastro")
    public String exibirCadastro() { return "cadastro"; }

    @GetMapping("/login")
    public String exibirLogin() { return "login"; }

    @PostMapping("/registrar")
    public String registrar(Usuario usuario) {
        // Criptografa a senha
        usuario.setSenhaHash(passwordEncoder.encode(usuario.getSenhaHash()));

        // Atribui ROLE_USER por padrão
        Role role = roleRepository.findByNome("ROLE_USER");
        usuario.getRoles().add(role);

        repository.save(usuario);
        return "redirect:/usuarios/login";
    }
}